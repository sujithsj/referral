<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <s:layout-component name="content">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
    <s:layout-render name="/includes/companyHeader.jsp"/>
    <s:layout-render name="/includes/companySideBar.jsp" openMenu="affiliate"/>

    <s:useActionBean beanclass="com.ds.action.affiliate.CompanyAffiliateSearchAction"
                     var="companyAffiliateSearchAction"/>
    <div id="content">
      <s:layout-render name="/includes/companyContentHeader.jsp" headerLabel="Affiliates"/>
      <div id="breadcrumb">
        <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
        <a href="#" class="current">Affiliates</a>
      </div>

      <div class="container-fluid">
        
        <div class="row-fluid">
          <div class="span12">
            <div class="widget-box">
              <div class="widget-title">
								<span class="icon">
									<i class=" icon-search"></i>
								</span>
                <h5>Search Affiliates</h5>
              </div>
              <div class="widget-content">
                <s:form beanclass="com.ds.action.affiliate.CompanyAffiliateSearchAction" class="form-inline">
                  <div class="input-prepend">
                    <span class="add-on"><i class="icon-user"></i></span><s:text name="login"
                                                                                 placeholder="Affiliate Login"/>
                  </div>
                  <div class="input-prepend">
                    <span class="add-on"><i class="icon-envelope"></i></span><s:text name="email" placeholder="Email"/>
                  </div>
                  <s:submit name="searchCompanyAffiliates" class="btn btn-inverse">Search</s:submit>
                </s:form>
              </div>
            </div>
          </div>
          <%--<div class="span4">
            <s:link beanclass="com.ds.action.affiliate.CompanyAffiliateInviteAction"
                    event="displayAllCompanyInvites" class="btn btn-success">
              <i class="icon-envelope icon-white"></i>&nbsp;View All Invites
            </s:link>
            <s:link beanclass="com.ds.action.affiliate.CompanyAffiliateGroupSearchAction" class="btn btn-success">
            <i class="icon-folder-close icon-white"></i>&nbsp;View Affiliate Groups
            </s:link>
          </div>--%>
        </div>

        <div class="row-fluid">
          <div class="span12">
            <div class="widget-box">
              <div class="widget-title">
								<span class="icon">
									<i class="icon-filter"></i>
								</span>
                <h5>Affiliates</h5>
              </div>
              <div class="widget-content nopadding">
                <table class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Affiliate Id</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th style="width: 10%">Actions</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${companyAffiliateSearchAction.companyAffiliates}" var="companyAffiliate">
                    <tr>
                      <td>${companyAffiliate.affiliate.login}</td>
                      <td>${companyAffiliate.affiliate.firstName} ${companyAffiliate.affiliate.lastName}</td>
                      <td>${companyAffiliate.affiliate.email}</td>
                      <td>
                        <div class="btn-group">
                          <s:link beanclass="com.ds.action.affiliate.CompanyAffiliateAction"
                                  event="createOrEditCompanyAffiliate" class="btn tip-bottom" title="Edit">
                             <i class="icon-edit"></i>
                            <s:param name="companyAffiliateId" value="${companyAffiliate.id}"/>
                          </s:link>
                          <s:link beanclass="com.ds.action.affiliate.CompanyAffiliateAction"
                                  event="resendWelcomeEmail" class="btn tip-bottom" title="Resend Welcome mail">
                             <i class="icon-envelope"></i>
                            <s:param name="companyAffiliateId" value="${companyAffiliate.id}"/>
                          </s:link>
                          
                        </div>
                      </td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
              <s:layout-render name="/layouts/paginationResultCount.jsp" paginatedBean="${companyAffiliateSearchAction}"/>
              <s:layout-render name="/layouts/pagination.jsp" paginatedBean="${companyAffiliateSearchAction}"/>
            </div>
          </div>
        </div>
        <s:layout-render name="/includes/footer.jsp"/>
      </div>
    </div>

  </s:layout-component>

</s:layout-render>