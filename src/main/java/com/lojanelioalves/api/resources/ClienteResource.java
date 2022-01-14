package com.lojanelioalves.api.resources;

import com.lojanelioalves.api.dto.ClienteDTO;
import com.lojanelioalves.api.dto.ClienteNovoDTO;
import com.lojanelioalves.api.entities.Cliente;
import com.lojanelioalves.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    //Todo Buscar Todos
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> lista = service.buscarTodos();
        return ResponseEntity.ok().body(lista);
    }

    //Todo Buscar Por ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        Cliente cliente = service.buscarPorId(id);
        return ResponseEntity.ok().body(cliente);

    }

    //Todo Paginacao
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> buscaPaginada(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "registroPorPagina", defaultValue = "24") Integer registroPorPagina,
            @RequestParam(value = "ordenacao", defaultValue = "nome") String ordenacao,
            @RequestParam(value = "direcao", defaultValue = "ASC") String direcao) {
        Page<Cliente> lista = service.buscaPaginada(pagina, registroPorPagina, ordenacao, direcao);
        Page<ClienteDTO> listaDTO = lista.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(listaDTO);
    }

    //Todo Cadastro
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody ClienteNovoDTO objDto) {
        Cliente obj = service.fromDTO(objDto);
        obj = service.cadastrar(obj);
        // Pegando a URI
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //Todo Edicão
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@Valid @RequestBody ClienteDTO objDto, @PathVariable Long id) {
        Cliente obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.atualizar(obj);
        return ResponseEntity.noContent().build();
    }

    //Todo Exclusão
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }


}
