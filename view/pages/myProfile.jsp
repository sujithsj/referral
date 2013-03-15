<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <s:layout-component name="content">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
    <s:layout-render name="/includes/companyHeader.jsp"/>
    <s:layout-render name="/includes/companySideBar.jsp"/>

    <s:useActionBean beanclass="com.ds.action.employee.MyProfileAction" var="myProfileAction"/>
    <div id="content">
      <s:layout-render name="/includes/companyContentHeader.jsp" includeHeaderBtnGrp="false"
                       headerLabel="Update Profile"/>

      <s:form beanclass="com.ds.action.employee.MyProfileAction" id="myProfileForm" class="form-horizontal">
        <div class="container-fluid">

          <div class="row-fluid">
            <div class="span8">
              <div class="widget-box">
                <div class="widget-title">
								<span class="icon">
									<i class="icon-user"></i>
								</span>
                  <h5>My Profile</h5>
                </div>
                <div class="widget-content nopadding">

                  <div class="control-group" id="1">
                    <s:label class="control-label" name="User Full Name"/>
                    <div class="controls">
                      <s:text name="fullName"/>
                    </div>
                  </div>
                  <div class="control-group" id="2">
                    <s:label class="control-label" name="Old Password"/>
                    <div class="controls">
                      <s:password name="oldPassword"  minlength="6"/>
                    </div>
                  </div>
                  <div class="control-group" id="3">
                    <s:label class="control-label" name="New Password"/>
                    <div class="controls">
                      <s:password name="newPassword"  minlength="6"/>
                    </div>
                  </div>
                  <div class="control-group" id="4">
                    <s:label class="control-label" name="Confirm Password"/>
                    <div class="controls">
                      <s:password name="confirmPassword"  minlength="6"/>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="row-fluid">
            <s:submit name="updateUserProfile" value="Save Changes" class="btn btn-success"/>
            <s:link beanclass="com.ds.action.company.CompanyDashboardAction"
                    class="btn btn-inverse"><i class="icon-hand-left icon-white"></i>&nbsp;Back To Dashboard</s:link>
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