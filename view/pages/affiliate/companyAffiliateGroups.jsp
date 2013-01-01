<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <s:layout-component name="content">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
    <s:layout-render name="/includes/companyHeader.jsp"/>
    <s:layout-render name="/includes/companySideBar.jsp" openMenu="affiliate"/>

    <s:useActionBean beanclass="com.ds.action.affiliate.CompanyAffiliateGroupSearchAction"
                     var="companyAffiliateGroupSearchAction"/>
    <div id="content">
      <s:layout-render name="/includes/companyContentHeader.jsp" headerLabel="Affiliate Groups"/>
      <div id="breadcrumb">
        <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
        <a href="#" class="current">Affiliate Groups</a>
      </div>

      <div class="container-fluid">

        <div class="row-fluid">
          <div class="span8">
            <div class="widget-box">
              <div class="widget-title">
								<span class="icon">
									<i class=" icon-search"></i>
								</span>
                <h5>Search Affiliate Groups</h5>
              </div>
              <div class="widget-content">
                <s:form beanclass="com.ds.action.affiliate.CompanyAffiliateGroupSearchAction" class="form-inline">
                  <div class="input-prepend">
                    <span class="add-on"><i class="icon-tag"></i></span><s:text name="name"
                                                                                placeholder="Group Name"/>
                  </div>
                  <s:submit name="searchCompanyAffiliateGroups" class="btn btn-inverse">Search</s:submit>
                </s:form>
              </div>
            </div>
          </div>
          <div class="span4">
            <s:link beanclass="com.ds.action.affiliate.CompanyAffiliateGroupAction"
                    event="createOrEditCompanyAffiliateGroup" class="btn btn-success">
              <i class="icon-folder-close icon-white"></i>&nbsp;Create Affiliate Group
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
                <h5>Affiliate Groups</h5>
              </div>
              <div class="widget-content nopadding">
                <table class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>No. of Affiliates</th>
                    <th style="width: 10%">Actions</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${companyAffiliateGroupSearchAction.companyAffiliateGroups}"
                             var="companyAffiliateGroup">
                    <tr>
                      <td>${companyAffiliateGroup.id}</td>
                      <td>${companyAffiliateGroup.name}</td>
                      <td>${companyAffiliateGroup.description}</td>
                      <td>${fn:length(companyAffiliateGroup.companyAffiliates)}</td>
                      <td>
                        <div class="btn-group">
                          <s:link beanclass="com.ds.action.affiliate.CompanyAffiliateGroupAction"
                                  event="createOrEditCompanyAffiliateGroup" class="btn tip-bottom" title="Details For Group">
                            <i class="icon-folder-open"></i>
                            <s:param name="companyAffiliateGroupId" value="${companyAffiliateGroup.id}"/>
                          </s:link>
                        </div>
                      </td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
              <s:layout-render name="/layouts/paginationResultCount.jsp"
                               paginatedBean="${companyAffiliateGroupSearchAction}"/>
              <s:layout-render name="/layouts/pagination.jsp" paginatedBean="${companyAffiliateGroupSearchAction}"/>
            </div>
          </div>
        </div>
        <s:layout-render name="/includes/footer.jsp"/>
      </div>
    </div>

  </s:layout-component>

</s:layout-render>