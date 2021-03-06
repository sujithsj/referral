<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

<s:layout-component name="content">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>

<div id="header">
  <h1><a href="./dashboard.html">Unicorn Admin</a></h1>
</div>

<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav btn-group">
    <li class="btn btn-inverse"><a title="" href="#"><i class="icon icon-user"></i> <span
        class="text">Profile</span></a></li>
    <li class="btn btn-inverse dropdown" id="menu-messages"><a href="#" data-toggle="dropdown"
                                                               data-target="#menu-messages" class="dropdown-toggle"><i
        class="icon icon-envelope"></i> <span class="text">Messages</span> <span
        class="label label-important">5</span> <b class="caret"></b></a>
      <ul class="dropdown-menu">
        <li><a class="sAdd" title="" href="#">new message</a></li>
        <li><a class="sInbox" title="" href="#">inbox</a></li>
        <li><a class="sOutbox" title="" href="#">outbox</a></li>
        <li><a class="sTrash" title="" href="#">trash</a></li>
      </ul>
    </li>
    <li class="btn btn-inverse"><a title="" href="${pageContext.request.contextPath}/company/Company.action"><i class="icon icon-cog"></i> <span
        class="text">Settings</span></a></li>
    <li class="btn btn-inverse"><a title="" href="login.html"><i class="icon icon-share-alt"></i> <span
        class="text">Logout</span></a></li>
  </ul>
</div>

<s:layout-render name="/includes/companySideBar.jsp"/>

