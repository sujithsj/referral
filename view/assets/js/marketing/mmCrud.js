$(document).ready(function() {
  $("#mmCrudForm").validate({
    errorClass: "help-inline",
    errorElement: "span",
    highlight:function(element, errorClass, validClass) {
      $(element).parents('.control-group').addClass('error');
    },
    unhighlight: function(element, errorClass, validClass) {
      $(element).parents('.control-group').removeClass('error');
      $(element).parents('.control-group').addClass('success');
    }
  });


  $("#adTypeSelect").change(function() {
    var adType = $(this).attr('value');
    setImageView(adType);
  });

  function setImageView(adType) {
    if (adType == 10) {
      /*banner*/
      $("#imageContainer").show();
    } else {
      $("#imageContainer").hide();
    }
  }

  function setAdPreview() {
    var mmId = $("#marketingMaterialId").val();

    if (mmId) {
      DS.Ajax.getJson("/api/mm/" + mmId + "/preview/", function(response) {
        //alert(response.sc);
        $("#previewContent").html(response.sc);
      });

    }
  }


  setImageView($("#adTypeSelect").val());
  setAdPreview();
});