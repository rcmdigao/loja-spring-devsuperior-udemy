package com.lojanelioalves.api.service;

import com.lojanelioalves.api.dto.CategoriaDTO;
import com.lojanelioalves.api.entities.Categoria;
import com.lojanelioalves.api.entities.Cliente;
import com.lojanelioalves.api.repositories.CategoriaRepository;
import com.lojanelioalves.api.service.exceptions.DataIntegrityException;
import com.lojanelioalves.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    //Todo Serviço: Buscar todas as categorias
    public List<Categoria> buscarTodos() {
        return repository.findAll();
    }

    //Todo Serviço: Buscar Categoria por ID
    public Categoria buscarPorId(Integer id) {
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
        // Pesquisando na base de dados pra ver se existe o cliente
        Categoria categoriaExistente = buscarPorId(objCategoria.getId());
        //Atualizo os dados
        updateDados(categoriaExistente, objCategoria);
        return repository.save(categoriaExistente);
    }

    //Todo Serviço: Metodo auxiliar para atualizar os dados.
    private void updateDados(Categoria categoriaExistente, Categoria objCategoria) {
        categoriaExistente.setNome(objCategoria.getNome());
    }

    //Todo Serviço: Excluir categoria por ID
    public void excluir(Integer id) {
        buscarPorId(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
        }


    }


    //Todo Serviço: Paginacao de Categorias
    public Page<Categoria> buscaPaginada(Integer pagina, Integer registroPorPagina, String ordenacao, String direcao) {
        PageRequest pageRequest = PageRequest.of(pagina, registroPorPagina, Direction.valueOf(direcao), ordenacao);
        return repository.findAll(pageRequest);
    }

    //Todo Servico: Metodo auxiliar de conversao CategoriaDTO => Categoria
    public Categoria fromDTO(CategoriaDTO objDTO){
        return new Categoria(objDTO.getId(), objDTO.getNome());
    }


}
