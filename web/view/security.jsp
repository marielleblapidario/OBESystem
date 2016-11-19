<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>     
    <c:when test="${login.posID eq 1}">
         <%@include file ="navbar.jsp" %>
      </c:when>
    <c:when test="${login.posID eq 2}">
        <%@include file ="navbar_faculty.jsp" %>
      </c:when>
    <c:when test="${login.posID eq 4}">
        <%@include file ="navbar_faculty.jsp" %>
      </c:when>
    <c:when test="${login == null}">
       <c:redirect url="login.jsp"/>
    </c:when>
    <c:otherwise>
        <%@include file ="navbar_program.jsp" %>
      </c:otherwise>
</c:choose>
