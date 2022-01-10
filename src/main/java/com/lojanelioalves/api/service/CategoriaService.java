package com.lojanelioalves.api.service;

import com.lojanelioalves.api.entities.Categoria;
import com.lojanelioalves.api.repositories.CategoriaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    // Servico de buscar todos
    public List<Categoria> buscarTodos(){
        return repository.findAll();
    }

    // Busca por id
    public Categoria buscarPorID(Integer id){
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(null);
    }
}
