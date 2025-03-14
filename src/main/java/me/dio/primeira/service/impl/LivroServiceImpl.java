package me.dio.primeira.service.impl;

import me.dio.primeira.domain.model.Livro;
import me.dio.primeira.domain.repository.LivroRepository;
import me.dio.primeira.service.LivroService;
import me.dio.primeira.service.exception.BusinessException;
import me.dio.primeira.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;

    public LivroServiceImpl(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Livro findById(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Livro não encontrado com o ID: " + id));
    }

    @Override
    @Transactional
    public Livro create(Livro livro) {
        ofNullable(livro).orElseThrow(() -> new BusinessException("Livro a ser criado não pode ser nulo."));
        ofNullable(livro.getTitulo()).orElseThrow(() -> new BusinessException("O título do livro não pode ser nulo."));
        ofNullable(livro.getIsbn()).orElseThrow(() -> new BusinessException("O ISBN do livro não pode ser nulo."));

        if (livroRepository.existsByIsbn(livro.getIsbn())) {
            throw new BusinessException("Já existe um livro com este ISBN.");
        }

        return livroRepository.save(livro);
    }

    @Override
    @Transactional
    public Livro update(Long id, Livro livro) {
        Livro existingLivro = findById(id);

        if (!existingLivro.getId().equals(livro.getId())) {
            throw new BusinessException("Os IDs de atualização devem ser iguais.");
        }

        existingLivro.setTitulo(livro.getTitulo());
        existingLivro.setIsbn(livro.getIsbn());
        existingLivro.setAutor(livro.getAutor());
        existingLivro.setDisponivel(livro.isDisponivel());

        return livroRepository.save(existingLivro);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Livro livro = findById(id);
        livroRepository.delete(livro);
    }
}