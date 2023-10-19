<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
    input, button {
        margin: 0.5rem auto !important;
    }
</style>
<form:form action="${pageContext.request.contextPath}/authUser" method="POST">
    <c:if test="${param.error != null}">
        <i>Sorry! Invalid username/password!</i>
    </c:if>
    <div class="row">
        <input type="text" name="username" placeholder="Username"/></div>
    <div class="row">
        <input type="password" name="password" placeholder="Password"/></div>
    <div class="row">
        <button class="primary" type="submit">Login</button>
    </div>
</form:form>
	
