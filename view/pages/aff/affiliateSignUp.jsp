<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

	<s:layout-component name="content">
		<s:useActionBean beanclass="com.ds.action.aff.AffiliateSignUpAction" var="affiliateSignUpAction"/>

		<%
			String email = request.getParameter("email");
			pageContext.setAttribute("email", email);
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
							<s:text name="affiliateDTO.firstName" placeholder="first name"/>
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
					<s:hidden name="companyAffiliateDTO.companyShortName" value="${affiliateSignUpAction.affiliateLocaleContext.companyShortName}"/>
					<div class="control-group">
            <div class="controls ">
              <s:submit name="registerAffiliate" value="Create Affiliate" class="btn btn-success"/>
            </div>
          </div>

				</s:form>

			</div>
		</div>
	</s:layout-component>
</s:layout-render>