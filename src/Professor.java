public class Professor extends Usuarios{
    private String materia, ocupacao;

    public Professor(boolean livros, int idade, String nome, String materia) {
        super(livros, idade, nome);
        if (idade <= 25) {
            throw new IllegalArgumentException("A idade do professor deve ser maior que 25 anos.");
        }
        setMateria(materia);
        ocupacao = "Professor";
    }

    public String getMateria() {
        return materia;
    }
    public void setMateria(String materia) {
        if (materia == null || materia.length() < 4) {
            throw new IllegalArgumentException("Informe uma matéria válida.");
        }
        this.materia = materia;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    @Override
    public String toString() {
        return ocupacao + ";" + super.toString() + ";" + materia;
    }
}
