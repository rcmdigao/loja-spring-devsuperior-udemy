package com.lojanelioalves.api.resources;

import com.lojanelioalves.api.entities.Categoria;
import com.lojanelioalves.api.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> lista = service.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id){
        Categoria categoria = service.buscarPorId(id);
        return ResponseEntity.ok().body(categoria);

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> cadastrar(@RequestBody Categoria obj){
        obj = service.cadastrar(obj);
        // Pegando a URI
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@RequestBody Categoria obj, @PathVariable Integer id){
        obj.setId(id);
        obj = service.atualizar(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }




}
