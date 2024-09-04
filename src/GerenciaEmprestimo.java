import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciaEmprestimo {
    private List<Emprestimo> emprestimoLista;
    private Biblioteca biblioteca;
    
    public GerenciaEmprestimo() {
        this.emprestimoLista = new ArrayList<>();
        try {
            this.biblioteca = new Biblioteca();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void attEmprestismos() {
        try (BufferedReader br = new BufferedReader(new FileReader("emprestimos.txt"))) {
            emprestimoLista.clear();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Usuarios usuario;
                boolean hasLivros = dados[3].equalsIgnoreCase("Ocupado") ? true : false;
                if (dados[0].equals("Professor")) {
                    usuario = new Professor(hasLivros,Integer.parseInt(dados[2]), dados[1], dados[4]);
                } else {
                    usuario = new Aluno(hasLivros,Integer.parseInt(dados[2]), dados[1], dados[4]);
                }
                Livros livro = new Livros(dados[5], dados[6], Integer.parseInt(dados[7]), Integer.parseInt(dados[8]), Integer.parseInt(dados[9]));
                Emprestimo emprestimo = new Emprestimo(livro, usuario, LocalDate.parse(dados[10]), LocalDate.parse(dados[11]));
                emprestimo.setId(Integer.parseInt(dados[12]));
                emprestimoLista.add(emprestimo);
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar armazenar o empréstimo.");
        }
    }

    public boolean listarEmprestimos(int opc) {
        attEmprestismos();
        if (emprestimoLista.isEmpty()) {
            System.out.println("Lista de empréstimos vazia.");
            return false;
        }

        if (opc == 1) {
            for (Emprestimo e : emprestimoLista) {
                System.out.println(e.toString());
            }
            return true;
        } 

        for (Emprestimo e : emprestimoLista) {
            System.out.println("Usuário: " + e.getUsuarios().getClass().getSimpleName() + " " + e.getUsuarios().getNome() + " Livro: " + e.getLivro().getTitulo() + " ISBN : " + e.getLivro().getIsbn() + " ID: " + e.getId() + " Data devolução: " + e.getDataDevolucao());
        }
        return true;
    }

    public void emprestarLivro(Livros livro, Usuarios usuario) throws IOException {
        attEmprestismos();
        Emprestimo emprestimo = new Emprestimo(livro, usuario, LocalDate.now(), LocalDate.now().plusDays(14));
        attEmprestismos();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("emprestimos.txt", true))) {
            bw.write(emprestimo.toString());
            bw.newLine();
            emprestimoLista.add(emprestimo);
            livro.setExemplares(livro.getExemplares() - 1);
            usuario.setLivros(true);
            biblioteca.addLivro(livro);
            System.out.println(usuario);
            biblioteca.addUsuario(usuario);
        } catch (Exception e) {
            System.out.println("Não foi possível adicionar empréstimo." + e.getMessage());
        }
    }

    public void devolverLivro(int id) throws IOException {
        attEmprestismos();

        Emprestimo emprestimoARemover = null;
        for (Emprestimo e : emprestimoLista) {
            System.out.println(e.getId());
            if (e.getId() == id) {
                emprestimoARemover = e;
                break;
            }
        }
        if (emprestimoARemover == null) {
            System.out.println("Empréstimo não encontrado.");
            return;
        }
        Livros livro = emprestimoARemover.getLivro();
        Livros livroAtualizar = biblioteca.rmvLivro(livro.getIsbn());
        Usuarios usuario = emprestimoARemover.getUsuarios();
        usuario.setLivros(false);
        biblioteca.rmvLivro(livro.getIsbn());
        biblioteca.rmvUsuario(usuario.getNome(), usuario.getClass().getSimpleName());
        System.out.println("Exe" + livro.getExemplares());
        livro.setExemplares(livroAtualizar.getExemplares()+1);
        usuario.setLivros(false);
        biblioteca.addLivro(livro);
        biblioteca.addUsuario(usuario);

        emprestimoLista.remove(emprestimoARemover);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("emprestimos.txt"))) {
            for (Emprestimo e : emprestimoLista) {
                bw.write(e.toString());
                bw.newLine();
            } 
        } catch (Exception e) {
            System.out.println("Não foi possível atualizar a lista de emprestimos. " + e.getMessage());
        }
    }
}
