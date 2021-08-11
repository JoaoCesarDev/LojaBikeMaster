package entidade;

import entidade.PedidosDoCliente;
import entidade.Produtos;
import entidade.ProdutosPedidosPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-11T15:41:14")
@StaticMetamodel(ProdutosPedidos.class)
public class ProdutosPedidos_ { 

    public static volatile SingularAttribute<ProdutosPedidos, ProdutosPedidosPK> produtosPedidosPK;
    public static volatile SingularAttribute<ProdutosPedidos, PedidosDoCliente> pedidosDoCliente;
    public static volatile SingularAttribute<ProdutosPedidos, Produtos> produtos;
    public static volatile SingularAttribute<ProdutosPedidos, Integer> quantidade;

}