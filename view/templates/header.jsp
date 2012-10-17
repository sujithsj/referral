<%@ page import="java.util.List" %>
<%@include file="/includes/taglibInclude.jsp" %>
<s:layout-definition>


  <div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container">
        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </a>
        <a class="brand" href="/html/pages/home.xhtml">PriceMia</a>

        <div class="nav-collapse">
          <ul class="nav" style="float:right;font-size:18px;">
            <li class="active"><a href="/pages/signup.jsp">SignUp</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#feedbackModal" data-toggle="modal">Feedback</a></li>
            <!--<li><a href="#contact">Contact</a></li>-->
          </ul>
        </div>
        <!--/.nav-collapse -->
      </div>
    </div>
  </div>
</s:layout-definition>