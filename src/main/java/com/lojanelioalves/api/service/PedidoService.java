package com.lojanelioalves.api.service;

import com.lojanelioalves.api.entities.ItemPedido;
import com.lojanelioalves.api.entities.PagamentoComBoleto;
import com.lojanelioalves.api.entities.Pedido;
import com.lojanelioalves.api.entities.enums.EstadoPagamento;
import com.lojanelioalves.api.repositories.ItemPedidoRepository;
import com.lojanelioalves.api.repositories.PagamentoRepository;
import com.lojanelioalves.api.repositories.PedidoRepository;
import com.lojanelioalves.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    // Servico de buscar todos
    public List<Pedido> buscarTodos() {
        return repository.findAll();
    }

    // Busca por id
    public Pedido find(Long id) {
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new
                ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: " + Pedido.class.getName()));

    }

    //Todo Cadastro de categorias
    public Pedido cadastrar(Pedido obj) {
        obj.setId((null));
        obj.setInstant(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);

        // Gerando data com 1 semana de vencimento
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstant());
        }

        // Salvando o Pedido
        obj = repository.save(obj);
        // Salvando o pagamento
        pagamentoRepository.save(obj.getPagamento());

        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.buscarPorID(ip.getProduto().getId()));
            ip.setPedido(obj);
        }

        // Salvando os itens
        itemPedidoRepository.saveAll(obj.getItens());
        return obj;
    }


}
