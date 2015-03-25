<%-- 
    Document   : bookQuerry
    Created on : Oct 3, 2014, 3:13:36 PM
    Author     : bin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <c:set var="count" value="0"/>
    <head>
        <title>Book Management</title>
    </head>
    <body BGCOLOR="#FDF5E6">
        <h1>Book Management</h1>
        <form name="userForm" action="LookupServlet" method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Author</th>
                        <th>Description</th>
                        <th>Cost</th>
                        <th>Category</th>
                        <th colspan="2">Operations</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${results}">

                        <tr>
                            <td><c:out value="${book.BOOK_ID}"/></td>
                            <td><c:out value="${book.SHORT}"/></td>
                            <td><c:out value="${book.AUTHOR}"/></td>
                            <td><c:out value="${book.LONG}"/></td> 
                            <td><c:out value="${book.COST}"/></td> 
                            <td><c:out value="${book.CATEGORY}"/></td> 
                            <td><input style="background-color:olivedrab" type="submit" value="Edit" name="edit${count}" /></td>
                            <td><input  style="background-color:red" type="submit" value="Delete" name="delete${count}" /></td>
                            <td><input type="hidden" value="${fn:escapeXml(book.BOOK_ID)}" name="id${count}"</td>
                                <c:set var="count" value="${count +1}"/>
                    
                    </tr>


                </c:forEach>  
                </tbody>
            </table>                   
        </form>
        <span style="float:left; width:100%;" >
            <form name="addForm" action="addServlet" method="POST">
                <input style="background-color:tan; height:50px; width:100%" type="submit" value="Add" name="add" />
            </form>
        </span>
        <span style=" float:right;width: 100%;">
            <form name="doneForm" action="index.jsp" method="POST">
                <input style="background-color:springgreen;height:50px; width:100%" type="submit" value="Done" name="done" />
            </form>
        </span>
    </body>
</html>

