import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner entrada = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        while (true) {
            System.out.println("--- Menu biblioteca ---");
            System.out.println("1) Cadastrar livro");
            System.out.println("2) Cadastrar usuário");
            System.out.println("3) Remover livro");
            System.out.println("4) Remover usuário");
            System.out.print(">> Opção: ");
            int opc = entrada.nextInt();
            entrada.nextLine();
            switch (opc) {
                case 1 -> {
                    while (true) {
                        try {
                            Livros livro = new Livros();
                            System.out.println("\nCadastrar livro:");
                            System.out.print("Informe o título do livro: ");
                            livro.setTitulo(entrada.nextLine());
                            System.out.print("Informe o autor do livro: ");
                            livro.setAutor(entrada.nextLine());
                            System.out.print("Informe o ano do livro: ");
                            livro.setAno(entrada.nextInt());
                            System.out.print("Informe o ISBN do livro (somente números): ");
                            livro.setIsbn(entrada.nextInt());;
                            biblioteca.addLivro(livro);
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage() + "\n");
                        }
                    }
                }
                case 2 -> {
                    while (true) {
                        try {
                            System.out.println("\nCadastrar usuário:");
                            String tipo;
                            while (true) {
                                System.out.print("Informe o tipo de usuário(Professor ou Aluno): ");
                                tipo = entrada.nextLine();
                                if (!tipo.equalsIgnoreCase("Professor") && !tipo.equalsIgnoreCase("Aluno")) {
                                    System.out.println("Informe uma opção correta.");
                                } else break;
                            }
                            System.out.print("Informe o nome: ");
                            String nome = entrada.nextLine();
                            System.out.print("Informe a idade: ");
                            int idade = entrada.nextInt();
                            entrada.nextLine();
                            if (tipo.equalsIgnoreCase("Aluno")) {
                                System.out.print("Informe o curso que está matriculado: ");
                                String curso = entrada.nextLine();
                                biblioteca.addUsuario(new Aluno(idade, nome, curso));
                            } else {
                                System.out.print("Informe a matéria lecionada: ");
                                String materia = entrada.nextLine();
                                biblioteca.addUsuario(new Professor(idade, nome, materia));
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage() + "\n");
                        }
                    }
                }
            }
        }
    }
}
