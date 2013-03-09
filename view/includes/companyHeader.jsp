<%@include file="/includes/taglibInclude.jsp" %>


<s:layout-definition>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jquery.gritter.css" type="text/css"/>
  
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
      <li class="btn btn-inverse"><a title="" href="${pageContext.request.contextPath}/company/Company.action"><i
          class="icon icon-cog"></i> <span
          class="text">Company Profile</span></a></li>
      <li class="btn btn-inverse"><a title="" href="login.html"><i class="icon icon-share-alt"></i> <span
          class="text">Logout</span></a></li>
    </ul>
  </div>
  <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.gritter.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/company/notification.js"></script>
</s:layout-definition>