<fieldset>
    <form:form modelAttribute="role">
        <legend>Editing ${role.friendlyName} role</legend>
        <div class="row responsive-label">
            <div class="col-sm-12 col-md-3"><label for="name">Name:</label></div>
            <div class="col-sm-12 col-md"><form:input path="name" id="name" readonly="true"/></div>
        </div>
        <div class="row responsive-label">
            <div class="col-sm-12 col-md-3"><label for="friendlyName">Friendly Name:</label></div>
            <div class="col-sm-12 col-md"><form:input path="friendlyName" id="friendlyName"/></div>
        </div>
        <div class="row responsive-label">
            <div class="col-sm-12 col-md-3"><label for="description">Description:</label></div>
            <div class="col-sm-12 col-md"><form:input path="description" id="description"/></div>
        </div>

        <fieldset>
            <legend>Permissions</legend>
            <form:checkboxes path="permissions" items="${permissions}" itemLabel="description" itemValue="name"
                             delimiter=" "/>
        </fieldset>
        <div class="button-group">
            <input type="submit" name="action" value="Save" class="button primary">
            <a href="<c:url value="/admin/roles" />/" class="button">Back</a>
        </div>
    </form:form>
</fieldset>