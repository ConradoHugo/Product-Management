package com.estoque;

public class ProdutoInvalidoException extends RuntimeException {
    public ProdutoInvalidoException(String mensagem) {
        super(mensagem);
    }
}