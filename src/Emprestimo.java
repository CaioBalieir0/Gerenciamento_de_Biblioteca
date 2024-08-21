import java.time.LocalDate;

public class Emprestimo {
    private LocalDate dataEmprestimo, dataDevolucao;
    private Livros livro;
    private Usuarios usuarios;

    public Emprestimo(Livros livro, Usuarios usuario, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        setDataEmprestimo(dataEmprestimo);
        setLivro(livro);
        setUsuarios(usuario);
        setDataDevolucao(dataDevolucao);
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
        this.livro = livro;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return usuarios + ";" + livro + ";" + dataEmprestimo + ";" + dataDevolucao;
    }
}