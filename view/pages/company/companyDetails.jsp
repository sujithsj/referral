<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <s:layout-component name="content">
    <s:useActionBean beanclass="com.ds.action.company.CompanyAction" var="companyAction"/>
    <div class="content-outer wrap">
    <div class="col_12">
      <div id="page-heading">
        <h4>Edit Company Information</h4>
      </div>

      <s:form beanclass="com.ds.action.company.CompanyAction" class="vertical">
      <div class="col_6">

        <s:label name="Company Name"/>
        <s:text name="companyDTO.name" class="check-empty auto-adjust"/>

        <div class="control-group">
          <s:label class="control-label" name="Description"/>
          <div class="controls ">
            <s:textarea name="companyDTO.description" placeholder="company description"/>
          </div>
        </div>

        <div class="control-group">
          <s:label class="control-label" name="Company Website"/>
          <div class="controls ">
            <s:text name="companyDTO.url" value="http://"/>
          </div>
        </div>

       

        <div class="col_2">
          <s:hidden name="companyShortName"/>
          <s:submit name="updateCompany" value="Save Changes" class="button blue small"/>
        </div>

        <div class="col_2">
          <s:link beanclass="com.ds.action.employee.UserSearchAction"
                  class="button blue small"><span class="icon white small" data-icon=":"></span>Back</s:link>
        </div>
        </s:form>
      </div>
    </div>
  </s:layout-component>
</s:layout-render>