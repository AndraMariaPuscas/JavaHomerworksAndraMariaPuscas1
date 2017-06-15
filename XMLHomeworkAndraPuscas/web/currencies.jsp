<%-- 
    Document   : currencies
    Created on : May 30, 2017, 9:11:17 PM
    Author     : andra
--%>
<%--
<%@page import="org.w3c.dom.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%@page import="javax.xml.parsers.*"%>
<%
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    String fileName = "/home/andra//NetBeansProjects2/JavaHomeworksAndraMariaPuscas/XMLHomeworkAndraPuscas/web/currencyDenominations.xml";
    Document doc = docBuilder.parse(fileName);
    //Element element = doc.getDocumentElement();

    NodeList n1 = doc.getElementsByTagName("RON");
    NodeList n2 = doc.getElementsByTagName("EUR");  
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Currency Denominations</title>
    </head>
    <body>  
        <table>
            <tr>
                <td>RON</td>
                <td>EUR</td>
            </tr>
            <tr>
                <%for (int i = 0; i < n1.getLength(); i++) {
                %>
                <td><%out.print(n1.item(i).getTextContent()); %></td>
                <td><%=n2.item(i).getFirstChild().getNodeValue() %></td>
                
            </tr> 
            <% }
            %>
        </table>
        
    </body>
</html>--%>
<%--//out.print("" + n1.item(i).getTextContent());
<%!
            public boolean isTextNode(Node n) {
                return n.getNodeName().equals("#text");
            }%> 

<td><%=n2.item(i).getFirstChild().getNodeValue() %></td>
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Currency Denominations</title>
    </head>
    <body>  
        <c:import var = "xmlFile" url = "currencyDenominations.xml"/>
        <x:parse xml = "${xmlFile}" var = "output"/>
        <table border style="solid">

            <tr>
                <td>RON</td>

                <x:forEach select="$output/root/RON/moneda0" var = "moneda0">
                    <td>
                        <x:out select = "$moneda0" />
                    </td>
                </x:forEach>


            </tr> 

            <tr>
                <td>EUR</td>

                <x:forEach select="$output/root/EUR/moneda1" var = "moneda1">
                    <td>
                        <x:out select = "$moneda1" />
                    </td>
                </x:forEach>

            </tr> 

            <tr>
                <td>USD</td>

                <x:forEach select="$output/root/USD/moneda2" var = "moneda2">
                    <td>
                        <x:out select = "$moneda2" />
                    </td>
                </x:forEach>

            </tr> 
        </table>

    </body>
</html>