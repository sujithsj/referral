<%@ page import="com.ds.constants.EnumCampaignType" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <%--<s:layout-component name="heading">Split Base Order Manually</s:layout-component>--%>
  <s:layout-component name="content">
    <s:useActionBean beanclass="com.ds.action.aff.AffiliateCampaignSearchAction" var="affCampaignSearchAction"/>
    <div class="container">
      <%--<div class="row">
        <div class="span3 bs-docs-sidebar">
          <s:layout-render name="/includes/menu/setupSidebar.jsp"/>
        </div>--%>

        <div class="span9 wrap">
         <%-- <div class="container ">
            <div class="row">
              <div class="span4">
                <p class="lead">Campaign</p>
              </div>
              <div class="span3 offset2">
                <s:link beanclass="com.ds.action.campaign.CampaignAction"
                        event="createOrEditCampaign" class="btn btn-primary">Create Campaign
                </s:link>
              </div>
            </div>

          </div>--%>
            <%--<ul class="breadcrumb">
              <li><a href="#">Setup</a> <span class="divider">/</span></li>
              <li class="active">User Accounts</li>
            </ul>--%>

         <%-- <fieldset>
            <legend><em>Search Campaign</em></legend>
            <s:form beanclass="com.ds.action.campaign.CampaignSearchAction" class="form-inline"
                    id="campaignSearchForm"
                    style="margin-bottom:10px;">
              <s:label name="Name"/>
              <s:text name="name" placeholder="title"/>
              <s:hidden name="campaignTypeId" id="campaignType"/>

              <div class="btn-toolbar">
                <div class="btn-group">

                  <a class="btn cpType" href="#" type="<%=EnumCampaignType.ALL.getId()%>">All</a>
                  <a class="btn cpType" href="#" type="<%=EnumCampaignType.SALE.getId()%>">Sale</a>
                  <a class="btn cpType" href="#" type="<%=EnumCampaignType.EMAIL_OPT_IN.getId()%>">Email Opt In</a>
                  <a class="btn cpType" href="#" type="<%=EnumCampaignType.USER_SIGN_UP.getId()%>">User Sign Up</a>
                  <a class="btn cpType" href="#" type="<%=EnumCampaignType.AFFILIATE_SIGN_UP.getId()%>">Affiliate Sign
                    Up</a>
                </div>
              </div>

              <s:submit name="searchCampaign" class="btn btn-warning">Search</s:submit>

              --%><%--Total Ads: ${mmSearchAction.totalAdCount}
                <span class="badge badge-info">Banner : ${mmSearchAction.totalBannerAds}</span>
                <span class="badge badge-info">Text ads : ${mmSearchAction.totalTextAds}</span>--%><%--
            </s:form>
          </fieldset>--%>


          <table class="striped table-condensed table-hover table-striped">

            <tr>
              <th>Name</th>
              <th>Type</th>
              <%--<th>Status</th>--%>
              <th>Reward Type</th>
              <th>Approved</th>
              <th>Pending</th>
              <th>Start Date</th>
              <th>End Date</th>
              <th>Actions</th>
            </tr>

            <tbody>
            <c:forEach items="${affCampaignSearchAction.campaigns}" var="campaign">
              <tr>
                <td>${campaign.name}</td>
                <td>${campaign.campaignType.type}</td>
                <%--<td>${campaign.active}</td>--%>
                <td>${campaign.commissionPlan.commissionStrategy.name}</td>
                <td>$Approved Commision</td>
                <td>$Pending Commision</td>
                <td>${campaign.startDate}</td>
                <td>${campaign.endDate}</td>
                <td>
                  <%--<s:link beanclass="com.ds.action.campaign.CampaignAction"
                          event="createOrEditCampaign" class="button blue small">
                    <span class="icon white small" data-icon="7"></span>Edit
                    <s:param name="campaignId" value="${campaign.id}"/>
                  </s:link>--%>

                   
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
          <s:layout-render name="/layouts/paginationResultCount.jsp" paginatedBean="${affCampaignSearchAction}"/>
          <s:layout-render name="/layouts/pagination.jsp" paginatedBean="${affCampaignSearchAction}"/>
        </div>

      </div>
    </div>
  </s:layout-component>
  <s:layout-component name="scriptComponent">

    <script type="text/javascript">

      $(document).ready(function() {
        
      });

    </script>
  </s:layout-component>
</s:layout-render>