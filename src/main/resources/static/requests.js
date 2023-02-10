$(document).ready(function () {

  $.ajax({
    url: window.location.href + 'api/greetings/1',
    contentType: 'application/json; charset=utf-8',
    dataType: 'json',
  }).done(function (greeting) {
    $('p#greeting').text(greeting.content);
  }).fail(function (err) {
    console.log(err);
  });

});