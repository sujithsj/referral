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

    <h1>Upload Logo</h1>
    <img src="/getImage?imageId=${companyAction.companyDTO.imageId}" alt="CompanyLogo">
    <form action="/fileUpload" multipart="1" method="post" enctype="multipart/form-data" id="companyLogoUploadForm">
      <input type="file" name="file" class="formelement" style="width: 312px;" id="marketing_tool_banner">
      <input type="hidden"  name="fileManageType" value="10" >
      <input type="hidden"  name="identifier" value="${companyAction.companyShortName}" >
      <input type="submit" value="upload" class="button blue big">
    </form>
  </s:layout-component>
</s:layout-render>