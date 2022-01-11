package com.lojanelioalves.api.service;

import com.lojanelioalves.api.entities.Cidade;
import com.lojanelioalves.api.entities.Produto;
import com.lojanelioalves.api.repositories.CidadeRepository;
import com.lojanelioalves.api.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    // Servico de buscar todos
    public List<Cidade> buscarTodos(){
        return repository.findAll();
    }

    // Busca por id
    public Cidade buscarPorID(Long id){
        Optional<Cidade> obj = repository.findById(id);
        return obj.orElseThrow(null);
    }
}
