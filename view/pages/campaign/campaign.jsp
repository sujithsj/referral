<%@ page import="com.ds.constants.EnumCampaignType" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <s:layout-component name="content">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
    <s:layout-render name="/includes/companyHeader.jsp"/>
    <s:layout-render name="/includes/companySideBar.jsp"/>

    <div id="content">
      <s:layout-render name="/includes/companyContentHeader.jsp" headerLabel="Campaigns"/>
      <div id="breadcrumb">
        <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
        <a href="#" class="current">Campaigns</a>
      </div>

      <div class="container-fluid">
        <s:useActionBean beanclass="com.ds.action.campaign.CampaignSearchAction" var="campaignSearchAction"/>

        <div class="row-fluid">
          <div class="span10">
            <div class="widget-box">
              <div class="widget-title">
								<span class="icon">
									<i class=" icon-search"></i>
								</span>
                <h5>Search Campaign</h5>
              </div>
              <div class="widget-content">
                <s:form beanclass="com.ds.action.campaign.CampaignSearchAction" class="form-inline">
                  <div class="input-prepend">
                    <span class="add-on"><i class="icon-user"></i></span><s:text name="name"
                                                                                 placeholder="Campaign Name"/>
                  </div>

                  <s:hidden name="campaignTypeId" id="campaignType"/>

                  <%--<div class="input btn-toolbar">--%>
                    <div class="btn-group">

                      <a class="btn cpType" href="#" type="<%=EnumCampaignType.ALL.getId()%>">All</a>
                      <a class="btn cpType" href="#" type="<%=EnumCampaignType.SALE.getId()%>">Sale</a>
                      <a class="btn cpType" href="#" type="<%=EnumCampaignType.EMAIL_OPT_IN.getId()%>">Email Opt In</a>
                      <a class="btn cpType" href="#" type="<%=EnumCampaignType.USER_SIGN_UP.getId()%>">User Sign Up</a>
                      <a class="btn cpType" href="#" type="<%=EnumCampaignType.AFFILIATE_SIGN_UP.getId()%>">Affiliate
                        Sign
                        Up</a>
                    </div>
                  <%--  </div>--%>
                  <s:submit name="searchCampaign" class="btn btn-inverse">Search</s:submit>
                </s:form>
              </div>
            </div>
          </div>
          <div class="span2">
            <s:link beanclass="com.ds.action.campaign.CampaignAction"
                    event="createOrEditCampaign" class="btn btn-success">
              <i class="icon-plus-sign icon-white"></i>&nbsp;Add  Campaign
            </s:link>
          </div>
        </div>
        <s:layout-render name="/includes/footer.jsp"/>
      </div>

    </div>


    <%--
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/excanvas.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.flot.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.peity.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/unicorn.dashboard.js"></script>
    --%>


  </s:layout-component>

</s:layout-render>