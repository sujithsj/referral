$(document).ready(function() {

  DS.Ajax.getJson("/api/nf/latest", function(response) {

    $.each(response.data, function(index, value) {
      $.gritter.add({
        title:  value.type,
        text:  value.msg,
        sticky: false
      });
    });
  });
  
});