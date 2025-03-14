package me.dio.primeira.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.primeira.domain.model.Livro;
import me.dio.primeira.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/livros")
@Tag(name = "Controller de Livros", description = "API RESTful para gerenciamento de livros.")
public record LivroController(LivroService livroService) {

    @GetMapping
    @Operation(summary = "Obter todos os livros", description = "Retorna uma lista de todos os livros cadastrados.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida.")
    })
    public ResponseEntity<List<Livro>> findAll() {
        var livros = livroService.findAll();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter um livro por ID", description = "Retorna um livro específico com base no ID fornecido.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida."),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado.")
    })
    public ResponseEntity<Livro> findById(@PathVariable Long id) {
        var livro = livroService.findById(id);
        return ResponseEntity.ok(livro);
    }

    @PostMapping
    @Operation(summary = "Criar um novo livro", description = "Cria um novo livro e retorna os dados do livro criado.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Dados do livro inválidos.")
    })
    public ResponseEntity<Livro> create(@RequestBody Livro livro) {
        var createdLivro = livroService.create(livro);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdLivro.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdLivro);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um livro", description = "Atualiza os dados de um livro existente com base no ID fornecido.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado."),
            @ApiResponse(responseCode = "422", description = "Dados do livro inválidos.")
    })
    public ResponseEntity<Livro> update(@PathVariable Long id, @RequestBody Livro livro) {
        var updatedLivro = livroService.update(id, livro);
        return ResponseEntity.ok(updatedLivro);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um livro", description = "Exclui um livro existente com base no ID fornecido.")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "204", description = "Livro excluído com sucesso."),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado.")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}