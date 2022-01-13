package com.lojanelioalves.api.service;

import com.lojanelioalves.api.dto.ClienteDTO;
import com.lojanelioalves.api.entities.Cliente;
import com.lojanelioalves.api.repositories.ClienteRepository;
import com.lojanelioalves.api.service.exceptions.DataIntegrityException;
import com.lojanelioalves.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    // Servico de buscar todos
    public List<Cliente> buscarTodos() {
        return repository.findAll();
    }

    // Busca por id
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new
                ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: " + Cliente.class.getName()));

    }

    //Todo Serviço: Atualizar Clientes
    public Cliente atualizar(Cliente objCliente) {
        // Pesquisando na base de dados pra ver se existe o cliente
        Cliente clienteExistente = buscarPorId(objCliente.getId());
        //Atualizo os dados
        updateDados(clienteExistente, objCliente);
        return repository.save(clienteExistente);
    }

    //Todo Serviço: Metodo auxiliar para atualizar os dados.
    private void updateDados(Cliente clienteExistente, Cliente objCliente) {
        clienteExistente.setNome(objCliente.getNome());
        clienteExistente.setEmail(objCliente.getEmail());
    }

    //Todo Serviço: Excluir Cliente por ID
    public void excluir(Long id) {
        buscarPorId(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Cliente que possui Pedidos!");
        }


    }


    //Todo Serviço: Paginacao de Clientes
    public Page<Cliente> buscaPaginada(Integer pagina, Integer registroPorPagina, String ordenacao, String direcao) {
        PageRequest pageRequest = PageRequest.of(pagina, registroPorPagina, Direction.valueOf(direcao), ordenacao);
        return repository.findAll(pageRequest);
    }

    //Todo Servico: Metodo auxiliar de conversao ClienteDTO => Cliente
    public Cliente fromDTO(ClienteDTO objDTO) {
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
    }






}
