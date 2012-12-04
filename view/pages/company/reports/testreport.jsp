<html>
<head>
         <script type="text/javascript" src="https://www.google.com/jsapi"></script>
  <script type="text/javascript">
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
      var data = google.visualization.arrayToDataTable([
        ['Year', 'Sales', 'Expenses'],
        ['2004',  1000,      400],
        ['2005',  1170,      460],
        ['2006',  660,       1120],
        ['2007',  1030,      540]
      ]);

      var options = {
        title: 'Company Performance',
        hAxis: {title: 'Year',  titleTextStyle: {color: 'red'}}
      };

      var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    }
  </script>

  <script type='text/javascript'>
    google.load('visualization', '1', {'packages':['annotatedtimeline']});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
      var data = new google.visualization.DataTable();
      data.addColumn('date', 'Date');
      data.addColumn('number', 'Sold Pencils');
      data.addColumn('string', 'title1');
      data.addColumn('string', 'text1');
      data.addColumn('number', 'Sold Pens');
      data.addColumn('string', 'title2');
      data.addColumn('string', 'text2');
      data.addRows([
        [new Date(2008, 1, 1), 30000, undefined, undefined, 40645, undefined, undefined],
        [new Date(2008, 1, 2), 14045, undefined, undefined, 20374, undefined, undefined],
        [new Date(2008, 1, 3), 55022, undefined, undefined, 50766, undefined, undefined],
        [new Date(2008, 1, 4), 75284, undefined, undefined, 14334, 'Out of Stock','Ran out of stock on pens at 4pm'],
        [new Date(2008, 1, 5), 41476, 'Bought Pens','Bought 200k pens', 66467, undefined, undefined],
        [new Date(2008, 1, 6), 33322, undefined, undefined, 39463, undefined, undefined]
      ]);

      var chart = new google.visualization.AnnotatedTimeLine(document.getElementById('chart_div1'));
      chart.draw(data, {displayAnnotations: true});
    }
  </script>

  <script type="text/javascript">

    google.load("visualization", "1", {packages:["annotatedtimeline"]});

    google.setOnLoadCallback(drawChart);

    function drawChart() {
      var query = new google.visualization.Query("/datasource?dsName=cIt&companyShortName=" + 'hk' + "&startDate=2012-12-01&endDate=2012-12-31");
      query.send(drawFeedbackTypeDistributionChart);
    }
    var drawFeedbackTypeDistributionChart = function(response) {
      
      if (response.isError()) {
        alert("Error in query: " + response.getMessage() + " " + response.getDetailedMessage());
        return;
      }

      var data = response.getDataTable(),
          chart = new google.visualization.AnnotatedTimeLine(document.getElementById('feedbackTypeDistribution'));

      chart.draw(data);
      /*chart.draw(data, {
       height: 200,
       is3D: true,
       title: "Feedback Type Distribution",
       width: 400
       });*/
    };

  </script>

</head>
<body>
<div id="feedbackTypeDistribution" style="width: 900px; height: 500px;"></div>
<div id="chart_div" style="width: 900px; height: 500px;"></div>
<div id="chart_div1" style="width: 900px; height: 500px;"></div>

</body>
</html>