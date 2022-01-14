package com.lojanelioalves.api.service;

import com.lojanelioalves.api.dto.ClienteDTO;
import com.lojanelioalves.api.dto.ClienteNovoDTO;
import com.lojanelioalves.api.entities.Cidade;
import com.lojanelioalves.api.entities.Cliente;
import com.lojanelioalves.api.entities.Endereco;
import com.lojanelioalves.api.entities.enums.TipoCliente;
import com.lojanelioalves.api.repositories.ClienteRepository;
import com.lojanelioalves.api.repositories.EnderecoRepository;
import com.lojanelioalves.api.service.exceptions.DataIntegrityException;
import com.lojanelioalves.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    //Todo Buscar Todos
    public List<Cliente> buscarTodos() {
        return repository.findAll();
    }

    //Todo Buscar Por ID
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new
                ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: " + Cliente.class.getName()));

    }


    //Todo Cadastro de Clientes
    @Transactional
    public Cliente cadastrar(Cliente obj) {
        obj.setId((null));
        obj = repository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    //Todo Atualizar Clientes
    public Cliente atualizar(Cliente objCliente) {
        // Pesquisando na base de dados pra ver se existe o cliente
        Cliente clienteExistente = buscarPorId(objCliente.getId());
        //Atualizo os dados
        updateDados(clienteExistente, objCliente);
        return repository.save(clienteExistente);
    }

    //Todo Metodo auxiliar para atualizar os dados.
    private void updateDados(Cliente clienteExistente, Cliente objCliente) {
        clienteExistente.setNome(objCliente.getNome());
        clienteExistente.setEmail(objCliente.getEmail());
    }

    //Todo Excluir Cliente por ID
    public void excluir(Long id) {
        buscarPorId(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Cliente que possui Pedidos!");
        }


    }

    //Todo Paginacao de Clientes
    public Page<Cliente> buscaPaginada(Integer pagina, Integer registroPorPagina, String ordenacao, String direcao) {
        PageRequest pageRequest = PageRequest.of(pagina, registroPorPagina, Direction.valueOf(direcao), ordenacao);
        return repository.findAll(pageRequest);
    }

    //Todo Conversao ClienteDTO => Cliente
    public Cliente fromDTO(ClienteDTO objDTO) {
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
    }


    //Todo Conversao ClienteNovoDTO => Cliente
    public Cliente fromDTO(ClienteNovoDTO objDTO) {
        Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
        Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDTO.getTelefone1());

        if (objDTO.getTelefone2() != null) {
            cli.getTelefones().add(objDTO.getTelefone2());
        }
        if (objDTO.getTelefone3() != null) {
            cli.getTelefones().add(objDTO.getTelefone3());
        }
        return cli;
    }


}
