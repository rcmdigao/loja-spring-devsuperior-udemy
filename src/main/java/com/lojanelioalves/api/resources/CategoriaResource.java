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
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Categoria categoria = service.find(id);
        return ResponseEntity.ok().body(categoria);

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria objCategoria){
        objCategoria = service.cadastrar(objCategoria);
        // Pegando a URI
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(objCategoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }




}
