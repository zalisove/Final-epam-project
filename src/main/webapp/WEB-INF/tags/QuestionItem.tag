<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="question" type="com.zalisove.db.bean.QuestionBean" %>
<%@attribute name="questionType" %>

<div class="row container p-3 shadow m-2 rounded-3 fs-4 border-top border-dark border-4 question"
     data-questionId="${question.question.id}">
    <button class="btn bg-light border border-1 shadow ms-auto deleted"
            style="width: 40px; height: 40px">
        <i class="fa fa-trash"></i>
    </button>
    <div class="d-flex align-text-top justify-content-between fs-5 mt-2 mb-2">
                    <textarea
                            class="textarea form-control bg-light p-2 border-top-0 mb-2 border-start-0  border-end-0 border-4 border-primary questionName"
                            placeholder="Question"
                            id="question"
                            maxlength="1024">${question.question.name}</textarea>
        <select class="form-select questionType " style="height: 40px">

            <option value="1" <c:if test="${questionType.equals('radio')}">selected</c:if>>
                One answer
            </option>
            <option value="2" <c:if test="${questionType.equals('checkbox')}">selected</c:if>>
                Many answer
            </option>

        </select>
    </div>

    <c:forEach items="${question.answers}" var="answer">
        <div class="input-group  mt-2 mb-2 answer" data-answerId="${answer.id}">
            <div class="input-group-text">
                <input type="${questionType}" class="form-check-input mt-0 answerType" name="${question.question.id}"
                        <c:if test="${answer.trueAnswer == true}">
                            checked
                        </c:if>/>
            </div>
            <input class="form-control answerValue" type="text" placeholder='Answer' value="${answer.name}"/>
            <button class="btn border border-1 ms-auto deleted"
                    style="width: 40px; height: 40px">
                <i class="fa fa-times "></i>
            </button>
        </div>
    </c:forEach>


    <div class="input-group  mt-2 mb-2 addAnswer">
        <div class="input-group-text">
            <input type="${questionType}" class="form-check-input mt-0 answerType " disabled>
        </div>
        <input type="text" class="form-control" placeholder="New Answer"/>
    </div>
</div>

