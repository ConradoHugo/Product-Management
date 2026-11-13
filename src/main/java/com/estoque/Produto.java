package com.estoque;

import java.math.BigDecimal;

public record Produto(Long id, String nome, BigDecimal preco, int quantidade) {

    public Produto {
        if (nome == null || nome.isBlank()) {
            throw new ProdutoInvalidoException("Nome é obrigatório");
        }
        if (preco == null || preco.signum() < 0) {
            throw new ProdutoInvalidoException("Preço não pode ser nulo ou negativo");
        }
        if (quantidade < 0) {
            throw new ProdutoInvalidoException("Quantidade não pode ser negativa");
        }
    }

    public Produto comId(Long novoId) {
        return new Produto(novoId, nome, preco, quantidade);
    }
}