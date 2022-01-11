package com.lojanelioalves.api.service;

import com.lojanelioalves.api.entities.Estado;
import com.lojanelioalves.api.repositories.EstadoRepository;
import com.lojanelioalves.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    // Servico de buscar todos
    public List<Estado> buscarTodos(){
        return repository.findAll();
    }

    // Busca por id
    public Estado buscarPorID(Long id){
        Optional<Estado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new
                ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: " + Estado.class.getName()));
    }
}
