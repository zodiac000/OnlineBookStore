<%-- 
    Document   : techPage
    Created on : Oct 7, 2014, 12:42:53 AM
    Author     : bin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tech Page</title>
    </head>
    <body BGCOLOR="#FDF5E6">
        <h1 ALIGN="CENTER">All-Time Best Computer Books</h1>
        <hr>
        <c:forEach var="book" items="${results}">

            <form name="backForm" action="mainServlet" method="POST">
                <input type="hidden" value="${fn:escapeXml(book.BOOK_ID)} " name="id" />


                <h2><i><c:out value="${book.SHORT}"/></i> by <c:out value="${book.AUTHOR}"/> ($<c:out value="${book.COST}"/>)</h2>  
                <br>
                <p><c:out value="${book.LONG}"/></p> 
                <br> 

                <input type="submit" value="Add to shopping cart" name="addCart" />
            </form>
            <hr>

        </c:forEach>   
        <form name="backForm" action="index.jsp" method="POST">
            <input type="submit" value="Back" name="back" />
        </form>

    </body>
</html>
