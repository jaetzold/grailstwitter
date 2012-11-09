<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
    <g:set var="following" value="${currentUser?.followed?.collect { it.id } }"/>
    <g:each var="person" in="${searchResult?.results}">
    <div id="name">
        ${person.realName}
        <g:if test="${person.id in following}">
        (already following this person)
        </g:if>
        <g:else>
        <g:form style="display: inline-block;" action="follow" controller="status" method="POST">
            <input type="hidden" name="id" value="${person.id}"/>
            <g:submitButton name="submit" value="follow"/>
        </g:form>
        </g:else>
    </div>
    </g:each>
</body>
</html>
