<%-- 
    Document   : Homepage.jsp
    Created on : Sep 28, 2015, 9:37:34 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="stylesheet.jsp"/>
    </head>
    <body>
        <jsp:include page="mp_header.jsp"/>
        
        
        <h1>Hello ${sessionScope.username}...!</h1>
         
       
    </body>
</html>
