public class Aluno extends Usuarios {
    private String curso, ocupacao;

    public Aluno(int idade, String nome, String curso) {
        super(idade, nome);
        this.curso = curso;
        ocupacao = "Aluno";
    }
    public Aluno() {
        this.curso = "";
        ocupacao = "Aluno";
    }

    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        if (curso == null || curso.length() < 4) {
            throw new IllegalArgumentException("Curso inválido.");
        }
        this.curso = curso;
    }

    @Override
    public String toString() {
        return ocupacao + ";" + super.toString() + ";" + curso;
    }
}
