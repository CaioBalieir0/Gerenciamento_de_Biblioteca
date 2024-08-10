public class Usuarios {
    private int idade;
    private String nome;
    
    public Usuarios(int idade, String nome) {
        this.idade = idade;
        this.nome = nome;
    }
    public Usuarios() {
        this.idade = 0;
        this.nome = "";
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        if (idade < 16) {
            throw new IllegalArgumentException("Idade precisa ser maior que 15 anos.");
        }
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if (nome == null || nome.length() < 3) {
            throw new IllegalArgumentException("Informe um nome válido.");
        }
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome + ";" + idade;
    }
}
