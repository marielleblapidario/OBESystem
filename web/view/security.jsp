<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>     
    <c:when test="${user.posID eq 1}">
         <%@include file ="navbar.jsp" %>
      </c:when>
    <c:when test="${user.posID eq 2}">
        <%@include file ="navbar_faculty.jsp" %>
      </c:when>
    <c:when test="${user == null}">
       <c:redirect url="login.jsp"/>
    </c:when>
</c:choose>
