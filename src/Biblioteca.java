import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livros> livrosLista;

    public Biblioteca() throws IOException {
        this.livrosLista = new ArrayList<>();
    }

    public void addLivro(Livros livro) throws IOException {
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

            livrosLista.clear();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                livrosLista.add(new Livros(dados[0], dados[1], Integer.parseInt(dados[2]), Integer.parseInt(dados[3])));
            }

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
}
