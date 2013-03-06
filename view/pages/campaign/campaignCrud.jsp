<%@ page import="com.ds.constants.EnumCampaignType" %>
<%@ page import="com.ds.constants.EnumCommisionStrategy" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

<s:layout-component name="content">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/daterangepicker.css" type="text/css"/>

<style type="text/css">
  .form-horizontal .control-label {
    float: left; /*original: width: 140px;*/
    width: 300px;
    padding-top: 5px;
    text-align: right;
  }

  .form-horizontal .controls {
  /*original: margin-left: 160px;*/
    margin-left: 330px;
  }

  .stepLabel {
    line-height: 22px;
    margin-bottom: 5px;
  }

  .tierLabel {
    line-height: 18px;
    margin-bottom: 5px;
  }
</style>

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
<div class="control-group" style="margin-right:10px;">
  <span class="label label-inverse pull-left stepLabel">Basic Information</span>
</div>
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
    <s:select name="campaignDTO.campaignTypeId" style="width:200px;" id="campaignTypeSel">
      <c:forEach items="<%=EnumCampaignType.getAllCampaignTypes()%>" var="pType">
        <s:option value="${pType.id}">${pType.type}</s:option>
      </c:forEach>
    </s:select>
  </div>
</div>
<div class="control-group" id="landingPageCg" style="display:none;">
  <s:label class="control-label" name="Default landing page"/>
  <div class="controls">
    <s:text name="campaignDTO.landingPage" placeholder="http://www.test.com"/>
  </div>
</div>
<div class="control-group">
  <s:label class="control-label" name="How do you want to reward your affiliates?"/>
  <div class="controls">
    <s:select name="commissionPlanDTO.commissionStrategyId" id="commStSel">
      <c:forEach items="<%=EnumCommisionStrategy.getAllCommissionStategies()%>" var="stType">
        <s:option value="${stType.id}">${stType.name}</s:option>
      </c:forEach>
    </s:select>
  </div>
</div>
  <%--<div class="control-group">
    <s:label class="control-label" name="Enable tiered Commissions"/>
    <div class="controls">
      <s:checkbox id="tiered" name="commissionPlanDTO.tiered"/>
    </div>
  </div>--%>
