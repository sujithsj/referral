<%@include file="/includes/taglibInclude.jsp" %>
<s:layout-definition>

<div id="sidebar">
			<%--<a href="#" class="visible-phone"><i class="icon icon-home"></i> Dashboard kk</a>--%>
			<ul>
				<li ><a href="/pages/company/dashboard.jsp"><i class="icon icon-home"></i> <span>Dashboard</span></a></li>
				<li>
					<a href="${pageContext.request.contextPath}/employee/UserSearch.action"><i class="icon icon-user"></i> <span>Users</span> <span class="label">3</span></a>
				</li>
				<li><a href="${pageContext.request.contextPath}/campaign/CampaignSearch.action"><i class="icon icon-tint"></i> <span>Campaigns</span></a></li>
				<li><a href="${pageContext.request.contextPath}/marketing/MarketingMaterialSearch.action"><i class="icon icon-file"></i> <span>Ads</span></a></li>
				<li><a href="tables.html"><i class="icon icon-leaf"></i> <span>Affiliates</span></a></li>
        <li><a href="grid.html"><i class="icon icon-gift"></i> <span>Payouts</span></a></li>
        <li class="submenu">
					<a href="#"><i class="icon icon-signal"></i> <span>Reports</span> <span class="label">3</span></a>
					<ul>
						<li><a href="form-common.html">Marketing Reports</a></li>
						<li><a href="form-validation.html">Affiliate Reports</a></li>
						<%--<li><a href="form-wizard.html">Wizard</a></li>--%>
					</ul>
				</li>
        <li>
					<a href="charts.html"><i class="icon icon-comment"></i> <span>Notifications</span></a>
				</li>
				<li class="submenu">
					<a href="#"><i class="icon icon-wrench"></i> <span>Settings</span> <span class="label">4</span></a>
					<ul>
						<li><a href="invoice.html">Company Settings</a></li>
						<li><a href="chat.html">Email Settings</a></li>
						<li><a href="calendar.html">Invoice Settings</a></li>
						<li><a href="gallery.html">Tracking Settings</a></li>
					</ul>
				</li>
				<%--<li>
					<a href="charts.html"><i class="icon icon-signal"></i> <span>Charts &amp; graphs</span></a>
				</li>
				<li>
					<a href="widgets.html"><i class="icon icon-inbox"></i> <span>Widgets</span></a>
				</li>--%>
			</ul>

		</div>
  </s:layout-definition>