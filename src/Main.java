import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner entrada = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        Biblioteca biblioteca = new Biblioteca();
        GerenciaEmprestimo gerenciaEmprestimo = new GerenciaEmprestimo();

        while (true) {
            System.out.println("--- Menu biblioteca ---");
            System.out.println("1) Cadastrar livro");
            System.out.println("2) Cadastrar usuário");
            System.out.println("3) Remover livro");
            System.out.println("4) Remover usuário");
            System.out.println("5) Emprestar livro:");
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
                            entrada.nextLine();
                            System.out.print("Informe o ISBN do livro (somente números): ");
                            livro.setIsbn(entrada.nextInt());
                            System.out.print("Informe a quantidade de exemplares: ");
                            livro.setExemplares((entrada.nextInt()));
                            entrada.nextLine();
                            if (biblioteca.addLivro(livro)) {
                                System.out.println("Livro adicionado com sucesso.");
                            } else System.out.println("Livro com ISBN: " + livro.getIsbn() + " já existe.");
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
                                if (biblioteca.addUsuario(new Aluno(idade, nome, curso))) {
                                    System.out.println("Aluno chamado: " + nome + " já cadastrado");
                                }
                            } else {
                                System.out.print("Informe a matéria lecionada: ");
                                String materia = entrada.nextLine();
                                if (biblioteca.addUsuario(new Professor(idade, nome, materia))) {
                                    System.out.println("Professor chamado: " + nome + " já cadastrado");
                                }
                            }
                            System.out.println("Usuário cadastrado com sucesso.");
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage() + "\n");
                        }
                    }
                }
                case 3 -> {
                    while (true) {
                        System.out.println("\nRemover um livro:");
                        if (!biblioteca.mostrarLivros()) {
                            System.out.println("Nenhum livro adicionado na biblioteca.");
                            return;
                        }
                        System.out.println();
                        System.out.print("Informe o ISBN do livro: ");
                        Livros livro = biblioteca.rmvLivro(entrada.nextInt());
                        entrada.nextLine();
                        System.out.println("Livro \"" + livro.getTitulo() + "\" ISBN: " + livro.getIsbn() + " removido com sucesso");
                        System.out.print("Deseja realizar a operação novamente (Sim/Não)?");
                        if (!entrada.nextLine().equalsIgnoreCase("sim")) {
                            break;
                        }
                    }
                }
                case 4 -> {
                    while (true) {
                        removerUsuario();
                        System.out.print("Deseja realizar a operação novamente (Sim/Não)?");
                        if (!entrada.nextLine().equalsIgnoreCase("sim")) {
                            break;
                        }
                    }
                }
            
                case 5 -> {
                    System.out.println("\nInforme o livro e o usuário que deseja emprestar:");
                    if (!biblioteca.mostrarLivros()) {
                        return;
                    }
                    System.out.println();
                    System.out.print("Informe o ISBN do livro: ");
                    Livros livro = biblioteca.rmvLivro(entrada.nextInt());
                    System.out.println(livro);
                    entrada.nextLine();

                    Usuarios usuario = removerUsuario();

                    gerenciaEmprestimo.emprestarLivro(usuario, livro);
                    gerenciaEmprestimo.listarEmprestimos();
                }
            }
        }
    }

    public static Usuarios removerUsuario() throws IOException {
        System.out.println("\nRemover um usuário:");
        Biblioteca biblioteca = new Biblioteca();
            if (!biblioteca.mostrarUsuarios()) {
                return null;
            }
            System.out.println();
            String tipo;
                while (true) {
                    System.out.print("Informe o tipo de usuário(Professor ou Aluno): ");
                    tipo = entrada.nextLine();
                    if (!tipo.equalsIgnoreCase("Professor") && !tipo.equalsIgnoreCase("Aluno")) {
                        System.out.println("Informe uma opção correta.");
                    } else break;
                }
            System.out.print("Informe o nome do usuário: ");
            Usuarios usuario = biblioteca.rmvUsuario(entrada.nextLine(), tipo);
            return usuario;
    }
}
