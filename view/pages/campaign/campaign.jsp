<%@ page import="com.ds.constants.EnumCampaignType" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <%--<s:layout-component name="heading">Split Base Order Manually</s:layout-component>--%>
  <s:layout-component name="content">
    <s:useActionBean beanclass="com.ds.action.campaign.CampaignSearchAction" var="campaignSearchAction"/>
    <div class="container">
      <div class="row">
        <div class="span3 bs-docs-sidebar">
          <s:layout-render name="/includes/menu/setupSidebar.jsp"/>
        </div>

        <div class="span9 wrap">
          <div class="container ">
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

          </div>
            <%--<ul class="breadcrumb">
              <li><a href="#">Setup</a> <span class="divider">/</span></li>
              <li class="active">User Accounts</li>
            </ul>--%>

          <fieldset>
            <legend><em>Search Campaign</em></legend>
            <s:form beanclass="com.ds.action.campaign.CampaignSearchAction" class="form-inline"
                    id="campaignSearchForm"
                    style="margin-bottom:10px;">
              <s:label name="Name"/>
              <s:text name="name" placeholder="title"/>
              <s:hidden name="campaignTypeId" id="campaignType"/>

              <div class="btn-toolbar">
                <div class="btn-group">

                  <a class="btn cpType" href="#" type="<%=EnumCampaignType.SALE.getId()%>">All</a>
                  <a class="btn cpType" href="#" type="<%=EnumCampaignType.EMAIL_OPT_IN.getId()%>">Banner</a>
                  <a class="btn cpType" href="#" type="<%=EnumCampaignType.USER_SIGN_UP.getId()%>">Text Ads</a>
                  <a class="btn cpType" href="#" type="<%=EnumCampaignType.AFFILIATE_SIGN_UP.getId()%>">Text Ads</a>
                </div>
              </div>

              <s:submit name="searchCampaign" class="btn btn-warning">Search</s:submit>

              <%--Total Ads: ${mmSearchAction.totalAdCount}
                <span class="badge badge-info">Banner : ${mmSearchAction.totalBannerAds}</span>
                <span class="badge badge-info">Text ads : ${mmSearchAction.totalTextAds}</span>--%>
            </s:form>
          </fieldset>


          <table class="striped table-condensed table-hover table-striped">

            <tr>
              <th>Name</th>
              <th>Type</th>
              <th>Status</th>
              <th>Reward Type</th>
              <th>Approved</th>
              <th>Pending</th>
              <th>Start Date</th>
              <th>End Date</th>
              <th>Actions</th>
            </tr>

            <tbody>
            <c:forEach items="${campaignSearchAction.campaigns}" var="campaign">
              <tr>
                <td>${campaign.name}</td>
                <td>${campaign.campaignType.type}</td>
                <%--<td>${campaign.ac}</td>--%>
                <td>
                  <s:link beanclass="com.ds.action.campaign.CampaignAction"
                          event="createOrEditCampaign" class="button blue small">
                    <span class="icon white small" data-icon="7"></span>Edit
                    <s:param name="campaignId" value="${campaign.id}"/>
                  </s:link>

                    <%-- <s:link beanclass="com.hk.action.admin.crud.catalog.tags.AssociateTagsAction"
                            event="entityTags" target="_blank" class="button orange small">Tag
                      <s:param name="entityId" value="${brand.id}"/>
                      <s:param name="type" value="${type}"/>
                    </s:link>--%>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
          <s:layout-render name="/layouts/paginationResultCount.jsp" paginatedBean="${campaignSearchAction}"/>
          <s:layout-render name="/layouts/pagination.jsp" paginatedBean="${campaignSearchAction}"/>
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