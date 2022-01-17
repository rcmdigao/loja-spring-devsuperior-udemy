package com.lojanelioalves.api.config;

import com.lojanelioalves.api.entities.*;
import com.lojanelioalves.api.entities.enums.EstadoPagamento;
import com.lojanelioalves.api.entities.enums.TipoCliente;
import com.lojanelioalves.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Computadores");
        Categoria cat4 = new Categoria(null, "Cama, Mesa e Banho");
        Categoria cat5 = new Categoria(null, "Higiene");
        Categoria cat6 = new Categoria(null, "Bebidas");
        Categoria cat7 = new Categoria(null, "Biscoitos");
        Categoria cat8 = new Categoria(null, "Doces");
        Categoria cat9 = new Categoria(null, "Conservas");
        Categoria cat10 = new Categoria(null, "Perfumaria");
        Categoria cat11 = new Categoria(null, "Cozinha");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);
        Produto p4 = new Produto(null, "Mesa", 80.00);
        Produto p5 = new Produto(null, "Toalha", 80.00);
        Produto p6 = new Produto(null, "Colcha", 80.00);
        Produto p7 = new Produto(null, "TV", 80.00);
        Produto p8 = new Produto(null, "Roçadeira", 80.00);
        Produto p9 = new Produto(null, "Abajour", 80.00);
        Produto p10 = new Produto(null, "Pendente", 80.00);
        Produto p11 = new Produto(null, "Shampoo", 80.00);

        // Cadastrando os produtos na categoria
        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9,p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));

        // Cadastrando as categorias dos produtos
        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));
        p7.getCategorias().addAll(Arrays.asList(cat4));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));
        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));


        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        /****************************************************************************/
        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);


        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));


        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("33022013", "94948504"));

        Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apt 303", "Jardim", "38929384", cli1, c1);
        Endereco end2 = new Endereco(null, "Rua Margarida", "200", "Apt 306", "Jardim", "74030392", cli1, c2);


        cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(end1, end2));

        /****************************************************************************/

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("22/01/2021 12:32"), cli1, end1);
        Pedido ped2 = new Pedido(null, sdf.parse("25/01/2021 10:32"), cli1, end1);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("25/02/2021 10:23"), sdf.parse("22/02/2021 20:23"));
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));


        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));


        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));





    }
}
