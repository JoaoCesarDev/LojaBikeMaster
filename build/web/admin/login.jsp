<%-- 
    Document   : login
    Created on : 19/08/2020, 20:00:11
    Author     : jcrfm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<form action = "j_security_check" method = "post" >
    <div id = "loginBox">
        <p> <strong> nome de usuário: </strong>
            <input type = "text" size = "20" name = "j_username"> </p>

        <p> <strong> senha: </strong>
            <input type = "password" size = "20" name = "j_password"> </p>
       
    </div>
          <p> <input type = "submit" value = "Acessar"> </p>   
</form>