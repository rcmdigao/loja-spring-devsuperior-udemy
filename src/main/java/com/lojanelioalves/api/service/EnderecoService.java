package com.lojanelioalves.api.service;

import com.lojanelioalves.api.entities.Categoria;
import com.lojanelioalves.api.entities.Endereco;
import com.lojanelioalves.api.repositories.CategoriaRepository;
import com.lojanelioalves.api.repositories.EnderecoRepository;
import com.lojanelioalves.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    // Servico de buscar todos
    public List<Endereco> buscarTodos(){
        return repository.findAll();
    }

    // Busca por id
    public Endereco find(Long id){
        Optional<Endereco> obj = repository.findById(id);
        return obj.orElseThrow(() -> new
                ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: " + Endereco.class.getName()));

    }
}
