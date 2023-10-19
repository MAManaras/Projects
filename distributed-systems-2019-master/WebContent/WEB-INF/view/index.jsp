<% if (request.getParameter("msg") != null) { %>
<h4><%= request.getParameter("msg") %>
</h4>
<% } %>

<sec:authorize access="hasAuthority('PERM_MANAGE_TERM')">
    <div class="row">
        <div class="card" style="max-width: 100%; border: 0">
            <c:choose>
                <c:when test="${term != null}">
                    There are currently ${term.applications.size()} applications submitted for the current term.
                    <form:form action="super/endTerm">
                        <input type="submit" value="End current term">
                    </form:form>
                </c:when>
                <c:otherwise>
                    <form:form action="super/startNewTerm">
                        <h3>Start new application term</h3>
                        Acceptance percentage: <input type="number" name="percentage" value="80">
                        <input type="submit" value="Start">
                    </form:form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</sec:authorize>

<div class="row">
    <sec:authorize access="hasAuthority('PERM_VIEW_ACCEPTED_APPLICATIONS')">
        <c:if test="${term == null}">
            <div class="card">
                <h3>End of term overview</h3>
                <a href="<c:url value="/super/acceptedApplications" />" class="button tertiary">View accepted
                    applications for the last
                    term</a>
            </div>
        </c:if>
    </sec:authorize>
    <sec:authorize access="hasAuthority('PERM_VERIFY_APPLICATIONS')">
        <c:if test="${term != null}">
            <div class="card">
                <h3>Application approvals</h3>
                <a href="<c:url value="/super/applicationNewQueue"/>" class="button tertiary">Approve submitted
                    applications</a>
            </div>
        </c:if>
    </sec:authorize>
    <sec:authorize access="hasAuthority('PERM_VERIFY_STUDENT')">
        <div class="card">
            <h3>Student account activations</h3>
            <a href="<c:url value="/super/activationQueue" />" class="button tertiary">Approve new student accounts</a>
        </div>
    </sec:authorize>
    <sec:authorize access="hasAuthority('PERM_EDIT_USERS')">
        <div class="card">
            <h3>Edit Users and Roles</h3>
            <a href="<c:url value="/admin/users" />/" class="button tertiary">Edit user accounts</a>
            <a href="<c:url value="/admin/roles" />/" class="button tertiary">Edit roles</a>
        </div>
    </sec:authorize>
</div>