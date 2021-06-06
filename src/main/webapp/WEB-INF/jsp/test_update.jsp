<html lang="uk">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<%@ page import="com.zalisove.db.Complexity" %>
<%@ page import="com.zalisove.db.QuestionType" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/css/customSelect2.css' />"/>

<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>


<c:set var="test" value="${requestScope.testBean.test}"/>
<c:url var="urlToDelete" value='${Path.COMMAND___DELETE_TEST}'>
    <c:param name="id" value="${test.id}"/>
</c:url>
<div>
    <div class="d-flex justify-content-center bg-body align-items-center ">
        <div class="container w-75 ">
            <div id="status"></div>
            <div class=" row container p-3 shadow m-2 rounded-3 fs-4 border-top border-dark border-4">
                <div>
                    <div class="container-fluid">

                    <textarea class="form-control mb-2"
                              placeholder="Test title"
                              id="testTitle"
                              maxlength="1024"
                    >${test.name}</textarea>


                        <div class="d-flex  mb-2">
                            <div class="d-flex border border-2 border-secondary rounded-3">
                                <input class="form-control"
                                       type="number"
                                       placeholder="minute"
                                       id="minute"
                                       min="0"
                                       oninput="checkNumberInput(this,2000)"
                                       value="<fmt:formatNumber value="${test.time/60}" groupingUsed="false"  pattern="#" maxFractionDigits="0"/>"/>
                                <input class="form-control"
                                       type="number"
                                       placeholder="second"
                                       id="second"
                                       oninput="checkNumberInput(this,60)"
                                       max="60"
                                       min="0"
                                       value="<fmt:formatNumber value="${test.time%60}" groupingUsed="false"  pattern="#" maxFractionDigits="0"/>"/>
                            </div>
                            <select class="form-select text-capitalize" id="complexity">
                                <option value="${Complexity.getComplexityId(Complexity.EASY)}"
                                        <c:if test="${Complexity.getComplexity(test).equals(Complexity.EASY)}"> selected </c:if>>
                                    ${Complexity.EASY.name().toLowerCase()}
                                </option>
                                <option value="${Complexity.getComplexityId(Complexity.NORMAL)}"
                                        <c:if test="${Complexity.getComplexity(test).equals(Complexity.NORMAL)}"> selected </c:if>
                                >${Complexity.NORMAL.name().toLowerCase()}
                                </option>
                                <option value="${Complexity.getComplexityId(Complexity.HARD)}"
                                        <c:if test="${Complexity.getComplexity(test).equals(Complexity.HARD)}"> selected </c:if>>
                                    ${Complexity.HARD.name().toLowerCase()}
                                </option>
                            </select>
                        </div>
                        <div class="mb-2">
                            <select class="select2 text-capitalize" id="subject">
                                <c:forEach items="${requestScope.subjects}" var="subject">
                                    <option value="${subject.id}" <c:if
                                            test="${test.subjectId == subject.id}"> selected</c:if>>
                                            ${subject.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="d-flex align-items-center justify-content-center">
                            <button class="btn btn-success form-control"
                                    onclick="parseTest('<c:url value='${Path.COMMAND___UPDATE_TEST}'/>')"> Save
                            </button>
                            <a class="btn btn-danger form-control" href="${urlToDelete}"> Delete
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <c:forEach items="${requestScope.testBean.questionBeans}" var="quesion">
                <c:choose>
                    <c:when test="${QuestionType.getQuestionType(quesion.question).equals(QuestionType.ONE_ANSWER)}">
                        <my:QuestionItem question="${quesion}" questionType="radio"/>
                    </c:when>
                    <c:when test="${QuestionType.getQuestionType(quesion.question).equals(QuestionType.MANY_ANSWER)}">
                        <my:QuestionItem question="${quesion}" questionType="checkbox"/>
                    </c:when>
                </c:choose>
            </c:forEach>

            <button class="btn btn-success form-control m-2" id="addQuestion"> Add question</button>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<script src="<c:url value='/js/updateTest.js' />"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</body>
</html>