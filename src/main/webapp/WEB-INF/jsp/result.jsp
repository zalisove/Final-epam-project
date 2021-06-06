<html lang="uk">
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body class="d-flex flex-column min-vh-100">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="position-relative">
    <div id="app"></div>
</div>
<div class="d-flex justify-content-center bg-body align-items-center ">
    <div class="container w-75 ">

        <div class="shadow p-0 m-2 rounded-3 fs-4 border-top border-dark border-4">

            <div class="bg-dark text-white p-2 text-capitalize">
                <fmt:message key="result.subject"/> : ${requestScope.subject.name}
            </div>
            <div class="p-2 text-capitalize">
                <fmt:message key="result.test_name"/> : ${requestScope.test.name}
            </div>
            <div class="fs-4 p-2 mt-4 text-capitalize d-flex justify-content-start">
                <fmt:message key="result.your_mark"/> :
            <c:choose>
                <c:when test="${requestScope.userTest.mark < 50}">
                   <p class="text-danger">${requestScope.userTest.mark}</p>
                </c:when>
                <c:when test="${requestScope.userTest.mark >= 50 && requestScope.userTest.mark < 80}">
                    <p class="text-warning">${requestScope.userTest.mark}</p>
                </c:when>
                <c:when test="${requestScope.userTest.mark > 80}">
                    <p class="text-success">${requestScope.userTest.mark}</p>
                </c:when>
            </c:choose>
            </div>

        </div>

    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>