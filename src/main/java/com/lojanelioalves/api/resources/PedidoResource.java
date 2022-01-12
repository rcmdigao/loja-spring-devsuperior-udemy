package com.lojanelioalves.api.resources;

import com.lojanelioalves.api.entities.Pedido;
import com.lojanelioalves.api.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> lista = service.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id){
        Pedido Pedido = service.find(id);
        return ResponseEntity.ok().body(Pedido);

    }


}
