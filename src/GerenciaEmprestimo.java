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
        try (BufferedReader br = new BufferedReader(new FileReader("Emprestimos.txt"))) {
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
                emprestimoLista.add(new Emprestimo(livro, usuario, LocalDate.parse(dados[10]), LocalDate.parse(dados[11])));
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar armazenar o empréstimo.");
        }
    }

    public void listarEmprestimos() {
        attEmprestismos();
        if (emprestimoLista.isEmpty()) {
            System.out.println("Lista de empréstimos vazia.");
            return;
        }

        for (Emprestimo e : emprestimoLista) {
            System.out.println(e.toString());
        }
    }

    public void emprestarLivro(Livros livro, Usuarios usuario) throws IOException {
        attEmprestismos();
        Emprestimo emprestimo = new Emprestimo(livro, usuario, LocalDate.now(), LocalDate.now().plusDays(14));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Emprestimos.txt", true))) {
            System.out.println(usuario);
            bw.write(emprestimo.toString());
            bw.newLine();
            emprestimoLista.add(emprestimo);
            biblioteca.addLivro(livro);
            biblioteca.addUsuario(usuario);
        } catch (Exception e) {
            System.out.println("Não foi possível adicionar empréstimo." + e.getMessage());
        }
    }
}
