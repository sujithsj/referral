<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <%--<s:layout-component name="heading">Split Base Order Manually</s:layout-component>--%>
  <s:layout-component name="content">

    <div class="container">

        <%--<s:layout-render name="${pageContext.request.contextPath}/includes/menu/setupSidebar.jsp"/>--%>

      <div class="row">
        <div class="span3 bs-docs-sidebar">
          <s:layout-render name="/includes/menu/setupSidebar.jsp"/>
        </div>

        <div class="span9">
          <div class="container">
            <div class="row">
              <div class="span4">
                <p class="lead">User Accounts</p>
              </div>
              <div class="span3 offset2">test</div>
            </div>
          </div>
        </div>

      </div>
    </div>
  </s:layout-component>
</s:layout-render>