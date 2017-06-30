<%-- 
    Document   : eBooksStoreMenu
    Created on : Jun 16, 2017, 3:19:28 PM
    Author     : andra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/menu.css" rel="stylesheet" type="text/css" />

    </head>
    <body>
        <ul id="nav">
            <c:choose>
                <c:when test="${actualUserRole == 'Admin'}">
                    <li><a href="#">Manage</a>
                        <ul>
                            <c:choose>
                                <c:when test="${actualUserRole == 'Admin'}">
                                    <li><a href="./eBooksStoreAdminUsersPage.jsp">Users</a></li>
                                    <li><a href="./eBooksStoreAdminUserRolesPage.jsp">User roles</a></li>
                                    <li><a href="./eBooksStoreAdminEBooks.jsp">eBooks</a></li>
                                    </c:when>
                                </c:choose>                              
                        </ul>
                    </li>
                    <li><a href="./eBooksStoreSoldeBooks.jsp">Sold eBooks</a></li>
                </c:when>
            </c:choose>        
            <c:choose>
                <c:when test="${actualUserRole == 'User'}">
                    <li><a href="./eBooksStoreSoldeBooks.jsp">Bought eBooks</a>
                    </li>    
                    <li><a href="eBooksStoreUserEBooks.jsp">eBooks</a>
                    </li>
                </c:when>
            </c:choose>                        
            <li><a href="./eBooksStoreExit.jsp">Log out</a></li>
        </ul>
        <script src="js/script.js"></script>
    </body>
</html>
