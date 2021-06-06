<html>
<%@ page import="com.zalisove.db.Complexity" %>
<%@ page import="com.zalisove.Path" %>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<div>
    <div class="container-fluid m-0 p-0 row">
        <div class="p-0 col-sm-2 list-group-flush">
            <p class="list-group-item list-group-item-info"><fmt:message key="index.subject"/></p>
            <c:forEach var="subject" items="${requestScope.subjects}">
                <c:url var="subjectUrl" value="">
                    <c:forEach items="${param}" var="entry">
                        <c:if test="${entry.key != 'subject' && entry.key != 'page'}">
                            <c:param name="${entry.key}" value="${entry.value}"/>
                        </c:if>
                    </c:forEach>
                    <c:if test="${requestScope.subjectId != subject.id}">
                        <c:param name="subject" value="${subject.id}"/>
                        <c:param name="page" value="1"/>
                    </c:if>
                </c:url>
                <c:choose>
                    <c:when test="${requestScope.subjectId == subject.id}">
                        <a href="${subjectUrl}"
                           class="list-group-item list-group-item-action text-capitalize active">${subject.name}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${subjectUrl}"
                           class="list-group-item list-group-item-action text-capitalize">${subject.name}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <div class="col">
            <nav class="navbar navbar-light bg-light shadow">
                <div class="container justify-content-start">
                    <span class="m-2"><fmt:message key="index.sort"/></span>
                    <label>
                        <select class="form-select ms-2 " onchange="getValue(this);">
                            <c:forEach var="item" items="${Path.sorted}">
                                <option value="${item.key}" ${item.key == requestScope.sortCommand ? 'selected="selected"' : ''}>
                                    <fmt:message key="${item.value}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </label>
                </div>
            </nav>
            <div class="container m-2 ">
                <div class="row justify-content-center row-cols-auto ">
                    <c:forEach var="test" items="${requestScope.tests}">
                        <c:if test="${empty sessionScope.user}">
                            <c:url var="testUrl" value="/controller">
                                <c:param name="command" value="test_page"/>
                                <c:param name="id" value="${test.id}"/>
                            </c:url>
                            <my:TestItem test="${test}"
                                         complexity="${Complexity.getComplexity(test).name().toLowerCase()}"
                                         testUrl="${testUrl}"/>
                        </c:if>
                        <c:if test="${not empty sessionScope.user}">
                            <c:if test="${Role.getRole(sessionScope.user).equals(Role.CLIENT)}">
                                <c:url var="testUrl" value="/controller">
                                    <c:param name="command" value="test_page"/>
                                    <c:param name="id" value="${test.id}"/>
                                </c:url>
                                <my:TestItem test="${test}"
                                             complexity="${Complexity.getComplexity(test).name().toLowerCase()}"
                                             testUrl="${testUrl}"/>
                            </c:if>
                            <c:if test="${Role.getRole(sessionScope.user).equals(Role.ADMIN)}">
                                <c:url var="testUrl" value="/controller">
                                    <c:param name="command" value="update_test_page"/>
                                    <c:param name="id" value="${test.id}"/>
                                </c:url>
                                <my:TestItem test="${test}"
                                             complexity="${Complexity.getComplexity(test).name().toLowerCase()}"
                                             testUrl="${testUrl}"/>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
            <nav class="d-flex justify-content-center">
                <ul class="pagination">
                    <c:forEach begin="1" end="${requestScope.pageCount}" step="1" var="index">
                        <c:url var="indexUrl" value="">
                            <c:forEach items="${param}" var="entry">
                                <c:if test="${entry.key != 'page'}">
                                    <c:param name="${entry.key}" value="${entry.value}"/>
                                </c:if>
                            </c:forEach>
                            <c:param name="page" value="${index}"/>
                        </c:url>
                        <li class="page-item"><a class="page-link" href="${indexUrl}">${index}</a></li>
                    </c:forEach>
                </ul>
            </nav>
        </div>
    </div>

</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
