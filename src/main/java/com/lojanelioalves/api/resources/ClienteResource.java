package com.lojanelioalves.api.resources;

import com.lojanelioalves.api.entities.Cliente;
import com.lojanelioalves.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> lista = service.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
        Cliente cliente = service.buscarPorId(id);
        return ResponseEntity.ok().body(cliente);

    }


}
