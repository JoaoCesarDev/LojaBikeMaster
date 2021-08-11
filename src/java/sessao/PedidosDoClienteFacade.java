/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessao;

import entidade.PedidosDoCliente;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jcrfm
 */
@Stateless
public class PedidosDoClienteFacade extends AbstractFacade<PedidosDoCliente> {

    @PersistenceContext(unitName = "LojaBikeMasterPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidosDoClienteFacade() {
        super(PedidosDoCliente.class);
    }
    //substituído - método de atualização chamado para recuperar o ID do pedido do banco de dados
    public PedidosDoCliente find(Object idpedidosDoCliente) {
        PedidosDoCliente pedido = em.find(PedidosDoCliente.class, idpedidosDoCliente);
        em.refresh(pedido);
        return pedido;
    }
     // manually created
    // in this implementation, there is only one order per customer
    // the data model however allows for multiple orders per customer
   @RolesAllowed("lojaBikeMasterAdmin")
    public PedidosDoCliente findByCliente(Object cliente) {
        return (PedidosDoCliente) em.createNamedQuery("PedidosDoCliente.findByCliente").setParameter("cliente", cliente).getSingleResult();
    }
}
