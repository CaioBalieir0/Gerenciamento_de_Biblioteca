public class Livros {
    private String titulo, autor;
    private int ano, isbn, exemplares;

    public Livros(String titulo, String autor, int ano, int isbn, int exemplares) {
        setTitulo(titulo);
        setAno(ano);
        setAutor(autor);
        setIsbn(isbn);
        setExemplares(exemplares);
    }
    public Livros(){}
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        if (titulo.isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser vazio.");
        } else {
            this.titulo = titulo;
        }
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        if (autor.isEmpty()) {
            throw new IllegalArgumentException("O autor não pode ser vazio.");
        } else {
            this.autor = autor;
        }
    }

    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        if (String.valueOf(ano).length() == 4) {
            this.ano = ano;
        } else {
            throw new IllegalArgumentException("Ano inválido.");
        }
    }

    public int getExemplares() {
        return exemplares;
    }
    public void setExemplares(int exemplares) {
        if (exemplares < 0) {
            throw new IllegalArgumentException("O número de exemplares deve ser maior que 0");
        }
        this.exemplares = exemplares;
    }

    public int getIsbn() {
        return isbn;
    }
    public void setIsbn(int isbn) {
        if (isbn <= 0) {
            throw new IllegalArgumentException("O ISBN deve ser um número positivo e diferente de 0.");
        }
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return titulo + ";" + autor + ";" + ano + ";" + isbn + ";" + exemplares;
    }
}
