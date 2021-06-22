<html lang="uk">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<div>
    <div class="container w-75 ">
        <div class=" row container p-3 shadow m-2 rounded-3 fs-4 border-top border-dark border-4">
            <div class="container-fluid">
                <div class="container m-2 ">


                    <form action="controller" method="post">

                        <input type="hidden" name="command" value="get_top_users">

                        <div class="mb-3">
                            <label for="from" class="form-label">from</label>
                            <input type="date" class="form-control" name="from" id="from" aria-describedby="emailHelp" required>
                        </div>
                        <div class="mb-3">
                            <label for="to" class="form-label">to</label>
                            <input type="date" class="form-control" name="to" id="to" required>
                        </div>

                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>


                    <c:forEach items="${requestScope.top_user}" var="user">

                        <c:url var="userChangeUrl" value="/controller">
                            <c:param name="command" value="user_page"/>
                            <c:param name="id" value="${user.key.id}"/>
                        </c:url>

                        <a class="text-decoration-none link-dark " href="${userChangeUrl}">
                            <div class="row shadow-sm m-2 p-2 border border-2 border-dark">
                                <div class="col-lg">${user.key.email}</div>
                                <div class="col-sm text-capitalize ">${Role.getRole(user.key).name().toLowerCase()}</div>
                                <div class="col-sm ">${user.value}</div>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
