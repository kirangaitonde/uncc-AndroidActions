<%-- 
    Document   : catalog.jsp
    Created on : Feb 27, 2015, 10:59:44 PM
    Author     : K G
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/include/header.jsp" />

<jsp:include page="/include/site-navigation.jsp" />    

<section id="main"> 

    <div id="cat1">
        <form name="apiName" method="get" action="api" >
            <h2> Select API <br> 
                <select id="selectedAPI" name="selectedAPI" >
                    <option value=" "> </option>
                    <c:forEach var="apiNameList" items="${apiNameList}">
                        <option value="${apiNameList}">${apiNameList}</option>
                    </c:forEach>

                </select><br>
                <input type="submit" value="select"/></h2>
        </form>

    </div> 

    <div id="cat2">  
        <form name="apiActionName" method="get" action="api" >
            <h2> Select Action <br> <select id="selectedAPIAction" name="selectedAPIAction" >     
                    <option value=" "> </option>
                    <c:forEach var="ac" items="${apiActionNameList}">
                        <option value="${ac}">${ac}</option>
                    </c:forEach>
                </select><br>
                <input type="submit" value="select"/></h2>
        </form> 
        <h3>Description:</h3>
        <h4>${actionDescription} </h4>

    </div>

</section>

<jsp:include page="/include/footer.jsp" />