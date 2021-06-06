<html lang="uk">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page import="com.zalisove.db.QuestionType" %>
<body onload="startTimer(${requestScope.test.test.time})" class="d-flex flex-column min-vh-100">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="position-relative">
    <div id="app"></div>
</div>
<div class="d-flex justify-content-center bg-body align-items-center ">
    <div class="container w-75 ">
        <div class="container shadow-lg p-3 m-auto mt-2 rounded-top fs-4 border-top border-dark border-4" id="test" data-testId = "${requestScope.test.test.id}">
            ${requestScope.test.test.name}
        </div>
        <c:forEach var="quesion" items="${requestScope.test.questionBeans}">
            <c:choose>
                <c:when test="${QuestionType.getQuestionType(quesion.question).equals(QuestionType.ONE_ANSWER)}">
                    <my:QuestionRadio question="${quesion}"/>
                </c:when>
                <c:when test="${QuestionType.getQuestionType(quesion.question).equals(QuestionType.MANY_ANSWER)}">
                    <my:QuestionCheckBox question="${quesion}"/>
                </c:when>
            </c:choose>
        </c:forEach>

        <button class="btn btn-primary mt-2 w-100" id="send" onclick="getQuestionAnswer('<c:url value='${Path.COMMAND___TEST}'/>')"><fmt:message key="passing_pest.send"/></button>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<script defer src= "<c:url value='/js/timerScript.js' />"></script>
<script defer src= "<c:url value='/js/sentTestInfo.js' />"></script>
</body>
</html>
