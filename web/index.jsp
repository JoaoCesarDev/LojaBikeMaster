<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>

<%-- Definir variável com escopo de sessão para rastrear a visualização de onde o usuário está vindo.
     Isso é usado pelo mecanismo de linguagem no Controlador para que
     os usuários visualizam a mesma página ao alternar entre inglês e Espanhol e Português. --%>
<c:set var = 'View' value = '/index' scope = 'session' />
        <div id="indexLeftColumn">
            <div id = "welcomeText">
            <p style = "font-size: large"><fmt:message key= 'saudação' /></p>
            <p><fmt:message key = 'texto_introdutório' /></p>
              
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

