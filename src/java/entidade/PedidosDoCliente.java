/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jcrfm
 */
@Entity
@Table(name = "pedidos_do_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidosDoCliente.findAll", query = "SELECT p FROM PedidosDoCliente p"),
    @NamedQuery(name = "PedidosDoCliente.findByIdpedidosDoCliente", query = "SELECT p FROM PedidosDoCliente p WHERE p.idpedidosDoCliente = :idpedidosDoCliente"),
    @NamedQuery(name = "PedidosDoCliente.findByCliente", query = "SELECT c FROM PedidosDoCliente c WHERE c.cliente = :cliente"), // manually created
    @NamedQuery(name = "PedidosDoCliente.findByMontante", query = "SELECT p FROM PedidosDoCliente p WHERE p.montante = :montante"),
    @NamedQuery(name = "PedidosDoCliente.findByDataCriacao", query = "SELECT p FROM PedidosDoCliente p WHERE p.dataCriacao = :dataCriacao"),
    @NamedQuery(name = "PedidosDoCliente.findByNumeroConfirmacao", query = "SELECT p FROM PedidosDoCliente p WHERE p.numeroConfirmacao = :numeroConfirmacao")})
   
public class PedidosDoCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpedidos_do_cliente")
    private Integer idpedidosDoCliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "montante")
    private BigDecimal montante;
    @Basic(optional = false)
    @Column(name = "data_criacao")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCriacao;
    @Basic(optional = false)
    @Column(name = "numero_confirmacao")
    private int numeroConfirmacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidosDoCliente")    
    private Collection<ProdutosPedidos> produtosPedidosCollection;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    
    public PedidosDoCliente() {
    }

    public PedidosDoCliente(Integer idpedidosDoCliente) {
        this.idpedidosDoCliente = idpedidosDoCliente;
    }

    public PedidosDoCliente(Integer idpedidosDoCliente, BigDecimal montante, LocalDateTime dataCriacao, int numeroConfirmacao) {
        this.idpedidosDoCliente = idpedidosDoCliente;
        this.montante = montante;
        this.dataCriacao = dataCriacao;
        this.numeroConfirmacao = numeroConfirmacao;
        
    }

    public Integer getIdpedidosDoCliente() {
        return idpedidosDoCliente;
    }

    public void setIdpedidosDoCliente(Integer idpedidosDoCliente) {
        this.idpedidosDoCliente = idpedidosDoCliente;
    }

    public BigDecimal getMontante() {
        return montante;
    }

    public void setMontante(BigDecimal montante) {
        this.montante = montante;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getNumeroConfirmacao() {
        return numeroConfirmacao;
    }

    public void setNumeroConfirmacao(int numeroConfirmacao) {
        this.numeroConfirmacao = numeroConfirmacao;
    }

    @XmlTransient
    public Collection<ProdutosPedidos> getProdutosPedidosCollection() {
        return produtosPedidosCollection;
    }

    public void setProdutosPedidosCollection(Collection<ProdutosPedidos> produtosPedidosCollection) {
        this.produtosPedidosCollection = produtosPedidosCollection;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedidosDoCliente != null ? idpedidosDoCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidosDoCliente)) {
            return false;
        }
        PedidosDoCliente other = (PedidosDoCliente) object;
        if ((this.idpedidosDoCliente == null && other.idpedidosDoCliente != null) || (this.idpedidosDoCliente != null && !this.idpedidosDoCliente.equals(other.idpedidosDoCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.PedidosDoCliente[idpedidosDoCliente=" + idpedidosDoCliente + "]";
    }
    
}
