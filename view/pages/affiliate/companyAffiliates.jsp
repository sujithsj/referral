<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

	<%--<s:layout-component name="heading">Split Base Order Manually</s:layout-component>--%>
	<s:layout-component name="content">
		<s:useActionBean beanclass="com.ds.action.affiliate.CompanyAffiliateSearchAction" var="companyAffiliateSearchAction"/>
		<div class="container">

				<%--<s:layout-render name="${pageContext.request.contextPath}/includes/menu/setupSidebar.jsp"/>--%>

			<div class="row">
				<div class="span3 bs-docs-sidebar">
					<s:layout-render name="/includes/menu/setupSidebar.jsp"/>
				</div>

				<div class="span9 wrap">
					<div class="container">
						<div class="row">
							<div class="span4">
								<p class="lead">Affiliate Accounts</p>
							</div>
							<div class="span3 offset2">
								<s:link beanclass="com.ds.action.affiliate.CompanyAffiliateAction"
								        event="createOrEditCompanyAffiliate" class="btn btn-primary">Add New Affiliate
								</s:link>
								<s:link beanclass="com.ds.action.affiliate.CompanyAffiliateGroupSearchAction" class="btn btn-primary">Groups
								</s:link>
							</div>
						</div>
					</div>
						<%--<ul class="breadcrumb">
												<li><a href="#">Setup</a> <span class="divider">/</span></li>
												<li class="active">User Accounts</li>
											</ul>--%>


					<fieldset>
						<legend><em>Filter Affiliates</em></legend>
						<s:form beanclass="com.ds.action.affiliate.CompanyAffiliateSearchAction" class="form-inline"
						        style="margin-bottom:10px;">
							<s:label name="Affiliate name"/>
							<s:text name="login" placeholder="user name"/>
							<s:label name="Email"/>
							<s:text name="email"/>
              <s:submit name="searchCompanyAffiliates" class="btn btn-warning">Search</s:submit>
						</s:form>
					</fieldset>



					<table class="striped table-condensed table-hover table-striped">

						<tr>
							<th>User Id</th>
							<th>Name</th>
							<th>Email</th>
							<th>Actions</th>
						</tr>

						<tbody>
						<c:forEach items="${companyAffiliateSearchAction.companyAffiliates}" var="companyAffiliate">
							<tr>
								<td>${companyAffiliate.affiliate.login}</td>
								<td>${companyAffiliate.affiliate.firstName} ${companyAffiliate.affiliate.lastName}</td>
								<td>${companyAffiliate.affiliate.email}</td>
								<td>
									<s:link beanclass="com.ds.action.affiliate.CompanyAffiliateAction"
									        event="createOrEditCompanyAffiliate" class="button blue small">
										<span class="icon white small" data-icon="7"></span>Edit
										<s:param name="companyAffiliateId" value="${companyAffiliate.id}"/>
									</s:link>
									<s:link beanclass="com.ds.action.affiliate.CompanyAffiliateAction"
									        event="resendWelcomeEmail" class="button blue small">
										<span class="icon white small" data-icon="7"></span>Resend Welcome mail
										<s:param name="companyAffiliateId" value="${companyAffiliate.id}"/>
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
          <s:layout-render name="/layouts/paginationResultCount.jsp" paginatedBean="${companyAffiliateSearchAction}"/>
          <s:layout-render name="/layouts/pagination.jsp" paginatedBean="${companyAffiliateSearchAction}"/>
				</div>

			</div>
		</div>
	</s:layout-component>
	<s:layout-component name="scriptComponent">

		<script type="text/javascript">

			$(document).ready(function() {
				/*alert('aaa');
				 $('.bs-docs-sidenav').affix({
				 offset: {
				 top: 155
				 , bottom: 170
				 }
				 });*/
			});

		</script>
	</s:layout-component>
</s:layout-render>