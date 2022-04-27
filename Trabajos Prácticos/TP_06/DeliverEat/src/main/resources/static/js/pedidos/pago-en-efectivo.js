$(function() {

   /**
   * Copiamos lo ingresado al DNI en Numero Central del CUIL 
   */
    $('#id-vuelto').prop('disabled', true);
    $('#monto-nn-efectivo').keyup(function () {
      var value = $(this).val();
      if (value - 1400 > 0) {
        $('#id-vuelto').val(value - 1400);
      }
      else {
        $('#id-vuelto').val("...");
      }
    });


});