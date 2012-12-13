<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <%--<s:layout-component name="heading">Split Base Order Manually</s:layout-component>--%>
  <s:layout-component name="content">
    <s:useActionBean beanclass="com.ds.action.aff.AffiliateAdSearchAction" var="affMmSearchAction"/>
    <div class="container">
      <div class="row">
          <%--<div class="span3 bs-docs-sidebar">
            <s:layout-render name="/includes/menu/setupSidebar.jsp"/>
          </div>--%>

        <div class="span9 wrap">
            <%--<div class="container ">
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

            </div>--%>


          <fieldset>
            <legend><em>Search Marketing Material</em></legend>
            <s:form beanclass="com.ds.action.aff.AffiliateAdSearchAction" class="form-inline"
                    id="mmSearchForm"
                    style="margin-bottom:10px;">
              <s:label name="Title"/>
              <s:text name="title" placeholder="title"/>
              <s:hidden name="type" id="mmType"/>

              <s:submit name="searchMarketingMaterial" class="btn btn-warning">Search</s:submit>


              <%-- <span class="badge badge-info">Banner : ${mmSearchAction.totalBannerAds}</span>
         <span class="badge badge-info">Text ads : ${mmSearchAction.totalTextAds}</span>--%>
            </s:form>
          </fieldset>


          <table class="striped table-condensed table-hover table-striped">

            <tr>
              <th>Title</th>
              <th>Preview</th>
              <th>Landing Page</th>
              <th>Actions</th>
            </tr>

            <tbody>
            <c:forEach items="${affMmSearchAction.marketingMaterials}" var="marketingMaterail">
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
                  <a href="#" class="shareAd" mmId="${marketingMaterail.id}">Share this ad</a>

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
          <s:layout-render name="/layouts/paginationResultCount.jsp" paginatedBean="${affMmSearchAction}"/>
          <s:layout-render name="/layouts/pagination.jsp" paginatedBean="${affMmSearchAction}"/>
        </div>

      </div>
    </div>
  </s:layout-component>
  <s:layout-component name="scriptComponent">

    <script type="text/javascript">

      $(document).ready(function() {

        var type = getParamFromURL('type');

        if(type){
          $("#mmType").val(type);
        } else{
          $("#mmType").val(-999);
        }
        $(".shareAd").click(function(event) {

          var mmId = $(this).attr('mmId');
          DS.Ajax.getJson("/api/mm/" + mmId + "/share/999", function(response) {
            alert(response.sc);
          })
        });

      });
    </script>
  </s:layout-component>
</s:layout-render>