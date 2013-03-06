<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

	<s:layout-component name="content">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/daterangepicker.css" type="text/css"/>
		<s:layout-render name="/includes/aff/affiliateHeader.jsp"/>
		<s:layout-render name="/includes/aff/affiliateSideBar.jsp"/>

		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		<s:useActionBean beanclass="com.ds.action.aff.AffiliateAdsAction" var="affiliateAdsAction"/>
		<div id="content">
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>Home</a>
				<a href="#" class="current">Dashboard</a>
			</div>

			<s:form beanclass="com.ds.action.aff.AffiliateAdsAction" class="form-horizontal" id="mmCrudForm">
				<div class="container-fluid">

					<div class="row-fluid">
						<div class="span7">
							<div class="widget-box">
								<div class="widget-title">
									<a href="#collapseCampaigns" data-toggle="collapse">
									<span class="icon">
										<i class="icon-magnet"></i>
									</span>
										<h5>Company</h5>
									</a>
								</div>
								<div class="collapse in " id="collapseCampaigns" style="border-bottom:1px solid #CDCDCD;">
									<div class="widget-content" style="border-bottom:none;">
										<s:select name="companyShortName" style="width:400px;">
											<c:forEach items="${affiliateAdsAction.allEligibleCompanies}" var="company">
												<s:option value="${company.shortName}">${company.name}</s:option>
											</c:forEach>
										</s:select>
									</div>
								</div>
							</div>

						</div>
					</div>

					<div class="row-fluid">
						<s:hidden name="marketingMaterialId" id="marketingMaterialId"/>
						<s:submit name="saveMarketingMaterial" value="Save Changes" class="btn btn-success"/>

						<s:link beanclass="com.ds.action.marketing.MarketingMaterialSearchAction"
						        class="btn btn-inverse"><i class="icon-hand-left icon-white"></i>&nbsp;Back</s:link>
					</div>
				</div>
			</s:form>
			<div class="container-fluid">
				<input type="hidden" id="startDate"/>
				<input type="hidden" id="endDate"/>
			</div>
		</div>


		<s:layout-render name="/includes/footer.jsp"/>


		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.validate.js"></script>
		<%--<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/marketing/mmCrud.js"></script>--%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/unicorn.form_validation.js"></script>

	</s:layout-component>

</s:layout-render>