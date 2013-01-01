<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

	<%--<s:layout-component name="heading">Split Base Order Manually</s:layout-component>--%>
	<s:layout-component name="content">
		<s:useActionBean beanclass="com.ds.action.aff.AffiliateSignUpAction" var="affiliateSignUpAction"/>

		<%--<header class="jumbotron subhead">
					<div class="container">
						<h1>
							Register your company
						</h1>
					</div>
				</header>--%>
		<%
			String email = request.getParameter("email");
			pageContext.setAttribute("email", email);

/*
			HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
			String requestURL = req.getRequestURL().toString();
			String subdomain = null;
			if (requestURL != null && requestURL.length() > "http://".length()) {
				requestURL = requestURL.substring(7);
			}
			if (requestURL != null && requestURL.contains(".")) {
				subdomain = requestURL.substring(0, requestURL.indexOf("."));
			}
			pageContext.setAttribute("subdomain", subdomain);
			System.out.println("aff login subdomain " + subdomain);
			//System.out.println(req.getRequestURL());
			//System.out.println(req.getRemoteHost());
			if (subdomain != null && subdomain.equals("dev")) {
				String redirectURL = "http://" + subdomain + ".healthkart.com";
				response.sendRedirect(redirectURL);
			}
*/
		%>
		<div class="container">

		<h1>Affiliate Sign Up</h1>

		<div class="row">
			<div class="span3 bs-docs-sidebar">

			</div>
			<div class="span9">

				<s:form beanclass="com.ds.action.aff.AffiliateSignUpAction" class="form-horizontal">
					<fieldset>
						<legend>test</legend>
						<div class="control-group">
							<s:label class="control-label" name="Email"/>
							<div class="controls ">
								<s:text name="affiliateDTO.email" placeholder="email" value="${email}"/>
							</div>
						</div>
					</fieldset>
					<div class="control-group">
						<s:label class="control-label" name="First Name"/>
						<div class="controls ">
							<s:text name="affiliateDTO.firstName" placeholder="first name" value="${affiliateSignUpAction.affiliateLocaleContext.companyShortName}"/>
						</div>
					</div>
					<div class="control-group">
						<s:label class="control-label" name="Last Name"/>
						<div class="controls">
							<s:text name="affiliateDTO.lastName" placeholder="last name"/>
							<div class="input-append"><span class="add-on" style="margin-left:-5px;">.ds.com</span></div>
						</div>
					</div>
					<div class="control-group">
						<s:label class="control-label" name="Password"/>
						<div class="controls ">
							<s:text name="affiliateDTO.passwordChecksum"/>
						</div>
					</div>
					<div class="control-group">
            <div class="controls ">
              <s:submit name="registerAffiliate" value="Create Affiliate" class="btn-large btn-primary"/>
            </div>
          </div>
					${affiliateSignUpAction.affiliateLocaleContext.companyShortName}
					<s:hidden name="companyAffiliateDTO.companyShortName" value="${affiliateSignUpAction.affiliateLocaleContext.companyShortName}"/>
				</s:form>


			</div>
		</div>
	</s:layout-component>
</s:layout-render>