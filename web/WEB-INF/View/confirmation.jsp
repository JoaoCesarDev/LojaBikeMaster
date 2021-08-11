<%-- 
    Document   : confirmation
    Created on : 18/07/2020, 19:47:17
    Author     : jcrfm
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    
   
            <div id="singleColumn">

                <p id="confirmationText">
                    <strong><fmt:message key="MensagemSucesso"/></strong>
                    <br><br>
                     <fmt:message key="numedodeconfirmacaomensagem"/>
                    <strong>${registroPedido.numeroConfirmacao}</strong>
                    <br>
                       <fmt:message key="MensagemdeContato"/> 
                    <br><br>
                    <fmt:message key="Mensagemdeagradecimento"/>
                </p>

                <div class="summaryColumn" >

                    <table id="orderSummaryTable" class="detailsTable" >
                        <tr class="header">
                            <th colspan="3"><fmt:message key="Resumopedido"/></th>
                        </tr>
                        
                        <tr class="tableHeading">
                            <td><fmt:message key="Produto"/></td>
                            <td><fmt:message key="Quantidade"/></td>
                            <td><fmt:message key="Preço"/></td>
                        </tr>
                        
                        <c:forEach var="produtosPedidos" items="${produtosPedidos}" varStatus="iter">

                            <tr class="${((iter.index % 2) != 0) ? 'white' : 'white'}">
                                <td><fmt:message key="${produtos[iter.index].nome}"/></td>
                                <td class="quantityColumn">
                                    ${produtosPedidos.quantidade}
                                </td>
                                <td class="confirmationPriceColumn">
                                    <fmt:formatNumber type="currency" currencySymbol="&reals;&dollar;"value=" ${produtos[iter.index].preco * produtosPedidos.quantidade}"/>
                                </td>
                            </tr>

                        </c:forEach>
                                
                         <tr class="lightBlue"><td colspan="3" style="padding: 0 20px"><hr></td></tr>
                         
                         <tr class="lightBlue">
                         <td colspan="2" id="deliverySurchargeCellLeft"><strong><fmt:message key="TaxadeEntrega"/>:</strong></td>
                         <td id="deliverySurchargeCellRight"><fmt:formatNumber type="currency" currencySymbol="&reals;&dollar;" value=" ${initParam.deliverySurcharge}"/></td>
                         </tr>
                         
                          <tr class="lightBlue">
                          <td colspan="2" id="totalCellLeft"><strong><fmt:message key="Total"/>:</strong></td>
                          <td id="totalCellRight"><fmt:formatNumber type="currency" currencySymbol="&reals;&dollar;" value=" ${registroPedido.montante}"/></td>
                          </tr>
                         
                          <tr class="lightBlue"><td colspan="3" style="padding: 0 20px"><hr></td></tr>
                          
                          <tr class="lightBlue">
                                <td colspan="3" id="dateProcessedRow"><strong><fmt:message key="DatadoProcessamento"/>:</strong>
                                  <fmt:parseDate value="${registroPedido.dataCriacao}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                  <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${parsedDateTime}" />
                                </td>
                          </tr>
                          
                    </table>

                </div>

                <div class="summaryColumn" >

                <table id="deliveryAddressTable" class="detailsTable">
                        <tr class="header">
                            <th colspan="3"><fmt:message key="EnderecodeEntrega"/></th>
                        </tr>
                    
                <td colspan="3" class="lightBlue">
                    ${cliente.nome}
                    <br>
                    ${cliente.endereco}
                    <br>
                    <fmt:message key="Bairro"/> ${cliente.bairro}
                    <br>
                    <hr>
                    <strong><fmt:message key="email"/>:</strong> ${cliente.email}
                    <br>
                    <strong><fmt:message key="Telefone"/>:</strong> ${cliente.telefone}
                </td>
               </table>
                </div>
          </div>  
       

