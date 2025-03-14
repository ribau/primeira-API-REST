# Projeto Biblioteca

Este projeto é uma API REST para gerenciar uma biblioteca, com funcionalidades como cadastro de livros, autores, usuários e empréstimos.

## Diagrama de Classes

```mermaid
classDiagram
    class Livro {
        +Long id
        +String titulo
        +String isbn
        +Autor autor
        +boolean disponivel
        +getters()
        +setters()
    }

    class Autor {
        +Long id
        +String nome
        +String nacionalidade
        +getters()
        +setters()
    }

    class Emprestimo {
        +Long id
        +LocalDate dataEmprestimo
        +LocalDate dataDevolucao
        +Livro livro
        +Usuario usuario
        +getters()
        +setters()
    }

    class Usuario {
        +Long id
        +String nome
        +String email
        +List~Emprestimo~ emprestimos
        +getters()
        +setters()
    }

    Livro "1" -- "1" Autor : possui
    Emprestimo "1" -- "1" Livro : empresta
    Emprestimo "1" -- "1" Usuario : feito por
    Usuario "1" -- "*" Emprestimo : possui
