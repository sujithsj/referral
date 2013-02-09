<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

<s:layout-component name="content">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/daterangepicker.css" type="text/css"/>
<s:layout-render name="/includes/companyHeader.jsp"/>
<s:layout-render name="/includes/companySideBar.jsp"/>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<div id="content">
    <s:layout-render name="/includes/companyContentHeader.jsp" headerLabel="Dashboard"/>
    <div id="breadcrumb">
        <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>Home</a>
        <a href="#" class="current">Dashboard</a>
    </div>

    <s:useActionBean beanclass="com.ds.action.company.CompanyDashboardAction" var="companyDashboardAction"/>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12 center" style="text-align: center;">
                <ul class="quick-actions">
                    <li>
                        <a href="#">
                            <i class="icon-people"></i>
                            Manage Affiliates
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="icon-user"></i>
                            Manage Users
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="icon-wallet"></i>
                            Manage Commissions
                        </a>
                    </li>
                </ul>
            </div>
        </div>

        <div class="row-fluid">

                        <div class="well">

               <div id="reportrange" class="pull-right" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
                  <i class="icon-calendar icon-large"></i>
                  <span></span> <b class="caret" style="margin-top: 8px"></b>
               </div>

               <script type="text/javascript">
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
                     }
                  );

                  //Set the initial state of the picker label
                  $('#reportrange span').html(Date.today().add({ days: -29 }).toString('MMMM d, yyyy') + ' - ' + Date.today().toString('MMMM d, yyyy'));

               });
               </script>

            </div>
            
            <div class="widget-box collapsible">
                <div class="widget-title">
                    <a href="#collapseStats" data-toggle="collapse">
                        <span class="icon"><i class="icon-signal"></i></span><h5>Company Statistics</h5>
                    </a>

                        <%--<div class="buttons"><a href="#" class="btn btn-mini"><i class="icon-refresh"></i> Update stats</a>
                        </div>--%>
                </div>
                <div class="collapse in" id="collapseStats">
                    <div class="widget-content ">
                        <div class="row-fluid">
                            <div class="span3">
                                <ul class="site-stats">
                                    <li><i class="icon-user"></i> <strong>${companyDashboardAction.totalReferrers}</strong>
                                        <small>Total Referrers</small>
                                    </li>
                                    <li><i class="icon-arrow-right"></i> <strong>16</strong>
                                        <small>New Users (last week)</small>
                                    </li>
                                    <li class="divider"></li>
                                    <li><i class="icon-shopping-cart"></i> <strong>259</strong>
                                        <small>Total Shop Items</small>
                                    </li>
                                    <li><i class="icon-tag"></i> <strong>8650</strong>
                                        <small>Total Orders</small>
                                    </li>
                                    <li><i class="icon-repeat"></i> <strong>29</strong>
                                        <small>Pending Orders</small>
                                    </li>
                                </ul>
                            </div>
                            <div class="span9" align="center">
                                <div id="feedbackTypeDistribution" style="width: 750px; height: 400px;"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="row-fluid">
            <div class="span6">
                <div class="widget-box collapsible">
                    <div class="widget-title">
                        <a href="#collapseTTAffByClick" data-toggle="collapse">
                            <span class="icon"><i class="icon-signal"></i></span><h5>Top Ten Affiliate By Clicks</h5>
                        </a>

                            <%--<div class="buttons"><a href="#" class="btn btn-mini"><i class="icon-refresh"></i> Update stats</a>
                            </div>--%>
                    </div>
                    <div class="collapse in" id="collapseTTAffByClick">
                        <div class="widget-content ">
                            <div id="topTenAffiliateByClickPieChart"
                                 style="width: 450px; height: 300px; ">
                                <div id="control1"></div>
                                <div id="control2"></div>
                                <div id="chart1"></div>
                            </div>
                                <%--<div id="topTenAffiliateByCommissionPieChart"
                                     style="width: 300px;  float:right;">
                                    <div id="control3"></div>
                                    <div id="control4"></div>
                                    <div id="chart2"></div>
                                </div>--%>
                        </div>
                    </div>
                </div>
            </div>
            <div class="span6">
                <div class="widget-box collapsible">
                    <div class="widget-title">
                        <a href="#collapseTTAffByRevenue" data-toggle="collapse">
                            <span class="icon"><i class="icon-signal"></i></span><h5>Pie</h5>
                        </a>

                            <%--<div class="buttons"><a href="#" class="btn btn-mini"><i class="icon-refresh"></i> Update stats</a>
                            </div>--%>
                    </div>
                    <div class="collapse in" id="collapseTTAffByRevenue">
                        <div class="widget-content ">
                            <div class="pie"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

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

        console.log(data);
        chart.draw(data);
        /*chart.draw(data, {
         height: 200,
         is3D: true,
         title: "Feedback Type Distribution",
         width: 400
         });*/
    };

</script>

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
                'ui': {'labelStacking': 'vertical', orientation:'horizontal'}

            }
        });

        slider.setState({'lowValue': 26});


        // Define a category picker control for the Gender column
        /*   var categoryPicker = new google.visualization.ControlWrapper({
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
         */

        // Define a Pie chart
        var pie = new google.visualization.ChartWrapper({
            'chartType': 'PieChart',
            'containerId': 'chart1',
            'options': {
                'width': 500,
                'height': 250,
                /*'chartArea': {'left': 15, 'top': 15, 'right': 0, 'bottom': 0},*/
                'pieSliceText': 'label',
                'legend': 'right',
                'backgroundColor': '#F9F9F9',
                'is3D':true
            },
            // Instruct the piechart to use colums 0 (Name) and 3 (Clicks)

            'view': {'columns': [0, 2]}
        });


        // Create a dashboard
        new google.visualization.Dashboard(document.getElementById('topTenAffiliateByClickPieChart')).
            // Establish bindings, declaring the both the slider and the category
            // picker will drive both charts.
                bind([slider], [pie]).draw(data);
        // Draw the entire dashboard.

    };

</script>
<s:layout-render name="/includes/footer.jsp"/>
</div>

</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.flot.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/date.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/daterangepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.flot.pie.min.js"></script>
<%--
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/excanvas.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.flot.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.peity.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/unicorn.dashboard.js"></script>
--%>
<script type="text/javascript">
    var data = [];
    var series = Math.floor(Math.random() * 10) + 1;
    for (var i = 0; i < series; i++)
    {
        data[i] = { label: "Series" + (i + 1), data: Math.floor(Math.random() * 100) + 1 }
    }

    var pie = $.plot($(".pie"), data, {
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
</script>


</s:layout-component>

</s:layout-render>