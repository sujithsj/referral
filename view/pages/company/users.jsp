<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <s:layout-component name="content">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
    <s:layout-render name="/includes/companyHeader.jsp"/>
    <s:layout-render name="/includes/companySideBar.jsp"/>

    <s:useActionBean beanclass="com.ds.action.employee.UserSearchAction" var="userSearchAction"/>
    <div id="content">
      <s:layout-render name="/includes/companyContentHeader.jsp" headerLabel="Manage Users"/>
      <div id="breadcrumb">
        <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
        <a href="#" class="current">Manage Users</a>
      </div>

      <div class="container-fluid">
        <div class="row-fluid">
          <div class="span10">
            <div class="widget-box">
              <div class="widget-title">
								<span class="icon">
									<i class=" icon-search"></i>
								</span>
                <h5>Search Users</h5>
              </div>
              <div class="widget-content">
                <s:form beanclass="com.ds.action.employee.UserSearchAction" class="form-inline">
                  <div class="input-prepend">
                    <span class="add-on"><i class="icon-user"></i></span><s:text name="userName"
                                                                                 placeholder="User Name"/>
                  </div>
                  <div class="input-prepend">
                    <span class="add-on"><i class="icon-envelope"></i></span><s:text name="email" placeholder="Email"/>
                  </div>
                  <s:submit name="searchUsers" class="btn btn-inverse">Search</s:submit>
                </s:form>
              </div>
            </div>
          </div>
          <div class="span2">
            <s:link beanclass="com.ds.action.employee.UserAction"
                    event="createOrEditUser" class="btn btn-inverse">
              <i class="icon-plus-sign icon-white"></i>Add new user
            </s:link>
          </div>
        </div>
        <div class="row-fluid">
          <div class="span12">
            <div class="widget-box">
              <div class="widget-title">
								<span class="icon">
									<i class="icon-filter"></i>
								</span>
                <h5>Users</h5>
              </div>
              <div class="widget-content nopadding">
                <table class="table table-bordered table-striped">
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
                        <div class="btn-group">
                          <s:link beanclass="com.ds.action.employee.UserAction" class="btn tip-bottom"
                                  event="createOrEditUser" title="Edit">
                            <i class="icon-edit"></i>
                            <s:param name="employeeId" value="${user.username}"/>
                          </s:link>
                          <s:link beanclass="com.ds.action.employee.UserAction" class="btn tip-bottom"
                                  event="resetPassword" title="Reset Password">
                            <i class="icon-repeat"></i>
                            <s:param name="employeeEmail" value="${user.email}"/>
                          </s:link>
                        </div>
                      </td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>


              </div>
              <s:layout-render name="/layouts/paginationResultCount.jsp" paginatedBean="${userSearchAction}"/>
                <s:layout-render name="/layouts/pagination.jsp" paginatedBean="${userSearchAction}"/>
            </div>
          </div>
        </div>

        <s:layout-render name="/includes/footer.jsp"/>
      </div>

    </div>


    <%--
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/excanvas.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.flot.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.peity.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/unicorn.dashboard.js"></script>
    --%>


  </s:layout-component>

</s:layout-render>