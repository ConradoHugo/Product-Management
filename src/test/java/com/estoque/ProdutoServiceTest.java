package com.estoque;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProdutoServiceTest {

    private ProdutoService service;

    @BeforeEach
    void setUp() {
        service = new ProdutoService(new ProdutoRepository());
    }

    @Test
    void criaProdutoComIdGerado() {
        Produto criado = service.criar("Mouse", new BigDecimal("80"), 5);
        assertNotNull(criado.id());
    }

    @Test
    void buscaProdutoExistente() {
        Produto criado = service.criar("Mouse", new BigDecimal("80"), 5);
        assertEquals(criado, service.buscar(criado.id()));
    }

    @Test
    void buscaInexistenteLancaExcecao() {
        assertThrows(ProdutoNaoEncontradoException.class, () -> service.buscar(99L));
    }

    @Test
    void listaTodosOsProdutos() {
        service.criar("A", BigDecimal.ONE, 1);
        service.criar("B", BigDecimal.ONE, 1);
        assertEquals(2, service.listar().size());
    }

    @Test
    void atualizaProdutoExistente() {
        Produto criado = service.criar("Mouse", new BigDecimal("80"), 5);

        Produto atualizado = service.atualizar(criado.id(), "Mouse Gamer", new BigDecimal("200"), 3);

        assertEquals("Mouse Gamer", service.buscar(criado.id()).nome());
        assertEquals(criado.id(), atualizado.id());
    }

    @Test
    void atualizarInexistenteLancaExcecao() {
        assertThrows(ProdutoNaoEncontradoException.class,
                () -> service.atualizar(99L, "X", BigDecimal.ONE, 1));
    }

    @Test
    void removeProduto() {
        Produto criado = service.criar("Mouse", new BigDecimal("80"), 5);

        service.remover(criado.id());

        assertThrows(ProdutoNaoEncontradoException.class, () -> service.buscar(criado.id()));
    }

    @Test
    void removerInexistenteLancaExcecao() {
        assertThrows(ProdutoNaoEncontradoException.class, () -> service.remover(99L));
    }
}