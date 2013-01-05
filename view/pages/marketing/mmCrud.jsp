<%@ page import="com.ds.constants.EnumMarketingMaterialType" %>
<%@ page import="com.ds.constants.FileManageType" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

<s:layout-component name="content">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/marketing/textAd.css" type="text/css"/>
<s:layout-render name="/includes/companyHeader.jsp"/>
<s:layout-render name="/includes/companySideBar.jsp"/>

<s:useActionBean beanclass="com.ds.action.marketing.MarketingMaterialAction" var="mmAction"/>
<div id="content">
  <c:choose>
    <c:when test="${mmAction.marketingMaterialId != null}">
      <s:layout-render name="/includes/companyContentHeader.jsp" includeHeaderBtnGrp="false"
                       headerLabel="Edit Ad"/>
    </c:when>
    <c:otherwise>
      <s:layout-render name="/includes/companyContentHeader.jsp" includeHeaderBtnGrp="false"
                       headerLabel="Create Ad"/>
    </c:otherwise>
  </c:choose>

  <s:form beanclass="com.ds.action.marketing.MarketingMaterialAction" class="form-horizontal" id="mmCrudForm">
    <div class="container-fluid">

      <div class="row-fluid">
        <div class="span5">
          <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-file"></i>
								</span>
              <h5>Ad</h5>
            </div>
            <div class="widget-content nopadding">
              <div class="control-group">
                <s:label class="control-label" name="Type Of Ad"/>
                <div class="controls">
                  <s:select name="type" id="adTypeSelect">
                    <c:forEach items="<%=EnumMarketingMaterialType.getAllMMTypes()%>" var="pType">
                      <s:option value="${pType.id}">${pType.type}</s:option>
                    </c:forEach>
                  </s:select>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="span7">
          <div class="widget-box">
            <div class="widget-title">
              <a href="#collapseCampaigns" data-toggle="collapse">
								<span class="icon">
									<i class="icon-magnet"></i>
								</span>
                <h5>Campaigns</h5>
              </a>
            </div>
            <div class="collapse in " id="collapseCampaigns" style="border-bottom:1px solid #CDCDCD;">
              <div class="widget-content" style="border-bottom:none;">
                <div class="span4">
                  <c:forEach items="${mmAction.allCampaigns}" var="campaign" varStatus="campaignCtr">
                    <c:if test="${campaignCtr.index % 2 == 0}">
                      <input type="checkbox" campaignId=${campaign.id}/>${campaign.name} <br/>
                    </c:if>
                  </c:forEach>
                </div>
                <div class="span4">
                  <c:forEach items="${mmAction.allCampaigns}" var="campaign" varStatus="campaignCtr">
                    <c:if test="${campaignCtr.index % 2 != 0}">
                      <input type="checkbox" campaignId=${campaign.id}/>${campaign.name} <br/>
                    </c:if>
                  </c:forEach>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>

      <div class="row-fluid">
        <div class="span5">
          <div class="widget-box">
            <div class="widget-title">
              <a href="#collapseAdInfo" data-toggle="collapse">
								<span class="icon">
									<i class="icon-magnet"></i>
								</span>
                <h5>Ad Content</h5>
              </a>
            </div>
            <div class="collapse in " id="collapseAdInfo">
              <div class="widget-content nopadding">

                <div class="control-group">
                  <s:label class="control-label" name="Title"/>
                  <div class="controls">
                    <s:text class="required" name="title"/>
                  </div>
                </div>
                <div class="control-group">
                  <s:label class="control-label" name="Body"/>
                  <div class="controls">
                    <s:textarea name="body"/>
                  </div>
                </div>
                <div class="control-group">
                  <s:label class="control-label" name="Landing Page Url"/>
                  <div class="controls">
                    <s:text name="landingPageURL" class="required"/>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="span7" id="adPreviewDiv">
          <div class="widget-box">
            <div class="widget-title">
              <a href="#collapseAdPreview" data-toggle="collapse">
								<span class="icon">
									<i class="icon-magnet"></i>
								</span>
                <h5>Ad Preview</h5>
              </a>
            </div>
            <div class="collapse in" id="collapseAdPreview">
              <div class="widget-content" id="previewContent">
                Ad Preview goes here
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row-fluid">
        <s:hidden name="marketingMaterialId" id="marketingMaterialId"/>
        <s:submit name="saveMarketingMaterial" value="Save Changes" class="btn btn-success"/>

        <s:link beanclass="com.ds.action.employee.UserSearchAction"
                class="btn btn-inverse"><i class="icon-hand-left icon-white"></i>&nbsp;Back</s:link>
      </div>
    </div>
  </s:form>
  <c:if test="${mmAction.marketingMaterialId !=null}">
    <div class="container-fluid" id="imageContainer" style="display:none">
      <div class="row-fluid">
        <div class="span5">
          <div class="widget-box">
            <div class="widget-title">
              <a href="#collapseImageWidget" data-toggle="collapse">
								<span class="icon">
									<i class="icon-magnet"></i>
								</span>
                <h5>Upload New Banner Image</h5>
              </a>
            </div>
            <div class="collapse in" id="collapseImageWidget">
              <div class="widget-content">
                <form action="/fileUpload" multipart="1" method="post" enctype="multipart/form-data"
                      id="mmImageUploadForm">
                  <input type="file" name="file" class="formelement" style="width: 312px;" id="marketing_tool_banner">
                  <input type="hidden" name="fileManageType" value="<%=FileManageType.MARKETING_MATERIAL%>">
                  <input type="hidden" name="identifier" value="${mmAction.marketingMaterialId}">
                  <input type="submit" value="&nbsp;Upload" class="btn btn-success"><i
                    class="icon-hand-left icon-white"></i></input>
                </form>
              </div>
            </div>
          </div>
        </div>
        <c:if test="${mmAction.imageId !=null}">
          <div class="span7">
            <div class="widget-box">
              <div class="widget-title">
                <a href="#collapseExistingImageWidget" data-toggle="collapse">
								<span class="icon">
									<i class="icon-magnet"></i>
								</span>
                  <h5>Existing Image</h5>
                </a>
              </div>
              <div class="collapse in" id="collapseExistingImageWidget">
                <div class="widget-content">
                  <img src="/getImage?imageId=${mmAction.imageId}" alt="CompanyLogo">
                  <br/><br/>
                  <s:link beanclass="com.ds.action.core.FileManageAction" event="deleteMarketingMaterialImage"
                          class="btn btn-danger"><i class="icon-hand-left icon-white"></i>&nbsp;Delete Image
                    <s:param name="companyShortName" value="${mmAction.marketingMaterialId}"/>
                  </s:link>
                </div>
              </div>
            </div>
          </div>
        </c:if>
      </div>
    </div>
  </c:if>

  <s:layout-render name="/includes/footer.jsp"/>
</div>
</div>

</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/marketing/mmCrud.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/js/unicorn.form_validation.js"></script>


</s:layout-component>

<s:layout-component name="scriptComponent">

  
</s:layout-component>

</s:layout-render>