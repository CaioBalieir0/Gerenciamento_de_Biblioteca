import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livros> livrosLista;
    private List<Usuarios> usuarioLista;
    BufferedWriter bw;

    public Biblioteca() throws IOException {
        this.livrosLista = new ArrayList<>();
        this.usuarioLista = new ArrayList<>();
        this.bw = new BufferedWriter(new FileWriter("Livros.txt", true));
        this.bw = new BufferedWriter(new FileWriter("Usuario.txt", true));
    }

    public List<Livros> getLivrosLista() {
        return livrosLista;
    }
    public List<Usuarios> getUsuarioLista() {
        return usuarioLista;
    }

    public boolean addLivro(Livros livro) throws IOException {
        try {
            attListas();
        } catch (IOException e) {
            System.out.println("Não foi possível atualizar a lista." + e.getMessage());
        }

        try (
        BufferedWriter bw = new BufferedWriter(new FileWriter("Livros.txt", true));
        BufferedReader br = new BufferedReader(new FileReader("Livros.txt"))
        ) {
            if (!livrosLista.isEmpty()) {
                for (Livros l : livrosLista) {
                    if (l.getIsbn() == livro.getIsbn()) {
                        return false;
                    }
                }    
            }
            bw.write(livro.toString());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao tentar adicionar o livro: " + e.getMessage());
        }
        return true;
    }
    public Livros rmvLivro(int isbn) throws IOException {
        try {
            attListas();
        } catch (IOException e) {
            System.out.println("Não foi possível atualizar a lista. " + e.getMessage());
        }

        if (livrosLista.isEmpty()) {
            System.out.println("Nenhum livro adicionado na biblioteca.");
            return null;
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
            return null;
        } 
        livrosLista.remove(livroARemover);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Livros.txt"));) {
            for (Livros l : livrosLista) {
                bw.write(l.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao tentar atualizar a lista: " + e.getMessage());
        }
        return livroARemover;
    }

    public boolean addUsuario(Usuarios usuario) {
        try {
            attListas();
        } catch (IOException e) {
            System.out.println("Não foi possível atualizar a lista." + e.getMessage());
        }

        if (!usuarioLista.isEmpty()) {
            for (Usuarios u : usuarioLista) {
                if (u instanceof Aluno && usuario instanceof Aluno && usuario.getNome().equalsIgnoreCase(u.getNome())) {
                    return false;
                } else if (u instanceof Professor && usuario instanceof Professor && usuario.getNome().equalsIgnoreCase(u.getNome())) {
                    return false;
                }
            }
        }

        try (
        BufferedWriter bw = new BufferedWriter(new FileWriter("Usuario.txt", true));
        BufferedReader br = new BufferedReader(new FileReader("Usuario.txt"))) {
            bw.write(usuario.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao tentar adicionar o aluno: " + e.getMessage());
        }
        return true;
    }
    public Usuarios rmvUsuario(String nome, String tipo) {
        try {
            attListas();
        } catch (IOException e) {
            System.out.println("Não foi possível atualizar a lista." + e.getMessage());
        }

        if (usuarioLista == null) {
            System.out.println("Nenhum usuário do sistema da biblioteca cadastrado.");
            return null;
        }

        Usuarios usuarioARemover = null;
        Class<?> tipoClasse = (tipo.equalsIgnoreCase("Aluno") ? Aluno.class : Professor.class);
        for (Usuarios u : usuarioLista) {
            if (u.getNome().equalsIgnoreCase(nome) && tipoClasse.isInstance(u)) {
                usuarioARemover = u;
                break;
            }
        }
        
        if (usuarioARemover == null) {
            System.out.println("Não existe nenhum " + tipoClasse.getSimpleName() + " chamado " + nome);
            return null;
        }
        usuarioLista.remove(usuarioARemover);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Usuario.txt"))) {
            for (Usuarios u : usuarioLista) {
                bw.write(u.toString());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Não foi possível atualizar a lista de usuários após a remoção." + e.getMessage());
        }
        return usuarioARemover;
    }

    public void attListas() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("Usuario.txt"))) {
            usuarioLista.clear();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals("Professor")) {
                    usuarioLista.add(new Professor(Integer.parseInt(dados[2]), dados[1], dados[4]));
                } else {
                    usuarioLista.add(new Aluno(Integer.parseInt(dados[2]), dados[1], dados[4]));
                }
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader("Livros.txt"))) {
            livrosLista.clear();
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                livrosLista.add(new Livros(dados[0], dados[1], Integer.parseInt(dados[2]), Integer.parseInt(dados[3]), Integer.parseInt(dados[4])));
            }
        }
    }

    public boolean mostrarLivros() {
        try {
            attListas();
        } catch (IOException e) {
            System.out.println("Não foi possível atualizar a lista. " + e.getMessage());
        }

        if (livrosLista.isEmpty()) {
            return false;
        }

        for (Livros l : livrosLista) {
            System.out.println(l);
        }
        return true;
    }
    public boolean mostrarUsuarios() {
        try {
            attListas();
        } catch (IOException e) {
            System.out.println("Não foi possível atualizar a lista. " + e.getMessage());
        }

        if (usuarioLista.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return false;
        }

        for (Usuarios u : usuarioLista) {
            System.out.println(u);
        }
        return true;
    }
}