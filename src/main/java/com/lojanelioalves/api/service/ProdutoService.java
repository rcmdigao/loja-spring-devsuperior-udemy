package com.lojanelioalves.api.service;

import com.lojanelioalves.api.entities.Categoria;
import com.lojanelioalves.api.entities.Produto;
import com.lojanelioalves.api.repositories.CategoriaRepository;
import com.lojanelioalves.api.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    //Todo Buscar Todos
    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    //Todo Excluir Cliente por ID
    public Produto buscarPorID(Long id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(null);
    }

    //Todo Busca Paginada de Produtos
    public Page<Produto> search(String nome, List<Integer> ids, Integer pagina, Integer registroPorPagina, String ordenacao, String direcao) {
        PageRequest pageRequest = PageRequest.of(pagina, registroPorPagina, Direction.valueOf(direcao), ordenacao);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }


}
