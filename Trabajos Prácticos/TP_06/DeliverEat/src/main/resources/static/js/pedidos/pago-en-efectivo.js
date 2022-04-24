$(function() {

   /**
   * Copiamos lo ingresado al DNI en Numero Central del CUIL 
   */
    $('#monto-nn-efectivo').keyup(function () {
      var value = $(this).val();
      if (value - 122 > 0) {
        $('#id-vuelto').val(value - 122);
      }
      else {
        $('#id-vuelto').val("...");
      }
    });


});