<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

	<%--<s:layout-component name="heading">Split Base Order Manually</s:layout-component>--%>
	<s:layout-component name="content">
		<s:useActionBean beanclass="com.ds.action.affiliate.AffiliateGroupSearchAction" var="affiliateGroupSearchAction"/>
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
								<s:link beanclass="com.ds.action.affiliate.AffiliateGroupAction"
								        event="createOrEditAffiliateGroup" class="btn btn-primary">Add New Group
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
						<s:form beanclass="com.ds.action.affiliate.AffiliateGroupSearchAction" class="form-inline"
						        style="margin-bottom:10px;">
							<s:label name="Group name"/>
							<s:text name="name" />
							<s:label name="Email"/>
              <s:submit name="searchAffiliateGroups" class="btn btn-warning">Search</s:submit>
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
						<c:forEach items="${affiliateGroupSearchAction.affiliateGroups}" var="affiliateGroup">
							<tr>
								<td>${affiliateGroup.id}</td>
								<td>${affiliateGroup.name}</td>
								<td>${affiliateGroup.description}</td>
								<td>4</td>
								<td>
									<%--<s:link beanclass="com.ds.action.affiliate.AffiliateGroupAction"
									        event="createOrEditAffiliateGroup" class="button blue small">
										<span class="icon white small" data-icon="7"></span>Edit
										<s:param name="affiliateGroupId" value="${affiliateGroup.id}"/>
									</s:link>--%>
									<%--<s:link beanclass="com.ds.action.affiliate.AffiliateAction"
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
          <s:layout-render name="/layouts/paginationResultCount.jsp" paginatedBean="${affiliateGroupSearchAction}"/>
          <s:layout-render name="/layouts/pagination.jsp" paginatedBean="${affiliateGroupSearchAction}"/>
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