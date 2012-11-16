<%@ page import="com.ds.constants.EnumMarketingMaterialType" %>
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
      </div>
    </div>
  </s:layout-component>
</s:layout-render>