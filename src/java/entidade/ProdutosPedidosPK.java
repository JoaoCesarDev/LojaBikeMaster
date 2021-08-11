/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jcrfm
 */
@Embeddable
public class ProdutosPedidosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "pedidos_do_cliente_idpedidos_do_cliente")
    private int pedidosDoClienteIdpedidosDoCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "produtos_idprodutos")
    private int produtosIdprodutos;

    public ProdutosPedidosPK() {
    }

    public ProdutosPedidosPK(int pedidosDoClienteIdpedidosDoCliente, int produtosIdprodutos) {
        this.pedidosDoClienteIdpedidosDoCliente = pedidosDoClienteIdpedidosDoCliente;
        this.produtosIdprodutos = produtosIdprodutos;
    }

    public int getPedidosDoClienteIdpedidosDoCliente() {
        return pedidosDoClienteIdpedidosDoCliente;
    }

    public void setPedidosDoClienteIdpedidosDoCliente(int pedidosDoClienteIdpedidosDoCliente) {
        this.pedidosDoClienteIdpedidosDoCliente = pedidosDoClienteIdpedidosDoCliente;
    }

    public int getProdutosIdprodutos() {
        return produtosIdprodutos;
    }

    public void setProdutosIdprodutos(int produtosIdprodutos) {
        this.produtosIdprodutos = produtosIdprodutos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pedidosDoClienteIdpedidosDoCliente;
        hash += (int) produtosIdprodutos;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutosPedidosPK)) {
            return false;
        }
        ProdutosPedidosPK other = (ProdutosPedidosPK) object;
        if (this.pedidosDoClienteIdpedidosDoCliente != other.pedidosDoClienteIdpedidosDoCliente) {
            return false;
        }
        if (this.produtosIdprodutos != other.produtosIdprodutos) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.ProdutosPedidosPK[ pedidosDoClienteIdpedidosDoCliente=" + pedidosDoClienteIdpedidosDoCliente + ", produtosIdprodutos=" + produtosIdprodutos + " ]";
    }
    
}
