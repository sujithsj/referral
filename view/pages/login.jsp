<%@ page import="com.ds.web.locale.AffiliateLocaleContext" %>
<%@ page import="com.ds.web.locale.AffiliateLocaleContextHolder" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

    <s:layout-component name="content">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.login.css" type="text/css"/>

        <s:useActionBean beanclass="com.ds.action.LoginAction" var="loginAction"/>
        <%
            /* HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
            String requestURL = req.getRequestURL().toString();
            String subdomain = null;
            if (requestURL != null && requestURL.length() > "http://".length()) {
              requestURL = requestURL.substring(7);
            }
            if (requestURL != null && requestURL.contains(".")) {
              subdomain = requestURL.substring(0, requestURL.indexOf("."));
            }
            //System.out.println(subdomain);
            //System.out.println(req.getRequestURL());
            //System.out.println(req.getRemoteHost());
            //would have to replace dev with www at some point of time... also might have to do some url rewriting
            if (subdomain != null && !subdomain.equals("dev")) {
              String redirectURL = "http://" + subdomain + ".healthkart.com/pages/aff/affiliateLogin.jsp";
              response.sendRedirect(redirectURL);
            }*/
        %>

        <div id="logo">
            <img src="${pageContext.request.contextPath}/assets/img/logo.png" alt=""/>
        </div>
        <div id="loginbox">
            <s:form beanclass="com.ds.action.LoginAction" id="loginform" class="form-vertical">
                <p>Enter email and password to continue.</p>

                <c:if test="${loginAction.authException}">
                    ${loginAction.authFailueMessage}
                </c:if>
                <div class="control-group">
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-user"></i></span><s:text name="j_username"
                                                                                         placeholder="Email"/>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-lock"></i></span><s:password name="j_password"
                                                                                             placeholder="Password"/>
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link" id="to-recover">Lost
                        password?</a></span>
          <span class="pull-right"><s:submit name="loginUser" class="btn btn-inverse" value="Login"/>
          <a href="/pages/signup.jsp" class="btn btn-success">Signup</a>
                </div>
                <%--</form>--%>
            </s:form>
            <form id="recoverform" action="/ResetPassword.action" class="form-vertical">
                <p>Enter your e-mail address below and we will send you instructions how to recover a password.</p>

                <div class="control-group">
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-envelope"></i></span><input type="text" name="userEmail"
                                                                                            placeholder="E-mail address"/>
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link" id="to-login">&lt; Back to login</a></span>
                    <span class="pull-right"><input type="submit" class="btn btn-inverse" value="Recover"/></span>
                </div>
            </form>
        </div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/unicorn.login.js"></script>

        </div>
    </s:layout-component>

</s:layout-render>