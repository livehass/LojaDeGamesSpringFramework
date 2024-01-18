package com.lojadegames.lojadegames.controller;


import com.lojadegames.lojadegames.model.Produto;
import com.lojadegames.lojadegames.repository.CategoriaRepository;
import com.lojadegames.lojadegames.repository.ProdutosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> ShowAll() {
        return ResponseEntity.ok(produtosRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Produto> ShowAllById(@PathVariable Long id) {
        return produtosRepository.findById(String.valueOf(id)).map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String nome) {
        return ResponseEntity.ok(produtosRepository.findAllByNomeContainingIgnoreCase(nome));
    }
    @GetMapping("/PrecoMaiorOuIgual/{preco}")
    ResponseEntity<List<Produto>> findAllByPrecoGreaterThanEqual(@PathVariable BigDecimal preco) {
        return ResponseEntity.ok(produtosRepository.findAllByPrecoGreaterThanEqual(preco));
    }
    @PutMapping
    public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
        return produtosRepository.findById(String.valueOf(produto.getId()))
                .map(reposta -> ResponseEntity.status(HttpStatus.OK).body(produtosRepository.save(produto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Produto> produto = produtosRepository.findById(String.valueOf(id));

        if (produto.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        produtosRepository.deleteById(String.valueOf(id));
    }
}
