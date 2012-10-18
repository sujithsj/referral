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
              <div class="span3 offset2">
                <s:link beanclass="com.ds.action.employee.UserAction"
                        event="createEmployee" class="btn btn-primary">Add new user
                </s:link>
              </div>
            </div>
          </div>
          <ul class="breadcrumb">
            <li><a href="#">Setup</a> <span class="divider">/</span></li>
            <li class="active">User Accounts</li>
          </ul>

          <table class="table table-condensed table-bordered table-striped">
            <thead>
            <tr>
              <th>heading 1</th>
              <th>heading 2</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>one</td>
              <td>two</td>
            </tr>
            </tbody>
          </table>
        </div>

      </div>
    </div>
  </s:layout-component>
  <s:layout-component name="scriptComponent">

    <script type="text/javascript">

      $(document).ready(function() {
        /*alert('aaa');
        $('.bs-docs-sidenav').affix({
          offset: {
            top: 155
            , bottom: 170
          }
        });*/
      });

    </script>
  </s:layout-component>
</s:layout-render>