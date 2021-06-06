$('.select2').select2({
    tags: true,
    width: 'resolve'
});

$(document).on('click', '.deleted', function () {
    const cur = $(this);
    cur.parent().remove();
});


$(document).on('click', '#addQuestion', function () {
    $(this).before("<div class=\"row container p-3 shadow m-2 rounded-3 fs-4 border-top border-dark border-4 question\"  data-questionId = \""+(getLastQuestionId()+1)+"\">\n" +
        "            <button class=\"btn bg-light border border-1 shadow ms-auto deleted\"\n" +
        "                    style=\"width: 40px; height: 40px\">\n" +
        "                <i class=\"fa fa-trash\"></i>\n" +
        "            </button>\n" +
        "            <div class=\"d-flex align-text-top justify-content-between fs-5 mt-2 mb-2\">\n" +
        "                    <textarea\n" +
        "                            class=\"textarea form-control bg-light p-2 border-top-0 mb-2 border-start-0  border-end-0 border-4 border-primary questionName\"\n" +
        "                            placeholder=\"Question\"\n" +
        "                            id=\"question\"\n" +
        "                            maxlength=\"1024\"></textarea>\n" +
        "                <select class=\"form-select questionType\" style=\"height: 40px\">\n" +
        "                     <option value=\"1\">One answer</option>\n" +
        "                     <option value=\"2\">Many answer</option>" +
        "                </select>\n" +
        "            </div>\n" +
        "            <div class=\"input-group  mt-2 mb-2 addAnswer\">\n" +
        "                <div class=\"input-group-text\">\n" +
        "                    <input type=\"radio\" class=\"form-check-input mt-0 answerType \" disabled>\n" +
        "                </div>\n" +
        "                <input type=\"text\" class=\"form-control\" placeholder=\"New Answer\"/>\n" +
        "            </div>\n" +
        "        </div>");
});


$(document).on('change', '.questionType', function () {
    const typeId = $(this).val()
    let type;
        if (typeId === "1"){
            type = "radio"
        }else if (typeId === "2"){
            type = "checkbox"
        }
    const parent = $(this).closest(".question")
    const item = $(parent).find(".answerType");
    item.prop("type", type)
});





$("textarea").keydown(function (e) {
    if (e.keyCode === 13 && !e.shiftKey) {
        e.preventDefault();
    }
    this.style.overflow = 'hidden';
    this.style.height = 0;
    this.style.height = this.scrollHeight + 'px';
});

function getLastQuestionId(){
    const question = $(".question");
    if (question.length === 0){
        return 0;
    }
    const lastQuestion = question[question.length-1];
    const lastId = $(lastQuestion).attr('data-questionId');
    if (lastId){
        return Number.parseInt(lastId);
    }else {
        return 0;
    }
}
function getLastAnswerId(){
    const answers = $(".answer");
    if (answers.length === 0){
        return 0;
    }
    const lastAnswer = answers[answers.length-1];
    const lastId = $(lastAnswer).attr('data-answerId');
    if (lastId){
        return Number.parseInt(lastId);
    }else {
        return 0;
    }
}


function parseTest(url){
    const parentUrl = new URL(document.location.href)
    const parentSearchParams = parentUrl.searchParams
    const testId = parentSearchParams.get("id");
    const testName = $("#testTitle").val();
    const minute = $("#minute").val();
    const second = $("#second").val();
    const time = (Number.parseInt(minute) * 60) + Number.parseInt(second);
    const complexityId = $("#complexity").val();
    const subject = $("#subject")
    let subjectId;
    let subjectVal;
    const  isUserSubject = $("#subject option:selected").attr("data-select2-tag")
    if (isUserSubject){
        subjectVal = subject.val();
        subjectId = -1;
    }else subjectId = subject.val();

    const questionBeans = [];
    const questionsElement = $(".question")

    for (let i = 0; i < questionsElement.length; i++) {
        const questionId = $(questionsElement[i]).attr('data-questionId');
        const questionName = $(questionsElement[i]).find(".questionName").val()
        const questionType = $(questionsElement[i]).find(".questionType").val()
        const answers = []
        const answerElement = $(questionsElement[i]).find(".answer")

        for (let j = 0; j < answerElement.length; j++) {
            const answerId = $(answerElement[j]).attr('data-answerId');
            const trueAnswerElement = $(answerElement[j]).find(".answerType")
            const answerValueElement = $(answerElement[j]).find(".answerValue")
            const trueAnswer = $(trueAnswerElement).is(':checked');
            const answerValue = $(answerValueElement).val();
            answers.push(makeAnswer(answerId,questionId,answerValue,trueAnswer))
        }
        const questionBean = makeQuestionBean(makeQuestion(questionId,testId,questionName,questionType),answers);
        questionBeans.push(questionBean)
    }

    const test = makeTest(testId,testName,time,complexityId,subjectId)
    const testBean = makeTestBean(test,questionBeans)
    testBean["subjectVal"] = subjectVal;
    let json = JSON.stringify(testBean);
    console.log(json)
    $.ajax({
        url: url,
        contentType: 'application/json',
        type: 'POST',
        async: false,
        data: json,
        processData: false,
        success: function () {
            $("#status").replaceWith(" <div id=\"status\">\n" +
                "            <div class=\"form-control is-valid m-2 text-success text-center\">\n" +
                "                Test saved\n" +
                "            </div>\n" +
                "        </div>\n");
        },
        error: function (XMLHttpRequest) {
            if (XMLHttpRequest.status === 500) {
                $("#status").replaceWith("<div class=\"form-control is-invalid m-2 text-danger text-center\">\n" +
                    "                No saved\n" +
                    "            </div>")
            }
        }
    });
}

$(document).on('click', '.addAnswer', function () {
    const type = $(this).find(".answerType").attr('type');
    const questionId =  $(this).parent().attr('data-questionId');
    const newAnswer = $(" <div class=\"input-group  mt-2 mb-2 answer\" data-answerId=\""+(getLastAnswerId()+1)+"\">\n" +
        "                <div class=\"input-group-text\">\n" +
        "                    <input type=\"" + type + "\" class=\"form-check-input mt-0 answerType\" name=\""+questionId+"\">\n" +
        "                </div>\n" +
        "                <input class=\"form-control answerValue\" type=\"text\" placeholder='Answer' />\n" +
        "                 <button class=\"btn border border-1 ms-auto deleted\"\n" +
        "                        style=\"width: 40px; height: 40px\">\n" +
        "                    <i class=\"fa fa-times \"></i>\n" +
        "                </button>"+
        "            </div>")
    $(this).before(newAnswer);

    const answers = $(this).parent().find("input");
    answers[answers.length - 3].focus();
});




function makeQuestion(id,testId,name,questionType){
    return{
        id: id,
        testId:testId,
        name: name,
        questionTypeId:questionType,
    }
}
function makeTest(id,name,time,complexityId,subject){
    return{
        id: id,
        name: name,
        time: time,
        complexityId:complexityId,
        subjectId:subject,
    }
}
function makeQuestionBean(question,answers){
    return{
        question: question,
        answers:answers,
    }
}
function makeTestBean(test, questionBeans){
    return{
        test: test,
        questionBeans: questionBeans,
    }
}
function makeAnswer(id,questionId,name,trueAnswer){
    return{
        id: id,
        name: name,
        questionId:questionId,
        trueAnswer:trueAnswer
    }
}