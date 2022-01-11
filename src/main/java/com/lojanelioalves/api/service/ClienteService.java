package com.lojanelioalves.api.service;

import com.lojanelioalves.api.entities.Cliente;
import com.lojanelioalves.api.repositories.ClienteRepository;
import com.lojanelioalves.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    // Servico de buscar todos
    public List<Cliente> buscarTodos(){
        return repository.findAll();
    }

    // Busca por id
    public Cliente buscarPorId(Long id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new
                ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: " + Cliente.class.getName()));

    }
}
