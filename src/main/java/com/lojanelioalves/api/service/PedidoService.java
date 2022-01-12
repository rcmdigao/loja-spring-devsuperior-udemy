package com.lojanelioalves.api.service;

import com.lojanelioalves.api.entities.Pedido;
import com.lojanelioalves.api.repositories.PedidoRepository;
import com.lojanelioalves.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    // Servico de buscar todos
    public List<Pedido> buscarTodos(){
        return repository.findAll();
    }

    // Busca por id
    public Pedido find(Long id){
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new
                ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: " + Pedido.class.getName()));

    }
}
