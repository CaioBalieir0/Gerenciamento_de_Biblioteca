import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner entrada = new Scanner(System.in);
    static Biblioteca biblioteca;
    public static void main(String[] args) throws IOException {
        GerenciaEmprestimo gerenciaEmprestimo = new GerenciaEmprestimo();
        biblioteca = new Biblioteca();

        while (true) {
            System.out.println("--- Menu biblioteca ---");
            System.out.println("1) Cadastrar livro");
            System.out.println("2) Cadastrar usuário");
            System.out.println("3) Remover livro");
            System.out.println("4) Remover usuário");
            System.out.println("5) Emprestar livro:");
            System.out.println("6) Devolver livro:");
            System.out.println("7) Listar empréstimos:");
            System.out.println("8) Sair");
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
                                if (!biblioteca.addUsuario(new Aluno(false,idade, nome, curso))) {
                                    System.out.println("Aluno chamado: " + nome + " já cadastrado");
                                } else System.out.println("Usuário cadastrado com sucesso");
                            } else {
                                System.out.print("Informe a matéria lecionada: ");
                                String materia = entrada.nextLine();
                                if (!biblioteca.addUsuario(new Professor(false, idade, nome, materia))) {
                                    System.out.println("Professor chamado: " + nome + " já cadastrado");
                                } else System.out.println("Usuário cadastrado com sucesso");
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage() + "\n");
                        }
                    }
                }
                case 3 -> {
                    while (true) {
                        System.out.println("\nRemover um livro:");
                        if (!biblioteca.mostrarTodosLivros()) {
                            System.out.println("Nenhum livro adicionado na biblioteca.");
                            break;
                        }
                        System.out.println();
                        System.out.print("Informe o ISBN do livro: ");
                        Livros livro = biblioteca.rmvLivro(entrada.nextInt());
                        if (livro == null) {
                            break;
                        }
                        entrada.nextLine();
                        System.out.println("Livro \"" + livro.getTitulo() + "\" ISBN: " + livro.getIsbn() + " removido com sucesso");
                        System.out.print("Deseja realizar a operação novamente (Sim/Não)?");
                        if (!entrada.nextLine().equalsIgnoreCase("sim")) {
                            break;
                        }
                    }
                }
                case 4 -> {
                    System.out.println("\nRemover um usuário:");
                    while (true) {
                        if (!biblioteca.mostrarTodosUsuarios()) {
                            break;
                        }
                        Usuarios usuario = removerUsuario();
                        if (usuario == null) {
                            break;
                        }
                        System.out.println("Usuário: " + usuario.getNome() + " removido com sucesso.");
                        System.out.print("Deseja realizar a operação novamente (Sim/Não)?");
                        if (!entrada.nextLine().equalsIgnoreCase("sim")) {
                            break;
                        }
                    }
                }
            
                case 5 -> {
                    System.out.println("\nEmprestar um livro:");
                    System.out.println("Lista de livros:");
                    if (!biblioteca.mostrarLivrosDisponiveis()) break;
                    System.out.println();
                    System.out.print("Informe o ISBN do livro: ");
                    Livros livro = biblioteca.rmvLivro(entrada.nextInt());
                    entrada.nextLine();
                    if (livro == null) break;

                    System.out.println("Lista de usuários:");
                    if (!biblioteca.mostrarUsuariosLivres()) break;
                    System.out.println();
                    Usuarios usuario = removerUsuario();
                    if (usuario == null) break;
                    
                    gerenciaEmprestimo.emprestarLivro(livro, usuario);
                }
                case 6 -> {
                    System.out.println("\nDevolver livro: ");
                    if (!gerenciaEmprestimo.listarEmprestimos(2)) {
                        break;
                    }

                    System.out.print("Informe o ID do empréstimo: ");
                    int id = entrada.nextInt();
                    entrada.nextLine();
                    gerenciaEmprestimo.devolverLivro(id);
                }
                case 7 -> {
                    System.out.println("\nLista de empréstimos: ");
                    gerenciaEmprestimo.listarEmprestimos(1);
                }
                case 8 -> {
                    System.out.println("Fim do programa, obrigado!");
                    entrada.close();
                    return;
                }
                default -> System.out.println("Selecione uma opção válida\n");
            }
        }
    }

    public static Usuarios removerUsuario() throws IOException {
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
