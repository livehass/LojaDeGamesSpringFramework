package com.lojadegames.lojadegames.repository;

import com.lojadegames.lojadegames.model.Categoria;
import com.lojadegames.lojadegames.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public List<Categoria> findAllByNomeContainingIgnoreCase(@Param("nome")String nome);
}