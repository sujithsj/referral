<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <s:layout-component name="content">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.login.css" type="text/css"/>

    <div id="logo">
      <img src="${pageContext.request.contextPath}/assets/img/logo.png" alt=""/>
    </div>
    <div id="loginbox" style="height:360px;">
      <s:form beanclass="com.ds.action.company.RegisterCompanyAction" id="loginform" class="form-vertical">
        <p>Sign Up for Referoscope.</p>

        <div class="control-group">
          <div class="controls">
            <div class="input-prepend">
              <span class="add-on"><i class="icon-tag"></i></span><s:text name="companyRegistrationDTO.userName"
                                                                          class="required" placeholder="User Name"/>
            </div>
          </div>
        </div>
        <div class="control-group">
          <div class="controls">
            <div class="input-prepend">
              <span class="add-on"><i class="icon-user"></i></span><s:text name="companyRegistrationDTO.email"
                                                                           class="required email" placeholder="email"/>
            </div>
          </div>
        </div>
        <div class="control-group">
          <div class="controls">
            <div class="input-prepend">
              <span class="add-on"><i class="icon-lock"></i></span><s:password name="companyRegistrationDTO.password"
                                                                               class="required" placeholder="Password"/>
            </div>
          </div>
        </div>
        <div class="control-group">
          <div class="controls ">
            <div class="input-prepend">
              <span class="add-on"><i class="icon-home"></i></span><s:text name="companyRegistrationDTO.name"
                                                                           class="required" placeholder="company name"/>

            </div>
          </div>
        </div>

        <div class="control-group">
          <div class="controls">

            <div class="input-append">
              <s:text name="companyRegistrationDTO.shortName" style="width:140px;margin-left:30px;"
                    class="required"  placeholder="company short name"/>
              <span class="add-on">.referoscope.com</span>
                <%--<div class="input-append"><span class="add-on">.ds.com</span></div>--%>
            </div>
          </div>
        </div>
        <%--<div class="control-group">
          <div class="controls ">
            <div class="input-prepend">
              <s:textarea name="companyRegistrationDTO.description" placeholder="company description"/>
            </div>
          </div>
        </div>--%>
        <div class="control-group">
          <div class="controls ">
            <div class="input-prepend">
              <span class="add-on"><i class="icon-bookmark"></i></span><s:text name="companyRegistrationDTO.url"
                                                                             class="required"  placeholder="company website"/>
                <%--<s:text name="companyRegistrationDTO.url" value="http://"/>--%>
            </div>
          </div>
        </div>
        <div class="form-actions">
          <span class="pull-right"><s:submit name="registerCompany" class="btn btn-inverse" value="Sign Up"/></span>

        </div>
      </s:form>
    </div>
    </div>

    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/signup/signup.js"></script>
	  <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/unicorn.form_validation.js"></script>


  </s:layout-component>

</s:layout-render>