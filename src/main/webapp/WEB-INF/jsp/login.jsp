<html lang="uk">
<%@ page import="com.zalisove.Path" %>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<fmt:setLocale
        value="${not empty sessionScope.defaultLocale
        ? sessionScope.defaultLocale
        : applicationScope.locales.get(0)}"
/>
<fmt:setBundle basename="massage"/>
<body>
<div class="h-100 d-flex justify-content-center bg-body align-items-center">
    <div class="container w-50 shadow-lg p-0 m-auto rounded">
        <nav class="navbar navbar-dark bg-dark">
            <div class="container d-flex justify-content-center">
                <a class="navbar-brand" href="<c:url value='${Path.COMMAND__START_PAGE}'/>">
                    TestOnline
                </a>
            </div>
        </nav>
        <div class="container">
            <form action="controller" method="post" class="container">
                <input type="hidden" name="command" value="${Path.COMMAND__LOGIN}">
                <c:if test="${requestScope.error == true}">
                    <div class="form-control is-invalid mt-2 text-danger text-center">
                        <fmt:message key="login.incorrect_email_or_password."/>
                    </div>
                </c:if>
                <c:if test="${requestScope.isBlocked == true}">
                    <div class="form-control is-invalid mt-2 text-danger text-center">
                        <fmt:message key="login.admin_block_you"/>
                    </div>
                </c:if>

                <div class="mt-2 mb-2">
                    <label for="exampleInputEmail1" class="form-label">
                        <fmt:message key="login.email_address"/>
                    </label>
                    <input type="email" name="email" class="form-control" id="exampleInputEmail1"
                           aria-describedby="emailHelp" required>
                </div>
                <div class="mt-2 mb-2">
                    <label for="newPassword" class="form-label">
                        <fmt:message key="login.password"/>
                    </label>
                    <input type="password" name="password" class="form-control" id="newPassword"
                          maxlength="20" required>
                </div>
                <button type="submit" class="container btn btn-primary"><fmt:message key="login.login"/></button>

                <div class="container m-3 d-flex justify-content-center">
                    <p><fmt:message key="login.new_to_test_online"/>
                        <a href="<c:url value='${Path.COMMAND__REGISTRATION_PAGE}'/>">
                            <fmt:message key="login.create_an_account"/>
                        </a>
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
