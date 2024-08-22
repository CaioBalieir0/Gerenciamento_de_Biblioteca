public class Usuarios {
    private int idade;
    private String nome;
    private boolean livros;
    
    public Usuarios(boolean livros, int idade, String nome) {
        setIdade(idade);
        setNome(nome);
        setLivros(livros);
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        if (idade < 16) {
            throw new IllegalArgumentException("Idade do usuário precisa ser maior que 15 anos.");
        }
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if (nome.isEmpty() || nome.length() < 3) {
            throw new IllegalArgumentException("Informe um nome válido.");
        }
        this.nome = nome;
    }

    public boolean hasLivros() {
        return livros;
    }
    public void setLivros(boolean livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return nome + ";" + idade + ";" + (livros ? "Ocupado" : "Livre");
    }
}
