<%@ page import="com.ds.constants.EnumCampaignType" %>
<%@ page import="com.ds.constants.EnumCommissionEarningStatus" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

<s:layout-component name="content">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
    <s:layout-render name="/includes/companyHeader.jsp"/>
    <s:layout-render name="/includes/companySideBar.jsp"/>

    <div id="content">
        <s:layout-render name="/includes/companyContentHeader.jsp" headerLabel="Campaigns"/>
        <div id="breadcrumb">
            <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
            <a href="#" class="current">Commissions</a>
        </div>

        <div class="container-fluid">
            <s:useActionBean beanclass="com.ds.action.commission.CommissionEarningSearchAction"
                             var="commissionSearchAction"/>

            <div class="row-fluid">
                <div class="span10">
                    <div class="widget-box">
                        <div class="widget-title">
								<span class="icon">
									<i class=" icon-search"></i>
								</span>
                            <h5>Search Commissions</h5>
                        </div>
                        <div class="widget-content">
                            <s:form beanclass="com.ds.action.commission.CommissionEarningSearchAction"
                                    id="commissionSearchForm"
                                    class="form-inline">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-tag"></i></span><s:text name="customer"
                                                                                                placeholder="Customer id"/>

                                        <%--todo affiliate ka drop down--%>
                                </div>

                                <s:hidden name="commissionEarningStatusId" id="commissionEarningStatus"/>

                                <%--<div class="input btn-toolbar">--%>
                                <div class="btn-group">

                                    <a class="btn ceType" href="#"
                                       type="<%=EnumCommissionEarningStatus.ALL.getId()%>">All</a>
                                    <a class="btn ceType" href="#"
                                       type="<%=EnumCommissionEarningStatus.PENDING_APPROVAL.getId()%>">Pending
                                        Approval</a>
                                    <a class="btn ceType" href="#"
                                       type="<%=EnumCommissionEarningStatus.APPROVED.getId()%>">Approved</a>
                                    <a class="btn ceType" href="#"
                                       type="<%=EnumCommissionEarningStatus.REJECTED.getId()%>">Rejected</a>


                                </div>
                                <%--  </div>--%>
                                <s:submit name="searchCommissionEarnings" class="btn btn-inverse">Search</s:submit>
                            </s:form>
                        </div>
                    </div>
                </div>
                <div class="span2">
                    <s:link beanclass="com.ds.action.campaign.CampaignAction"
                            event="createOrEditCampaign" class="btn btn-success">
                        <i class="icon-plus-sign icon-white"></i>&nbsp;Add Commission //TODO
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
                            <h5>Commissions</h5>
                        </div>
                        <div class="widget-content nopadding">
                        <s:form beanclass="com.ds.action.commission.CommissionEarningSearchAction"
                                id="commissionSearchForm"
                                class="form-inline">
                            <table class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" id="markAllChk"/></th>
                                    <th>Campaign</th>
                                    <th>Affiliate</th>
                                    <th>Amount</th>
                                    <th>Type(Direct/Recur)</th>
                                    <th>Customer</th>
                                    <th>Commission Date</th>
                                    <th>Acted By</th>
                                    <th>Acted On</th>
                                    <th style="width: 10%">Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${commissionSearchAction.commissionEarnings}"
                                           var="commissionEarning">
                                    <tr>
                                        <td><input type="checkbox" value="${commissionEarning.id}"
                                                   class="checkCheckBoxGroup commissionEarningChk"
                                                   name="commissionEarningChk"/></td>
                                        <td>${commissionEarning.campaign.name}</td>
                                        <td>${commissionEarning.affiliate.login}</td>
                                        <td>${commissionEarning.earning}</td>
                                        <td>${commissionEarning.directCommission}</td>
                                        <td>${commissionEarning.eventTracking.customerId}</td>
                                        <td>${commissionEarning.earningDate}</td>
                                        <td>${commissionEarning.actedBy.username}</td>
                                        <td>${commissionEarning.actedOn}</td>
                                        <td>
                                            <div class="btn-group">
                                                <s:link beanclass="com.ds.action.campaign.CampaignAction"
                                                        event="createOrEditCampaign" class="btn tip-bottom"
                                                        title="Edit">
                                                    <i class="icon-edit"></i>View event details
                                                    <s:param name="campaignId" value="${campaign.id}"/>
                                                </s:link>
                                                <s:link beanclass="com.ds.action.commission.CommissionEarningSearchAction"
                                                        event="markAsApproved" class="btn tip-bottom"
                                                        title="Approve Commission">
                                                    <i class="icon-edit"></i>Approve
                                                    <s:param name="commissionEarningId"
                                                             value="${commissionEarning.id}"/>
                                                </s:link>
                                                    <%-- <s:link beanclass="com.ds.action.employee.UserAction"
                                                            event="createOrEditUser" title="Edit">
                                                      <i class="icon-edit"></i>
                                                      <s:param name="employeeId" value="${user.username}"/>
                                                    </s:link>--%>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>




                            <s:select name="bulkAction">
                                <s:option value="">Choose an Action:</s:option>
                                <s:option value="<%=EnumCommissionEarningStatus.APPROVED.getStatus()%>">Approve</s:option>
                                <s:option value="<%=EnumCommissionEarningStatus.REJECTED.getStatus()%>">Reject</s:option>
                            </s:select>

                            <s:submit name="bulkUpdateCommissionEarning" value="Bulk Update Selected Tasks"
                                      id="bulkUpdate"
                                      class="button blue small"/>
                        </s:form>



                        <div class="row-fluid">
                            <div class="span3">
                                <s:layout-render name="/layouts/paginationResultCount.jsp"
                                                 paginatedBean="${commissionSearchAction}"/>
                            </div>
                            <div class="span9">
                                <s:layout-render name="/layouts/pagination.jsp"
                                                 paginatedBean="${commissionSearchAction}"/>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
            <s:layout-render name="/includes/footer.jsp"/>
        </div>

    </div>


</s:layout-component>
<s:layout-component name="scriptComponent">

    <script type="text/javascript">

        $(document).ready(function() {
            $('.ceType').click(function(event) {
                var status = $(this).attr('type');
                $("#commissionEarningStatus").val(status);
                var searchForm = $("#commissionSearchForm")[0];
                var actionUrl = searchForm.action;
                actionUrl += '?searchCommissionEarnings';
                searchForm.action = actionUrl;
                searchForm.submit();
            });

            $.each($(".ceType"), function(index, value) {
                var type = $(this).attr('type');
                var selType = $("#commissionEarningStatus").val();

                if (selType === type) {
                    $(this).addClass('disabled');
                }
            });

            $('#markAllChk').click(function() {
                if ($(this).attr("checked") == "checked") {
                    $('.commissionEarningChk').attr("checked", "checked");
                } else {
                    $('.commissionEarningChk').removeAttr("checked");
                }
            });

            $('#bulkUpdate').click(function() {
                var checkedCommissionEarning;
                var nameToBeSet = "";
                var checkedEarnings = $('.commissionEarningChk').filter(':checked');
                var i = 0 ;
                for (i = 0; i < checkedEarnings.length; i++) {
                    var checkedEarning = checkedEarnings[i];
                    nameToBeSet = "earningIds[" + i + "]";
                    $(checkedEarning).attr("name", nameToBeSet);
                }
                return true;
            });
        });

    </script>
</s:layout-component>

</s:layout-render>