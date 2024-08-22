public class Aluno extends Usuarios {
    private String curso, ocupacao;

    public Aluno(boolean livros, int idade, String nome, String curso) {
        super(livros, idade, nome);
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

    public String getOcupacao() {
        return ocupacao;
    }

    @Override
    public String toString() {
        return ocupacao + ";" + super.toString() + ";" + curso;
    }
}
