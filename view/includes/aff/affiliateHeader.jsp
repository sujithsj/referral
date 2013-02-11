<%@include file="/includes/taglibInclude.jsp" %>

<s:useActionBean beanclass="com.ds.action.aff.AffiliateDashboardAction" var="affiliateDashboardAction"  />
<s:layout-definition>

	<div id="header">
			<h1><%--<a href="./dashboard.html">Unicorn Admin</a>--%></h1>
		</div>

	<div id="user-nav" class="navbar navbar-inverse">
		<ul class="nav btn-group">
			<li class="btn btn-inverse"><a title="" href="#"><i class="icon icon-user"></i> <span
					class="text">Profile</span></a></li>

			<li class="btn btn-inverse dropdown" id="menu-messages">
				<a title="" href="${pageContext.request.contextPath}/aff/companyAffiliateNotification.action" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle">
					<i class="icon icon-envelope"></i>
					<span class="text">Notification</span>
					<%--todo rahul: get the unread notification number below --%>
					<span	class="label label-important">${affiliateDashboardAction.numberOfPendingNotifications}</span>
				</a>
				<%--<ul class="dropdown-menu">
					<li><a class="sAdd" title="" href="#">new message</a></li>
					<li><a class="sInbox" title="" href="#">inbox</a></li>
					<li><a class="sOutbox" title="" href="#">outbox</a></li>
					<li><a class="sTrash" title="" href="#">trash</a></li>
				</ul>--%>
			</li>


<%--
			<li class="btn btn-inverse"><a title="" href="${pageContext.request.contextPath}/company/Company.action"><i
					class="icon icon-cog"></i> <span
					class="text">Settings</span></a></li>
--%>
			<li class="btn btn-inverse"><a title="Logout" href="${pageContext.request.contextPath}/Logout.action"><i class="icon icon-share-alt"></i> <span
					class="text">Logout</span></a></li>
		</ul>
	</div>
</s:layout-definition>