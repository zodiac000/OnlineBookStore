<%-- 
    Document   : add
    Created on : Oct 3, 2014, 3:29:50 PM
    Author     : bin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Client Lookup</title>
    </head>
    <body BGCOLOR=\"#FDF5E6\">
        <h1>Book List</h1>
        <form name="viewForm" action="LookupServlet" method="POST">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Author</th>
                        <th>Description</th>
                        <th>Cost</th>
                        <th>Category</th>
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
                        </tr>


                    </c:forEach>  
                </tbody>
            </table>                   
        </form>
        <h1>New Book</h1>
        <form name="addingForm" action="LookupServlet" method="POST">
            <table>
                <thead>
                    <tr>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Book id</td>
                        <td><input type="text" name="itemID" value="" /></td>
                    </tr>
                    <tr>
                        <td>Book name</td>
                        <td><input type="text" name="name" value="" /></td>
                    </tr>
                    <tr>
                        <td>Author</td>
                        <td><input type="text" name="author" value="" /></td>
                    </tr>
                    <tr>
                        <td>Description </td>
                        <td><input type="text" name="detail" value="" /> </td>
                    </tr>
                    <tr>
                        <td>Cost </td>
                        <td><input type="text" name="cost" value="" /> </td>
                    </tr>
                    <tr>
                        <td>Category </td>
                        <td><select name="category">
                                <option value="Kids">Kids Book</option>
                                <option value="Tech">Technology Book</option>
                            </select> </td>
                    </tr>

                </tbody>
            </table>
            <table>
                <thead>
                    <tr>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="submit" value="Add" name="add" /></td>
                        <td><input type="reset" value="Cancel" name="cancel" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