<div id="tieredCommission" style="display:none;">
  <div id="tier1Comm" class="control-group">
    <fieldset>
      <div class="control-group" style="margin-right:10px;">
        <span class="label label-success pull-left tierLabel">Tier 1</span>
      </div>
      <div class="control-group" id="tier1RecurRevShareDiv">
        <s:label class="control-label" name="Initial Revenue share"/>
        <div class="controls ">
          <s:text name="commissionPlanDTO.tier1InitCom"/>
          <div class="input-append"><span class="add-on" style="margin-left:-5px;">%</span></div>
        </div>
        <s:label class="control-label" name="Recurring Revenue share"/>
        <div class="controls ">
          <s:text name="commissionPlanDTO.tier1RecurCom"/>
          <div class="input-append"><span class="add-on" style="margin-left:-5px;">%</span></div>
        </div>
      </div>

      <div class="control-group" id="tier1RecurCommDiv">
        <s:label class="control-label" name="Initial Commission"/>
        <div class="controls">
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">Rs.</span></div>
          <s:text name="commissionPlanDTO.tier1InitCom"/>
        </div>
        <s:label class="control-label" name="Recurring Commission"/>
        <div class="controls">
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">Rs</span></div>
          <s:text name="commissionPlanDTO.tier1RecurCom"/>
        </div>
      </div>

      <div class="control-group" id="tier1OneTimeRevShareDiv">
        <s:label class="control-label" name="Revenue Share"/>
        <div class="controls">
          <s:text name="commissionPlanDTO.tier1OneTimeCom"/>
          <div class="input-append"><span class="add-on" style="margin-left:-5px;">%</span></div>
        </div>
      </div>

      <div class="control-group" id="tier1OneTimeCommDiv">
        <s:label class="control-label" name="Commission"/>
        <div class="controls">
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">Rs.</span></div>
          <s:text name="commissionPlanDTO.tier1OneTimeCom"/>
        </div>
      </div>
    </fieldset>
  </div>
  <div id="tier2Comm">
    <fieldset>
      <div class="control-group" style="margin-right:10px;">
        <span class="label label-success pull-left tierLabel">Tier 1</span>
      </div>

      <input type="button" id="hideTier2Btn" class="btn btn-inverse" value="Hide Tier">

      <div class="control-group" id="tier2RecurRevShareDiv">
        <s:label class="control-label" name="Initial Revenue share"/>
        <div class="controls ">
          <s:text name="commissionPlanDTO.tier2InitCom"/>
          <div class="input-append"><span class="add-on" style="margin-left:-5px;">%</span></div>
        </div>
        <s:label class="control-label" name="Recurring Revenue share"/>
        <div class="controls ">
          <s:text name="commissionPlanDTO.tier2RecurCom"/>
          <div class="input-append"><span class="add-on" style="margin-left:-5px;">%</span></div>
        </div>
      </div>

      <div class="control-group" id="tier2RecurCommDiv">
        <s:label class="control-label" name="Initial Commission"/>
        <div class="controls">
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">Rs.</span></div>
          <s:text name="commissionPlanDTO.tier2InitCom"/>
        </div>
        <s:label class="control-label" name="Recurring Commission"/>
        <div class="controls">
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">Rs.</span></div>
          <s:text name="commissionPlanDTO.tier2RecurCom"/>
        </div>
      </div>

      <div class="control-group" id="tier2OneTimeRevShareDiv">
        <s:label class="control-label" name="Revenue Share"/>
        <div class="controls">
          <s:text name="commissionPlanDTO.tier2OneTimeCom"/>
          <div class="input-append"><span class="add-on" style="margin-left:-5px;">%</span></div>
        </div>
      </div>

      <div class="control-group" id="tier2OneTimeCommDiv">
        <s:label class="control-label" name="Commission"/>
        <div class="controls">
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">Rs.</span></div>
          <s:text name="commissionPlanDTO.tier2OneTimeCom"/>
        </div>
      </div>
    </fieldset>
  </div>
  <div id="tier3Comm">
    <fieldset>
      <div class="control-group" style="margin-right:10px;">
        <span class="label label-success pull-left tierLabel">Tier 1</span>
      </div>

      <input type="button" id="hideTier3Btn" class="btn btn-inverse" value="Hide Tier">

      <div class="control-group" id="tier3RecurRevShareDiv">
        <s:label class="control-label" name="Initial Revenue share"/>
        <div class="controls ">
          <s:text name="commissionPlanDTO.tier3InitCom"/>
          <div class="input-append"><span class="add-on" style="margin-left:-5px;">%</span></div>
        </div>
        <s:label class="control-label" name="Recurring Revenue share"/>
        <div class="controls ">
          <s:text name="commissionPlanDTO.tier3RecurCom"/>
          <div class="input-append"><span class="add-on" style="margin-left:-5px;">%</span></div>
        </div>
      </div>

      <div class="control-group" id="tier3RecurCommDiv">
        <s:label class="control-label" name="Initial Commission"/>
        <div class="controls">
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">Rs.</span></div>
          <s:text name="commissionPlanDTO.tier3InitCom"/>
        </div>
        <s:label class="control-label" name="Recurring Commission"/>
        <div class="controls">
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">Rs.</span></div>
          <s:text name="commissionPlanDTO.tier3RecurCom"/>
        </div>
      </div>

      <div class="control-group" id="tier3OneTimeRevShareDiv">
        <s:label class="control-label" name="Revenue Share"/>
        <div class="controls">
          <s:text name="commissionPlanDTO.tier3OneTimeCom"/>
          <div class="input-append"><span class="add-on" style="margin-left:-5px;">%</span></div>
        </div>
      </div>

      <div class="control-group" id="tier3OneTimeCommDiv">
        <s:label class="control-label" name="Commission"/>
        <div class="controls">
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">Rs.</span></div>
          <s:text name="commissionPlanDTO.tier3OneTimeCom"/>
        </div>
      </div>
    </fieldset>
  </div>
  <input type="button" id="addTierBtn" class="btn btn-success" value="Add Tier">
