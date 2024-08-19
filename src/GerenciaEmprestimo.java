import java.io.IOException;
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

    public void listarEmprestimos() {
        for (Emprestimo e : emprestimoLista) {
            System.out.println(e.toString());
        }
    }

    public void emprestarLivro(Usuarios usuario, Livros livro) throws IOException {
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

        emprestimoLista.add(new Emprestimo(livro, usuario, LocalDate.now(), LocalDate.now().plusDays(14)));
    }
}
