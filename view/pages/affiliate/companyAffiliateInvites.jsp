<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <s:layout-component name="content">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
    <s:layout-render name="/includes/companyHeader.jsp"/>
    <s:layout-render name="/includes/companySideBar.jsp" openMenu="affiliate"/>

    <s:useActionBean beanclass="com.ds.action.affiliate.CompanyAffiliateInviteAction"
                     var="companyAffiliateInviteAction"/>
    <div id="content">
      <s:layout-render name="/includes/companyContentHeader.jsp" headerLabel="Affiliate Invites"/>
      <div id="breadcrumb">
        <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
        <a href="#" class="current">Affiliate Invites</a>
      </div>

      <div class="container-fluid">

        <div class="row-fluid">
          <div class="span12">
            <div class="widget-box">
              <div class="widget-title">
								<span class="icon">
									<i class=" icon-envelope"></i>
								</span>
                <h5>Send Invite to Affiliate</h5>
              </div>
              <div class="widget-content">
                <s:form beanclass="com.ds.action.affiliate.CompanyAffiliateInviteAction" class="form-inline">
                  <div class="input-prepend">
                    <span class="add-on"><i class="icon-envelope"></i></span><s:text name="affiliateEmail"
                                                                                     placeholder="Email To Send Invite"/>
                  </div>
                  <s:submit name="sendInviteEmail" class="btn btn-success">Send Mail Invite</s:submit>
                </s:form>
              </div>
            </div>
          </div>

        </div>

        <div class="row-fluid">
          <div class="span12">
            <div class="widget-box">
              <div class="widget-title">
								<span class="icon">
									<i class="icon-th-list"></i>
								</span>
                <h5>All Invites</h5>
              </div>
              <div class="widget-content nopadding">
                <table class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Invite Id</th>
                    <th>Affiliate Email</th>
                    <th>Status</th>
                    <th style="width: 10%">Actions</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${companyAffiliateInviteAction.companyAffiliateInvites}"
                             var="companyAffiliateInvite">
                    <tr>
                      <td>${companyAffiliateInvite.id}</td>
                      <td>${companyAffiliateInvite.affiliateEmail}</td>
                      <td>Yet to Be Converted</td>
                      <td>
                        <div class="btn-group">
                          <s:link beanclass="com.ds.action.affiliate.CompanyAffiliateInviteAction"
                                  event="sendInviteEmail" class="btn tip-bottom" title="Resend Invitation mail">
                            <i class="icon-envelope"></i>
                            <s:param name="companyAffiliateInviteId" value="${companyAffiliateInvite.id}"/>
                            <s:param name="affiliateEmail" value="${companyAffiliateInvite.affiliateEmail}"/>
                          </s:link>
                          <s:link beanclass="com.ds.action.affiliate.CompanyAffiliateInviteAction"
                                  event="deleteInvite" class="btn btn-danger tip-bottom" title="Cancel Invite">
                            <i class="icon-ban-circle"></i>
                            <s:param name="companyAffiliateInviteId" value="${companyAffiliateInvite.id}"/>
                          </s:link>

                        </div>
                      </td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
	            <div class="row-fluid">
	              <div class="span3">
	                <s:layout-render name="/layouts/paginationResultCount.jsp" paginatedBean="${companyAffiliateInviteAction}"/>
	              </div>		                                  
	              <div class="span9">
	                <s:layout-render name="/layouts/pagination.jsp" paginatedBean="${companyAffiliateInviteAction}"/>
	              </div>

	            </div>

              <%--<s:layout-render name="/layouts/paginationResultCount.jsp"
                               paginatedBean="${companyAffiliateInviteAction}"/>

              <s:layout-render name="/layouts/pagination.jsp" paginatedBean="${companyAffiliateInviteAction}"/>--%>
            </div>
          </div>
        </div>
        <s:layout-render name="/includes/footer.jsp"/>
      </div>
    </div>

  </s:layout-component>

</s:layout-render>