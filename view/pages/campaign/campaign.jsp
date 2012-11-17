<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <%--<s:layout-component name="heading">Split Base Order Manually</s:layout-component>--%>
  <s:layout-component name="content">
    <s:useActionBean beanclass="com.ds.action.campaign.CampaignAction" var="campaignAction"/>
    <div class="container">
      <div class="row">
        <div class="span3 bs-docs-sidebar">
          <s:layout-render name="/includes/menu/setupSidebar.jsp"/>
        </div>

        <div class="span9 wrap">
          <div class="container ">
            <div class="row">
              <div class="span4">
                <p class="lead">Marketing Material</p>
              </div>
              <div class="span3 offset2">
                <s:link beanclass="com.ds.action.marketing.MarketingMaterialAction"
                        event="createOrEditMarketingMaterial" class="btn btn-primary">Create Ad
                </s:link>
              </div>
            </div>

          </div>
            <%--<ul class="breadcrumb">
              <li><a href="#">Setup</a> <span class="divider">/</span></li>
              <li class="active">User Accounts</li>
            </ul>--%>

          <fieldset>
            <legend><em>Search Marketing Material</em></legend>
            <s:form beanclass="com.ds.action.marketing.MarketingMaterialSearchAction" class="form-inline"
                    id="mmSearchForm"
                    style="margin-bottom:10px;">
              <s:label name="Title"/>
              <s:text name="title" placeholder="title"/>
              <s:label name="Landing Page"/>
              <s:text name="landingPage"/>
              <s:hidden name="type" id="mmType"/>

              <div class="btn-toolbar">
                <div class="btn-group">

                  <a class="btn mmType" href="#" type="<%=EnumMarketingMaterialType.ALL.getId()%>">All</a>
                  <a class="btn mmType" href="#" type="<%=EnumMarketingMaterialType.Banner.getId()%>">Banner</a>
                  <a class="btn mmType" href="#" type="<%=EnumMarketingMaterialType.TextLink.getId()%>">Text Ads</a>
                </div>
              </div>

              <s:submit name="searchMarketingMaterial" class="btn btn-warning">Search</s:submit>

              Total Ads: ${mmSearchAction.totalAdCount}
                <span class="badge badge-info">Banner : ${mmSearchAction.totalBannerAds}</span>
                <span class="badge badge-info">Text ads : ${mmSearchAction.totalTextAds}</span>
            </s:form>
          </fieldset>


          <table class="striped table-condensed table-hover table-striped">

            <tr>
              <th>Title</th>
              <th>Type</th>
              <th>Landing Page</th>
              <th>Actions</th>
            </tr>

            <tbody>
            <c:forEach items="${mmSearchAction.marketingMaterials}" var="marketingMaterail">
              <tr>
                <td>${marketingMaterail.title}</td>
                <td>${marketingMaterail.marketingMaterialType.type}</td>
                <td>${marketingMaterail.landingPageUrl}</td>
                <td>
                  <s:link beanclass="com.ds.action.marketing.MarketingMaterialAction"
                          event="createOrEditMarketingMaterial" class="button blue small">
                    <span class="icon white small" data-icon="7"></span>Edit
                    <s:param name="marketingMaterialId" value="${marketingMaterail.id}"/>
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
          <s:layout-render name="/layouts/paginationResultCount.jsp" paginatedBean="${mmSearchAction}"/>
          <s:layout-render name="/layouts/pagination.jsp" paginatedBean="${mmSearchAction}"/>
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