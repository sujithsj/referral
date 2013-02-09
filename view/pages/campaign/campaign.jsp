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
                <s:form beanclass="com.ds.action.campaign.CampaignSearchAction" id="campaignSearchForm"
                        class="form-inline">
                  <div class="input-prepend">
                    <span class="add-on"><i class="icon-tag"></i></span><s:text name="name"
                                                                                 placeholder="Campaign Name"/>
                  </div>

                  <s:hidden name="campaignTypeId" id="campaignType"/>

                  <%--<div class="input btn-toolbar">--%>
                  <div class="btn-group">

                    <a class="btn cpType" href="#" type="<%=EnumCampaignType.ALL.getId()%>">All</a>
                    <a class="btn cpType" href="#" type="<%=EnumCampaignType.SALE.getId()%>">Sale</a>
                    <%--<a class="btn cpType" href="#" type="<%=EnumCampaignType.EMAIL_OPT_IN.getId()%>">Email Opt In</a>
                    <a class="btn cpType" href="#" type="<%=EnumCampaignType.USER_SIGN_UP.getId()%>">User Sign Up</a>--%>
                    <a class="btn cpType" href="#" type="<%=EnumCampaignType.SOCIAL_REFERRAL.getId()%>">Social Referral</a>
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
              <i class="icon-plus-sign icon-white"></i>&nbsp;Add Campaign
            </s:link>
          </div>
        </div>

        <div class="row-fluid">
          <div class="span12">
            <div class="widget-box">
              <div class="widget-title">
								<span class="icon">
									<i class="icon-filter"></i>
								</span>
                <h5>Campaigns</h5>
              </div>
              <div class="widget-content nopadding">
                <table class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Status</th>
                    <th>Reward Type</th>
                    <th>Approved</th>
                    <th>Pending</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th style="width: 10%">Actions</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${campaignSearchAction.campaigns}" var="campaign">
                    <tr>
                      <td>${campaign.name}</td>
                      <td>${campaign.campaignType.type}</td>
                      <td><span class="label label-success">Active</span> </td>
                      <%--<td>${campaign.active}</td>--%>
                      <td>${campaign.commissionPlan.commissionStrategy.name}</td>
                      <td>Rs. 3000</td>
                      <td>Rs. 180</td>
                      <td>${campaign.startDate}</td>
                      <td>${campaign.endDate}</td>
                      <td>
                        <div class="btn-group">
                          <s:link beanclass="com.ds.action.campaign.CampaignAction"
                                  event="createOrEditCampaign" class="btn tip-bottom" title="Edit">
                           <i class="icon-edit"></i>
                            <s:param name="campaignId" value="${campaign.id}"/>
                          </s:link>
                          <s:link beanclass="com.ds.action.campaign.CampaignIntegrateAction"
                                  event="integrate" class="btn tip-bottom" title="Integrate">
                           <i class="icon-edit"></i>
                            <s:param name="campaignId" value="${campaign.id}"/>
                          </s:link>

                         <%-- <s:link beanclass="com.ds.action.employee.UserAction"
                                  event="createOrEditUser" title="Edit">
                            <i class="icon-edit"></i>
                            <s:param name="employeeId" value="${user.username}"/>
                          </s:link>--%>
                        </div>
                      </td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>

              <div class="row-fluid">
                <div class="span3">
                  <s:layout-render name="/layouts/paginationResultCount.jsp" paginatedBean="${campaignSearchAction}"/>
                </div>
                <div class="span9">
                  <s:layout-render name="/layouts/pagination.jsp" paginatedBean="${campaignSearchAction}"/>
                </div>

              </div>

                <%--<div class="pagination">
                  <ul>
                    <li class="disabled"><span>Prev</span></li>
                    <li class="active"><span>1</span></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">6</a></li>
                    <li><a href="#">7</a></li>
                    <li><a href="#">8</a></li>
                    <li><a href="#">Next</a></li>
                  </ul>
                </div>--%>
            </div>
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
  <s:layout-component name="scriptComponent">

    <script type="text/javascript">

      $(document).ready(function() {
        $('.cpType').click(function(event) {
          var type = $(this).attr('type');
          $("#campaignType").val(type);
          var searchForm = $("#campaignSearchForm")[0];
          var actionUrl = searchForm.action;
          actionUrl += '?searchCampaign';
          searchForm.action = actionUrl;
          searchForm.submit();
        });

        $.each($(".cpType"), function(index, value) {
          var type = $(this).attr('type');
          var selType = $("#campaignType").val();

          if (selType === type) {
            $(this).addClass('disabled');
          }
        });
      });

    </script>
  </s:layout-component>

</s:layout-render>