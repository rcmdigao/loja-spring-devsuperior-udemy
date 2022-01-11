package com.lojanelioalves.api.resources;

import com.lojanelioalves.api.entities.Estado;
import com.lojanelioalves.api.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    @Autowired
    private EstadoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Estado>> findAll() {
        List<Estado> lista = service.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Estado Estados = service.buscarPorID(id);
        return ResponseEntity.ok().body(Estados);

    }


}
