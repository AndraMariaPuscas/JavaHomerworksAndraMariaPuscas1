<%-- 
    Document   : eBooksStoreMainPage
    Created on : Jun 16, 2017, 3:36:03 PM
    Author     : andra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
        <link rel="stylesheet" type="text/css" href="./css/eBooksStoreCSS.css">
    </head>
    <body>
        <c:out value="EBooks Store"></c:out>
        <%-- test if a valid user is logged in --%>
        <c:choose>
            <c:when test="${validUser == true}"> <%-- if a valid user is logged then display page --%>
                <%@ include file="./utils/eBooksStoreMenu.jsp" %>
            </c:when>
            <c:otherwise> <%-- if no valid user then open Authentication and authorization that is index.jsp  page --%>
                <c:redirect url="./index.jsp"></c:redirect>
            </c:otherwise>
        </c:choose>
    </body>
</html>
