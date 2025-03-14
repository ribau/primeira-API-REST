package me.dio.primeira.service;

import me.dio.primeira.domain.model.Livro;

import java.util.List;

public interface LivroService {
    List<Livro> findAll();
    Livro findById(Long id);
    Livro create(Livro livro);
    Livro update(Long id, Livro livro);
    void delete(Long id);
}