<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <%--<s:layout-component name="heading">Split Base Order Manually</s:layout-component>--%>
  <s:layout-component name="content">

    <div class="container">


    <div class="row">
      <div class="span3 bs-docs-sidebar">
       
      </div>
      <div class="span9">

        <s:form beanclass="com.ds.action.company.RegisterCompanyAction" class="form-horizontal">

          <div class="control-group">
            <s:label class="control-label" name="Your Name"/>
            <div class="controls ">
              <s:text name="companyRegistrationDTO.userName" placeholder="user name"/>
            </div>
          </div>
          <div class="control-group">
            <s:label class="control-label" name="Company Name"/>
            <div class="controls ">
              <s:text name="companyRegistrationDTO.name" placeholder="company name"/>
            </div>
          </div>
          <div class="control-group">
            <s:label class="control-label" name="Short Name"/>
            <div class="controls" >
              <s:text name="companyRegistrationDTO.shortName" /><div class="input-append"><span class="add-on">.ds.com</span></div>
            </div>
          </div>
          <div class="control-group">
            <s:label class="control-label" name="Company Website"/>
            <div class="controls ">
              <s:text name="companyRegistrationDTO.url" value="http://"/>
            </div>
          </div>
          <div class="control-group">
            <s:label class="control-label" name="Email"/>
            <div class="controls ">
              <s:text name="companyRegistrationDTO.email"/>
            </div>
          </div>
          <div class="control-group">
            <s:label class="control-label" name="Password"/>
            <div class="controls ">
              <s:text name="companyRegistrationDTO.password" />
            </div>
          </div>

        </s:form>


      </div>
    </div>
  </s:layout-component>
</s:layout-render>