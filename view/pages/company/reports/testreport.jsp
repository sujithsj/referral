<html>
<head>

  <%--src="${pageContext.request.contextPath}/assets/js/jquery.js"--%>
  <script type="text/javascript" src="https://www.google.com/jsapi"></script>
  <%--<script type="text/javascript">
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
  </script>--%>

  <%--<script type='text/javascript'>
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

        [new Date(2008, 1, 2), 14045, undefined, undefined, 20374, undefined, undefined],
        [new Date(2008, 1, 3), 55022, undefined, undefined, 50766, undefined, undefined],
        [new Date(2008, 1, 4), 75284, undefined, undefined, 14334, 'Out of Stock','Ran out of stock on pens at 4pm'],
        [new Date(2008, 1, 5), 41476, 'Bought Pens','Bought 200k pens', 66467, undefined, undefined],
        [new Date(2008, 1, 6), 33322, undefined, undefined, 39463, undefined, undefined]
      ]);

      var chart = new google.visualization.AnnotatedTimeLine(document.getElementById('chart_div1'));
      chart.draw(data, {displayAnnotations: true});
    }
  </script>--%>

  <%--<script type="text/javascript">

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

      console.log(data);
      chart.draw(data);
      /*chart.draw(data, {
       height: 200,
       is3D: true,
       title: "Feedback Type Distribution",
       width: 400
       });*/
    };

  </script>--%>


  <script type="text/javascript">

    google.load('visualization', '1.0', {'packages':['controls']});

    google.setOnLoadCallback(drawPieChart);

    function drawPieChart() {
      var query = new google.visualization.Query("/datasource?dsName=ttAffByClick&companyShortName=" + 'hk' + "&startDate=2012-12-01&endDate=2012-12-31");
      query.send(drawTopTenAffByClickChart);
    }
    var drawTopTenAffByClickChart = function(response) {

      if (response.isError()) {
        alert("Error in query: " + response.getMessage() + " " + response.getDetailedMessage());
        return;
      }

      var data = response.getDataTable();



      // Define a slider control for the Age column.
      var slider = new google.visualization.ControlWrapper({
        'controlType': 'NumberRangeFilter',
        'containerId': 'control1',
        'options': {
          'filterColumnLabel': 'Clicks',
          'ui': {'labelStacking': 'vertical'}
        }
      });


      // Define a category picker control for the Gender column
      var categoryPicker = new google.visualization.ControlWrapper({
        'controlType': 'CategoryFilter',
        'containerId': 'control2',
        'options': {
          'filterColumnLabel': 'Affiliate Type',
          filterColumnIndex : 1,
          'ui': {
            'labelStacking': 'vertical',
            'allowTyping': false,
            'allowMultiple': false
          }
        }
      });


      // Define a Pie chart
      var pie = new google.visualization.ChartWrapper({
        'chartType': 'PieChart',
        'containerId': 'chart1',
        'options': {
          'width': 600,
          'height': 300,
          'title': 'Top Ten Affiliates By Click',
          'chartArea': {'left': 15, 'top': 15, 'right': 0, 'bottom': 0},
          'pieSliceText': 'percentage',
          'legend': 'right'
        },
        // Instruct the piechart to use colums 0 (Name) and 3 (Clicks)

        'view': {'columns': [0, 2]}
      });


      // Create a dashboard
      new google.visualization.Dashboard(document.getElementById('topTenAffiliateByClickPieChart')).
        // Establish bindings, declaring the both the slider and the category
        // picker will drive both charts.
          bind([slider, categoryPicker], [pie]).draw(data);
        // Draw the entire dashboard.




    };

  </script>

</head>
<body>
<div id="feedbackTypeDistribution" style="width: 900px; height: 500px;"></div>
<div id="topTenAffiliateByClickPieChart" style="width: 900px; height: 500px;">
  <div id="control1"></div>
  <div id="control2"></div>
  <div id="chart1"></div>

</div>
<div id="chart_div" style="width: 900px; height: 500px;"></div>
<div id="chart_div1" style="width: 900px; height: 500px;"></div>

</body>
</html>