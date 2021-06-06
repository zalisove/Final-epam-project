function sendAjaxForm(ajax_form, url,redirectUrl) {
    $.ajax({
        url: url,
        type: "POST",
        dataType: "html",
        data: $("#" + ajax_form).serialize(),
        success: function () {
            window.location.reload();
        },
        error: function (response) {
            if (response.status === 400) {
                $("#massageDiv").replaceWith("<div class=\"form-control is-invalid mt-2 text-danger text-center\" id=\"massageDiv\">This email already exist</div>\n");
            }
            if (response.status === 401) {
                window.location.href = redirectUrl;
            }
        }
    });
}