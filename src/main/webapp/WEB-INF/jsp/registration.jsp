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
                <input type="hidden" name="command" value="${Path.COMMAND___REGISTRATION}">

                <c:if test="${requestScope.error == true}">
                    <div class="form-control is-invalid mt-2 text-danger text-center">
                        <fmt:message key="registration.such_email_already_exists"/>
                    </div>
                </c:if>

                <div class="mt-2 mb-2">
                    <label for="inputEmail" class="form-label required"><fmt:message key="registration.email_address"/></label>
                    <input type="email" name="email" class="form-control required" id="inputEmail"
                           aria-describedby="emailHelp" required>
                </div>

                <div class="mt-2 mb-2">
                    <label for="inputName" class="form-label "><fmt:message key="registration.name"/></label>
                    <input type="text" name="name" class="form-control" id="inputName">
                </div>

                <div class="mt-2 mb-2">
                    <label for="inputSurname" class="form-label"><fmt:message key="registration.surname"/></label>
                    <input type="text" name="surname" class="form-control" id="inputSurname">
                </div>

                <div class="mt-2 mb-2">
                    <label for="newPassword" class="form-label required"><fmt:message key="registration.password"/></label>
                    <input type="password" name="password" class="form-control" oninput="checkPasswordAndConfirm()" id="newPassword"
                           minlength="8" maxlength="20" required>
                </div>

                <div class="mt-2 mb-2">
                    <label for="confirmPassword" class="form-label required"><fmt:message key="registration.confirm_password"/></label>
                    <input type="password" name="confirm" class="form-control" oninput="checkPasswordAndConfirm()" id="confirmPassword"
                           minlength="8" maxlength="20" required>
                </div>
                <button type="submit" class="container btn btn-primary"><fmt:message key="registration.registration"/></button>

                <div class="container m-3 d-flex justify-content-center">
                    <p><fmt:message key="registration.have_account_in_TestOnline"/>
                        <a href="<c:url value='${Path.COMMAND__LOGIN_PAGE}'/>">
                            <fmt:message key="registration.login"/>
                        </a>
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
