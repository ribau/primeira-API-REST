package me.dio.primeira.controller;
import me.dio.primeira.model.Livro;
import me.dio.primeira.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Livros")


public class LivroController {
@Autowired
private LivroRepository livroRepository;

@GetMapping
public List<Livro> getAllLivros(){
    return livroRepository.findAll();
}
@PostMapping
public Livro adicionarLivro(@RequestBody Livro livro){
    return livroRepository.save(livro);


    
}
}