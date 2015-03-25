<%-- 
    Document   : editPage
    Created on : Oct 7, 2014, 7:56:26 PM
    Author     : bin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit</title>
    </head>
    <body BGCOLOR="#FDF5E6">

        <c:set var="rid">${row.getItemID()}</c:set>
        <c:set var="rname">${row.getName()}</c:set>
        <c:set var="rauthor">${row.getAuthor()}</c:set>
        <c:set var="rlong">${row.getDetail()}</c:set>
        <c:set var="rcost">${row.getCost()}</c:set>
        <c:set var="rcategory">${row.getCategory()}</c:set>

        <h1>${rid}</h1>
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
                        <td>Book ID</td>
                        <td><input type="text" name="itemID" value="${rid}" readonly/></td>
                    </tr>
                    <tr>
                        <td>Book name</td>
                        <td><input type="text" name="name" value="${rname}" /></td>
                    </tr>
                    <tr>
                        <td>Author</td>
                        <td><input type="text" name="author" value="${rauthor}" /></td>
                    </tr>
                    <tr>
                        <td>Description </td>
                        <td><input type="text" name="detail" value="${rlong}" /> </td>
                    </tr>
                    <tr>
                        <td>Cost </td>
                        <td><input type="text" name="cost" value="${rcost}" /> </td>
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
                        <td><input type="submit" value="Save" name="save" /></td>
                        <td><input type="submit" value="Cancel" name="cancel" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>



</html>
