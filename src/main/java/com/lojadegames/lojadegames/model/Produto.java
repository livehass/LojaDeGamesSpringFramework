package com.lojadegames.lojadegames.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_produtos")
@Getter
@Setter
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produuto é de preenchimento obrigatório")
    @Size(min = 3, message = "O nome deve conter no minimo 3 letras")
    private String  nome;


    @NotBlank(message = "A descricao do produuto é de preenchimento obrigatório")
    @Size(min = 3, message = "A descricao deve conter no minimo 3 letras")
    private String  descricao;


    @NotNull(message = "O valor de um item não pode ser nulo")
    private double preco;

    @ManyToOne
    @JsonIgnoreProperties("produtos")
    private Categoria categoria;
}
