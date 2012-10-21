<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <s:layout-component name="content">
    <s:useActionBean beanclass="com.ds.action.employee.UserAction" var="userAction"/>
    <div class="content-outer wrap">
      <div class="col_12">
        <div id="page-heading">
          <c:choose>
            <c:when test="${userAction.employeeId != null}">
              <h4>Edit User</h4>
            </c:when>
            <c:otherwise>
              <h4>Create User</h4>
            </c:otherwise>
          </c:choose>
        </div>

        <s:form beanclass="com.ds.action.employee.UserAction" class="vertical">
          <div class="col_6">

            <s:label name="User Full Name"/>
            <s:text name="userDTO.fullName" class="check-empty auto-adjust"/>

            <s:label name="Email"/>
            <s:text name="userDTO.email" class="check-empty auto-adjust"/>
            
            <s:label name="Password"/>
            <s:text name="userDTO.newPassword" class="check-empty auto-adjust"/>

            <s:label name="Send Email On Affiliate Signup"/>
            <s:checkbox name="userDTO.sendEmailOnAddAffiliate" class="check-empty auto-adjust" maxlength="11"/>

            <s:label name="Send Email On Affiliate Joining"/>
            <s:checkbox name="userDTO.sendEmailOnJoinAffiliate" class="auto-adjust"/>

            <s:label name="Send Email On Payout"/>
            <s:checkbox name="userDTO.sendEmailOnPayout" class="auto-adjust"/>

            <s:label name="Send Email On Goal Conversion"/>
            <s:checkbox name="userDTO.sendEmailOnGoalConversion" class="auto-adjust"/>

            <s:label name="account non expired"/>
            <s:checkbox name="userDTO.accountNonExpired" class="auto-adjust"/>

            <s:label name="Account non locked"/>
            <s:checkbox name="userDTO.accountNonLocked" class="auto-adjust"/>

            <s:label name="Enabled"/>
            <s:checkbox name="userDTO.enabled" class="auto-adjust"/>

            <s:label name="Credintals non expired"/>
            <s:checkbox name="userDTO.credentialsNonExpired" class="auto-adjust"/>
            

          </div>

          <div class="clear"></div>

          <div class="col_2">
            <s:hidden name="employeeId"/>
            <s:submit name="updateEmployee" value="Save Changes" class="button blue small"/>
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