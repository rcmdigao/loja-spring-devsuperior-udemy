package com.lojanelioalves.api.service;

import com.lojanelioalves.api.entities.Categoria;
import com.lojanelioalves.api.repositories.CategoriaRepository;
import com.lojanelioalves.api.service.exceptions.ObjectNotFoundException;
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
    public Categoria buscarPorId(Integer id){
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new
                ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: " + Categoria.class.getName()));

    }

    //Todo Serviço: Cadastro de categorias
    public Categoria cadastrar(Categoria obj) {
        obj.setId((null));
        return repository.save(obj);
    }

    //Todo Serviço: Atualizar categorias
    public Categoria atualizar(Categoria objCategoria) {
        buscarPorId(objCategoria.getId()); // Pesquisando pra ver se já tem no banco.
        return repository.save(objCategoria);
    }






}
