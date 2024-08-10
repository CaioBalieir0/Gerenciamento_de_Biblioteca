public class Aluno extends Usuarios {
    private String curso;

    public Aluno(int idade, String nome, String curso) {
        super(idade, nome);
        this.curso = curso;
    }
    public Aluno() {
        this.curso = "";
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
}
