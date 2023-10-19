<h2>
    <div style="text-align: center"> List of accepted applications</div>
</h2>
<div class="row">
    <table border="1">
        <tr>
            <th style="flex-grow: 0; padding-left: 2em; padding-right: 2.25em;"></th>
            <th>Username</th>
            <th>Name</th>
        </tr>
        <c:forEach var="application" items="${applications}" varStatus="loop">
            <tr>
                <td style="padding-left: 2em; padding-right: 2.25em; flex-grow: 0;">${loop.index + 1}</td>
                <td>${application.submitter.username}</td>
                <td>${application.submitter.fullName}</td>
            </tr>
        </c:forEach>
    </table>
</div>