<div id="content">
  <div id="content-header">
    <h1>Dashboard</h1>

    <div class="btn-group">
      <a class="btn btn-large tip-bottom" title="Manage Ads" href="${pageContext.request.contextPath}/marketing/MarketingMaterialSearch.action"><i class="icon-file"></i></a>
      <a class="btn btn-large tip-bottom" title="Manage Users" href="${pageContext.request.contextPath}/employee/UserSearch.action"><i class="icon-user"></i></a>
      <a class="btn btn-large tip-bottom" title="Manage Affiliates" href="${pageContext.request.contextPath}/affiliate/CompanyAffiliateSearch.action"><i class="icon-leaf"></i></a>
      <a class="btn btn-large tip-bottom" title="Manage Comments"><i class="icon-comment"></i><span
          class="label label-important">5</span></a>
      <a class="btn btn-large tip-bottom" title="Manage Orders"><i class="icon-shopping-cart"></i></a>

    </div>
  </div>
  <div id="breadcrumb">
    <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
    <a href="#" class="current">Dashboard</a>
  </div>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12 center" style="text-align: center;">
        <ul class="stat-boxes">
          <li>
            <div class="left peity_bar_good"><span>2,4,9,7,12,10,12</span>+20%</div>
            <div class="right">
              <strong>36094</strong>
              Visits
            </div>
          </li>
          <li>
            <div class="left peity_bar_neutral"><span>20,15,18,14,10,9,9,9</span>0%</div>
            <div class="right">
              <strong>1433</strong>
              Users
            </div>
          </li>
          <li>
            <div class="left peity_bar_bad"><span>3,5,9,7,12,20,10</span>-50%</div>
            <div class="right">
              <strong>8650</strong>
              Orders
            </div>
          </li>
          <li>
            <div class="left peity_line_good"><span>12,6,9,23,14,10,17</span>+70%</div>
            <div class="right">
              <strong>8650</strong>
              Orders
            </div>
          </li>
        </ul>
      </div>
    </div>
    <div class="row-fluid">
      <div class="span12">
        <div class="alert alert-info">
          Welcome in the <strong>Unicorn Admin Theme</strong>. Don't forget to check all the pages!
          <a href="#" data-dismiss="alert" class="close">�</a>
        </div>
        <div class="widget-box">
          <div class="widget-title"><span class="icon"><i class="icon-signal"></i></span><h5>Site Statistics</h5>

            <div class="buttons"><a href="#" class="btn btn-mini"><i class="icon-refresh"></i> Update stats</a></div>
          </div>
          <div class="widget-content">
            <div class="row-fluid">
              <div class="span4">
                <ul class="site-stats">
                  <li><i class="icon-user"></i> <strong>1433</strong>
                    <small>Total Users</small>
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
              <div class="span8">
                <div class="chart"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row-fluid">
      <div class="span6">
        <div class="widget-box">
          <div class="widget-title"><span class="icon"><i class="icon-file"></i></span><h5>Recent Posts</h5><span
              title="54 total posts" class="label label-info tip-left">54</span></div>
          <div class="widget-content nopadding">
            <ul class="recent-posts">
              <li>
                <div class="user-thumb">
                  <img width="40" height="40" alt="User" src="img/demo/av2.jpg">
                </div>
                <div class="article-post">
                  <span class="user-info"> By: neytiri on 2 Aug 2012, 09:27 AM, IP: 186.56.45.7 </span>

                  <p>
                    <a href="#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
                  </p>
                  <a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Publish</a>
                  <a href="#" class="btn btn-danger btn-mini">Delete</a>
                </div>
              </li>
              <li>
                <div class="user-thumb">
                  <img width="40" height="40" alt="User" src="img/demo/av3.jpg">
                </div>
                <div class="article-post">
                  <span class="user-info"> By: john on on 24 Jun 2012, 04:12 PM, IP: 192.168.24.3 </span>

                  <p>
                    <a href="#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
                  </p>
                  <a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Publish</a>
                  <a href="#" class="btn btn-danger btn-mini">Delete</a>
                </div>
              </li>
              <li>
                <div class="user-thumb">
                  <img width="40" height="40" alt="User" src="img/demo/av1.jpg">
                </div>
                <div class="article-post">
                  <span class="user-info"> By: michelle on 22 Jun 2012, 02:44 PM, IP: 172.10.56.3 </span>

                  <p>
                    <a href="#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
                  </p>
                  <a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Publish</a>
                  <a href="#" class="btn btn-danger btn-mini">Delete</a>
                </div>
              </li>
              <li class="viewall">
                <a title="View all posts" class="tip-top" href="#"> + View all + </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="span6">
        <div class="widget-box">
          <div class="widget-title"><span class="icon"><i class="icon-comment"></i></span><h5>Recent Comments</h5><span
              title="88 total comments" class="label label-info tip-left">88</span></div>
          <div class="widget-content nopadding">
            <ul class="recent-comments">
              <li>
                <div class="user-thumb">
                  <img width="40" height="40" alt="User" src="img/demo/av1.jpg">
                </div>
                <div class="comments">
                  <span class="user-info"> User: michelle on IP: 172.10.56.3 </span>

                  <p>
                    <a href="#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
                  </p>
                  <a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Approve</a>
                  <a href="#" class="btn btn-warning btn-mini">Mark as spam</a> <a href="#"
                                                                                   class="btn btn-danger btn-mini">Delete</a>
                </div>
              </li>
              <li>
                <div class="user-thumb">
                  <img width="40" height="40" alt="User" src="img/demo/av3.jpg">
                </div>
                <div class="comments">
                  <span class="user-info"> User: john on IP: 192.168.24.3 </span>

                  <p>
                    <a href="#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
                  </p>
                  <a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Approve</a>
                  <a href="#" class="btn btn-warning btn-mini">Mark as spam</a> <a href="#"
                                                                                   class="btn btn-danger btn-mini">Delete</a>
                </div>
              </li>
              <li>
                <div class="user-thumb">
                  <img width="40" height="40" alt="User" src="img/demo/av2.jpg">
                </div>
                <div class="comments">
                  <span class="user-info"> User: neytiri on IP: 186.56.45.7 </span>

                  <p>
                    <a href="#">Vivamus sed auctor nibh congue, ligula vitae tempus pharetra...</a>
                  </p>
                  <a href="#" class="btn btn-primary btn-mini">Edit</a> <a href="#" class="btn btn-success btn-mini">Approve</a>
                  <a href="#" class="btn btn-warning btn-mini">Mark as spam</a> <a href="#"
                                                                                   class="btn btn-danger btn-mini">Delete</a>
                </div>
              </li>
              <li class="viewall">
                <a title="View all comments" class="tip-top" href="#"> + View all + </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    
    <s:layout-render name="/includes/footer.jsp"/>
  </div>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/excanvas.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.flot.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.peity.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/unicorn.dashboard.js"></script>



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

</s:layout-component>

</s:layout-render>