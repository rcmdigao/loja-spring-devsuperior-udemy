package com.lojanelioalves.api.resources;

import com.lojanelioalves.api.dto.ProdutoDTO;
import com.lojanelioalves.api.entities.Produto;
import com.lojanelioalves.api.resources.utils.URL;
import com.lojanelioalves.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> lista = service.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    //Todo Paginacao
    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> buscaPaginada(

            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "registroPorPagina", defaultValue = "24") Integer registroPorPagina,
            @RequestParam(value = "ordenacao", defaultValue = "nome") String ordenacao,
            @RequestParam(value = "direcao", defaultValue = "ASC") String direcao) {

        String nomeDecodificado = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeStringInteger(categorias);

        Page<Produto> lista = service.search(nomeDecodificado, ids, pagina, registroPorPagina, ordenacao, direcao);
        Page<ProdutoDTO> listaDTO = lista.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listaDTO);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id){
        Produto Produto = service.buscarPorID(id);
        return ResponseEntity.ok().body(Produto);

    }


}
