<%@ page import="com.ds.web.locale.AffiliateLocaleContext" %>
<%@ page import="com.ds.web.locale.AffiliateLocaleContextHolder" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

    <s:layout-component name="content">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.login.css" type="text/css"/>

        <%
            String reponse = request.getParameter("response");
            pageContext.setAttribute("reponse", reponse);
        %>

        <div id="logo">
            <img src="${pageContext.request.contextPath}/assets/img/logo.png" alt=""/>
        </div>

        <div id="loginbox">


            <c:if test="${reponse eq 'failure'}">
                <div id="confirmationBox">
                    <p>
                        <strong>We could not confirm your email address with referoscope.</strong>
                    </p>

                    <p>
                        Your registration is not complete.<br/>
                        You can try registering again.
                    </p>

                    <p>
                        Go to <a href="/pages/signup.jsp">Signup</a>
                    </p>
                </div>
            </c:if>
            <c:if test="${reponse eq 'success'}">
                <div id="confirmationBox">
                    <p>
                        <strong>Thanks for confirming your email address with referoscope !!</strong>
                    </p>

                    <p>
                        Your registration is complete.<br/>
                        You can now login with your authentication details.
                    </p>

                    <p>
                        Go to <a href="/pages/login.jsp"> Login Page</a>
                    </p>
                </div>
            </c:if>
        </div>

    </s:layout-component>

</s:layout-render>