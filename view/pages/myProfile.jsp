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

      <s:form beanclass="com.ds.action.employee.UserAction" id="userBasicInfoForm" class="form-horizontal">
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

                  <div class="control-group" id="1">
                    <s:label class="control-label" name="User Full Name"/>
                    <div class="controls">
                      <s:text class="required" name="userDTO.fullName"/>
                    </div>
                  </div>
                  <div class="control-group" id="2">
                    <s:label class="control-label" name="Email"/>
                    <div class="controls">
                      <s:text name="userDTO.email" class="required email"/>
                    </div>
                  </div>
                  <div class="control-group" id="3">
                    <s:label class="control-label" name="Password"/>
                    <div class="controls">
                      <s:password name="userDTO.password" class="required" minlength="6"/>
                    </div>
                  </div>


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
                  <div class="control-group">
                    <s:label name="Email On Affiliate Request" class="control-label"/>
                    <div class="controls">
                      <s:checkbox name="userDTO.sendEmailOnAddAffiliate"/>
                    </div>
                  </div>
                  <div class="control-group">
                    <s:label name="Email On Affiliate Addition" class="control-label"/>
                    <div class="controls">
                      <s:checkbox name="userDTO.sendEmailOnJoinAffiliate"/>
                    </div>
                  </div>
                  <div class="control-group">
                    <s:label name="Email On Payout" class="control-label"/>
                    <div class="controls">
                      <s:checkbox name="userDTO.sendEmailOnPayout"/>
                    </div>
                  </div>
                  <div class="control-group">
                    <s:label name="Enabled" class="control-label"/>
                    <div class="controls">
                      <s:checkbox name="userDTO.enabled"/>
                    </div>
                  </div>

                    <%-- <div class="control-group">
                      <s:label name="Send Email On Goal Conversion" class="control-label"/>
                      <div class="controls">
                        <s:checkbox name="userDTO.sendEmailOnGoalConversion"/>
                      </div>
                    </div>--%>
                    <%-- <div class="control-group">
                      <s:label name="account non expired" class="control-label"/>
                      <div class="controls">
                        <s:checkbox name="userDTO.accountNonExpired"/>
                      </div>
                    </div>--%>
                    <%--<div class="control-group">
                      <s:label name="Account non locked" class="control-label"/>
                      <div class="controls">
                        <s:checkbox name="userDTO.accountNonLocked"/>
                      </div>
                    </div>--%>

                    <%--<div class="control-group">
                      <s:label name="Credintals non expired" class="control-label"/>
                      <div class="controls">
                        <s:checkbox name="userDTO.credentialsNonExpired"/>
                      </div>
                    </div>--%>

                </div>
              </div>

            </div>
          </div>

          <div class="row-fluid">
            <div class="span6">
              <div class="widget-box collapsible">
                <div class="widget-title">
                  <a href="#collapseRoles" data-toggle="collapse">
								<span class="icon">
									<i class="icon-magnet"></i>
								</span>
                    <h5>User Roles</h5>
                  </a>
                </div>
                <div class="collapse " id="collapseRoles">
                  <div class="widget-content nopadding">

                    <c:forEach items="${userAction.userRoles}" var="role" varStatus="roleCount">

                      <s:hidden name="userDTO.rolesToSync[${roleCount.index}]" value="${role.name}"/>
                      <s:checkbox name="userDTO.rolesToSync[${roleCount.index}]"/> ${role.name}<br/>
                    </c:forEach>

                  </div>
                </div>
              </div>
            </div>
          </div>


          <div class="row-fluid">
            <s:hidden name="employeeId"/>
            <s:submit name="saveUser" value="Save Changes" class="btn btn-success"/>

            <s:link beanclass="com.ds.action.employee.UserSearchAction"
                    class="btn btn-inverse"><i class="icon-hand-left icon-white"></i>&nbsp;Back</s:link>
          </div>
        </div>
      </s:form>

      <s:layout-render name="/includes/footer.jsp"/>
    </div>
    </div>

    </div>


    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/user/userCrud.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/assets/js/unicorn.form_validation.js"></script>
--%>


  </s:layout-component>

</s:layout-render>