$(document).ready(function() {
  var campaignType = $(this).attr('value');
  renderLandingPageCg(campaignType);

  $("#form-wizard").get(0).setAttribute('action', "/campaign/Campaign.action?saveCampaign");
  setCommissionViews($("#commStSel").val());

  $("#campaignTypeSel").change(function() {
    var type = $(this).attr('value');
    renderLandingPageCg(type);

  });

  $("#commStSel").change(function() {
    var type = $(this).attr('value');
    setCommissionViews(type);

  });

  $("#advControlBtn").click(function() {
    $("#advanceControls").toggle();
  });

  function renderLandingPageCg(type) {
    if (type == 60) {
      $("#landingPageCg").show();
    } else {
      $("#landingPageCg").hide();
    }
  }

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
      if (tier2Visible) {
        $("#tier2RecurRevShareDiv").show();
        $("#tier2RecurCommDiv").hide();
        $("#tier2OneTimeRevShareDiv").hide();
        $("#tier2OneTimeCommDiv").hide();
      }
      if (tier2Visible) {
        $("#tier3RecurRevShareDiv").show();
        $("#tier3RecurCommDiv").hide();
        $("#tier3OneTimeRevShareDiv").hide();
        $("#tier3OneTimeCommDiv").hide();
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
      if (tier2Visible) {
        $("#tier2RecurRevShareDiv").hide();
        $("#tier2RecurCommDiv").show();
        $("#tier2OneTimeRevShareDiv").hide();
        $("#tier2OneTimeCommDiv").hide();
      }
      if (tier3Visible) {
        $("#tier3RecurRevShareDiv").hide();
        $("#tier3RecurCommDiv").show();
        $("#tier3OneTimeRevShareDiv").hide();
        $("#tier3OneTimeCommDiv").hide();
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
        if (tier2Visible) {
          $("#tier2RecurRevShareDiv").hide();
          $("#tier2RecurCommDiv").hide();
          $("#tier2OneTimeRevShareDiv").show();
          $("#tier2OneTimeCommDiv").hide();
        }
        if (tier3Visible) {
          $("#tier3RecurRevShareDiv").hide();
          $("#tier3RecurCommDiv").hide();
          $("#tier3OneTimeRevShareDiv").show();
          $("#tier3OneTimeCommDiv").hide();
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
          if (tier2Visible) {
            $("#tier2RecurRevShareDiv").hide();
            $("#tier2RecurCommDiv").hide();
            $("#tier2OneTimeRevShareDiv").hide();
            $("#tier2OneTimeCommDiv").show();
          }
          if (tier3Visible) {
            $("#tier3RecurRevShareDiv").hide();
            $("#tier3RecurCommDiv").hide();
            $("#tier3OneTimeRevShareDiv").hide();
            $("#tier3OneTimeCommDiv").show();
          }
          $("#recurRevShareDiv").hide();
          $("#recurCommDiv").hide();
          $("#oneTimeRevShareDiv").hide();
          $("#oneTimeCommDiv").show();
        }
  }

  $("#addTierBtn").click(function() {
    var tier2Visible = $("#tier2Comm").is(":visible");
    var tier3Visible = $("#tier3Comm").is(":visible");

    if (!tier2Visible) {
      $("#tier2Comm").show();
    } else {
      $("#tier3Comm").show();
      $("#addTierBtn").hide();
    }
    var type = $("#commStSel").val();
    setCommissionViews(type);
  });

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


  $("#hideTier2Btn").click(function() {
    $("#tier2Comm").hide();
  });

  $("#hideTier3Btn").click(function() {
    $("#tier3Comm").hide();
    $("#addTierBtn").show();
  });

  /*$("#recurRevShareDiv").show();

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


  $("#tier2RecurRevShareDiv").show();
  $("#tier2RecurCommDiv").hide();
  $("#tier2OneTimeRevShareDiv").hide();
  $("#tier2OneTimeCommDiv").hide();


  $("#tier3RecurRevShareDiv").show();
  $("#tier3RecurCommDiv").hide();
  $("#tier3OneTimeRevShareDiv").hide();
  $("#tier3OneTimeCommDiv").hide();
*/

  $('#campaignRange').daterangepicker({}, function(start, end) {

    $(this).val(start.toString('MMMM d, yyyy') + ' - ' + end.toString('MMMM d, yyyy'));

    $("#startDate").val(start.toString('MMMM dd yyyy'));
    $("#endDate").val(end.toString('MMMM dd yyyy'));


  });


});