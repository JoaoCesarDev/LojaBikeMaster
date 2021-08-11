/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessao;

import carrinho.CarrinhoDeCompra;
import carrinho.CarrinhoDeCompraItem;
import entidade.Cliente;
import entidade.PedidosDoCliente;
import entidade.ProdutosPedidos;
import entidade.ProdutosPedidosPK;
import entidade.Produtos;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext; 

/**
 *
 * @author jcrfm
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)

public class GerenciadorPedido {
    
    @PersistenceContext(unitName = "LojaBikeMasterPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    @EJB
    private ProdutosFacade produtoFacade;
    @EJB
    private PedidosDoClienteFacade PedidosDoClienteFacade;
    @EJB
    private ProdutosPedidosFacade produtosPedidosFacade;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    
    public int lugarDoPedido(String nome, String email, String telefone, String endereco, String bairro, String estado, String cep, CarrinhoDeCompra carrinho) {

        try {
            Cliente cliente = adicionarCliente(nome, email, telefone, endereco, bairro, estado, cep);
            PedidosDoCliente pedido = adicionarPedido(cliente, carrinho);
            adicionarItensPedidos(pedido, carrinho);
            return pedido.getIdpedidosDoCliente();
        } catch (Exception e) {
            context.setRollbackOnly();
            return 0;
        }
    }
    
    public Cliente adicionarCliente(String nome, String email, String telefone, String endereco, String bairro, String estado, String cep) {
        
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setEmail(email);
            cliente.setTelefone(telefone);
            cliente.setEndereco(endereco);
            cliente.setBairro(bairro);
            cliente.setEstado(estado);
            cliente.setCep(cep);
            
            em.persist(cliente);
            return cliente;
           
    }
    
    private PedidosDoCliente adicionarPedido(Cliente cliente,CarrinhoDeCompra carrinho){
        LocalDateTime dataCriacao = LocalDateTime.now();
        
        // Configuração do Pedido do Cliente
        PedidosDoCliente pedido = new PedidosDoCliente();
        pedido.setCliente(cliente);
        pedido.setMontante(BigDecimal.valueOf(carrinho.getTotal()));
        pedido.setDataCriacao(dataCriacao);
        // Cria o número de confirmação
        Random random = new Random();
        int i = random.nextInt(999999999);
        pedido.setNumeroConfirmacao(i);

        em.persist(pedido);
        return pedido;
        
    }
    
    private void adicionarItensPedidos(PedidosDoCliente pedido,CarrinhoDeCompra carrinho){
        
        em.flush();
        
        List<CarrinhoDeCompraItem> itens = carrinho.getItens();
        
        // itera através do carrinho de compras e cria Pedidos de Produdos 
        
        for (CarrinhoDeCompraItem scItem : itens){
            
            int idproduto = scItem.getProduto().getIdprodutos();
            
            // Configura a chave primária do objeto
            ProdutosPedidosPK produtospedidospk = new ProdutosPedidosPK();
            produtospedidospk.setPedidosDoClienteIdpedidosDoCliente(pedido.getIdpedidosDoCliente());
            produtospedidospk.setProdutosIdprodutos(idproduto);
            
            // cria o item pedido usando PK objeto
            ProdutosPedidos itemPedido = new ProdutosPedidos(produtospedidospk);
            
            // Coloca a quantidade
            itemPedido.setQuantidade(scItem.getQuantidade());
            
            em.persist(itemPedido);
        }
    }
    public Map getDetalhesPedido(int pedidoId){
        
        Map pedidoMap = new HashMap();
        
        // Obtém pedido
        PedidosDoCliente pedido = PedidosDoClienteFacade.find(pedidoId);
        
        //Obtém cliente      
        Cliente cliente = pedido.getCliente();
        
        // Obtém todos os clientes pedidos
        List<ProdutosPedidos> produtosPedidos = produtosPedidosFacade.findByIdPedido(pedidoId);
        
        // obtém detalhes dos produtos para itens pedidos
        List<Produtos> produtos = new ArrayList<Produtos>();
               
        for(ProdutosPedidos op : produtosPedidos){
            
            Produtos p = (Produtos)produtoFacade.find(op.getProdutosPedidosPK().getProdutosIdprodutos());
            produtos.add(p);
        }
             // adiciona cada item ao mapa do pedido
             
             pedidoMap.put("registroPedido",pedido);
             pedidoMap.put("cliente",cliente);
             pedidoMap.put("produtosPedidos",produtosPedidos);
             pedidoMap.put("produtos",produtos);
             
             return pedidoMap;
             
        }
    }
