package com.lojanelioalves.api.resources;

import com.lojanelioalves.api.entities.Categoria;
import com.lojanelioalves.api.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Categoria categoria = service.buscarPorID(id);
        return ResponseEntity.ok().body(categoria);

    }


}
