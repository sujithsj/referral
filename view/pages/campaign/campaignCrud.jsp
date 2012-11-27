<%@ page import="com.ds.constants.EnumCampaignType" %>
<%@ page import="com.ds.constants.EnumCommisionStrategy" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

<s:layout-component name="content">
<s:useActionBean beanclass="com.ds.action.campaign.CampaignAction" var="campaignAction"/>
<div class="container content-outer wrap">
<%--<div class="row">--%>
<div class="span12">
  <div id="page-heading">
    <c:choose>
      <c:when test="${campaignAction.campaignId != null}">
        <h4>Edit Campaign</h4>
      </c:when>
      <c:otherwise>
        <h4>Create campaign</h4>
      </c:otherwise>
    </c:choose>
  </div>
</div>

<%--<div class="row">--%>
<s:form beanclass="com.ds.action.campaign.CampaignAction" class="form-horizontal">
<div class="span12">

<div class="control-group">
  <s:label class="control-label" name="Campaign Name"/>
  <div class="controls ">
    <s:text name="campaignDTO.name" placeholder="campaign name"/>
  </div>
</div>

<div class="control-group">
  <s:label class="control-label" name="Description"/>
  <div class="controls ">
    <s:textarea name="campaignDTO.description" placeholder="campaign description"/>
  </div>
</div>

<div class="control-group">
  <s:label class="control-label" name="Campaign Type"/>
  <div class="controls ">
    <s:select name="campaignDTO.campaignTypeId">
      <c:forEach items="<%=EnumCampaignType.getAllCampaignTypes()%>" var="pType">
        <s:option value="${pType.id}">${pType.type}</s:option>
      </c:forEach>
    </s:select>
  </div>
</div>

<div class="control-group">
  <s:label class="control-label" name="How do you want to reward your affiliates?"/>
  <div class="controls ">
    <s:select name="commissionPlanDTO.commissionStrategyId" id="commStSel">
      <c:forEach items="<%=EnumCommisionStrategy.getAllCommissionStategies()%>" var="stType">
        <s:option value="${stType.id}">${stType.name}</s:option>
      </c:forEach>
    </s:select>
  </div>
</div>

<div class="control-group">
  <s:label class="control-label" name="Enable tiered Commissions"/>
  <div class="controls">
    <s:checkbox id="tiered" name="commissionPlanDTO.tiered"/>
  </div>
</div>

<div id="tieredCommission">
  <div id="tier1Comm" class="control-group">
    <fieldset>
      <legend>Tier 1</legend>

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
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">$</span></div>
          <s:text name="commissionPlanDTO.tier1InitCom"/>
        </div>
        <s:label class="control-label" name="Recurring Commission"/>
        <div class="controls">
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">$</span></div>
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
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">$</span></div>
          <s:text name="commissionPlanDTO.tier1OneTimeCom"/>
        </div>
      </div>
    </fieldset>
  </div>
  <div id="tier2Comm">
    <fieldset>
      <legend>Tier 2</legend>

      <input type="button" id="hideTier2Btn" value="Hide Tier">

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
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">$</span></div>
          <s:text name="commissionPlanDTO.tier2InitCom"/>
        </div>
        <s:label class="control-label" name="Recurring Commission"/>
        <div class="controls">
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">$</span></div>
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
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">$</span></div>
          <s:text name="commissionPlanDTO.tier2OneTimeCom"/>
        </div>
      </div>
    </fieldset>
  </div>
  <div id="tier3Comm">
    <fieldset>
      <legend>Tier 3</legend>

      <input type="button" id="hideTier3Btn" value="Hide Tier">

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
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">$</span></div>
          <s:text name="commissionPlanDTO.tier3InitCom"/>
        </div>
        <s:label class="control-label" name="Recurring Commission"/>
        <div class="controls">
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">$</span></div>
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
          <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">$</span></div>
          <s:text name="commissionPlanDTO.tier3OneTimeCom"/>
        </div>
      </div>
    </fieldset>
  </div>
  <input type="button" id="addTierBtn" value="Add Tier">
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
      <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">$</span></div>
      <s:text name="commissionPlanDTO.initCom"/>
    </div>
    <s:label class="control-label" name="Recurring Commission"/>
    <div class="controls">
      <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">$</span></div>
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
      <div class="input-prepend"><span class="add-on" style="margin-right:-5px;">$</span></div>
      <s:text name="commissionPlanDTO.oneTimeCom"/>
    </div>
  </div>

</div>

<input type="button" id="advControlBtn" value="Advanced Settings">

<div id="advanceControls">
  <div class="control-group">
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
  </div>

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

</div>
<div class="clear"></div>

<div class="col_2">
    <%--        <s:hidden name="marketingMaterialId"/>--%>
  <s:submit name="saveCampaign" value="Save Changes" class="button blue small"/>
</div>

<div class="col_2">
  <s:link beanclass="com.ds.action.campaign.CampaignSearchAction"
          class="button blue small"><span class="icon white small" data-icon=":"></span>Back</s:link>
</div>
</div>
</div>
</s:form>
</div>
</div>
<%--</div>--%>
</s:layout-component>
<s:layout-component name="scriptComponent">
  <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/campaign/campaignCrud.js"></script>
</s:layout-component>
</s:layout-render>