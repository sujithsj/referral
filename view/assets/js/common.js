if (typeof(DS) == 'undefined') {
  DS = {};
}

jQuery(document).ready(function($) {
  $('.auto-adjust').keyup(function() {
    adjustElemWidth($(this));
  });

  $('.auto-adjust').change(function() {
    adjustElemWidth($(this));
  });

  $('[type="submit"]').click(function() {
    var success = checkEmpty($(this)) && checkRadioGroup($(this)) && checkCheckBoxGroup($(this)) && checkMultiSelect($(this));
    if (success) {
      return true;
    } else {
      var ul = document.createElement('ul');
      ul.className = "errorList";
      var li = document.createElement('li');
      li.className = "errorMessage";

      $(li).append("Kindly mention all the required fields!");
      ul.appendChild(li);
      $('#error-messages').append(ul);
      $('#error-messages').css({
        backgroundColor:"firebrick"
      });
      $('#error-messages').show();
      return false;
    }
  });



  $('.search').click(function() {
    var srchBtn = $(this);
    if ($(srchBtn).hasClass('include-store')) {
      var srchForm = $(srchBtn).parents()[0];

      $(srchForm).append(
          "<input type='hidden' value='"
              + $('#storeSelect').val()
              + "' name='storeId'>"
          );
    }
  });

  $('.verify').click(function() {
    var elem = $(this);
    var customMsg = "";
    if ($(elem).attr('message') != null) {
      customMsg = $(elem).attr('message').trim();
    }
    var confirmationMsg = (customMsg != "") ? customMsg : "Are you sure you want to perform this action?";

    return confirm(confirmationMsg);
  });

  setWidthLimits();
  adjustWidth();
  setDisabled();

});

jQuery(document).click(function() {
  $('#error-messages').hide();
  $('#error-messages').html(" ");
});

function adjustWidth() {
  $('.auto-adjust').each(function() {
    adjustElemWidth($(this));
  });
}

function adjustElemWidth(elem) {
  var textSize = elem.val().length * 7.5;

  elem.css({
    width :  textSize
  });
}

function checkEmpty(submitElem) {
  var error = 0;
  var elem,val;
  submitElem.parents('form').find('.check-empty').each(function() {
    elem = $(this);
    elem.css({
      borderColor:"#CCCCCC"
    });

    val = elem.val().trim();
    if (val === "") {
      elem.css({
        borderColor: "red"
      });
      error = 1;
    }
  });
  return !error;
}

function setWidthLimits() {
  $('.auto-adjust').each(function() {
    var elem = $(this);
    var minElemWidth = 200;
    var parentElem = elem.parents()[0];

    parentElem = elem.parents()[0];
    minElemWidth = Math.min.apply(Math, new Array($(parentElem).width(), 200));
    elem.css({
      maxWidth:$(parentElem).width(),
      minWidth:minElemWidth
    });
  });
}

function getParamFromURL(name)
{
  name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
  var regexS = '[\\?&]' + name + "=([^&#]*)";
  var regex = new RegExp(regexS);
  var results = regex.exec(window.location.href);
  if (results == null)
    return "";
  else
    return results[1];
}
;

/**
 *
 * Ajax function start
 */
DS.Ajax = function() {
  return {
    getJson: function(url, onSuccess, onError) {
      jQuery.ajax({type: "GET", url: url, dataType: "json",
        success: function(json) {
          if (typeof onSuccess === "function")
            onSuccess.call(this, json);
        },
        error: function(xhr, a_status) {
          var errorMsg;
          if (typeof onError === "function") {
            onError.call(this, xhr, a_status);
          } else if (a_status === "error") { // Expected status: timeout/error/notmodified/parsererror
            if (xhr.status == 404) {
              errorMsg = " This request does not exist.";
            } else if (xhr.status == 500) {
              errorMsg = "An unexpected error occured on server while processing this request. Try Again."
            }
          } else if (a_status === "timeout") {
            errorMsg = "Stay the patient course. Of little worth is your ire. The network is down."
          } else if (a_status === "parsererror") {
            errorMsg = "Yesterday it worked. Today it is not working. The response from server couldn't be processed properly."
          }
          if (errorMsg) {
            alert(errorMsg);
          }
        },
        beforeSend: function(xhr) {
          if (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
          }
        }
      });
    },
    postJson: function(url, map, onSuccess, onError) {
      jQuery.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: url,
        data: JSON.stringify(map),
        dataType: "json",
        success: function(json) {
          if (typeof onSuccess === "function")
            onSuccess.call(this, json);
        },
        error: function(xhr, a_status) {
          if (typeof onError === "function") {
            onError.call(this, xhr, a_status);
          } else if (a_status === "error") { // Expected status: timeout/error/notmodified/parsererror
            if (xhr.status == 404) {
              alert("You step in the stream, but the water has moved on. This request does not exist.");
            } else if (xhr.status == 500) {
              alert("An unexpected error occured on server while processing this request. Try Again.");
            }
          } else if (a_status === "timeout") {
            alert("Stay the patient course. Of little worth is your ire. The network is down.");
          } else if (a_status === "parsererror") {
            alert("Yesterday it worked. Today it is not working. The response from server couldn't be processed properly.");
          }
        },
        beforeSend: function(xhr) {
          if (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
          }
        }
      });
    }
  };
}();
/**
 *
 * Ajax function end
 */

/**
 *
 * String function start
 */
DS.String = function() {
  return {
    isBlank: function(str) {
      return (!str || /^\s*$/.test(str));
    },
    isEmpty: function(str) {
      return (!str || 0 === str.length);
    }
  }
}();

/**
 *
 * String function start
 */


function setDisabled() {
  if ($('.disabled')) {
    $('.disabled').each(function() {
      var elem = $(this);
      elem.attr("disabled", "disabled");
    });
  }
}

function checkRadioGroup(submitElem) {
  var error = 0;
  if (submitElem.parents('form').find('.checkRadioGroup').length > 0) {
    var selectedElems = submitElem.parents('form').find('.checkRadioGroup').filter("[type='radio']");
    error = checkSelectionGroup(selectedElems);
  }
  return !error;
}

function checkCheckBoxGroup(submitElem) {
  var error = 0;
  if (submitElem.parents('form').find('.checkCheckBoxGroup').length > 0) {
    var selectedElems = submitElem.parents('form').find('.checkCheckBoxGroup').filter("[type='checkbox']");
    error = checkSelectionGroup(selectedElems);
  }
  return !error;
}

function checkMultiSelect(submitElem) {
  var error = 0;
  var multiSelects = submitElem.parents('form').find('.checkMultiSelect');
  $(multiSelects).each(function() {
    if ($(this).find('.selectedValues').length == 0) {
      error = 1;
    }
  });
  return !error;
}

