$('#elegir-hora').click(function () {
    //check if checkbox is checked
    if ($(this).is(':checked')) {

        $('#datePickerId').removeAttr('disabled'); //enable input
        $('#timePickerId').removeAttr('disabled'); //enable input

    }
});

$('#lo-antes-posible').click(function () {
    //check if checkbox is checked
    if ($(this).is(':checked')) {

        $('#datePickerId').attr('disabled', true); //disable input
        $('#timePickerId').attr('disabled', true); //disable input

    }
});

Date.prototype.addDays = function(days) {
    var date = new Date(this.valueOf());
    date.setDate(date.getDate() + days);
    return date;
}


datePickerId.max = new Date().addDays(7).toISOString().slice(0, -14);
datePickerId.min = new Date().toISOString().slice(0, -14);


