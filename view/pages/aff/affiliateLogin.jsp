<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

	<%--<s:layout-component name="heading">Split Base Order Manually</s:layout-component>--%>
	<s:layout-component name="content">

		<%--<header class="jumbotron subhead">
					<div class="container">
						<h1>
							Register your company
						</h1>
					</div>
				</header>--%>
		<%

			HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
			String requestURL = req.getRequestURL().toString();
			String subdomain = null;
			if (requestURL != null && requestURL.length() > "http://".length()) {
				requestURL = requestURL.substring(7);
			}
			if (requestURL != null && requestURL.contains(".")) {
				subdomain = requestURL.substring(0, requestURL.indexOf("."));
			}
			System.out.println("aff login subdomain " + subdomain);
			pageContext.setAttribute("subdomain", subdomain);
			//System.out.println(req.getRequestURL());
			//System.out.println(req.getRemoteHost());
			if (subdomain != null && subdomain.equals("dev")) {
				String redirectURL = "http://" + subdomain + ".healthkart.com";
				response.sendRedirect(redirectURL);
			}
		%>
		<div class="container">

		<h1>Affiliate Login page</h1>

		<div class="row">
		<div class="span3 bs-docs-sidebar">

		</div>
		<div class="span9 wrap">
			<div class="container">
				<div class="row">
					<div class="span3 offset2" style="margin-right:20px">
						<a href="${pageContext.request.contextPath}/pages/aff/affiliateSignUp.jsp" class="btn btn-primary" style="margin-left:150px;">Sign Up</a>
<%--						<s:link beanclass="com.ds.action.affiliate.CompanyAffiliateGroupSearchAction" class="btn btn-primary">Groups
						</s:link>--%>
					</div>
				</div>
			</div>

			<div class="span9">

				<s:form beanclass="com.ds.action.aff.AffiliateLoginAction" class="vertical">
					<fieldset>
						<legend><em>Login using an existing account</em></legend>
						<s:label name="Email"/>
						<s:text name="login" class="auto-adjust check-empty"/>

						<s:label name="Password"/>
						<s:password name="password" class="auto-adjust check-empty"/>
					</fieldset>

					<div class="col_3">
						<s:submit name="loginAffiliate" class="button blue small" value="Login"/>
					</div>
				</s:form>

			</div>
		</div>
	</s:layout-component>
</s:layout-render>