package com.lojanelioalves.api.resources;

import com.lojanelioalves.api.dto.CategoriaDTO;
import com.lojanelioalves.api.entities.Categoria;
import com.lojanelioalves.api.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    //Todo Buscar Todos
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> buscarTodos() {
        List<Categoria> lista = service.buscarTodos();
        List<CategoriaDTO> listaDTO = lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTO);
    }

    //Todo Paginacao
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> buscaPaginada(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "registroPorPagina", defaultValue = "24") Integer registroPorPagina,
            @RequestParam(value = "ordenacao", defaultValue = "nome") String ordenacao,
            @RequestParam(value = "direcao", defaultValue = "ASC") String direcao) {
        Page<Categoria> lista = service.buscaPaginada(pagina, registroPorPagina, ordenacao, direcao);
        Page<CategoriaDTO> listaDTO = lista.map(obj -> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(listaDTO);
    }

    //Todo Buscar Por ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {
        Categoria categoria = service.buscarPorId(id);
        return ResponseEntity.ok().body(categoria);

    }

    //Todo Cadastro
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody CategoriaDTO objDto) {
        Categoria obj = service.fromDTO(objDto);
        obj = service.cadastrar(obj);
        // Pegando a URI
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //Todo Edição
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id) {
        Categoria obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.atualizar(obj);
        return ResponseEntity.noContent().build();
    }

    //Todo Exclusão
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }





}
