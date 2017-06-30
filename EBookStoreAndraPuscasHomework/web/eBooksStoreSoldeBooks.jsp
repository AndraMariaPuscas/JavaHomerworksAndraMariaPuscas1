<%-- 
    Document   : eBooksStoreSoldeBooks
    Created on : Jun 27, 2017, 7:31:01 PM
    Author     : andra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="./css/eBooksStoreCSS.css">
        <title>Sold eBooks</title>
    </head>
    <body>
        <%-- test if an admin or a user is authenticated and authorized --%>
        <c:choose>
            <c:when test="${actualUserRole == 'Admin'}">
                <!-- include menu -->
                <%@ include file="./utils/eBooksStoreMenu.jsp" %>
                <form action="${pageContext.request.contextPath}/eBooksStoreSoldeBooks">
                    <sql:setDataSource 
                        var="snapshot" 
                        driver="org.apache.derby.jdbc.ClientDriver"
                        url="jdbc:derby://localhost:1527/eBooksDB;create=true;"
                        user="andra"  
                        password="andra"/>
                    <sql:query dataSource="${snapshot}" var="result">
                        SELECT  USERS.NAME, SOLDEBOOKS.Title, authors.author_name, SOLDEBOOKS.ISBN 
                        FROM SOLDEBOOKS, USERS, AUTHORS, SOLDEBOOKS_USERS
                        WHERE USERS.USER_ID=SOLDEBOOKS_USERS.USER_ID AND
                        SOLDEBOOKS.ID_AUTHOR=AUTHORS.ID_AUTHOR AND
                        SOLDEBOOKS_USERS.ID_SOLDBOOK=SOLDEBOOKS.ID 
                        ORDER BY USERS.NAME ASC

                    </sql:query>
                    <table border="1" width="100%">
                        <tr>
                            <td width="19%" class="thc"> User </td>    
                            <td width="19%" class="thc">Book</td>
                            <td width="19%" class="thc">Author</td>
                            <td width="19%" class="thc">ISBN</td>
                    </table>    
                    <table border="1" width="100%">    
                        </tr>
                        <c:forEach var="row" varStatus="loop" items="${result.rows}"> 
                            <tr>
                                <td width="19%" class="tdc"><c:out value="${row.name}"/></td>
                                <td width="19%" class="tdc"><c:out value="${row.title}"/></td>
                                <td width="19%" class="tdc"><c:out value="${row.author_name}"/></td>
                                <td width="19%" class="tdc"><c:out value="${row.isbn}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
            </c:when>
            <c:when test="${actualUserRole == 'User'}">   
                <!-- include menu -->
                <%@ include file="./utils/eBooksStoreMenu.jsp" %>
                <form action="${pageContext.request.contextPath}/eBooksStoreSoldeBooks">
                    <sql:setDataSource 
                        var="snapshot" 
                        driver="org.apache.derby.jdbc.ClientDriver"
                        url="jdbc:derby://localhost:1527/eBooksDB;create=true;"
                        user="andra"  
                        password="andra"/>
                    <%-- populate the fields with books bought by the authenticated user --%>
                    <sql:query dataSource="${snapshot}" var="result">
                        SELECT  USERS.NAME, SOLDEBOOKS.Title, authors.author_name, SOLDEBOOKS.ISBN 
                        FROM SOLDEBOOKS, USERS, AUTHORS, SOLDEBOOKS_USERS
                        WHERE users.username='${actualUser}' and
                        USERS.USER_ID=SOLDEBOOKS_USERS.USER_ID AND
                        SOLDEBOOKS.ID_AUTHOR=AUTHORS.ID_AUTHOR AND
                        SOLDEBOOKS_USERS.ID_SOLDBOOK=SOLDEBOOKS.ID 

                    </sql:query>
                    <table border="1" width="100%">
                        <tr>
                            <td width="19%" class="thc"> User </td>    
                            <td width="19%" class="thc">Title</td>
                            <td width="19%" class="thc">Author</td>
                            <td width="19%" class="thc">ISBN</td>
                    </table>    
                    <table border="1" width="100%">    
                        </tr>
                        <c:forEach var="row" varStatus="loop" items="${result.rows}"> 
                            <tr>
                                <td width="19%" class="tdc"><c:out value="${row.name}"/></td>
                                <td width="19%" class="tdc"><c:out value="${row.title}"/></td>
                                <td width="19%" class="tdc"><c:out value="${row.author_name}"/></td>
                                <td width="19%" class="tdc"><c:out value="${row.isbn}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
            </c:when>
            
            <c:otherwise>
                <c:redirect url="./index.jsp"></c:redirect>
            </c:otherwise>
        </c:choose>
    </body>
</html>
