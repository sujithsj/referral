<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

	<%--<s:layout-component name="heading">Split Base Order Manually</s:layout-component>--%>
	<s:layout-component name="content">
		<s:useActionBean beanclass="com.ds.action.affiliate.CompanyAffiliateInviteAction" var="companyAffiliateInviteAction"/>
		<div class="container">

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
								<%--<s:link beanclass="com.ds.action.affiliate.CompanyAffiliateInviteAction"
								        event="displayAllCompanyInvites" class="btn btn-primary">Add New Affiliate
								</s:link>--%>
								<s:link beanclass="com.ds.action.affiliate.CompanyAffiliateGroupSearchAction"
								        class="btn btn-primary">Groups
								</s:link>
							</div>
						</div>
					</div>

					<fieldset>
						<legend><em>Send Invite to Affiliate</em></legend>
						<s:form beanclass="com.ds.action.affiliate.CompanyAffiliateInviteAction" class="form-inline"
						        style="margin-bottom:10px;">
							<s:label name="Affiliate Email"/>
							<s:text name="affiliateEmail" placeholder="affiliate email"/>
							<s:submit name="sendInvite" class="btn btn-warning">Send Invite</s:submit>
						</s:form>
					</fieldset>


					<table class="striped table-condensed table-hover table-striped">

						<tr>
							<th>Invite Id</th>
							<th>Affiliate Email</th>
							<th>Status</th>
							<th>Action</th>
						</tr>

						<tbody>
						<c:forEach items="${companyAffiliateInviteAction.companyAffiliateInvites}"
						           var="companyAffiliateInvite">
							<tr>
								<td>${companyAffiliateInvite.id}</td>
								<td>${companyAffiliateInvite.affiliateEmail}</td>
								<td>Yet to Be Converted</td>
								<td>
									resend email waala action
<%--
									<s:link beanclass="com.ds.action.affiliate.CompanyAffiliateAction"
									        event="resendWelcomeEmail" class="button blue small">
										<span class="icon white small" data-icon="7"></span>Resend Welcome mail
										<s:param name="companyAffiliateId" value="${companyAffiliate.id}"/>
									</s:link>
--%>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<s:layout-render name="/layouts/paginationResultCount.jsp"
					                 paginatedBean="${companyAffiliateInviteAction}"/>
					<s:layout-render name="/layouts/pagination.jsp" paginatedBean="${companyAffiliateInviteAction}"/>
				</div>

			</div>
		</div>
	</s:layout-component>
	<s:layout-component name="scriptComponent">

		<script type="text/javascript">

			$(document).ready(function() {

			});

		</script>
	</s:layout-component>
</s:layout-render>