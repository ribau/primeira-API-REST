package me.dio.primeira.domain.repository;

import me.dio.primeira.domain.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    boolean existsByIsbn(String isbn); // Verifica se um livro com o ISBN já existe
}