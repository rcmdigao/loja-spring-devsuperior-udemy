package com.lojanelioalves.api.resources;

import com.lojanelioalves.api.dto.CategoriaDTO;
import com.lojanelioalves.api.entities.Categoria;
import com.lojanelioalves.api.entities.Pedido;
import com.lojanelioalves.api.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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


    //Todo Cadastro
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody Pedido pedido) {
        Pedido obj = service.cadastrar(pedido);
        // Pegando a URI
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }



}
