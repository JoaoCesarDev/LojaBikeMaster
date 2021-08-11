/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessao;

import java.util.List;
import entidade.ProdutosPedidos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jcrfm
 */
@Stateless
public class ProdutosPedidosFacade extends AbstractFacade<ProdutosPedidos> {

    @PersistenceContext(unitName = "LojaBikeMasterPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutosPedidosFacade() {
        super(ProdutosPedidos.class);
    }
    
    // criado à mão
    public List<ProdutosPedidos> findByIdPedido(Object id) {
        return em.createNamedQuery("ProdutosPedidos.findByPedidosDoClienteIdpedidosDoCliente").setParameter("pedidosDoClienteIdpedidosDoCliente", id).getResultList();
    }
    
}
