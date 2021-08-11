<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : categoria
    Created on : 18/07/2020, 19:46:38
    Author     : jcrfm
--%>
<c:set var = "View" value = "/categoria" scope = "session"/> 
            <%--<sql:query var="categorias" dataSource="jdbc/lojabikemaster">
                    SELECT * FROM categoria
            </sql:query>
                
            <sql:query var="categoriaselecionada" dataSource="jdbc/lojabikemaster">
                    SELECT nome FROM categoria WHERE idcategoria=?
                    <sql:param value="${pageContext.request.queryString}"/>
            </sql:query> 
                    
            <sql:query var="categoryProducts" dataSource="jdbc/lojabikemaster">
                    SELECT * FROM produtos WHERE categoria_idcategoria = ?
                    <sql:param value="${pageContext.request.queryString}"/>
            </sql:query>--%>
                   
            <div id="categoryLeftColumn">
                
                <c:forEach var= "categoria" items="${categorias}">
                    <c:choose>
                        <c:when test="${categoria.nome == categoriaselecionada.nome}">
                            
                            <div class="categoryButton" id="selectedCategory">
                              
                                <span class="categoryText">
                                    ${categoria.nome}
                                </span>
                               
                            </div>
                            
                        </c:when>
                    <c:otherwise>
                            <a href="<c:url value='categoria?${categoria.idcategoria}'/>" class="categoryButton">
                                <span class="categoryText">
                                    ${categoria.nome}
                                </span>
                            </a>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
               
            </div>
     
            <div id="categoryRightColumn">
                 <p id="categoryTitle"> 
                   ${categoriaselecionada.nome}
                </p>
                
                <table id="productTable">
                    
                    <c:forEach var="produtos" items="${categoriaprodutos}" varStatus="iter">

                        <tr class="${((iter.index % 2) == 0) ? 'LightSteelBlue' : 'white'}">
                            <td>
                                <img src="${initParam.productImagePath}${produtos.nome}.png"
                                     alt="<fmt:message key='${produtos.nome}'/>">
                            </td>
                            <td>
                                <fmt:message key="${produtos.nome}"/>
                                <br>
                                <span class="smallText"><fmt:message key='${produtos.descricao}'/></span>
                            </td>
                            <td>
                                <fmt:formatNumber type="currency" currencySymbol="&reals;&dollar;" value="${produtos.preco}"/>
                             </td>
                            <td>
                                <form action="<c:url value='adicionaraocarrinho'/>" method="post">
                                    <input type="hidden"
                                           name="produtoId"
                                           value="${produtos.idprodutos}">
                                    <input type="submit"
                                           name="submit"
                                           value="<fmt:message key="Adicionaraocarrinho"/>"> 
                                </form>
                             </td>
                        </tr>

                    </c:forEach>
                </table>
            </div>
        
    