package entidade;

import entidade.Produtos;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-11T15:41:14")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile SingularAttribute<Categoria, Short> idcategoria;
    public static volatile SingularAttribute<Categoria, String> nome;
    public static volatile CollectionAttribute<Categoria, Produtos> produtosCollection;

}