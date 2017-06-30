<%-- 
    Document   : eBooksStoreAdminEBooks
    Created on : Jun 16, 2017, 3:24:55 PM
    Author     : andra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eBooksStore Management</title>
        <link rel="stylesheet" type="text/css" href="./css/eBooksStoreCSS.css">
    </head>
    <body>
        <h1>Manage the books from Electronic Books Store</h1>
        <%-- test if actual user is authenticated and authorized --%>
    <c:choose>
        <c:when test="${validUser == true}">   
            <!-- include menu -->
            <%@ include file="./utils/eBooksStoreMenu.jsp" %>
            <form action="${pageContext.request.contextPath}/eBooksStoreAdminEBooks">
                <sql:setDataSource 
                    var="snapshot" 
                    driver="org.apache.derby.jdbc.ClientDriver"
                    url="jdbc:derby://localhost:1527/eBooksDB;create=true;"
                    user="andra"  
                    password="andra"/>
                <sql:query dataSource="${snapshot}" var="result">
                    SELECT  BOOKS.Title AS TITLE, authors.author_name, BOOKS.ISBN, BOOKS.PRICE, BOOKS. RATING, BOOKS.EBOOK_ID 
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
                <%-- Details --%> 
                <sql:setDataSource 
                    var="snapshotnames" 
                    driver="org.apache.derby.jdbc.ClientDriver"
                    url="jdbc:derby://localhost:1527/eBooksDB;create=true;"
                    user="andra"  
                    password="andra"/>
                <sql:query dataSource="${snapshotnames}" var="resultnames">
                    SELECT AUTHOR_NAME from AUTHORS ORDER BY AUTHOR_NAME ASC 
                </sql:query>
                <table class="tablecenterdwithborder">
                    <tr><td>    
                            <table>
                                <tr>
                                    <td> Title </td>
                                    <td> <input type="text" name="ebook_title"></input></td>
                                </tr>
                                <tr>
                                    <td> Author </td>
                                    <td> <input type="text" name="ebook_author"></input></td>
                                </tr>
                                <tr>
                                    <td> ISBN </td>
                                    <td> <input type="text" name="ebook_isbn"></input></td>
                                </tr>
                                <tr>
                                    <td> Price </td>
                                    <td> <input type="text" name="ebook_price"></input></td>
                                </tr>
                                <tr>
                                    <td> Rating </td>
                                    <td> <input type="text" name="ebook_rating"></input></td>
                                </tr>
                                
                            </table>
                            <%-- buttons --%>
                            <table>

                                <tr><td class="tdc"><input type="submit" class="ebooksstorebutton" name="ebook_insert" value="Insert"></td> 
                                    <td class="tdc"><input type="submit" class="ebooksstorebutton" name="ebook_update" value="Update"></td>
                                    <td class="tdc"><input type="submit" class="ebooksstorebutton" name="ebook_delete" value="Delete"></td> 
                                    <td class="tdc"><input type="submit" class="ebooksstorebutton" name="ebook_cancel" value="Cancel"></td>
                                </tr>  
                            </table>
                        </td></tr>
                </table>    
            </form>
        </c:when>
        <c:otherwise>
            <c:redirect url="./index.jsp"></c:redirect>
        </c:otherwise>
    </c:choose>



</body>
</html>
