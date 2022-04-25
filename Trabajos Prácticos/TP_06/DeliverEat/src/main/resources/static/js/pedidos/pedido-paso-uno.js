$('#elegir-hora').click(function () {
    //check if checkbox is checked
    if ($(this).is(':checked')) {

        $('#time-picker').removeAttr('disabled'); //enable input

    }
});

$('#lo-antes-posible').click(function () {
    //check if checkbox is checked
    if ($(this).is(':checked')) {

        $('#time-picker').attr('disabled', true); //disable input

    }
});