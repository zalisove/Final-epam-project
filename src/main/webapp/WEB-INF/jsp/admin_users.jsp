<html lang="uk">
<%@ page import="com.zalisove.db.Role" %>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<div>
    <div class="d-flex justify-content-center bg-body align-items-center ">
        <div class="container w-75 ">
            <div class=" row container p-3 shadow m-2 rounded-3 fs-4 border-top border-dark border-4">
                <div class="container-fluid">
                    <div class="container m-2 ">

                        <div class="text-decoration-none link-dark ">
                            <div class="row shadow-sm m-2 p-2 border border-2 border-dark">
                                <div class="col-lg"><fmt:message key="admin_users.email"/> </div>
                                <div class="col-sm "><fmt:message key="admin_users.role"/></div>
                                <div class="col-sm "><fmt:message key="admin_users.blocked"/></div>
                            </div>
                        </div>
                        <c:forEach items="${requestScope.users}" var="user">

                            <c:url var="userChangeUrl" value="/controller">
                              <c:param name="command" value="user_page"/>
                              <c:param name="id" value="${user.id}"/>
                            </c:url>

                          <a class="text-decoration-none link-dark " href="${userChangeUrl}">
                            <div class="row shadow-sm m-2 p-2 border border-2 border-dark">
                                <div class="col-lg">${user.email}</div>
                                <div class="col-sm text-capitalize ">${Role.getRole(user).name().toLowerCase()}</div>
                                <div class="col-sm ">${user.blocked}</div>
                            </div>
                          </a>
                        </c:forEach>

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
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
