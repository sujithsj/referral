<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <s:layout-component name="content">
    <s:useActionBean beanclass="com.ds.action.affiliate.AffiliateAction" var="affiliateAction"/>
    <div class="content-outer wrap">
      <div class="col_12">
        <div id="page-heading">
	        <c:choose>
		        <c:when test="${affiliateAction.affiliateId != null}">
			        <h4>Edit Affiliate</h4>
		        </c:when>
		        <c:otherwise>
			        <h4>Create Affiliate</h4>
		        </c:otherwise>
	        </c:choose>
        </div>

        <s:form beanclass="com.ds.action.affiliate.AffiliateAction" class="vertical">
          <div class="col_6">

            <s:label name="Login"/>
            <s:text name="affiliateDTO.login" class="check-empty auto-adjust"/>

	          <s:label name="Email"/>
	          <s:text name="affiliateDTO.email" class="check-empty auto-adjust"/>

	          <s:label name="First Name"/>
	          <s:text name="affiliateDTO.firstName" class="check-empty auto-adjust"/>

	          <s:label name="Last Name"/>
	          <s:text name="affiliateDTO.lastName" class="check-empty auto-adjust"/>


	          <%--<c:set var="companyAffiliates" value="companyAffiliates"/>
	          <s:select name="parentAffiliateId" style="height:30px;font-size:1.2em;padding:1px;">
		          <s:option value="0">-None-</s:option>
		          <c:forEach items="${companyAffiliates}" var="comAff">
			          <s:option value="${comAff.id}">${comAff.name}</s:option>
		          </c:forEach>
	          </s:select>--%>

            <s:label name="Password"/>
            <s:text name="affiliateDTO.passwordChecksum" class="check-empty auto-adjust"/>


	        company short name is :: ${affiliateAction.affiliateDTO.companyShortName}
          </div>

          <div class="clear"></div>

          <div class="col_2">
            <s:hidden name="affiliateId"/>
            <s:hidden name="affiliateDTO.companyShortName" value="${affiliateAction.affiliateDTO.companyShortName}"/>
            <s:submit name="updateAffiliate" value="Save Changes" class="button blue small"/>
          </div>


          <div class="col_2">
            <s:link beanclass="com.ds.action.affiliate.AffiliateSearchAction" 
                    class="button blue small"><span class="icon white small" data-icon=":"></span>Back</s:link>
          </div>
        </s:form>
      </div>
    </div>
  </s:layout-component>
</s:layout-render>
