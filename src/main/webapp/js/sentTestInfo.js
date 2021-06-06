function getQuestionAnswer(url) {
    let questions = document.getElementsByClassName("question");
    let questionsId = [];
    let test = {};
    let questionsID_answerID = [];


    test["id"] = document.getElementById("test").getAttribute("data-testId");

    for (let i = 0; i < questions.length; i++) {
        questionsId.push(questions.item(i).getAttribute("data-questionId"));
    }

    for (const questionId of questionsId) {
        let customObject = {};
        let radios = document.getElementsByName(questionId);
        let answersId = [];
        for (let i = 0, length = radios.length; i < length; i++) {
            if (radios.item(i).checked) {
                answersId.push(radios.item(i).getAttribute("data-answerId"))
            }
        }
        customObject["answer_Id"] = answersId;
        customObject["questions_Id"] = questionId;
        questionsID_answerID.push(customObject);
    }

    test["questionsId_answerId"] = questionsID_answerID;
    let json = JSON.stringify(test);
    console.log(json)
    $.ajax({
        url: url,
        contentType: 'application/json',
        type: 'POST',
        async: false,
        data: json,
        processData: false,
        success: function () {
            let newUrl = new URL(window.location.href);
            let urlSearchParams = newUrl.searchParams;
            urlSearchParams.set("command", "test_result_page");
            urlSearchParams.set("id", test.id);
            document.location.href = newUrl;
        },
        error: function (XMLHttpRequest) {
            if (XMLHttpRequest.status === 500) {
                let newUrl = new URL(window.location.href);
                let urlSearchParams = newUrl.searchParams;
                urlSearchParams.set("command", "error_page");
                urlSearchParams.set("id", test.id);
                document.location.href = newUrl;
            }
        }
    });
}