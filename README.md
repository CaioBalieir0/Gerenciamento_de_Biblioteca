# Sistema de Gerenciamento de Biblioteca

## Descrição

Um sistema de gerenciamento de biblioteca desenvolvido em Java que implementa operações básicas de CRUD (Create, Read, Update, Delete) para gerenciar livros e usuários além de gerenciar o empréstimo de livros. Este projeto é ideal para fins educacionais e para entender como construir uma aplicação de CRUD em Java com armazenamento de dados em arquivos `.txt`.

## Funcionalidades

- **Cadastrar Livro:** Insere novos livros com título, autor, ano de publicação, exemplares disponíveis e ISBN.
- **Remover Livro:** Remove um livro armazenado a partir do seu ISBN.
- **Atualizar Livro:** Atualiza as informações do livro.
- **Cadastrar Usuário:** Insere novos usuários com idade, nome, status de livro emprestado, sendo Alunos (com curso) ou Professores (com matérias lecionadas).
- **Remover Usuário:** Remove um usuário a partir da sua classe (Aluno ou Professor) e nome.
- **Atualizar Usuário:** Atualiza as informações do usuário.
- **Emprestar Livro:** Realiza empréstimos de livros disponíveis para usuários, com base na classe (Aluno ou Professor) e nome do usuário e ISBN do livro.
- **Devolver Livro:** Devolve o livro emprestado para a biblioteca com base no ID do empréstimo.

## Tecnologias

- **Linguagem:** Java
- **Armazenamento de Dados:** Arquivos de texto (`.txt`)

## Instalação

1. **Clone o Repositório:**

   ```
   git clone https://github.com/CaioBalieir0/Gerenciamento_de_Biblioteca
   ```
2. **Navegue até o Diretório do Projeto:**

   ```
   cd Gerenciamento_de_Biblioteca
   ```
3. **Compile o Código e execute a aplicação:**

## Estrutura dos Arquivos

O sistema utiliza arquivos `.txt` para armazenar dados. Os arquivos são criados e gerenciados automaticamente pelo programa. Os principais arquivos utilizados são:

- **livros.txt:** Armazena a lista de livros com informações como título, autor, ano de publicação, exemplares disponíveis e ISBN.
- **usuarios.txt:** Armazena a lista de usuários com informações como idade, nome, status de livro emprestado, sendo Alunos (com curso) ou Professores (com matérias lecionadas).
- **emprestimos.txt:** Armazena a lista de empréstimos com informações como livro, usuário e ID do empréstimo.

## Contato

- **LinkedIn:** [https://www.linkedin.com/in/caio-balieiro-63569926a/](https://www.linkedin.com/in/caio-balieiro-63569926a/)
- **E-mail:** [caiobalieiro676@gmail.com]()
