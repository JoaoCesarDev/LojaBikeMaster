/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessao;

import entidade.Produtos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jcrfm
 */
@Stateless
public class ProdutosFacade extends AbstractFacade<Produtos> {

    @PersistenceContext(unitName = "LojaBikeMasterPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutosFacade() {
        super(Produtos.class);
    }
    
}
