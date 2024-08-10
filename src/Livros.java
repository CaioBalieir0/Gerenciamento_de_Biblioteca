import java.io.IOException;

public class Livros {
    private String titulo, autor;
    private int ano, isbn;

    public Livros(String titulo, String autor, int ano, int isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.isbn = isbn;
    }
    public Livros() {
        this.titulo = "";
        this.autor = "";
        this.ano = 0;
        this.isbn = 0;
    }
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) throws IOException {
        if (titulo == null || titulo == "") {
            throw new IOException("O título não pode ser vazio.");
        } else {
            this.titulo = titulo;
        }
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) throws IOException {
        if (autor == "") {
            throw new IOException("O autor não pode ser vazio.");
        } else {
            this.autor = autor;
        }
    }

    public int getAno() {
        return ano;
    }
    public void setAno(int ano) throws IOException {
        if (String.valueOf(ano).length() == 4) {
            this.ano = ano;
        } else {
            throw new IOException("Ano inválido.");
        }
    }

    public int getIsbn() {
        return isbn;
    }
    public void setIsbn(int isbn) throws IOException {
        if (isbn == 0) {
            throw new IOException("O ISBN não pode ser 0.");
        }
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return titulo + ";" + autor + ";" + ano + ";" + isbn;
    }
}
