<%-- 
    Document   : pedido
    Created on : 18/07/2020, 19:46:28
    Author     : jcrfm
--%>
<c:set var = 'View' value = '/pedido' scope = 'session' />


        <div id="main">
            

            <div id="singleColumn">
                <c:choose>
                     <c:when test="${carrinho.numeroDeItens > 1}">    
                        <p><fmt:message key="SeuCarrinhoContém"/> ${carrinho.numeroDeItens} <fmt:message key="itens"/>.</p>
                </c:when>
                <c:when test="${carrinho.numeroDeItens == 1}">
                        <p><fmt:message key="SeuCarrinhoContém"/>  ${carrinho.numeroDeItens} <fmt:message key="item"/>.</p>
                </c:when>
                <c:otherwise>
                        <p><fmt:message key="Seucarrinhovazio"/>.</p>
                </c:otherwise>
                </c:choose> 
                
                <div id="actionBar">
                     <%-- clear cart widget --%>
                     <c:if test="${!empty carrinho && carrinho.numeroDeItens != 0}">

                     <c:url var="url" value="visualizarcarrinho">
                        <c:param name="clear" value="true"/>
                     </c:url>
                        
                     <a href="${url}" class="bubble hMargin"><fmt:message key="LimparCarrinho"/></a>
                     </c:if>
                     
                     <%-- continue shopping widget --%>
                     <c:set var="value">
                     <c:choose>
                     <%-- if 'selectedCategory' session object exists, send user to previously viewed category --%>
                     <c:when test="${!empty categoriaselecionada}">
                        categoria
                     </c:when>
                     <%-- otherwise send user to welcome page --%>  
                     <c:otherwise>
                    index.jsp
                     </c:otherwise>
                     </c:choose>
                     </c:set>
                     <c:url var="url" value="${value}"/>
                        <a href="${url}" class="bubble hMargin"><fmt:message key="ContinuarComprando"/></a>

                    <%-- checkout widget --%>
                    <c:if test="${!empty carrinho && carrinho.numeroDeItens != 0}">
                        <a href="<c:url value='checkout'/>" class="bubble hMargin"><fmt:message key="ProsseguirparaFinalizar"/></a>
                    </c:if>
                </div>
                
                <c:if test="${!empty carrinho && carrinho.numeroDeItens != 0}">
                        
                    <h4 id="subtotal"><fmt:message key="Subtotal"/>:
                        <fmt:formatNumber type="currency" currencySymbol="&reals;&dollar;" value="${carrinho.subtotal}"/>
                    </h4>

                <table id="cartTable">

                    <tr class="header">
                        <th><fmt:message key="Produto"/></th>
                        <th><fmt:message key="Nome"/></th>
                        <th><fmt:message key="Preço"/></th>
                        <th><fmt:message key="Quantidade"/></th>
                    </tr>
                    
                    <c:forEach var="carrinhoItem" items="${carrinho.itens}" varStatus="iter">

                            <c:set var="produto" value="${carrinhoItem.produto}"/>

                            <tr class="${((iter.index % 2) == 0) ? 'lightBlue' : 'white'}">
                            <td>
                                <img src="${initParam.productImagePath}${produto.nome}.png"
                                     alt="<fmt:message key="${produtos.nome}"/>">
                            </td>

                            <td><fmt:message key="${produto.nome}"/></td>
                    
                            <td>
                                <fmt:formatNumber type="currency" currencySymbol="&reals;&dollar;" value="${carrinhoItem.total}"/>
                                <br>
                                <span class="smallText">( <fmt:formatNumber type="currency" currencySymbol="&reals;&dollar;" value="${produto.preco}" />)</span>
                            </td>
                            <td>
                            <form action="atualizarcarrinho" method="post">
                                <input type="hidden"
                                       name="produtoId"
                                       value="${produto.idprodutos}">
                                       
                                <input type="text"
                                       maxlength="2"
                                       size="2"
                                       value="${carrinhoItem.quantidade}"
                                       name="quantidade"
                                       style="margin:5px">
                                <input type="submit"
                                       name="submit"
                                       value="<fmt:message key='Atualizar'/>">
                            </form>
                            </td>
                            </tr>
                        </c:forEach>
                        </table>
                    </c:if>
            </div>
   
