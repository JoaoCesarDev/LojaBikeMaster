/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrinho;

import entidade.Produtos;
import java.util.*;
/**
 *
 * @author jcrfm
 */
public class CarrinhoDeCompra {
    List<CarrinhoDeCompraItem> itens;
    int numeroDeItens;
    double total;
    
    public CarrinhoDeCompra(){
        itens = new ArrayList<CarrinhoDeCompraItem>();
        numeroDeItens = 0;
        total = 0;
    }
    /**
     * Adiciona um <code>CarrinhoDecomprasItem</code> para o <code>CarrinhoDeCompras</code>'s
     * <code>itens</code> list. se um item da especificação <code>produto</code>
     * já existe na lista do carrinho de compras, a quantidade daquele item é incrementada
     *
     * @param produto o <code>Produtos</code> que define o tipo de item do carrinho de compras
     * @see CarrinhoDeComprasItem
     */
    public synchronized void adicionaritem(Produtos produto){
        
        boolean novoItem = true;
        
        for (CarrinhoDeCompraItem scItem : itens){
            if(Objects.equals(scItem.getProduto().getIdprodutos(), produto.getIdprodutos())){
                
                novoItem = false;
                scItem.incrementoQuantidade();
            }
        }
        if(novoItem){
            CarrinhoDeCompraItem scItem = new CarrinhoDeCompraItem(produto);
            itens.add(scItem);
        }
    }
    
    /**
     * Atualiza o <code>CarrinhoDecomprasItem</code> do especificado
     * <code>produto</code> para a quantidade especificada. Se '<code>0</code>'
     * a quantidade dada, ao <code>CarrinhoDecomprasItem</code> é removida
     * do <code>CarrinhoDeCompras</code>'s <code>itens</code> list.
     *
     * @param produto o <code>Produtos</code> que define o tipo de item do carrinho de compras
     * @param quantidade o número que o <code>CarrinhoDecomprasItem</code> é atualizado
     * @see CarrinhoDeCompraItem
     */
    
    public synchronized void atualizar(Produtos produto, String quantidade){
        
        short qtd = -1;
        
        // cast quantity as short
        
        qtd = Short.parseShort(quantidade);
        if(qtd >=0){
            
            CarrinhoDeCompraItem item = null;
            
            for(CarrinhoDeCompraItem ScItem : itens){
                if(Objects.equals(ScItem.getProduto().getIdprodutos(), produto.getIdprodutos())){
                    if(qtd != 0){
                        ScItem.setQuantidade(qtd);
                    }
                    else{
                        item = ScItem;
                        break;
                    }
                }
            }
            if(item != null){
                 // remove do carrinho
                itens.remove(item);
            }
        }
    }
    /**
     * Retorna a lista de <code>CarrinhoDecomprasItem</code>.
     *
     * @return os <code>itens</code> list
     * @see CarrinhoDeCompraItem
     */
    public synchronized List<CarrinhoDeCompraItem> getItens(){
        return itens;
    }
    
    /**
     * Retorna a soma das quantidades para todos os itens mantidos no carrinho de compras
     * <code>itens</code> list.
     *
     * @return o número de itens no carrinho de compras.
     * @see CarrinhoDeCompraItem
     */
    
    public synchronized int getNumeroDeItens(){
        
       numeroDeItens = 0;
       
       for (CarrinhoDeCompraItem ScItem : itens){
           
           numeroDeItens += ScItem.getQuantidade();
       }
       
       return numeroDeItens;
    }
    
    /**
     * Retorna a soma dos preços dos produtos multiplicados pela quantidade para todos
     * itens na lista do carrinho de compras. Este é o custo total excluindo a sobretaxa.
     *
     * @return O custo de todos os itens vezes suas quantidades.
     * @see CarrinhoDeCompraItem
     */
    
    public synchronized double getSubtotal(){
        
        double montante = 0;
        
        for (CarrinhoDeCompraItem ScItem : itens){
            Produtos produto = (Produtos)ScItem.getProduto();
            montante += (ScItem.getQuantidade()* produto.getPreco().doubleValue());
        }
        return montante;
    }
    
    /**
     * Calcula o total do custo do pedido. Este método adiciona o subtotal a
     * designada sobretaxa e coloca a <code>total</code> variável da instância
     * com o resultado.
     *
     * @param sobretaxa a designada sobretaxa para todos os pedidos.
     * @see CarrinhoDeCompraItem
     */
    public synchronized void calculaTotal(String sobretaxa){
        
        double montante= 0;
        
        double s = Double.parseDouble(sobretaxa);
        
        montante = this.getSubtotal();
        
        montante += s;
        
        total = montante;
    }
    
    /**
     * Retorna o custo total do pedido pela quantidade
     * <code>CarrinhoDeCompras</code> instância.
     *
     * @return o custo de todos os itens vezes suas quantidades mais sobretaxa.
     */
    
    public synchronized double getTotal(){
        
        return total;
    }
    
    /**
     * Esvazia o carrihno de compras. Todos os itens são removidos do carrinho.
     * <code>itens</code> list, <code>numerodeItens</code> e
     * <code>total</code> são colocados a '<code>0</code>'.
     *
     * @see CarrinhoDeCompraItem
     */
    
    public synchronized void clear(){
        itens.clear();
        numeroDeItens = 0;
        total = 0;
    }
}

