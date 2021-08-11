/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jcrfm
 */
@Entity
@Table(name = "produtos_pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProdutosPedidos.findAll", query = "SELECT p FROM ProdutosPedidos p"),
    @NamedQuery(name = "ProdutosPedidos.findByPedidosDoClienteIdpedidosDoCliente", query = "SELECT p FROM ProdutosPedidos p WHERE p.produtosPedidosPK.pedidosDoClienteIdpedidosDoCliente = :pedidosDoClienteIdpedidosDoCliente"),
    @NamedQuery(name = "ProdutosPedidos.findByProdutosIdprodutos", query = "SELECT p FROM ProdutosPedidos p WHERE p.produtosPedidosPK.produtosIdprodutos = :produtosIdprodutos"),
    @NamedQuery(name = "ProdutosPedidos.findByQuantidade", query = "SELECT p FROM ProdutosPedidos p WHERE p.quantidade = :quantidade")})
public class ProdutosPedidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProdutosPedidosPK produtosPedidosPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade")
    private int quantidade;
    @JoinColumn(name = "pedidos_do_cliente_idpedidos_do_cliente", referencedColumnName = "idpedidos_do_cliente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PedidosDoCliente pedidosDoCliente;
    @JoinColumn(name = "produtos_idprodutos", referencedColumnName = "idprodutos", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produtos produtos;

    public ProdutosPedidos() {
    }

    public ProdutosPedidos(ProdutosPedidosPK produtosPedidosPK) {
        this.produtosPedidosPK = produtosPedidosPK;
    }

    public ProdutosPedidos(ProdutosPedidosPK produtosPedidosPK, int quantidade) {
        this.produtosPedidosPK = produtosPedidosPK;
        this.quantidade = quantidade;
    }

    public ProdutosPedidos(int pedidosDoClienteIdpedidosDoCliente, int produtosIdprodutos) {
        this.produtosPedidosPK = new ProdutosPedidosPK(pedidosDoClienteIdpedidosDoCliente, produtosIdprodutos);
    }

    public ProdutosPedidosPK getProdutosPedidosPK() {
        return produtosPedidosPK;
    }

    public void setProdutosPedidosPK(ProdutosPedidosPK produtosPedidosPK) {
        this.produtosPedidosPK = produtosPedidosPK;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public PedidosDoCliente getPedidosDoCliente() {
        return pedidosDoCliente;
    }

    public void setPedidosDoCliente(PedidosDoCliente pedidosDoCliente) {
        this.pedidosDoCliente = pedidosDoCliente;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (produtosPedidosPK != null ? produtosPedidosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutosPedidos)) {
            return false;
        }
        ProdutosPedidos other = (ProdutosPedidos) object;
        if ((this.produtosPedidosPK == null && other.produtosPedidosPK != null) || (this.produtosPedidosPK != null && !this.produtosPedidosPK.equals(other.produtosPedidosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ProdutosPedidos[ produtosPedidosPK=" + produtosPedidosPK + " ]";
    }
    
}
