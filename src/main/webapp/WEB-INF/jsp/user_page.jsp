<html lang="uk">
<%@ page import="com.zalisove.db.Complexity" %>
<%@ page import="com.zalisove.Path" %>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<div>
    <div class="d-flex justify-content-center bg-body align-items-center ">
        <div class="container w-75 ">
            <div class=" row container p-3 shadow m-2 rounded-3 fs-4 border-top border-dark border-4">
                <div class="container-fluid">
                    <p class="text"><fmt:message key="user_page.email_address"/> : ${requestScope.user.email}</p>
                    <p class="text"><fmt:message key="user_page.name"/>: ${requestScope.user.name}</p>
                    <p class="text"><fmt:message key="user_page.surname"/>: ${requestScope.user.surname}</p>
                    <div class="d-flex justify-content-center align-items-center">
                        <form action="controller" method="get">
                            <input type="hidden" name="command" value="${Path.COMMAND___CHANGE_USER_PAGE}">
                            <input type="hidden" name="id" value="${requestScope.user.id}">
                            <button class="btn btn-success"><fmt:message key="user_page.change_date"/></button>
                        </form>
                        <c:if test="${Role.getRole(sessionScope.user).equals(Role.ADMIN) && Role.getRole(requestScope.user).equals(Role.getRole(sessionScope.user)) == false}">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="${Path.COMMAND___DELETE_USER}">
                                <input type="hidden" name="id" value="${requestScope.user.id}">
                                <button type="submit" class="btn btn-danger text-capitalize"><fmt:message key="user_page.delete_user"/></button>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="container m-2 ">

                <div class="row justify-content-center row-cols-auto ">

                    <c:forEach var="test" items="${requestScope.testsAndMark}">
                        <c:if test="${not empty sessionScope.user}">
                            <c:if test="${Role.getRole(sessionScope.user).equals(Role.CLIENT)}">
                                <c:url var="testUrl" value="/controller">
                                    <c:param name="command" value="test_page"/>
                                    <c:param name="id" value="${test.key.id}"/>
                                </c:url>
                                <my:TestItem test="${test.key}"
                                             complexity="${Complexity.getComplexity(test.key).name().toLowerCase()}"
                                             testUrl="${testUrl}"
                                             mark="${test.value}"/>
                            </c:if>
                            <c:if test="${Role.getRole(sessionScope.user).equals(Role.ADMIN)}">
                                <c:url var="testUrl" value="/controller">
                                    <c:param name="command" value="update_test_page"/>
                                    <c:param name="id" value="${test.key.id}"/>
                                </c:url>
                                <my:TestItem test="${test.key}"
                                             complexity="${Complexity.getComplexity(test.key).name().toLowerCase()}"
                                             testUrl="${testUrl}"
                                             mark="${test.value}"/>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
