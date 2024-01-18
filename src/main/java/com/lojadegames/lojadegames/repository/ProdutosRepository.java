package com.lojadegames.lojadegames.repository;

import com.lojadegames.lojadegames.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutosRepository extends JpaRepository<Produto, String> {
    public List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome")String nome);

    List<Produto> findAllByPrecoGreaterThanEqual(BigDecimal preco);
}
