<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <r:require modules="app"/>
        <r:layoutResources/>
    </head>
    <body>

        <div class="pageBody">
            <div id="spinner" class="spinner" style="display:none;">
                <img src="${resource(dir:'images',file:'spinner.gif')}" alt="code:'spinner.alt',default:'Loading...')}" />
            </div>
            <div id="grailsLogo"><a href="http://grails.org"><img src="${resource(dir:'images',file:'grails_logo.png')}" alt="Grails" border="0" /></a></div>
            <sec:ifLoggedIn>
                <strong>Welcome <sec:username/></strong><br/><br/>
            </sec:ifLoggedIn>
            <g:layoutBody />
        </div>
        <r:layoutResources/>

    </body>
</html>
