package com.estoque;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ProdutoTest {

    @Test
    void criaProdutoValido() {
        Produto p = new Produto(null, "Teclado", new BigDecimal("150"), 10);
        assertEquals("Teclado", p.nome());
    }

    @Test
    void rejeitaNomeVazio() {
        assertThrows(ProdutoInvalidoException.class,
                () -> new Produto(null, " ", BigDecimal.TEN, 1));
    }

    @Test
    void rejeitaPrecoNegativo() {
        assertThrows(ProdutoInvalidoException.class,
                () -> new Produto(null, "X", new BigDecimal("-1"), 1));
    }

    @Test
    void rejeitaQuantidadeNegativa() {
        assertThrows(ProdutoInvalidoException.class,
                () -> new Produto(null, "X", BigDecimal.TEN, -1));
    }
}