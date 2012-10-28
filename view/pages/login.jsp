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
    <div class="container">

        <h1>Login page</h1>
    <div class="row">
      <div class="span3 bs-docs-sidebar">

      </div>
      <div class="span9">

         <s:form beanclass="com.ds.action.LoginAction" class="vertical">
            <fieldset>
              <legend><em>Login using an existing account</em></legend>
              <s:label name="Email"/>
              <s:text name="email" class="auto-adjust check-empty"/>

              <s:label name="Password"/>
              <s:password name="password" class="auto-adjust check-empty"/>
            </fieldset>

            <div class="col_3">
              <s:submit name="loginUser" class="button blue small" value="Login"/>
            </div>
          </s:form>

      </div>
    </div>
  </s:layout-component>
</s:layout-render>