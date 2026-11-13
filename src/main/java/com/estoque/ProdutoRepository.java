package com.estoque;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class ProdutoRepository {

    private final Map<Long, Produto> banco = new LinkedHashMap<>();
    private final AtomicLong sequencia = new AtomicLong();

    public Produto salvar(Produto produto) {
        Produto salvo = produto.id() == null
                ? produto.comId(sequencia.incrementAndGet())
                : produto;
        banco.put(salvo.id(), salvo);
        return salvo;
    }

    public Optional<Produto> buscarPorId(Long id) {
        return Optional.ofNullable(banco.get(id));
    }

    public List<Produto> listarTodos() {
        return List.copyOf(banco.values());
    }

    public boolean remover(Long id) {
        return banco.remove(id) != null;
    }
}