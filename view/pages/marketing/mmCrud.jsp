<%@ page import="com.ds.constants.EnumMarketingMaterialType" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <s:layout-component name="content">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
    <s:layout-render name="/includes/companyHeader.jsp"/>
    <s:layout-render name="/includes/companySideBar.jsp"/>

    <s:useActionBean beanclass="com.ds.action.marketing.MarketingMaterialAction" var="mmAction"/>
    <div id="content">
      <c:choose>
        <c:when test="${mmAction.marketingMaterialId != null}">
          <s:layout-render name="/includes/companyContentHeader.jsp" includeHeaderBtnGrp="false"
                           headerLabel="Edit Ad"/>
        </c:when>
        <c:otherwise>
          <s:layout-render name="/includes/companyContentHeader.jsp" includeHeaderBtnGrp="false"
                           headerLabel="Create Ad"/>
        </c:otherwise>
      </c:choose>

      <s:form beanclass="com.ds.action.marketing.MarketingMaterialAction" class="form-horizontal">
        <div class="container-fluid">

          <div class="row-fluid">
            <div class="span4">
              <div class="widget-box">
                <div class="widget-title">
								<span class="icon">
									<i class="icon-file"></i>
								</span>
                  <h5>Ad</h5>
                </div>
                <div class="widget-content nopadding">
                  <div class="control-group">
                    <s:label class="control-label" name="Type Of Ad"/>
                    <div class="controls">
                      <s:select name="type">
                        <c:forEach items="<%=EnumMarketingMaterialType.getAllMMTypes()%>" var="pType">
                          <s:option value="${pType.id}">${pType.type}</s:option>
                        </c:forEach>
                      </s:select>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="span8">
              <div class="widget-box">
                <div class="widget-title">
                  <a href="#collapseCampaigns" data-toggle="collapse">
								<span class="icon">
									<i class="icon-magnet"></i>
								</span>
                    <h5>Campaigns</h5>
                  </a>
                </div>
                <div class="collapse in " id="collapseCampaigns" style="border-bottom:1px solid #CDCDCD;">
                  <div class="widget-content" style="border-bottom:none;">
                    <div class="span4">
                      <c:forEach items="${mmAction.allCampaigns}" var="campaign" varStatus="campaignCtr">
                        <c:if test="${campaignCtr.index % 2 == 0}">
                          <input type="checkbox" campaignId=${campaign.id}/>${campaign.name} <br/>
                        </c:if>
                      </c:forEach>
                    </div>
                    <div class="span4">
                      <c:forEach items="${mmAction.allCampaigns}" var="campaign" varStatus="campaignCtr">
                        <c:if test="${campaignCtr.index % 2 != 0}">
                          <input type="checkbox" campaignId=${campaign.id}/>${campaign.name} <br/>
                        </c:if>
                      </c:forEach>
                    </div>
                  </div>
                    <%--<div class="widget-content nopadding">
                      <c:forEach items="${mmAction.allCampaigns}" var="campaign">
                        <input type="checkbox" campaignId=${campaign.id}/>${campaign.name} <br/>
                      </c:forEach>
                    </div>--%>
                </div>
              </div>

            </div>
          </div>

          <div class="row-fluid">
              <%--<s:hidden name="employeeId"/>
                          <s:submit name="saveUser" value="Save Changes" class="btn btn-success"/>
              --%>
            <s:link beanclass="com.ds.action.employee.UserSearchAction"
                    class="btn btn-inverse"><i class="icon-hand-left icon-white"></i>&nbsp;Back</s:link>
          </div>
        </div>
      </s:form>

      <s:layout-render name="/includes/footer.jsp"/>
    </div>
    </div>

    </div>


    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/assets/js/unicorn.form_validation.js"></script>


  </s:layout-component>

  <s:layout-component name="scriptComponent">

    <script type="text/javascript">

      $(document).ready(function() {
        $('.cpType').click(function(event) {
          var type = $(this).attr('type');
          $("#campaignType").val(type);
          var searchForm = $("#campaignSearchForm")[0];
          var actionUrl = searchForm.action;
          actionUrl += '?searchCampaign';
          searchForm.action = actionUrl;
          searchForm.submit();
        });

        $.each($(".cpType"), function(index, value) {
          var type = $(this).attr('type');
          var selType = $("#campaignType").val();
          if (selType === type) {
            $(this).addClass('disabled');
          }
        });
      });

    </script>
  </s:layout-component>

</s:layout-render>