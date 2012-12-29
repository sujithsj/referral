<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <s:layout-component name="content">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
    <s:layout-render name="/includes/companyHeader.jsp"/>
    <s:layout-render name="/includes/companySideBar.jsp"/>

    <s:useActionBean beanclass="com.ds.action.employee.UserAction" var="userAction"/>
    <div id="content">
      <c:choose>
        <c:when test="${userAction.employeeId != null}">
          <s:layout-render name="/includes/companyContentHeader.jsp" includeHeaderBtnGrp="false"
                           headerLabel="Edit  User"/>
        </c:when>
        <c:otherwise>
          <s:layout-render name="/includes/companyContentHeader.jsp" includeHeaderBtnGrp="false"
                           headerLabel="Create New User"/>
        </c:otherwise>
      </c:choose>

      <div class="container-fluid">
        <div class="row-fluid">
          <div class="span6">
            <div class="widget-box">
              <div class="widget-title">
								<span class="icon">
									<i class="icon-user"></i>
								</span>
                <h5>User Information</h5>
              </div>
              <div class="widget-content nopadding">
                <s:form beanclass="com.ds.action.employee.UserAction" id="basic_validate" class="form-horizontal">
                  <div class="control-group">
                    <s:label class="control-label" name="User Full Name"/>
                    <div class="controls">
                      <s:text class="required" name="userDTO.fullName"/>
                    </div>
                  </div>
                  <div class="control-group">
                    <s:label class="control-label" name="Email"/>
                    <div class="controls">
                      <s:text name="userDTO.email" class="required email"/>
                    </div>
                  </div>
                  <div class="control-group">
                    <s:label class="control-label" name="Password"/>
                    <div class="controls">
                      <s:password name="userDTO.password" class="required" minlength="6"/>
                    </div>
                  </div>

                  <input type="submit" name="submit" value="Register"/>


                </s:form>
              </div>
            </div>
          </div>
          <div class="span6">
            <div class="widget-box">
              <div class="widget-title">
								<span class="icon">
									<i class="icon-wrench"></i>
								</span>
                <h5>User Settings</h5>
              </div>
              <div class="widget-content nopadding">
                <s:form beanclass="com.ds.action.employee.UserAction" class="form-horizontal">
                  <div class="control-group">
                    <label class="control-label">Normal text input</label>

                    <div class="controls">
                      <input type="text"/>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label">Password input</label>

                    <div class="controls">
                      <input type="password"/>
                    </div>
                  </div>
                </s:form>
              </div>
            </div>
          </div>
        </div>
        <div class="row-fluid">
          <div class="span12">
            <div class="widget-box">
              <div class="widget-title">
                <a href="#collapseRoles" data-toggle="collapse">
								<span class="icon">
									<i class="icon-magnet"></i>
								</span>
                  <h5>User Roles</h5>
                </a>
              </div>
              <div class="collapse in" id="collapseRoles">
                <div class="widget-content nopadding">
                  <s:form beanclass="com.ds.action.employee.UserAction" class="form-horizontal">
                    <div class="control-group">
                      <label class="control-label">Normal text input</label>

                      <div class="controls">
                        <input type="text"/>
                      </div>
                    </div>
                    <div class="control-group">
                      <label class="control-label">Password input</label>

                      <div class="controls">
                        <input type="password"/>
                      </div>
                    </div>
                  </s:form>
                </div>
              </div>
            </div>
          </div>
        </div>

        <s:layout-render name="/includes/footer.jsp"/>
      </div>

    </div>


    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/user/userCrud.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/assets/js/unicorn.form_validation.js"></script>
    <%--

    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.flot.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.peity.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/unicorn.dashboard.js"></script>
    --%>


  </s:layout-component>

</s:layout-render>