<%@ page import="com.ds.constants.EnumCampaignType" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <s:layout-component name="content">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
    <s:layout-render name="/includes/companyHeader.jsp"/>
    <s:layout-render name="/includes/companySideBar.jsp"/>

    <s:useActionBean beanclass="com.ds.action.campaign.CampaignAction" var="campaignAction"/>
    <div id="content">
      <c:choose>
        <c:when test="${campaignAction.campaignId != null}">
          <s:layout-render name="/includes/companyContentHeader.jsp" includeHeaderBtnGrp="false"
                           headerLabel="Edit Campaign"/>
        </c:when>
        <c:otherwise>
          <s:layout-render name="/includes/companyContentHeader.jsp" includeHeaderBtnGrp="false"
                           headerLabel="Create Campaign"/>
        </c:otherwise>
      </c:choose>


      <div class="container-fluid">
        <div class="row-fluid">
          <div class="span12">
            <div class="widget-box">
              <div class="widget-title">
                        <span class="icon">
                          <i class="icon icon-tint"></i>
                        </span>
                <h5>Campaign</h5>
              </div>
              <div class="widget-content nopadding">
                <s:form beanclass="com.ds.action.campaign.CampaignAction" id="form-wizard" class="form-horizontal">
                  <%--<form  class="form-horizontal" method="post">--%>
                  <div id="form-wizard-1" class="step">
                    <div class="control-group">
                      <s:label class="control-label" name="Campaign Name"/>
                      <div class="controls">
                        <s:text name="campaignDTO.name" placeholder="campaign name"/>
                      </div>
                    </div>
                    <div class="control-group">
                      <s:label class="control-label" name="Description"/>
                      <div class="controls">
                        <s:textarea name="campaignDTO.description" placeholder="campaign description"/>
                      </div>
                    </div>
                    <div class="control-group">
                      <s:label class="control-label" name="Campaign Type"/>
                      <div class="controls">
                        <s:select name="campaignDTO.campaignTypeId" style="width:200px;">
                          <c:forEach items="<%=EnumCampaignType.getAllCampaignTypes()%>" var="pType">
                            <s:option value="${pType.id}">${pType.type}</s:option>
                          </c:forEach>
                        </s:select>
                      </div>
                    </div>

                  </div>
                  <div id="form-wizard-2" class="step">
                    <div class="control-group">
                      <label class="control-label">Email</label>

                      <div class="controls">
                        <input id="email" type="text" name="email"/>
                      </div>
                    </div>
                    <div class="control-group">
                      <label class="control-label">EULA Acceptation</label>

                      <div class="controls">
                        <input id="eula" type="checkbox" name="eula"/>
                      </div>
                    </div>
                  </div>
                  <div class="form-actions">
                    <input id="back" class="btn btn-primary" type="reset" value="Back"/>
                    <input id="next" class="btn btn-primary" type="submit" value="Next"/>

                    <div id="status"></div>
                  </div>
                  <div id="submitted"></div>
                </s:form>
              </div>
            </div>
          </div>
        </div>


        <s:layout-render name="/includes/footer.jsp"/>
      </div>

    </div>


    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.wizard.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/assets/js/unicorn.form_validation.js"></script>
    
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.flot.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.peity.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/unicorn.dashboard.js"></script>--%>?


  </s:layout-component>
  <s:layout-component name="scriptComponent">

    <script type="text/javascript">

      $(document).ready(function() {
        $("#form-wizard").formwizard();
      });

    </script>
  </s:layout-component>

</s:layout-render>