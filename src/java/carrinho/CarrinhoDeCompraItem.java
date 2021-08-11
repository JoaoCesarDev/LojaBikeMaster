/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrinho;
import entidade.Produtos;

/**
 *
 * @author jcrfm
 */
public class CarrinhoDeCompraItem {
    Produtos produto;
    short quantidade;
    
    public CarrinhoDeCompraItem(Produtos produto){
        this.produto = produto;
        quantidade = 1;
    }
    public Produtos getProduto(){
        return produto;
    }
    public short getQuantidade(){
        return quantidade;
    }
    public void setQuantidade(short quantidade){
        this.quantidade = quantidade;
    }
    public void incrementoQuantidade(){
        quantidade++;
    }
    public void decrementoQuantidade(){
        quantidade--;
    }
    
    public double getTotal(){
        double montante = 0;
        montante = (this.getQuantidade()* produto.getPreco().doubleValue());
        return montante;
    }
}
