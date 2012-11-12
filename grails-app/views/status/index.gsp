<html>
<head>
    <meta name="layout" content="main"/>
    <title>What Are You Doing?</title>
    <r:require modules="jquery, grailsEvents"/>
    <r:script>
var currentUserId = <sec:loggedInUserInfo field="id"/>;
    var grailsEvents = new grails.Events("http://localhost:8080/grailstwitter/");
    grailsEvents.on('statusAdded', function(data) {
        console.log("Received message for user " + data.userId);
        if (data.userId == currentUserId) {
            $("#messages").prepend(data.content);
        }
    });
</r:script>
</head>
<body>
    <h1>Search For People To Follow</h1>
    <div class="searchForm">
        <g:form controller="searchable">
            <g:textField name="q" value=""/>
        </g:form>
    </div>

    <!--
    ${grailsApplication.mainContext.getBean('grailsResourceProcessor').dumpResources().encodeAsHTML()}
    -->

    <h1>What Are You Doing?</h1>
    <div class="updatStatusForm">
        <g:formRemote url="[action: 'updateStatus']" update="messages" name="updateStatusForm"
                      onSuccess="jQuery('#message').val('')">
            <g:textArea name="message" value=""/><br/>
            <g:submitButton name="Update Status"/>
        </g:formRemote>
    </div>
    <div id="messages">
        <g:render template="statusMessages" collection="${statusMessages}" var="statusMessage"/>
    </div>
</body>
</html>
