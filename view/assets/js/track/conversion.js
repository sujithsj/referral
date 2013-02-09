var rf_c_short_code;
var rf_revenue;
var rf_campaign_uid;
var rf_tx_uid;
var rf_uid;
var rf_u_f_name;
var rf_u_l_name;
var rf_email_new_referrer;
var rf_disable_new_referrer;
var rf_auto_create;
var rf_u_email;

var params = {

    rf_c_short_code: rf_c_short_code,
    rf_campaign_uid: rf_campaign_uid,
    rf_u_email: rf_u_email,
    rf_revenue: rf_revenue,
    rf_tx_uid: rf_tx_uid,
    rf_uid: rf_uid,
    rf_u_f_name: rf_u_f_name,
    rf_u_l_name: rf_u_l_name,
    rf_auto_create: rf_auto_create,
    rf_disable_new_referrer: rf_disable_new_referrer,
    rf_email_new_referrer : rf_email_new_referrer
};
/*var mbsy_has_trial;
 var mbsy_add_to_group_id;
 var mbsy_event_data1;
 var mbsy_event_data2;
 var mbsy_event_data3;*/

/*var jqxhr = $.getJSON(

 "http://dev.healthkart.com/api/cr/track",
 {
 rf_c_short_code: rf_c_short_code,
 rf_campaign_uid: rf_campaign_uid,
 rf_u_email: rf_u_email,
 rf_revenue: rf_revenue,
 rf_tx_uid: rf_tx_uid,
 rf_uid: rf_uid,
 rf_u_f_name: rf_u_f_name,
 rf_u_l_name: rf_u_l_name,
 rf_auto_create: rf_auto_create,
 rf_disable_new_referrer: rf_disable_new_referrer,
 rf_email_new_referrer : rf_email_new_referrer
 },

 function(data){
 //console.log(data);
 }

 );*/


jQuery.ajax({

    contentType: "application/json; charset=utf-8",
    url: "http://dev.healthkart.com/api/cr/track?jsoncallback=?",
    data: params,
    dataType: "json",
    success: function(json) {
        if (typeof onSuccess === "function")
            onSuccess.call(this, json);
    }

});