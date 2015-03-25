<%-- 
    Document   : bookList
    Created on : Oct 3, 2014, 3:08:06 PM
    Author     : bin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kids Page</title>
    </head>
    <body BGCOLOR="#FDF5E6">
        <h1 ALIGN="CENTER">All-Time Best Children's Fantasy Books</h1>
        <hr>
        <c:forEach var="book" items="${results}">

            <form name="backForm" action="mainServlet" method="POST">
                <c:set var="bid">${book.BOOK_ID}</c:set>
                <c:set var="bname">${book.SHORT}</c:set>
                <input type="hidden" value="${bid} " name="id" />


                <h2><i><c:out value="${bname}"/></i> by <c:out value="${book.AUTHOR}"/> ($<c:out value="${book.COST}"/>)</h2>  
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
