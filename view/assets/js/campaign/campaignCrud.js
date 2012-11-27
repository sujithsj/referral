$(document).ready(function() {

  $("#commStSel").change(function() {
    var type = $(this).attr('value');
    setCommissionViews(type);

  });

  $("#advControlBtn").click(function() {
    $("#advanceControls").toggle();
  });

  function setCommissionViews(type) {
    var tier1Visible = $("#tier1Comm").is(":visible");
    var tier2Visible = $("#tier2Comm").is(":visible");
    var tier3Visible = $("#tier3Comm").is(":visible");

    if (type == 10) {
      if (tier1Visible) {
        $("#tier1RecurRevShareDiv").show();
        $("#tier1RecurCommDiv").hide();
        $("#tier1OneTimeRevShareDiv").hide();
        $("#tier1OneTimeCommDiv").hide();
      }
      $("#recurRevShareDiv").show();
      $("#recurCommDiv").hide();
      $("#oneTimeRevShareDiv").hide();
      $("#oneTimeCommDiv").hide();
    }
    else if (type == 20) {
      if (tier1Visible) {
        $("#tier1RecurRevShareDiv").hide();
        $("#tier1RecurCommDiv").show();
        $("#tier1OneTimeRevShareDiv").hide();
        $("#tier1OneTimeCommDiv").hide();
      }
      $("#recurRevShareDiv").hide();
      $("#recurCommDiv").show();
      $("#oneTimeRevShareDiv").hide();
      $("#oneTimeCommDiv").hide();
    }
    else if (type == 30) {
        if (tier1Visible) {
          $("#tier1RecurRevShareDiv").hide();
          $("#tier1RecurCommDiv").hide();
          $("#tier1OneTimeRevShareDiv").show();
          $("#tier1OneTimeCommDiv").hide();
        }
        $("#recurRevShareDiv").hide();
        $("#recurCommDiv").hide();
        $("#oneTimeRevShareDiv").show();
        $("#oneTimeCommDiv").hide();
      }
      else if (type == 40) {
          if (tier1Visible) {
            $("#tier1RecurRevShareDiv").hide();
            $("#tier1RecurCommDiv").hide();
            $("#tier1OneTimeRevShareDiv").hide();
            $("#tier1OneTimeCommDiv").show();
          }
          $("#recurRevShareDiv").hide();
          $("#recurCommDiv").hide();
          $("#oneTimeRevShareDiv").hide();
          $("#oneTimeCommDiv").show();
        }
  }

  $("#tiered").click(function() {
    var isTiered = $(this).is(':checked');
    var type = $("#commStSel").val();
    if (isTiered) {
      $("#tieredCommission").show();
      $("#nonTierCommision").hide();
    } else {
      $("#tieredCommission").hide();
      $("#nonTierCommision").show();
    }
    setCommissionViews(type);
  });

  $("#recurRevShareDiv").show();

  $("#tieredCommission").hide();
  $("#tier2Comm").hide();
  $("#tier3Comm").hide();

  $("#recurCommDiv").hide();
  $("#oneTimeRevShareDiv").hide();
  $("#oneTimeCommDiv").hide();
  $("#advanceControls").hide();


  $("#tier1RecurRevShareDiv").show();
  $("#tier1RecurCommDiv").hide();
  $("#tier1OneTimeRevShareDiv").hide();
  $("#tier1OneTimeCommDiv").hide();
});