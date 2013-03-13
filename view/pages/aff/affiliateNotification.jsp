<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

	<s:layout-component name="content">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
		<s:layout-render name="/includes/aff/affiliateHeader.jsp"/>
		<s:layout-render name="/includes/aff/affiliateSideBar.jsp"/>

		<s:useActionBean beanclass="com.ds.action.aff.AffiliateNotificationAction"
		                 var="affiliateNotificationAction"/>
		<div id="content">
			<s:layout-render name="/includes/companyContentHeader.jsp" headerLabel="Affiliate Notifications"/>
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
				<a href="#" class="current">Affiliate Notification</a>
			</div>

			<div class="container-fluid">


				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
								<h5>Pending Notifications</h5>
							</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped">
									<thead>
									<tr>
										<th>Notification Id</th>
										<th>Notification Message</th>
										<th>Date</th>
										<th style="width: 10%">Actions</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${affiliateNotificationAction.notificationList}"
									           var="notification">
										<tr>
											<td>${notification.id}</td>
											<td>${notification.message}</td>
											<td>${notification.createDate}</td>
											<td>
												<div class="btn-group">
													<s:link beanclass="com.ds.action.aff.AffiliateNotificationAction"
													        event="notificationRead" class="btn btn-success tip-bottom" title="Message Read">
														<i class="icon-minus-sign"></i>
														<s:param name="notificationId" value="${notification.id}"/>
													</s:link>

												</div>
											</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="row-fluid">
								<div class="span3">
									<s:layout-render name="/layouts/paginationResultCount.jsp"
									                 paginatedBean="${affiliateNotificationAction}"/>
								</div>
								<div class="span9">
									<s:layout-render name="/layouts/pagination.jsp" paginatedBean="${affiliateNotificationAction}"/>
								</div>

							</div>

								<%--<s:layout-render name="/layouts/paginationResultCount.jsp"
																							 paginatedBean="${companyAffiliateInviteAction}"/>

															<s:layout-render name="/layouts/pagination.jsp" paginatedBean="${companyAffiliateInviteAction}"/>--%>
						</div>
					</div>
				</div>
				<s:layout-render name="/includes/footer.jsp"/>
			</div>
		</div>

	</s:layout-component>

</s:layout-render>