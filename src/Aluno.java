public class Aluno extends Usuarios {
    private String curso, ocupacao;

    public Aluno(int idade, String nome, String curso) {
        super(idade, nome);
        if (idade <= 16) {
            throw new IllegalArgumentException("A idade do aluno deve ser maior que 15 anos.");
        }
        setCurso(curso);
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
