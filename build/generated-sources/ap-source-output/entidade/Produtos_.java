package entidade;

import entidade.Categoria;
import entidade.ProdutosPedidos;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-11T15:41:14")
@StaticMetamodel(Produtos.class)
public class Produtos_ { 

    public static volatile SingularAttribute<Produtos, BigDecimal> preco;
    public static volatile SingularAttribute<Produtos, Categoria> categoriaIdcategoria;
    public static volatile CollectionAttribute<Produtos, ProdutosPedidos> produtosPedidosCollection;
    public static volatile SingularAttribute<Produtos, Integer> idprodutos;
    public static volatile SingularAttribute<Produtos, Date> ultimaAtualizacao;
    public static volatile SingularAttribute<Produtos, String> nome;
    public static volatile SingularAttribute<Produtos, String> descricao;

}