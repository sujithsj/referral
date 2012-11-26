$(document).ready(function() {

  $("#commStSel").change(function() {
    var type = $(this).attr('value');
    if (type == 10) {
      $("#recurRevShareDiv").show();
      $("#recurCommDiv").hide();
      $("#oneTimeRevShareDiv").hide();
      $("#oneTimeCommDiv").hide();
    }
    else if (type == 20) {
      $("#recurRevShareDiv").hide();
      $("#recurCommDiv").show();
      $("#oneTimeRevShareDiv").hide();
      $("#oneTimeCommDiv").hide();
    }
    else if (type == 30) {
        $("#recurRevShareDiv").hide();
        $("#recurCommDiv").hide();
        $("#oneTimeRevShareDiv").show();
        $("#oneTimeCommDiv").hide();
      }
      else if (type == 40) {
          $("#recurRevShareDiv").hide();
          $("#recurCommDiv").hide();
          $("#oneTimeRevShareDiv").hide();
          $("#oneTimeCommDiv").show();
        }

  });

  $("#advControlBtn").click(function() {
    $("#advanceControls").toggle();
  });

  $("#recurRevShareDiv").show();
  $("#recurCommDiv").hide();
  $("#oneTimeRevShareDiv").hide();
  $("#oneTimeCommDiv").hide();
  $("#advanceControls").hide();
});