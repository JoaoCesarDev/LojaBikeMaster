<%-- 
    Document   : checkout
    Created on : 18/07/2020, 19:46:54
    Author     : jcrfm
--%>
<c:set var = 'View' value = '/checkout' scope = 'session' />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/jquery.validate.js" type="text/javascript"></script>

<script type="text/javascript">
      $(document).ready(function(){
        $("#checkoutForm").validate({
            rules: {
                nome "required",
                email: {
                    required: true,
                    email: true
                },
                telefone {
                    required: true,
                    number: true,
                    minlength: 17
                },
                endereco {
                    required: true
                },
                cep: {
                    required: true,
                    cep: true
                }
            }
        });
    });
</script> 

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS\lojabikemaster.css">
        <title>Loja Bike Master</title>
    </head>
    <body>
        <div id="main">
            

            <div id="singleColumn">

                <h2><fmt:message key="Finalizar"/></h2>

                <p> <fmt:message key="FinalizarTexto"/></p>

                <c:if test="${!empty orderFailureFlag}">
                    <p class="error"> <fmt:message key="orderFailureError"/></p>
                </c:if>
                
                <form action="<c:url value='purchase'/>" method="post">

                    <table id="checkoutTable">
                        <c:if test="${!empty validacaoErrorFlag}">
                        <tr>
                            <td colspan="2" style="text-align:left">
                                <span class="error smallText"> Forneça entradas válidas para os seguintes campos:

                                 <c:if test="${!empty nomeError}">
                                 <br><span class="indent"><strong>nome</strong> (e.g., Bilbo Baggins)</span>
                                 </c:if>
                                 <c:if test="${!empty emailError}">
                                 <br><span class="indent"><strong>email</strong> (e.g., bbaggins@hobbit.com)</span>
                                 </c:if>
                                 <c:if test="${!empty telefoneError}">
                                 <br><span class="indent"><strong>telefone</strong> (e.g., 222333444)</span>
                                 </c:if>
                                 <c:if test="${!empty enderecoError}">
                                 <br><span class="indent"><strong>endereco</strong> (e.g., Korunní 56)</span>
                                 </c:if>
                                 <c:if test="${!empty bairroError}">
                                 <br><span class="indent"><strong>bairro</strong> (e.g., 45)</span>
                                 </c:if>
                                 <c:if test="${!empty estadoError}">
                                 <br><span class="indent"><strong>estado</strong> (e.g., 2)</span>
                                 </c:if>
                                 <c:if test="${!empty cepError}">
                                 <br><span class="indent"><strong>cep</strong> (e.g., 1111222233334444)</span>
                                 </c:if>
                                 
                                 </span>
                                 
                        </td>
                        </tr>
                        </c:if>
                        <tr>
                            <td><label for="nome"><fmt:message key="Nome"/>:</label></td>
                            <td class="inputField">
                                <input type="text"
                                        size="31"
                                        accesskey="" accept="" maxlength="55"
                                        id="name"
                                        name="nome"
                                        value="${param.nome}">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="email"><fmt:message key="email"/>:</label></td>
                            <td class="inputField">
                                <input type="text"
                                       size="31"
                                       maxlength="45"
                                       id="email"
                                       name="email"
                                       value="${param.email}">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="telefone"><fmt:message key="Telefone"/>:</label></td>
                            <td class="inputField">
                                <input type="text"
                                       size="31"
                                       maxlength="17"
                                       id="telefone"
                                       name="telefone"
                                       value="${param.telefone}">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="endereco"><fmt:message key="Endereço"/>:</label></td>
                            <td class="inputField">
                                <input type="text"
                                size="31"
                                maxlength="45"
                                id="endereco"
                                name="endereco"
                                value="${param.endereco}">
                            </td>
                        </tr>
                        <tr>
                            <td><label for="bairro"><fmt:message key="Bairro"/>:</label></td>
                            <td class="inputField">
                                <input type="text"
                                size="31"
                                maxlength="45"
                                id="bairro"
                                name="bairro"
                                value="${param.bairro}">
                            </td>
                        </tr>
                           
                        <%--    prague
                            <select name="estado">
                                <c:forEach begin="1" end="10" var="regionNumber">
                                    <option value="${regionNumber}"
                                    <c:if test="${param.estado eq regionNumber}">selected</c:if>>${regionNumber}</option>
                                </c:forEach>
                            </select>--%>
                           <tr>
                            <td><label for="bairro"><fmt:message key="Estado"/>:</label></td>
                            <td class="inputField">
                                <input type="text"
                                size="31"
                                maxlength="45"
                                id="estado"
                                name="estado"
                                value="${param.estado}">
                            </td>
                        </tr> 
                        <tr>
                        <td><label for="cep"><fmt:message key="Cep"/>:</label></td>
                                   <td class="inputField">
                                       <input type="text"
                                              size="31"
                                              maxlength="19"
                                              id="cep"
                                              name="cep"
                                              value="${param.cep}">
                                    </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                            <input type="submit" value="<fmt:message key='submit'/>">
                            </td>
                        </tr>

                    </table>

                </form>

                <div id="infoBox">

                    <ul>
                        <li><fmt:message key="AsuaEntregaéGarantida!"/></li>
                        <li><fmt:formatNumber type="currency" currencySymbol="&real;&dollar;" value="${initParam.deliverySurcharge}"/>
                            <fmt:message key="deliveryFee2"/></li>
                    </ul>

                    <table id="priceBox">
                        <tr>
                        <td>subtotal:</td>
                         <td class="checkoutPriceColumn">
                         <fmt:formatNumber type="currency" currencySymbol="&real;&dollar;" value="${carrinho.subtotal}"/></td>
                        </tr>
                    <tr>
                        <td>Taxa de Entrega :</td>
                        <td class="checkoutPriceColumn">
                            <fmt:formatNumber type="currency" currencySymbol="&real;&dollar;" value="${initParam.deliverySurcharge}"/></td>
                    </tr>
                    <tr>
                         <td class="total">total:</td>
                         <td class="total checkoutPriceColumn">
                           <fmt:formatNumber type="currency" currencySymbol="&real;&dollar;" value="${carrinho.total}"/></td>
                    </tr>
               
                    </table>
                </div>
    </body>

