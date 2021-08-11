/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jcrfm
 */
@Entity
@Table(name = "subcategoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subcategoria.findAll", query = "SELECT s FROM Subcategoria s"),
    @NamedQuery(name = "Subcategoria.findByIdsubcategoria", query = "SELECT s FROM Subcategoria s WHERE s.idsubcategoria = :idsubcategoria"),
    @NamedQuery(name = "Subcategoria.findByNome", query = "SELECT s FROM Subcategoria s WHERE s.nome = :nome"),
    @NamedQuery(name = "Subcategoria.findByIdcategoria", query = "SELECT s FROM Subcategoria s WHERE s.idcategoria = :idcategoria")})
public class Subcategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsubcategoria")
    private Integer idsubcategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcategoria")
    private int idcategoria;

    public Subcategoria() {
    }

    public Subcategoria(Integer idsubcategoria) {
        this.idsubcategoria = idsubcategoria;
    }

    public Subcategoria(Integer idsubcategoria, String nome, int idcategoria) {
        this.idsubcategoria = idsubcategoria;
        this.nome = nome;
        this.idcategoria = idcategoria;
    }

    public Integer getIdsubcategoria() {
        return idsubcategoria;
    }

    public void setIdsubcategoria(Integer idsubcategoria) {
        this.idsubcategoria = idsubcategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsubcategoria != null ? idsubcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subcategoria)) {
            return false;
        }
        Subcategoria other = (Subcategoria) object;
        if ((this.idsubcategoria == null && other.idsubcategoria != null) || (this.idsubcategoria != null && !this.idsubcategoria.equals(other.idsubcategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Subcategoria[ idsubcategoria=" + idsubcategoria + " ]";
    }
    
}
