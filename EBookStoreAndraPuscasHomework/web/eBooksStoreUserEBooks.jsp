<%-- 
    Document   : eBooksStoreUserEBooks
    Created on : Jun 24, 2017, 12:43:29 PM
    Author     : andra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eBooks</title>
        <link rel="stylesheet" type="text/css" href="./css/eBooksStoreCSS.css">
    </head>
    <body>

        <%-- test if actual user is authenticated and authorized --%>
        <c:choose>
            <c:when test="${validUser == true}">   
                <!-- include menu -->
                <%@ include file="./utils/eBooksStoreMenu.jsp" %>
                <form action="${pageContext.request.contextPath}/eBooksStoreUserEBooks">

                    <sql:setDataSource 
                        var="snapshot" 
                        driver="org.apache.derby.jdbc.ClientDriver"
                        url="jdbc:derby://localhost:1527/eBooksDB;create=true;"
                        user="andra"  
                        password="andra"/>
                    <sql:query dataSource="${snapshot}" var="result">
                        SELECT  BOOKS.Title AS TITLE, authors.author_name, BOOKS.ISBN, BOOKS.PRICE, BOOKS. RATING 
                        FROM BOOKS 
                        INNER JOIN EBOOKS_AUTHORS on books.ISBN = EBOOKS_AUTHORS.ISBN
                        INNER JOIN AUTHORS ON AUTHORS.ID_AUTHOR=EBOOKS_AUTHORS.ID_AUTHOR 

                    </sql:query>
                    <table border="1" width="100%">
                        <tr>
                            <td width="5%" class="thc">  </td>
                            <td width="19%" class="thc"> Title </td>    
                            <td width="19%" class="thc">Author</td>
                            <td width="19%" class="thc">ISBN</td>
                            <td width="19%" class="thc">Price</td>
                            <td width="19%" class="thc">Rating</td>

                    </table>    
                    <table border="1" width="100%">    
                        </tr>
                        <c:forEach var="row" varStatus="loop" items="${result.rows}"> 
                            <tr>
                                <td width="5%" class="tdc"><input type="checkbox" name="ebooks_checkbox" value="${row.ebook_id}"></td>
                                <td width="19%" class="tdc"><c:out value="${row.title}"/></td>
                                <td width="19%" class="tdc"><c:out value="${row.author_name}"/></td>
                                <td width="19%" class="tdc"><c:out value="${row.isbn}"/></td>
                                <td width="19%" class="tdc"><c:out value="${row.price}"/></td>
                                <td width="19%" class="tdc"><c:out value="${row.rating}"/></td>

                            </tr>
                        </c:forEach>
                    </table>
        </table>   
        <%--button for ordering the books--%>
        <input type="submit" class="ebooksstorebutton" name="user_ebooks_order" value="Order">
    </form>
</c:when>
<c:otherwise>
    <c:redirect url="./index.jsp"></c:redirect>
</c:otherwise>
</c:choose>

</form>

</body>
</html>
