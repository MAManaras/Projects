<form:form modelAttribute="user">
    <fieldset>
        <legend>Editing User ${user.username}</legend>
        <div class="row responsive-label">
            <div class="col-sm-12 col-md-3"><label for="username">Username:</label></div>
            <div class="col-sm-12 col-md"><form:input path="username" id="username" readonly="true"/></div>
        </div>
        <div class="row responsive-label">
            <div class="col-sm-12 col-md-3"><label for="fullName">Full Name:</label></div>
            <div class="col-sm-12 col-md"><form:input path="fullName" id="fullName"/></div>
        </div>
        <div class="row responsive-label">
            <div class="col-sm-12 col-md-3"><label for="phone">Phone number:</label></div>
            <div class="col-sm-12 col-md"><form:input path="phone" id="phone"/></div>
        </div>
        <div class="row responsive-label">
            <div class="col-sm-12 col-md-3"><label for="password">Password:</label></div>
            <div class="col-sm-12 col-md"><input type="text" name="password" value="" id="password"
                                                 placeholder="Unchanged"/></div>
        </div>
        <div class="row responsive-label">
            <div class="col-sm-12 col-md-3"><label for="department">Department:</label></div>
            <div class="col-sm-12 col-md"><form:select path="department" id="department" items="${departments}"
                                                       itemValue="id" itemLabel="name"/></div>
        </div>
        <div class="row responsive-label">
            <div class="col-sm-12 col-md-3"><label for="role">Role:</label></div>
            <div class="col-sm-12 col-md"><form:select path="role" id="role" items="${roles}" itemValue="name"
                                                       itemLabel="friendlyName"/></div>
        </div>
        <div class="row responsive-label">
            <div class="button-group col-sm-12 col-md">
                <input type="submit" name="action" value="Save" class="button primary">
                <a href="../" class="button">Back</a>
            </div>
        </div>
    </fieldset>
</form:form>
