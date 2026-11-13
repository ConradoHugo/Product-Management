package com.estoque;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto criar(String nome, BigDecimal preco, int quantidade) {
        return repository.salvar(new Produto(null, nome, preco, quantidade));
    }

    public Produto buscar(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    public List<Produto> listar() {
        return repository.listarTodos();
    }

    public Produto atualizar(Long id, String nome, BigDecimal preco, int quantidade) {
        buscar(id); // garante que existe
        return repository.salvar(new Produto(id, nome, preco, quantidade));
    }

    public void remover(Long id) {
        if (!repository.remover(id)) {
            throw new ProdutoNaoEncontradoException(id);
        }
    }
}