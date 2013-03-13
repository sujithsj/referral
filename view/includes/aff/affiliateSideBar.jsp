<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@include file="/includes/taglibInclude.jsp" %>
<s:layout-definition>

	<%
		String openMenu = (String) pageContext.getAttribute("openMenu");
		Boolean openAffSubMenu = false, openSettingsSubMenu = false, openReportsSubMenu = false;
		if (StringUtils.isNotBlank(openMenu)) {
			if ("affiliate".equalsIgnoreCase(openMenu)) {
				openAffSubMenu = true;
			} else if ("settings".equalsIgnoreCase(openMenu)) {
				openSettingsSubMenu = true;
			} else if ("reports".equalsIgnoreCase(openMenu)) {
				openReportsSubMenu = true;
			}
		}

		pageContext.setAttribute("openAffSubMenu", openAffSubMenu);
		pageContext.setAttribute("openSettingsSubMenu", openSettingsSubMenu);
		pageContext.setAttribute("openReportsSubMenu", openReportsSubMenu);
	%>
	<div id="sidebar">
			<%--<a href="#" class="visible-phone"><i class="icon icon-home"></i> Dashboard kk</a>--%>
		<ul>
			<li><a href="${pageContext.request.contextPath}/aff/AffiliateDashboard.action"><i class="icon icon-home"></i>
				<span>Dashboard</span></a></li>
				<%--
							<li>
								<a href="${pageContext.request.contextPath}/employee/UserSearch.action"><i class="icon icon-user"></i> <span>Users</span></a>
							</li>
				--%>
			<li><a href="${pageContext.request.contextPath}/aff/marketing/AffiliateClickTraffic.action"><i class="icon icon-tint"></i>
				<span>Click Traffic (x)</span></a>
			</li>
			<li><a href="${pageContext.request.contextPath}/aff/marketing/AffiliateRevenue.action"><i class="icon icon-file"></i>
				<span>Revenue (x)</span></a>
			</li>
			<li><a href="${pageContext.request.contextPath}/aff/marketing/AffiliateConversion.action"><i class="icon icon-file"></i>
				<span>Conversion (x)</span></a>
			</li>
			<li><a href="${pageContext.request.contextPath}/marketing/MarketingMaterialSearch.action"><i class="icon icon-file"></i>
				<span>Commission</span></a>
			</li>
			<li><a href="${pageContext.request.contextPath}/aff/AffiliateAds.action"><i class="icon icon-file"></i>
				<span>Ads</span></a>
			</li>
			<c:choose>
				<c:when test="${openAffSubMenu}">
					<li class="submenu open">
				</c:when>
				<c:otherwise>
					<li class="submenu">
				</c:otherwise>
			</c:choose>
				<a href="#"><i class="icon icon-signal"></i> <span>Affiliates </span></a>
				<ul>
					<li><a href="${pageContext.request.contextPath}/affiliate/CompanyAffiliateSearch.action"><i
							class="icon icon-leaf"></i> <span>Manage Affiliates</span></a></li>
					<li><a href="${pageContext.request.contextPath}/affiliate/CompanyAffiliateInvite.action"><i
							class="icon icon-envelope"></i> <span>Affiliate Invites</span></a></li>
					<li><a href="${pageContext.request.contextPath}/affiliate/CompanyAffiliateGroupSearch.action"><i
							class="icon icon-folder-close"></i> <span>Affiliate Groups</span></a></li>
						<%--<li><a href="form-wizard.html">Wizard</a></li>--%>
				</ul>
			</li>

			<li><a href="grid.html"><i class="icon icon-gift"></i> <span>Payouts</span></a></li>
			<li><a href="${pageContext.request.contextPath}/commission/CommissionEarningSearch.action"><i
					class="icon icon-gift"></i> <span>Commissions</span></a></li>
			<li class="submenu">
				<a href="#"><i class="icon icon-signal"></i> <span>Reports</span> <%--<span class="label">3</span>--%></a>
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
				<a href="#"><i class="icon icon-wrench"></i> <span>Settings</span> <%--<span class="label">4</span>--%></a>
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