<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="question" type="com.zalisove.db.bean.QuestionBean" %>

<div class="container shadow-lg p-3 m-auto mt-2 rounded-top border-top border-dark border-4">
    <div class="fs-5 mt-2 mb-2 question" data-questionId = "${question.question.id}">${question.question.name}</div>
    <c:forEach var="answer" items="${question.answers}">
        <div class="input-group m-2">
            <div class="input-group-text">
                <input class="form-check-input mt-0 answer"
                       data-answerId = "${answer.id}"
                       type="checkbox"
                       name="${question.question.id}">
            </div>
            <div class="form-control">${answer.name}</div>
        </div>
    </c:forEach>
</div>