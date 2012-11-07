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
        <g:link id="${person.id}" action="follow" controller="status">follow</g:link>
        </g:else>
    </div>
    </g:each>
</body>
</html>
