package entidade;

import entidade.Cliente;
import entidade.ProdutosPedidos;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-11T15:41:14")
@StaticMetamodel(PedidosDoCliente.class)
public class PedidosDoCliente_ { 

    public static volatile SingularAttribute<PedidosDoCliente, Cliente> cliente;
    public static volatile CollectionAttribute<PedidosDoCliente, ProdutosPedidos> produtosPedidosCollection;
    public static volatile SingularAttribute<PedidosDoCliente, LocalDateTime> dataCriacao;
    public static volatile SingularAttribute<PedidosDoCliente, Integer> idpedidosDoCliente;
    public static volatile SingularAttribute<PedidosDoCliente, Integer> numeroConfirmacao;
    public static volatile SingularAttribute<PedidosDoCliente, BigDecimal> montante;

}