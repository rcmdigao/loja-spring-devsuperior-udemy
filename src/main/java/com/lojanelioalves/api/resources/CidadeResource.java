package com.lojanelioalves.api.resources;

import com.lojanelioalves.api.entities.Categoria;
import com.lojanelioalves.api.entities.Cidade;
import com.lojanelioalves.api.service.CategoriaService;
import com.lojanelioalves.api.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {

    @Autowired
    private CidadeService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cidade>> findAll() {
        List<Cidade> lista = service.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Cidade cidades = service.buscarPorID(id);
        return ResponseEntity.ok().body(cidades);

    }


}
