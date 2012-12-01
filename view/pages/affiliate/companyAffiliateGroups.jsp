<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

	<%--<s:layout-component name="heading">Split Base Order Manually</s:layout-component>--%>
	<s:layout-component name="content">
		<s:useActionBean beanclass="com.ds.action.affiliate.CompanyAffiliateGroupSearchAction"
		                 var="companyAffiliateGroupSearchAction"/>
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
								<p class="lead">Affiliate Groups</p>
							</div>
							<div class="span3 offset2">
								<s:link beanclass="com.ds.action.affiliate.CompanyAffiliateGroupAction"
								        event="createOrEditCompanyAffiliateGroup" class="btn btn-primary">Add New Group
								</s:link>
							</div>
						</div>
					</div>
						<%--<ul class="breadcrumb">
												<li><a href="#">Setup</a> <span class="divider">/</span></li>
												<li class="active">User Accounts</li>
											</ul>--%>


					<fieldset>
						<legend><em>Filter Groups</em></legend>
						<s:form beanclass="com.ds.action.affiliate.CompanyAffiliateGroupSearchAction"
						        class="form-inline"
						        style="margin-bottom:10px;">
							<s:label name="Group Name"/>
							<s:text name="name"/>
							<s:submit name="searchCompanyAffiliateGroups" class="btn btn-warning">Search</s:submit>
						</s:form>
					</fieldset>


					<table class="striped table-condensed table-hover table-striped">

						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Description</th>
							<th>Affiliates</th>
							<th>Actions</th>
						</tr>

						<tbody>
						<c:forEach items="${companyAffiliateGroupSearchAction.companyAffiliateGroups}"
						           var="companyAffiliateGroup">
							<tr>
								<td>${companyAffiliateGroup.id}</td>
								<td>${companyAffiliateGroup.name}</td>
								<td>${companyAffiliateGroup.description}</td>
								<td>4</td>
								<td>
									<s:link beanclass="com.ds.action.affiliate.CompanyAffiliateGroupAction"
									        event="createOrEditCompanyAffiliateGroup" class="button blue small">
										<span class="icon white small" data-icon="7"></span>Edit
										<s:param name="companyAffiliateGroupId" value="${companyAffiliateGroup.id}"/>
									</s:link>
										<%--<s:link beanclass="com.ds.action.affiliate.CompanyAffiliateActioneAction"
											 event="resendWelcomeEmail" class="button blue small">
										 <span class="icon white small" data-icon="7"></span>Delete
										 <s:param name="affiliateId" value="${affiliateGroup.id}"/>
									 </s:link>--%>
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
					<s:layout-render name="/layouts/paginationResultCount.jsp"
					                 paginatedBean="${companyAffiliateGroupSearchAction}"/>
					<s:layout-render name="/layouts/pagination.jsp" paginatedBean="${companyAffiliateGroupSearchAction}"/>
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