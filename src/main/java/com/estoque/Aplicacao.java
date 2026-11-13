package com.estoque;

import java.math.BigDecimal;

public class Aplicacao {

    public static void main(String[] args) {
        ProdutoService service = new ProdutoService(new ProdutoRepository());

        Produto teclado = service.criar("Teclado", new BigDecimal("150.00"), 10);
        service.criar("Mouse", new BigDecimal("80.00"), 25);

        service.atualizar(teclado.id(), "Teclado Mecânico", new BigDecimal("320.00"), 8);
        service.remover(2L);

        service.listar().forEach(System.out::println);
    }
}