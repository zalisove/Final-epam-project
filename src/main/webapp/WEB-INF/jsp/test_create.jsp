<html lang="uk">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<%@ page import="com.zalisove.db.Complexity" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/css/customSelect2.css' />"/>


<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/jspf/header.jspf" %>


<div class="d-flex justify-content-center bg-body align-items-center ">
    <div class="container w-75 ">
        <div id="status"></div>

        <div class="container row  p-3 shadow m-2 rounded-3 fs-4 border-top border-dark border-4">

            <div>
                <div class="container-fluid">

                    <textarea class="form-control mb-2" placeholder="Test title" id="testTitle" maxlength="1024"></textarea>

                    <div class="d-flex  mb-2">
                        <div class="d-flex border border-2 border-secondary rounded-3">
                            <input class="form-control" type="number" placeholder="minute" oninput="checkNumberInput(this,2000)"  id="minute" min="0"/>
                            <input class="form-control" type="number" placeholder="second" oninput="checkNumberInput(this,60)"  id="second" max="60"
                                   min="0"/>
                        </div>
                        <select class="form-select text-capitalize" id="complexity">
                            <option value="${Complexity.getComplexityId(Complexity.EASY)}">${Complexity.EASY.name().toLowerCase()}</option>
                            <option value="${Complexity.getComplexityId(Complexity.NORMAL)}">${Complexity.NORMAL.name().toLowerCase()}</option>
                            <option value="${Complexity.getComplexityId(Complexity.HARD)}">${Complexity.HARD.name().toLowerCase()}</option>
                        </select>
                    </div>
                    <div class="mb-2">
                        <select class="select2 text-capitalize" style="width: 100%" id="subject">
                            <c:forEach items="${requestScope.subjects}" var="subject">
                                <option value="${subject.id}">${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button class="btn btn-success form-control"
                            onclick="parseTest('<c:url value='${Path.COMMAND___CREATE_TEST}'/>')"> Save
                    </button>
                </div>
            </div>
        </div>
        <div class="row container p-3 shadow m-2 rounded-3 fs-4 border-top border-dark border-4 question"
             data-questionId="1">
            <button class="btn bg-light border border-1 shadow ms-auto deleted"
                    style="width: 40px; height: 40px">
                <i class="fa fa-trash"></i>
            </button>
            <div class="d-flex align-text-top justify-content-between fs-5 mt-2 mb-2">

                            <textarea
                                    class="form-control bg-light p-2 border-top-0 mb-2 border-start-0  border-end-0 border-4 border-primary questionName textarea"
                                    placeholder="Question"
                                    maxlength="1024"></textarea>

                <select class="form-select questionType">
                    <option value="1"><fmt:message key="test_crate.one_answer"/> </option>
                    <option value="2"><fmt:message key="test_crate.many_answer"/></option>
                </select>
            </div>
            <div class="input-group  mt-2 mb-2 addAnswer">
                <div class="input-group-text">
                    <input type="radio" class="form-check-input mt-0 answerType" disabled>
                </div>
                <input type="text" class="form-control" placeholder="New Answer"/>

            </div>
        </div>
        <button class="btn btn-success form-control m-2" id="addQuestion"> Add question</button>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<script src="<c:url value='/js/createTest.js' />"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</body>
</html>