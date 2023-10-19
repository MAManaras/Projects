<form:form>
    <fieldset>
        <legend>Application</legend>
        <p>Id: ${application.id}</p>

        <p>Submitted by: ${application.submitter.username} - ${application.submitter.fullName}</p>
        <p>Department: ${application.submitter.department.name}</p>
        <p>Submission Date: ${application.submissionDate}</p>

        Claims:
        <ul>
            <li>Student income: ${application.studentIncome}</li>
            <li>Family income: ${application.familyIncome}</li>
            <li>Number of studying siblings: ${application.studentSiblings}</li>

            <c:if test="${application.bothParentsUnemployed}">
                <li>Both parents are unemployed</li>
            </c:if>

            <c:if test="${application.commuting}">
                <li>The students permanent residence is in a different city</li>
            </c:if>
        </ul>
        <br/>
        <input type="hidden" name="id" value="${application.id}">
        <div class="button-group">
            <input type="submit" name="action" value="Approve" class="button primary">
            <input type="submit" name="action" value="Reject" class="button secondary">
        </div>
    </fieldset>
</form:form>