<%-- 
    Document   : index
    Created on : 18/08/2020, 14:08:21
    Author     : jcrfm
--%>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <div id="adminmenu" class="alignleft">
        <p><a href="<c:url value='visualizarclientes'/>">Visualizar todos os Clientes</a></p>

        <p><a href="<c:url value='visualizarpedidos'/>">Visualizar todos os Pedidos</a></p>

        <p><a href="<c:url value='sair'/>">Sair</a></p>
    </div>
    
    <c:if test="${!empty clienteList}">

    <table id="adminTable" class="detailsTable">

        <tr class="header">
            <th colspan="4">Clientes</th>
        </tr>

        <tr class="tableHeading">
            <td>Id Cliente</td>
            <td>Nome</td>
            <td>email</td>
            <td>Telefone</td>
        </tr>
        
        <c:forEach var="cliente" items="${clienteList}" varStatus="iter">

            <tr class="${((iter.index % 2) == 1) ? 'lightBlue' : 'white'} tableRow"
                onclick="document.location.href='registroClientes?${cliente.idcliente}'">

              <%-- Below anchor tags are provided in case JavaScript is disabled --%>
                <td><a href="registroClientes?${cliente.idcliente}" class="noDecoration">${cliente.idcliente}</a></td>
                <td><a href="registroClientes?${cliente.idcliente}" class="noDecoration">${cliente.nome}</a></td>
                <td><a href="registroClientes?${cliente.idcliente}" class="noDecoration">${cliente.email}</a></td>
                <td><a href="registroClientes?${cliente.idcliente}" class="noDecoration">${cliente.telefone}</a></td>
            </tr>

        </c:forEach>

    </table>
    </c:if>
            
    <%-- orderList is requested --%>
<c:if test="${!empty orderList}">

    <table id="adminTable" class="detailsTable">

        <tr class="header">
            <th colspan="4">Pedidos</th>
        </tr>

        <tr class="tableHeading">
            <td>Id Pedido</td>
            <td>Número de Confirmação</td>
            <td>Montante</td>
            <td>Data de Criação</td>
        </tr>
        
         <c:forEach var="pedido" items="${orderList}" varStatus="iter">

            <tr class="${((iter.index % 2) == 1) ? 'lightBlue' : 'white'} tableRow"
                onclick="document.location.href='registroPedido?${pedido.idpedidosDoCliente}'">

              <%-- Below anchor tags are provided in case JavaScript is disabled --%>
                <td><a href="registroPedido?${pedido.idpedidosDoCliente}" class="noDecoration">${pedido.idpedidosDoCliente}</a></td>
                <td><a href="registroPedido?${pedido.idpedidosDoCliente}" class="noDecoration">${pedido.numeroConfirmacao}</a></td>
                <td><a href="registroPedido?${pedido.idpedidosDoCliente}" class="noDecoration">
                        <fmt:formatNumber type="currency"
                                          currencySymbol="&reals;&dollar;"
                                          value="${pedido.montante}"/></a></td>

                <td><a href="registroPedido?${pedido.idpedidosDoCliente}" class="noDecoration">
                        <fmt:parseDate value="${pedido.dataCriacao}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                        <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${parsedDateTime}" /></a></td>
            </tr>

        </c:forEach>

    </table>
                                  
</c:if>
 
