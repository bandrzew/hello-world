$(document).ready(function () {

    $.ajax({
        url: window.location.href + '/api/greetings',
    }).done(function (greeting) {
        $('p#greeting').text(greeting);
    }).fail(function (err) {
        console.log(err);
    });

});