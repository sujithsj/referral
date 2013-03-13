<%@include file="/includes/taglibInclude.jsp" %>

<%
  String headerLabel = (String) pageContext.getAttribute("headerLabel");
  pageContext.setAttribute("headerLabel", headerLabel);
  Boolean includeHeaderBtnGrp = (Boolean) pageContext.getAttribute("includeHeaderBtnGrp");
  if (includeHeaderBtnGrp != null) {
    pageContext.setAttribute("includeHeaderBtnGrp", includeHeaderBtnGrp);
  } else {
    pageContext.setAttribute("includeHeaderBtnGrp", true);
  }
%>
<s:layout-definition>


  <div id="content-header">
    <h1>${headerLabel}</h1>

    <c:if test="${includeHeaderBtnGrp == true}">
      <div class="btn-group">
        <a class="btn btn-large tip-bottom" title="Manage Ads"
           href="${pageContext.request.contextPath}/marketing/MarketingMaterialSearch.action"><i
            class="icon-file"></i></a>
        <a class="btn btn-large tip-bottom" title="Manage Users"
           href="${pageContext.request.contextPath}/employee/UserSearch.action"><i class="icon-user"></i></a>
        <a class="btn btn-large tip-bottom" title="Manage Affiliates"
           href="${pageContext.request.contextPath}/affiliate/CompanyAffiliateSearch.action"><i class="icon-leaf"></i></a>
        <a class="btn btn-large tip-bottom" title="Manage Comments"
           href="${pageContext.request.contextPath}/company/CompanyNotification.action"><i class="icon-comment"></i><span
            class="label label-important">5</span></a>
        <a class="btn btn-large tip-bottom" title="Manage Orders"><i class="icon-shopping-cart"></i></a>

      </div>
    </c:if>
  </div>
</s:layout-definition>