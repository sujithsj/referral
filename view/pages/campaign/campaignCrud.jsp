<%@ page import="com.ds.constants.EnumCampaignType" %>
<%@ page import="com.ds.constants.EnumCommisionStrategy" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <s:layout-component name="content">
    <s:useActionBean beanclass="com.ds.action.campaign.CampaignAction" var="campaignAction"/>
    <div class="content-outer wrap">
    <div class="col_12">
      <div id="page-heading">
        <c:choose>
          <c:when test="${campaignAction.campaignId != null}">
            <h4>Edit Campaign</h4>
          </c:when>
          <c:otherwise>
            <h4>Create campaign</h4>
          </c:otherwise>
        </c:choose>
      </div>

      <s:form beanclass="com.ds.action.campaign.CampaignAction" class="vertical">
      <div class="col_6">

        <div class="control-group">
          <s:label class="control-label" name="Campaign Name"/>
          <div class="controls ">
            <s:text name="campaignDTO.name" placeholder="campaign name"/>
          </div>
        </div>

        <div class="control-group">
          <s:label class="control-label" name="Campaign Type"/>
          <div class="controls ">
            <s:select name="campaignDTO.campaignTypeId">
              <c:forEach items="<%=EnumCampaignType.getAllCampaignTypes()%>" var="pType">
                <s:option value="${pType.id}">${pType.type}</s:option>
              </c:forEach>
            </s:select>
          </div>

          <div class="control-group">
            <s:label class="control-label" name="How do you want to reward your affiliates?"/>
            <div class="controls ">
              <s:select name="commissionPlanDTO.commissionStrategyId">
                <c:forEach items="<%=EnumCommisionStrategy.getAllCommissionStategies()%>" var="stType">
                  <s:option value="${stType.id}">${stType.name}</s:option>
                </c:forEach>
              </s:select>
            </div>


            <div class="control-group" id="">
            <s:label class="control-label" name="How do you want to reward your affiliates?"/>
            <div class="controls ">
              <s:select name="commissionPlanDTO.commissionStrategyId">
                <c:forEach items="<%=EnumCommisionStrategy.getAllCommissionStategies()%>" var="stType">
                  <s:option value="${stType.id}">${stType.name}</s:option>
                </c:forEach>
              </s:select>
            </div>


          </div>


            <%--<s:label name="Title"/>
                        <s:text name="title" class="check-empty auto-adjust"/>

                        <s:label name="Body"/>
                        <s:text name="body" class="auto-adjust"/>

                        <s:label name="Landing Page Url"/>
                        <s:text name="landingPageURL" class="check-empty auto-adjust"/>
            --%>

        </div>

        <div class="clear"></div>

          <%-- <div class="col_2">
            <s:hidden name="marketingMaterialId"/>
            <s:submit name="saveMarketingMaterial" value="Save Changes" class="button blue small"/>
          </div>--%>

        <div class="col_2">
          <s:link beanclass="com.ds.action.marketing.MarketingMaterialSearchAction"
                  class="button blue small"><span class="icon white small" data-icon=":"></span>Back</s:link>
        </div>
        </s:form>


      </div>
    </div>
  </s:layout-component>
</s:layout-render>