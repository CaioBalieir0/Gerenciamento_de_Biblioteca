import java.time.LocalDate;

public class Emprestimo {
    private LocalDate dataEmprestimo, dataDevolucao;
    private Livros livro;
    private Usuarios usuarios;
    private int id;
    private static int contador = 1;

    public Emprestimo(Livros livro, Usuarios usuario, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        setDataEmprestimo(dataEmprestimo);
        setLivro(livro);
        setUsuarios(usuario);
        setDataDevolucao(dataDevolucao);
        id = contador++;
    }
    

    public Emprestimo() {
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataEmprestimo.plusDays(14);
        this.livro = null;
        this.usuarios = null;
    }


    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Livros getLivro() {
        return livro;
    }
    public void setLivro(Livros livro) {
        if (livro.getExemplares() < 1) {
            throw new IllegalArgumentException("Não ha exemplares disponiveis para este livro");
        }
        this.livro = livro;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(Usuarios usuarios) {
        if (usuarios.hasLivros()) {
            throw new IllegalArgumentException("O usuário ja emprestou um livro.");
        }
        this.usuarios = usuarios;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return usuarios.toString() + ";" + livro.toString() + ";" + dataEmprestimo + ";" + dataDevolucao + ";" + id;
    }
}