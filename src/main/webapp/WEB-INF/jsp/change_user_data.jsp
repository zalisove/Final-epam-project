<html lang="uk">
<%@ page import="com.zalisove.Path" %>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<div>
    <div class="d-flex justify-content-center bg-body align-items-center ">
        <div class="container w-75 ">
            <div class=" row container p-3 shadow m-2 rounded-3 fs-4 border-top border-dark border-4">
                <form action="controller" method="post" class="container" id="changeDataForma">
                    <input type="hidden" name="command" value="${Path.COMMAND___UPDATE_USER_DATA}">
                    <input type="hidden" name="id" value="${requestScope.user.id}">


                    <div id="massageDiv"></div>
                    <c:if test="${requestScope.user.id == sessionScope.user.id}">
                        <div class="mt-2 mb-2">
                            <label for="inputEmail" class="form-label required"><fmt:message
                                    key="change_user_date.email_address"/></label>
                            <input type="email" name="email" class="form-control required" id="inputEmail"
                                   aria-describedby="emailHelp" required value="${requestScope.user.email}">
                        </div>

                        <div class="mt-2 mb-2">
                            <label for="inputName" class="form-label "><fmt:message
                                    key="change_user_date.name"/></label>
                            <input type="text" name="name" class="form-control" id="inputName"
                                   value="${requestScope.user.name}">
                        </div>

                        <div class="mt-2 mb-2">
                            <label for="inputSurname" class="form-label"><fmt:message
                                    key="change_user_date.surname"/></label>
                            <input type="text" name="surname" class="form-control" id="inputSurname"
                                   value="${requestScope.user.surname}">
                        </div>

                        <div class="mt-2 mb-2">
                            <label for="newPassword" class="form-label required"><fmt:message
                                    key="change_user_date.password"/></label>
                            <input type="password" name="password" class="form-control" oninput="checkPasswordAndConfirm()"
                                   id="newPassword"
                                   minlength="8" maxlength="20">
                        </div>

                        <div class="mt-2 mb-2">
                            <label for="confirmPassword" class="form-label required"><fmt:message
                                    key="change_user_date.confirm_password"/></label>
                            <input type="password" name="confirm" class="form-control" oninput="checkPasswordAndConfirm()"
                                   id="confirmPassword"
                                   minlength="8" maxlength="20">
                        </div>
                    </c:if>
                    <c:if test="${Role.getRole(sessionScope.user).equals(Role.ADMIN) && requestScope.user.id != sessionScope.user.id}">
                        <label class="m-2 p-2">${requestScope.user.email}</label>
                    </c:if>
                    <c:if test="${Role.getRole(sessionScope.user).equals(Role.ADMIN) && Role.getRole(requestScope.user).equals(Role.getRole(sessionScope.user)) == false}">
                        <select class="form-select questionType text-capitalize" name="role" style="height: 40px">

                            <c:if test="${Role.CLIENT.equals(Role.getRole(requestScope.user))}">
                                <option value="${Role.getRoleId(Role.ADMIN)}">${Role.ADMIN.name().toLowerCase()}</option>
                                <option selected
                                        value="${Role.getRoleId(Role.CLIENT)}">${Role.CLIENT.name().toLowerCase()}</option>
                            </c:if>

                            <c:if test="${Role.ADMIN.equals(Role.getRole(requestScope.user))}">
                                <option selected
                                        value="${Role.getRoleId(Role.ADMIN)}">${Role.ADMIN.name().toLowerCase()}</option>
                                <option value="${Role.getRoleId(Role.CLIENT)}">${Role.CLIENT.name().toLowerCase()}</option>
                            </c:if>
                        </select>

                        <div class="form-check form-switch">
                            <input class="form-check-input" type="checkbox" name="isBlocked" id="flexSwitchCheckDefault"
                                    <c:if test="${requestScope.user.blocked == true}">
                                        checked
                                    </c:if>/>
                            <label class="form-check-label" for="flexSwitchCheckDefault"><fmt:message
                                    key="change_user_date.blocked"/></label>
                        </div>
                    </c:if>
                    <c:if test="${Role.getRole(sessionScope.user).equals(Role.ADMIN)
                                    && requestScope.user.id == sessionScope.user.id
                                    || requestScope.user.id == sessionScope.user.id
                                    || Role.getRole(sessionScope.user).equals(Role.ADMIN)
                                    && Role.getRole(requestScope.user).equals(Role.CLIENT)}">

                        <input class="btn btn-success" type="button"
                               onclick="sendAjaxForm('changeDataForma','<c:url value="controller"/>','<c:url value="${Path.COMMAND__START_PAGE}"/>')"
                               value="<fmt:message key="change_user_date.change_data"/>">
                    </c:if>

                </form>

                <c:if test="${Role.getRole(sessionScope.user).equals(Role.ADMIN) && Role.getRole(requestScope.user).equals(Role.getRole(sessionScope.user)) == false}">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="${Path.COMMAND___DELETE_USER}">
                        <input type="hidden" name="id" value="${requestScope.user.id}">
                        <button type="submit" class="btn btn-danger text-capitalize"><fmt:message
                                key="change_user_date.delete_user"/></button>
                    </form>
                </c:if>

            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<script src="<c:url value='/js/changeUserData.js' />"></script>
</body>
</html>
