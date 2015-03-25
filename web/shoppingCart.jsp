<%-- 
    Document   : bookResults
    Created on : Oct 3, 2014, 2:16:00 PM
    Author     : bin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Status of Your Order</title>
    </head>
    <c:set var="count" value="0"/>
    <body BGCOLOR="#FDF5E6">
        <h1 ALIGN="CENTER">Status of Your Order</h1>

        <form name="detailForm" action="mainServlet" method="POST">
            <TABLE BORDER=1 ALIGN="CENTER">
                <TR BGCOLOR="#FFAD00">
                    <TH>Item ID</th>
                    <TH>Description</th>
                    <TH>Unit Cost</th>
                    <TH>Number</th>
                    <TH>Total Cost</th>
                </tr>
                
                <c:forEach var="order" items="${cart}">
                    

                        <tr>

                            <td><c:out value="${order.getItemID()}"/></td>
                        <td><i><c:out value="${order.getName()}"/></i> by <c:out value="${order.getAuthor()}"/></td>


                        <td>$<c:out value="${order.getUnitCost()}"/></td> 

                        <td><input type="text" value="${fn:escapeXml(order.getNumItems())} " name="num${count}" /><input type="submit" value="Update Order" name="update${count}" /></td> 

                        <td><c:out value="${order.getTotalCost()}"/></td>
                        <td><input type="hidden" value="${fn:escapeXml(order.getItemID())} " name="id${count}" /></td>
                            <c:set var="count" value="${count +1}"/>

                    </tr>
                </c:forEach> 
            </table>
        </form>
    <center>
        <form name="detailForm" action="index.jsp" method="POST"style="display:inline;">
            <input type="submit" value="Main Page" name="main" />
        </form>

        <form name="detailForm" action="LookupServlet" method="POST" style="display:inline;">
            <input type="submit" value="Kids Book Page" name="kids" />
        </form>

        <form name="detailForm" action="LookupServlet" method="POST" style="display:inline;">
            <input type="submit" value="Technolgy Book Page" name="tech" />
        </form>

        <form name="detailForm" action="checkout.jsp" method="POST"style="display:inline;">
            <input type="submit" value="Check Out" name="checkout" />
        </form>

    </center>

</body>
</html>
