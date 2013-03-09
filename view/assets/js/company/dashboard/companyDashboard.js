$(document).ready(function() {

  

  

  $('#reportrange').daterangepicker(
  {
    ranges: {
      'Today': ['today', 'today'],
      'Yesterday': ['yesterday', 'yesterday'],
      'Last 7 Days': [Date.today().add({ days: -6 }), 'today'],
      'Last 30 Days': [Date.today().add({ days: -29 }), 'today'],
      'This Month': [Date.today().moveToFirstDayOfMonth(), Date.today().moveToLastDayOfMonth()],
      'Last Month': [Date.today().moveToFirstDayOfMonth().add({ months: -1 }), Date.today().moveToFirstDayOfMonth().add({ days: -1 })]
    },
    opens: 'left',
    format: 'MM/dd/yyyy',
    separator: ' to ',
    startDate: Date.today().add({ days: -29 }),
    endDate: Date.today(),
    minDate: '01/01/2012',
    maxDate: '12/31/2013',
    locale: {
      applyLabel: 'Submit',
      fromLabel: 'From',
      toLabel: 'To',
      customRangeLabel: 'Custom Range',
      daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
      monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
      firstDay: 1
    },
    showWeekNumbers: true,
    buttonClasses: ['btn-danger']
  },
      function(start, end) {

        $('#reportrange span').html(start.toString('MMMM d, yyyy') + ' - ' + end.toString('MMMM d, yyyy'));

        $("#startDate").val(start.toString('MMMM dd yyyy'));
        $("#endDate").val(end.toString('MMMM dd yyyy'));

        drawTopTenAffByCommissionPie();
      }
      );

  //Set the initial state of the picker label
  $('#reportrange span').html(Date.today().add({ days: -29 }).toString('MMMM d, yyyy') + ' - ' + Date.today().toString('MMMM d, yyyy'));

  $("#startDate").val(Date.today().add({ days: -29 }).toString('MMMM dd yyyy'));
  $("#endDate").val(Date.today().toString('MMMM dd yyyy'));

  drawTopTenAffByCommissionPie();

});

function drawTopTenAffByCommissionPie() {

  var startDate = $("#startDate").val();
  var endDate = $("#endDate").val();
  DS.Ajax.getJson("/api/company/dashboard/ttAffByComm?stDt=" + startDate + "&edDt=" + endDate, function(response) {

    var data = [];

    $.each(response.data, function(index, value) {
      data[index] = {label: value.affiliateLogin, data:value.commission };
    });

    var pie = $.plot($("#ttAffByCommission"), data, {
      series: {
        pie: {
          show: true,
          radius: 3 / 4,
          label: {
            show: true,
            radius: 3 / 4,
            formatter: function(label, series) {
              return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">' + label + '<br/>' + Math.round(series.percent) + '%</div>';
            },
            background: {
              opacity: 0.5,
              color: '#000'
            }
          },
          innerRadius: 0.2
        },
        legend: {
          show: false
        }
      }
    });
  });

}
;