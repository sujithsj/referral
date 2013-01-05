<%@ page import="com.ds.constants.EnumMarketingMaterialType" %>
<%@ page import="com.ds.constants.FileManageType" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <s:layout-component name="content">
    <s:useActionBean beanclass="com.ds.action.marketing.MarketingMaterialAction" var="mmAction"/>
    <div class="content-outer wrap">
      <div class="col_12">
        <div id="page-heading">
          <c:choose>
            <c:when test="${mmAction.marketingMaterialId != null}">
              <h4>Edit Ad</h4>
            </c:when>
            <c:otherwise>
              <h4>Create Ad</h4>
            </c:otherwise>
          </c:choose>
        </div>

        <s:form beanclass="com.ds.action.marketing.MarketingMaterialAction" class="vertical">

          <div class="col_6">

            <s:select name="type">
              <c:forEach items="<%=EnumMarketingMaterialType.getAllMMTypes()%>" var="pType">
                <s:option value="${pType.id}">${pType.type}</s:option>
              </c:forEach>
            </s:select>

            <c:forEach items="${mmAction.allCampaigns}" var="campaign">
              <input type="checkbox" campaignId=${campaign.id}/>${campaign.name}
            </c:forEach>


            <s:label name="Title"/>
            <s:text name="title" class="check-empty auto-adjust"/>

            <s:label name="Body"/>
            <s:text name="body" class="auto-adjust"/>

            <s:label name="Landing Page Url"/>
            <s:text name="landingPageURL" class="check-empty auto-adjust"/>


          </div>

          <div class="clear"></div>

          <div class="col_2">
            <s:hidden name="marketingMaterialId"/>
            <s:submit name="saveMarketingMaterial" value="Save Changes" class="button blue small"/>
          </div>

          <div class="col_2">
            <s:link beanclass="com.ds.action.marketing.MarketingMaterialSearchAction"
                    class="button blue small"><span class="icon white small" data-icon=":"></span>Back</s:link>
          </div>
        </s:form>

        <h1>Upload Image for Ad</h1>
        <img src="/getImage?imageId=${mmAction.imageId}" alt="CompanyLogo">
        <s:link beanclass="com.ds.action.core.FileManageAction" event="deleteMarketingMaterialImage"
                class="button blue small"><span class="icon white small" data-icon=":"></span>Delete Logo
          <s:param name="companyShortName" value="${mmAction.marketingMaterialId}"/>
        </s:link>
        <form action="/fileUpload" multipart="1" method="post" enctype="multipart/form-data" id="mmImageUploadForm">
          <input type="file" name="file" class="formelement" style="width: 312px;" id="marketing_tool_banner">
          <input type="hidden" name="fileManageType" value="<%=FileManageType.MARKETING_MATERIAL%>">
          <input type="hidden" name="identifier" value="${mmAction.marketingMaterialId}">
          <input type="submit" value="upload" class="button blue big">
        </form>


      </div>
    </div>
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