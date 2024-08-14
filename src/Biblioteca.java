import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livros> livrosLista;
    private List<Usuarios> usuarioLista;

    public Biblioteca() throws IOException {
        this.livrosLista = new ArrayList<>();
        this.usuarioLista = new ArrayList<>();
    }

    public void addLivro(Livros livro) throws IOException {
        try {
            attListas();
        } catch (IOException e) {
            System.out.println("Não foi possível atualizar a lista." + e.getMessage());
        }
        try (
        BufferedWriter bw = new BufferedWriter(new FileWriter("Livros.txt", true));
        BufferedReader br = new BufferedReader(new FileReader("Livros.txt"))
        ) {
            if (livrosLista != null) {
                for (Livros l : livrosLista) {
                    if (l.getIsbn() == livro.getIsbn() || l.getTitulo().equalsIgnoreCase(livro.getTitulo())) {
                        System.out.println("Título e/ou ISBN ja existente.");
                        return;
                    }
                }    
            }
            bw.write(livro.toString());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao tentar adicionar o livro: " + e.getMessage());
        }
        
    }

    public void rmvLivro(int isbn) throws IOException {
        
        if (livrosLista == null) {
            System.out.println("Nenhum livro adicionado na biblioteca.");
            return;
        }

        Livros livroARemover = null;
        for (Livros l : livrosLista) {
            if (l.getIsbn() == isbn) {
                livroARemover = l;
                break;
            }
        }

        if (livroARemover == null) {
            System.out.println("Livro com ISBN: " + isbn + " não encontrado.");
            return;
        } 
        System.out.println("Livro: " + livroARemover.getTitulo() + " ISBN: " + livroARemover.getIsbn() + " removido com sucesso.");
        livrosLista.remove(livroARemover);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Livros.txt"));) {
            for (Livros l : livrosLista) {
                bw.write(l.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao tentar atualizar a lista: " + e.getMessage());
        }
    }

    public void addUsuario(Usuarios usuario) {
            try (
            BufferedWriter bw = new BufferedWriter(new FileWriter("Usuario.txt", true));
            BufferedReader br = new BufferedReader(new FileReader("Usuario.txt"))) {
                bw.write(usuario.toString());
                bw.newLine();
                } catch (IOException e) {
                    System.out.println("Ocorreu um erro ao tentar adicionar o aluno: " + e.getMessage());
                }
    }

    public void rmvUsuario(String nome) {
        try {
            attListas();
        } catch (IOException e) {
            System.out.println("Não foi possível atualizar a lista." + e.getMessage());
        }
        if (usuarioLista == null) {
            System.out.println("Nenhum usuário do sistema da biblioteca cadastrado.");
            return;
        }


        
    }


    public void attListas() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("Usuario.txt"))) {
            usuarioLista.clear();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals("Professor")) {
                    usuarioLista.add(new Professor(Integer.parseInt(dados[1]), dados[0], dados[2]));
                } else {
                    usuarioLista.add(new Aluno(Integer.parseInt(dados[1]), dados[0], dados[2]));
                }
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader("Livros.txt"))) {
            livrosLista.clear();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                livrosLista.add(new Livros(dados[0], dados[1], Integer.parseInt(dados[2]), Integer.parseInt(dados[3])));
            }
        }
    }

}