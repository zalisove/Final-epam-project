<html>

<%@ include file="/WEB-INF/jspf/head.jspf" %>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/css/error_500_style.css' />"/>
<link href="https://fonts.googleapis.com/css?family=Encode+Sans+Semi+Condensed:100,200,300,400" rel="stylesheet">
<body class="d-flex flex-column min-vh-100 loading">
    <h1>500</h1>
    <h2>Unexpected Error <b>:(</b></h2>
    <div class="gears">
        <div class="gear one">
            <div class="bar"></div>
            <div class="bar"></div>
            <div class="bar"></div>
        </div>
        <div class="gear two">
            <div class="bar"></div>
            <div class="bar"></div>
            <div class="bar"></div>
        </div>
        <div class="gear three">
            <div class="bar"></div>
            <div class="bar"></div>
            <div class="bar"></div>
        </div>
    </div>

<script src="<c:url value='/js/error_500_Script.js' />"></script>
</body>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</html>