<%-- customerRecord is requested --%>
<c:if test="${!empty registroClientes}">

    <table id="adminTable" class="detailsTable">

        <tr class="header">
            <th colspan="2">Detalhes dos Clientes</th>
        </tr>
        <tr>
            <td style="width: 290px"><strong>Cliente id:</strong></td>
            <td>${registroClientes.idcliente}</td>
        </tr>
        <tr>
            <td><strong>Nome:</strong></td>
            <td>${registroClientes.nome}</td>
        </tr>
        <tr>
            <td><strong>email:</strong></td>
            <td>${registroClientes.email}</td>
        </tr>
        <tr>
            <td><strong>Telefone:</strong></td>
            <td>${registroClientes.telefone}</td>
        </tr>
        <tr>
            <td><strong>Endereço:</strong></td>
            <td>${registroClientes.endereco}</td>
        </tr>
        <tr>
            <td><strong>Bairro:</strong></td>
            <td>${registroClientes.bairro}</td>
        </tr>
         <tr>
            <td><strong>Estado:</strong></td>
            <td>${registroClientes.estado}</td>
        </tr>
        <tr>
            <td><strong>Cep:</strong></td>
            <td>${registroClientes.cep}</td>
        </tr>

        <tr><td colspan="2" style="padding: 0 20px"><hr></td></tr>

        <tr class="tableRow"
            onclick="document.location.href='registroPedido?${pedido.idpedidosDoCliente}'">
            <td colspan="2">
                <%-- Anchor tag is provided in case JavaScript is disabled --%>
                <a href="registroPedido?${pedido.idpedidosDoCliente}" class="noDecoration">
                <strong>Resumo de Visualização dos Pedidos &#x279f;</strong></a></td>
        </tr>
    </table>

</c:if>
                
<%-- orderRecord is requested --%>
<c:if test="${!empty registroPedido}">

    <table id="adminTable" class="detailsTable">

        <tr class="header">
            <th colspan="2">Resumo dos Pedidos</th>
        </tr>
        <tr>
            <td><strong>Pedido id:</strong></td>
            <td>${registroPedido.idpedidosDoCliente}</td>
        </tr>
        <tr>
            <td><strong>Número de Confirmação:</strong></td>
            <td>${registroPedido.numeroConfirmacao}</td>
        </tr>
        <tr>
            <td><strong>Data do Processamento:</strong></td>
            <td>
                <fmt:parseDate value="${registroPedido.dataCriacao}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${parsedDateTime}" />
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <table class="embedded detailsTable">
                   <tr class="tableHeading">
                        <td class="rigidWidth">Produto</td>
                        <td class="rigidWidth">Quantidade</td>
                        <td>preço</td>
                    </tr>

                    <tr><td colspan="3" style="padding: 0 20px"><hr></td></tr>

                    <c:forEach var="produtosPedido" items="${produtosPedidos}" varStatus="iter">

                        <tr>
                            <td>
                                <fmt:message key="${produtos[iter.index].nome}"/>
                            </td>
                            <td>
                                ${produtosPedido.quantidade}
                            </td>
                            <td class="confirmationPriceColumn">
                                <fmt:formatNumber type="currency" currencySymbol="&reals;&dollar;"
                                                  value="${produtos[iter.index].preco * produtosPedido.quantidade}"/>
                            </td>
                        </tr>

                    </c:forEach>

                    <tr><td colspan="3" style="padding: 0 20px"><hr></td></tr>

                    <tr>
                        <td colspan="2" id="deliverySurchargeCellLeft"><strong>Taxa de Entrega:</strong></td>
                        <td id="deliverySurchargeCellRight">
                            <fmt:formatNumber type="currency"
                                              currencySymbol="&reals;&dollar; "
                                              value="${initParam.deliverySurcharge}"/></td>
                    </tr>

                    <tr>
                        <td colspan="2" id="totalCellLeft"><strong>Montante Total:</strong></td>
                        <td id="totalCellRight">
                            <fmt:formatNumber type="currency"
                                              currencySymbol="&reals;&dollar;"
                                              value="${registroPedido.montante}"/></td>
                    </tr>
                </table>
            </td>
        </tr>

        <tr><td colspan="3" style="padding: 0 20px"><hr></td></tr>

        <tr class="tableRow"
            onclick="document.location.href='registroClientes?${cliente.idcliente}'">
            <td colspan="2">
                <%-- Anchor tag is provided in case JavaScript is disabled --%>
                <a href="registroClientes?${cliente.idcliente}" class="noDecoration">
                    <strong>Visualizar Detalhes dos Clientes &#x279f;</strong></a></td>
        </tr>
    </table>

</c:if>
                                        