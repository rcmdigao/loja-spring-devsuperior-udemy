package com.lojanelioalves.api.service;

import com.lojanelioalves.api.entities.Produto;
import com.lojanelioalves.api.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    // Servico de buscar todos
    public List<Produto> buscarTodos(){
        return repository.findAll();
    }

    // Busca por id
    public Produto buscarPorID(Long id){
        Optional<Produto> obj = repository.findById(id);
        return obj.orElseThrow(null);
    }
}
