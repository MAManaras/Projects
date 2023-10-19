<form:form>
    <fieldset>
        <legend>Account</legend>
        Name: ${user.username} <br/>
        Full name: ${user.fullName} <br/>
        Phone number: ${user.phone} <br/>
        Department: ${user.department.name} <br/>

        <input type="hidden" name="username" value="${user.username}"/> <br/>
        <div class="button-group" style="border: none;">
            <input type="submit" name="action" value="Activate" class="button primary"/>
            <input type="submit" name="action" value="Reject" class="button secondary"/>
        </div>
    </fieldset>
</form:form>