</div>

<div id="nonTierCommision">

  <div class="control-group" id="recurRevShareDiv">
    <s:label class="control-label" name="Initial Revenue share"/>
    <div class="controls ">
      <s:text name="commissionPlanDTO.initCom"/>
      <div class="input-append"><span class="add-on" style="margin-left:-5px;">%</span></div>
    </div>
    <s:label class="control-label" name="Recurring Revenue share"/>
    <div class="controls ">
      <s:text name="commissionPlanDTO.recurCom"/>
      <div class="input-append"><span class="add-on" style="margin-left:-5px;">%</span></div>
    </div>
  </div>

  <div class="control-group" id="recurCommDiv">
    <s:label class="control-label" name="Initial Commission"/>
    <div class="controls">
      <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">Rs.</span></div>
      <s:text name="commissionPlanDTO.initCom"/>
    </div>
    <s:label class="control-label" name="Recurring Commission"/>
    <div class="controls">
      <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">Rs.</span></div>
      <s:text name="commissionPlanDTO.recurCom"/>
    </div>
  </div>

  <div class="control-group" id="oneTimeRevShareDiv">
    <s:label class="control-label" name="Revenue Share"/>
    <div class="controls">
      <s:text name="commissionPlanDTO.oneTimeCom"/>
      <div class="input-append"><span class="add-on" style="margin-left:-5px;">%</span></div>
    </div>
  </div>

  <div class="control-group" id="oneTimeCommDiv">
    <s:label class="control-label" name="Commission"/>
    <div class="controls">
      <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">Rs.</span></div>
      <s:text name="commissionPlanDTO.oneTimeCom"/>
    </div>
  </div>

</div>

</div>

<div id="form-wizard-2" class="step">
  <div class="control-group" style="margin-right:10px;">
    <span class="label label-inverse pull-left stepLabel">Advanced Settings</span>
  </div>

  <div class="control-group">
    <s:label class="control-label" name="Campaign Time Range"/>

    <div class="controls">
      <div class="input-prepend">
        <span class="add-on"><i class="icon-calendar"></i></span><input type="text" id="campaignRange"/>
      </div>
    </div>
  </div>

  <%--<s:text name="campaignDTO.startDate"/>--%>
  <s:hidden id="startDate" name="campaignDTO.startDate" />
  <s:hidden id="endDate" name="campaignDTO.endDate" />
  
    <%-- <div class="control-group">
        <s:label class="control-label" name="Start Date"/>

        <div class="controls">
            <s:text name="campaignDTO.startDate"/>
        </div>
    </div>
    <div class="control-group">
        <s:label class="control-label" name="End Date"/>

        <div class="controls">
            <s:text name="campaignDTO.endDate"/>
        </div>
    </div>--%>

  <div class="control-group">
    <s:label class="control-label" name="Is this a private campaign ?"/>

    <div class="controls">
      <s:checkbox name="campaignDTO.visibleToAll"/>
    </div>
  </div>

  <div class="control-group">
    <s:label class="control-label" name="Limit recurring commissions by period: (optional)"/>

    <div class="controls">
      <s:text name="commissionPlanDTO.limitRecurCommDays"/>
    </div>
  </div>

  <div class="control-group">
    <s:label class="control-label" name="Limit recurring commissions by number of renewals: (optional)"/>

    <div class="controls">
      <s:text name="commissionPlanDTO.limitRecurCommTxn"/>
    </div>
  </div>

  <div class="control-group">
    <s:label class="control-label" name="Automatically approve commissions"/>

    <div class="controls">
      <s:checkbox name="commissionPlanDTO.autoApproveComm"/>
    </div>
  </div>


</div>


<div class="form-actions">
  <input id="back" class="btn btn-inverse" type="reset" value="Back"/>
  <input id="next" class="btn btn-success" type="submit" value="Next"/>

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

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/date.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/daterangepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/campaign/campaignCrud.js"></script>

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