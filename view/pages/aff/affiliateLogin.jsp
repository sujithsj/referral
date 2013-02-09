<%--
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">


	<s:layout-component name="content">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.login.css" type="text/css"/>

		<%

			String email = request.getParameter("email");
			pageContext.setAttribute("email", email);
		%>
		<div id="logo">
			<img src="${pageContext.request.contextPath}/assets/img/logo.png" alt=""/>
		</div>
		<p style="color:white;text-align:center;font-size:medium;">
			Affiliate Login page
		</p>

		<div id="loginbox">
			<s:form beanclass="com.ds.action.aff.AffiliateLoginAction" id="loginform" class="form-vertical">
				<p>Enter email and password to continue.</p>

				<div class="control-group">
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-user"></i></span><s:text name="login" placeholder="Email" value="${email}"/>
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-lock"></i></span><s:password name="password" placeholder="Password"/>
						</div>
					</div>
				</div>
				<div class="form-actions">
					<span class="pull-left"><a href="affiliateLogin.jsp#" class="flip-link" id="to-recover">Lost password?</a></span>
		      <span class="pull-right"><s:submit name="loginAffiliate" class="btn btn-inverse" value="Login"/>
			    <c:url value="/pages/affiliateSignUp.jsp" var="signUpUrl">
						<c:param name="email" value="${email}"/>
					</c:url>
					<a href="${signUpUrl}" class="btn btn-success">Sign Up</a>
				</div>				
			</s:form>
			<form id="recoverform" action="affiliateLogin.jsp#" class="form-vertical">
				<p>Enter your e-mail address below and we will send you instructions how to recover a password.</p>

				<div class="control-group">
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-envelope"></i></span><input type="text" placeholder="E-mail address"/>
						</div>
					</div>
				</div>
				<div class="form-actions">
					<span class="pull-left"><a href="affiliateLogin.jsp#" class="flip-link" id="to-login">&lt; Back to login</a></span>
					<span class="pull-right"><input type="submit" class="btn btn-inverse" value="Recover"/></span>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/unicorn.login.js"></script>

	</s:layout-component>
</s:layout-render>
--%>
