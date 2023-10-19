<fieldset>
    <legend>Current roles:</legend>
    <ul>
        <c:forEach var="role" items="${roles}">
            <li>${role.friendlyName} <a href="<c:url value="/admin/roles/${role.name}/edit" />" class="button"
                                        role="button">Edit</a>
                <c:if test="${role.users.size() == 0}">
                    <a href="<c:url value="/admin/roles/${role.name}/delete" />" role="button" class="button secondary">Delete</a>
                </c:if>
                <c:if test="${role.users.size() != 0}">
                    <a href="" role="button" class="button" style="background-color: #550000"
                       title="Cannot delete a role when there are users assigned to it">Delete</a>
                </c:if>
            </li>
        </c:forEach>
    </ul>
    <form:form modelAttribute="newRole" action="new">
        <form:input path="friendlyName" placeholder="Name" required="true"/>
        <form:button name="submit">New Role</form:button>
    </form:form>
</fieldset>
