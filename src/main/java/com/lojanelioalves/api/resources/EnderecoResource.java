package com.lojanelioalves.api.resources;

import com.lojanelioalves.api.entities.Categoria;
import com.lojanelioalves.api.entities.Endereco;
import com.lojanelioalves.api.service.CategoriaService;
import com.lojanelioalves.api.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

    @Autowired
    private EnderecoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Endereco>> findAll() {
        List<Endereco> lista = service.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Endereco endereco = service.find(id);
        return ResponseEntity.ok().body(endereco);

    }


}
