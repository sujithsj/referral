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
        <input type="hidden" id="startDate"/>
        <input type="hidden" id="endDate"/>

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

                <div id="reportrange" class="pull-right"
                     style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
                    <i class="icon-calendar icon-large"></i>
                    <span></span> <b class="caret" style="margin-top: 8px"></b>
                </div>


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
                                    <li><i class="icon-user"></i>
                                        <strong>${companyDashboardAction.totalReferrers}</strong>
                                        <small>Total Referrers</small>
                                    </li>
                                    <li><i class="icon-arrow-right"></i>
                                        <strong>${companyDashboardAction.referersInLastWeek}</strong>
                                        <small>New Users (last week)</small>
                                    </li>
                                    <li class="divider"></li>
                                    <li><i class="icon-shopping-cart"></i>
                                        <strong>${companyDashboardAction.totalRevenue}</strong>
                                        <small>Total Revenue Tracked</small>
                                    </li>
                                    <li><i class="icon-tag"></i>
                                        <strong>${companyDashboardAction.totalCommission}</strong>
                                        <small>Total Commission Tracked</small>
                                    </li>
                                    <li><i class="icon-repeat"></i>
                                        <strong>${companyDashboardAction.numberOfPendingCommission}</strong>
                                        <small>Pending Commissions</small>
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
                            <div class="pie" id="ttAffByCommission" ></div>
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
                    </div>
                    <div class="collapse in" id="collapseTTAffByRevenue">
                        <div class="widget-content ">
                            <div class="pie" id="ttAffByRevenue" ></div>
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

        /*console.log(data);*/
        chart.draw(data);
        /*chart.draw(data, {
         height: 200,
         is3D: true,
         title: "Feedback Type Distribution",
         width: 400
         });*/
    };

</script>


<s:layout-render name="/includes/footer.jsp"/>
</div>

</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.flot.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/date.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/daterangepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.flot.pie.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/company/dashboard/companyDashboard.js"></script>

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