function getValue(p) {
    const url = new URL(window.location.href)
    const search_params = url.searchParams;
    if (p.value != null) {
        search_params.set("sort", p.value);
    }else {
        search_params.delete("sort");
    }
    console.log(url)
    document.location.href = url;
}

function getValueSelect() {
    $("#localeSubmit").click();
}


function checkPasswordAndConfirm() {
    const password = document.querySelector('input[name=password]');
    const confirm = document.querySelector('input[name=confirm]');
    if (confirm.value !== password.value) {
        confirm.setCustomValidity('Passwords do not match');
    }else {
        confirm.setCustomValidity('');
    }
}


function checkNumberInput(input,number){
    const inputVal = $(input).val();
    if (inputVal > number){
        $(input).val(number);
    }
}



