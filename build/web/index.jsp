<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>

<%-- Definir vari�vel com escopo de sess�o para rastrear a visualiza��o de onde o usu�rio est� vindo.
     Isso � usado pelo mecanismo de linguagem no Controlador para que
     os usu�rios visualizam a mesma p�gina ao alternar entre ingl�s e Espanhol e Portugu�s. --%>
<c:set var = 'View' value = '/index' scope = 'session' />
        <div id="indexLeftColumn">
            <div id = "welcomeText">
            <p style = "font-size: large"><fmt:message key= 'sauda��o' /></p>
            <p><fmt:message key = 'texto_introdut�rio' /></p>
              
            </div>
        </div>

        <div id="indexRightColumn">
            <c:forEach var="categoria" items="${categorias}">
                <div class="categoryBox">
                    <a href="categoria?${categoria.idcategoria}">
                        
                    <span class="categoryLabelText"><fmt:message key= '${categoria.nome}'/></span>
                    
                    <img src="${initParam.categoryImagePath}${categoria.nome}.jpg" 
                         alt="<fmt:message key = '${categoria.nome}' />" >
                    </a>
                </div>
            </c:forEach>
        </div>

