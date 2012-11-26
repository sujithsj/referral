<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

	<s:layout-component name="content">
		<s:useActionBean beanclass="com.ds.action.affiliate.CompanyAffiliateAction" var="companyAffiliateAction"/>
		<div class="content-outer wrap">
			<div class="col_12">
				<div id="page-heading">
					<c:choose>
						<c:when test="${companyAffiliateAction.companyAffiliateId != null}">
							<h4>Edit Affiliate</h4>
						</c:when>
						<c:otherwise>
							<h4>Create Affiliate</h4>
						</c:otherwise>
					</c:choose>
				</div>

				<s:form beanclass="com.ds.action.affiliate.CompanyAffiliateAction" class="vertical">
					<div class="col_6">

						<s:label name="Login"/>
						<c:choose>
							<c:when test="${companyAffiliateAction.companyAffiliateId != null}">
								<s:text name="affiliateDTO.login" value="${companyAffiliateAction.affiliateDTO.login}" class="check-empty auto-adjust" readonly="true"/>
							</c:when>
							<c:otherwise>
								<s:text name="affiliateDTO.login" class="check-empty auto-adjust"/>
							</c:otherwise>
						</c:choose>

						<s:label name="Email"/>
						<s:text name="affiliateDTO.email" class="check-empty auto-adjust"/>

						<s:label name="First Name"/>
						<s:text name="affiliateDTO.firstName" class="check-empty auto-adjust"/>

						<s:label name="Last Name"/>
						<s:text name="affiliateDTO.lastName" class="check-empty auto-adjust"/>


							<%--<c:set var="companyAffiliates" value="${affiliateAction.companyAffiliates}"/>--%>
						<s:select name="companyAffiliateDTO.parentCompanyAffiliateId"
						          style="height:30px;font-size:1.2em;padding:1px;">
							<s:option value="">-None-</s:option>
							<c:forEach items="${companyAffiliateAction.companyAffiliateListExcludingSelf}" var="comAff">
								<s:option value="${comAff.id}">${comAff.affiliate.firstName}</s:option>
							</c:forEach>
						</s:select>

						<s:label name="Password"/>
						<s:password name="affiliateDTO.passwordChecksum" class="check-empty auto-adjust"/>


						company short name is :: ${companyAffiliateAction.companyShortName}
					</div>

					<div class="clear"></div>

					<div class="col_2">
						<s:hidden name="affiliateDTO.affiliateId" value="${companyAffiliateAction.affiliateDTO.affiliateId}"/>
						<s:hidden name="companyAffiliateDTO.companyAffiliateId" value="${companyAffiliateAction.companyAffiliateDTO.companyAffiliateId}"/>
						<s:hidden name="companyAffiliateDTO.affiliateId" value="${companyAffiliateAction.companyAffiliateDTO.affiliateId}"/>
						<s:hidden name="companyAffiliateId" value="${companyAffiliateAction.companyAffiliateId}"/>
						<s:hidden name="companyShortName" value="${companyAffiliateAction.companyShortName}"/>
						<s:submit name="saveAffiliate" value="Save Changes" class="button blue small"/>
					</div>


					<div class="col_2">
						<s:link beanclass="com.ds.action.affiliate.CompanyAffiliateSearchAction"
						        class="button blue small"><span class="icon white small"
						                                        data-icon=":"></span>Back</s:link>
					</div>
				</s:form>
			</div>
		</div>
	</s:layout-component>
</s:layout-render>
