<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <%--<s:layout-component name="heading">Split Base Order Manually</s:layout-component>--%>
  <s:layout-component name="content">
    <s:useActionBean beanclass="com.ds.action.employee.UserSearchAction" var="userSearchAction"/>
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


          <fieldset>
              <legend><em>Filter Brands</em></legend>
              <s:form beanclass="com.ds.action.employee.UserSearchAction">
                <s:label name="User name"/>
                <s:text name="userName" placeholder="user name"/>

                <s:label name="Email"/>
                <s:text name="email"/>
                <s:submit name="searchUsers" class="button blue small">Search</s:submit>
              </s:form>
            </fieldset>

          
          <s:layout-render name="/layouts/paginationResultCount.jsp" paginatedBean="${userSearchAction}"/>
          <s:layout-render name="/layouts/pagination.jsp" paginatedBean="${userSearchAction}"/>
          <table class="table table-condensed table-bordered table-striped">
            <thead>
            <tr>
              <th>User Id</th>
              <th>Name</th>
              <th>Email</th>
              <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userSearchAction.users}" var="user">
              <tr>
                <td>${user.username}</td>
                <td>${user.fullName}</td>
                <td>${user.email}</td>
                <td>
                <s:link beanclass="com.ds.action.employee.UserAction"
                        event="createOrEditUser" class="button blue small">
                  <span class="icon white small" data-icon="7"></span>Edit
                  <s:param name="employeeId" value="${user.username}"/>
                </s:link>
                 <%-- <s:link beanclass="com.hk.action.admin.crud.catalog.tags.AssociateTagsAction"
                        event="entityTags" target="_blank" class="button orange small">Tag
                  <s:param name="entityId" value="${brand.id}"/>
                  <s:param name="type" value="${type}"/>
                </s:link>--%>
              </td>
              </tr>
            </c:forEach>
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