<%-- 
    Document   : site-navigation
    Created on : Feb 28, 2015, 12:13:46 AM
    Author     : K G
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<aside id="navigator">
    <a href="<c:url value='/action' />">View By Action</a><br>
    <a href="<c:url value='/api' />">View By API</a><br>
    <a href="index.jsp">Diffrence</a><br>
    <a href="index.jsp">Intersection</a><br>
</aside> 