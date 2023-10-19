<fieldset>
    <legend>Current users:</legend>
    <ul>
        <c:forEach var="user" items="${users}">
            <li>${user.username} - ${user.fullName} <a href="<c:url value="/admin/users/${user.username}/edit" />"
                                                       class="button"
                                                       role="button">Edit</a>
                <a href="<c:url value="/admin/users/${user.username}/delete" />/" role="button"
                   class="button secondary">Delete</a>
            </li>
        </c:forEach>
    </ul>
    <form:form modelAttribute="newUser" action="new">
        <form:input path="username" placeholder="Username" required="true"/>
        <form:button name="submit">New User</form:button>
    </form:form>
</fieldset>
