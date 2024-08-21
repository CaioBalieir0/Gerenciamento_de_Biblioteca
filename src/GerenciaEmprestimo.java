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

    public void armazenarEmprestismos() {
        try (BufferedReader br = new BufferedReader(new FileReader("Emprestimos.txt"))) {
            emprestimoLista.clear();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Usuarios usuario;
                if (dados[0].equals("Professor")) {
                    usuario = new Professor(Integer.parseInt(dados[2]), dados[1], dados[4]);
                } else {
                    usuario = new Aluno(Integer.parseInt(dados[2]), dados[1], dados[4]);
                }
                Livros livro = new Livros(dados[5], dados[6], Integer.parseInt(dados[7]), Integer.parseInt(dados[8]), Integer.parseInt(dados[9]));
                emprestimoLista.add(new Emprestimo(livro, usuario, LocalDate.parse(dados[10]), LocalDate.parse(dados[11])));
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar armazenar o empréstimo.");
        }
    }

    public void listarEmprestimos() {
        armazenarEmprestismos();
        if (emprestimoLista.isEmpty()) {
            System.out.println("Lista de empréstimos vazia.");
            return;
        }

        for (Emprestimo e : emprestimoLista) {
            System.out.println(e.toString());
        }
    }

    public void emprestarLivro(Usuarios usuario, Livros livro) throws IOException {
        armazenarEmprestismos();
        if (livro.getExemplares() < 1) {
            System.out.println("Não ha exemplares disponiveis para este livro.");
            return;
        }
        if (usuario.hasLivros()) {
            System.out.println("O usuário ja emprestou um livro.");
            return;
        }

        livro.setExemplares(livro.getExemplares()-1);
        usuario.setLivros(true);
        biblioteca.addLivro(livro);
        biblioteca.addUsuario(usuario);
        Emprestimo emprestimo = new Emprestimo(livro, usuario, LocalDate.now(), LocalDate.now().plusDays(14));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Emprestimos.txt", true))) {
            bw.write(emprestimo.toString());
            bw.newLine();
        } catch (Exception e) {
            System.out.println("Erro ao tentar armazenar o empréstimo.");
        }
    }
}
