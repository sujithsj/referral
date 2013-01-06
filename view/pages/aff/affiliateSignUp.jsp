<%@include file="/includes/taglibInclude.jsp" %>
<s:layout-render name="/templates/general.jsp">
	<s:layout-component name="content">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.login.css" type="text/css"/>
		<div id="logo">
			<img src="${pageContext.request.contextPath}/assets/img/logo.png" alt="referoscope logo"/>
		</div>
		<p style="color:white;text-align:center;font-size:medium;">
			Affiliate Sign Up
		</p>
		<s:useActionBean beanclass="com.ds.action.aff.AffiliateSignUpAction" var="affiliateSignUpAction"/>
		<%
			String email = request.getParameter("email");
			pageContext.setAttribute("email", email);
		%>
		<div id="loginbox" style="height:230px;">
		<s:form id="loginform" beanclass="com.ds.action.aff.AffiliateSignUpAction" class="form-vertical">
			<br/>

			<div class="control-group">
				<div class="controls ">
					<div class="input-prepend">
						<span class="add-on"><i class="icon-tag"></i></span>
						<s:text name="affiliateDTO.email" class="required email" placeholder="Affiliate Email" value="${email}"/>
					</div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls ">
					<div class="input-prepend">
						<span class="add-on"><i class="icon-tag"></i></span>
						<s:text name="affiliateDTO.firstName" placeholder="First Name" class="required"/>
					</div>
				</div>
			</div>
			<div class="control-group">

				<div class="controls">
					<div class="input-prepend">
						<span class="add-on"><i class="icon-tag"></i></span>
						<s:text name="affiliateDTO.lastName" placeholder="Last Name"/>
					</div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls ">
					<div class="input-prepend">
						<span class="add-on"><i class="icon-tag"></i></span>
						<s:password name="affiliateDTO.passwordChecksum" placeholder="Password" class="required"/>
					</div>
				</div>
			</div>
			<s:hidden name="companyAffiliateDTO.companyShortName"
			          value="${affiliateSignUpAction.affiliateLocaleContext.companyShortName}"/>
			<div class="control-group">
				<div class="controls ">
					<s:submit name="registerAffiliate" value="Create Affiliate" class="btn btn-success"/>
				</div>
			</div>

		</s:form>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.validate.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/signup/signup.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/unicorn.form_validation.js"></script>
	</s:layout-component>
</s:layout-render>