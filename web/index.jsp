<%-- 
    Document   : index.jsp
    Created on : Oct 2, 2014, 10:02:11 PM
    Author     : bin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All-Time Best Book Store</title>
    </head>
    <body BGCOLOR="#FDF5E6">
        <h1 ALIGN=CENTER>All-Time Best Book Store</h1>
        
        
        
        <form name="storeSelectForm" action="LookupServlet" method="POST">
            <center><input type="submit" value="Kids Page by JSP" name="kids" style="height:120px; width:350px"/></center>
        </form>
        
        <form name="storeSelectForm2" action="LookupServlet" method="POST">
            <center><input type="submit" value="Tech Page by JSP" name="tech" style="height:120px; width:350px"/></center>
        </form>
        
        <form name="storeSelectForm3" action="ViewList" method="POST">
            <center><input  type="submit" value="Book Management by JSP" name="bookManage" style="height:120px; width:350px; background-color:brown; color:black;"/></center>
        </form>
        
    </body>
</html>
