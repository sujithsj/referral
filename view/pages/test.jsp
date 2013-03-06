<%@ page import="com.ds.constants.EnumMarketingMaterialType" %>
<%@ page import="com.ds.constants.FileManageType" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

  <s:layout-component name="content">
    


    <div id="content">

      <form action="#" method="get" class="form-horizontal">
        <select>
          <option>First option</option>
          <option>Second option</option>
          <option>Third option</option>
          <option>Fourth option</option>
          <option>Fifth option</option>
          <option>Sixth option</option>
          <option>Seventh option</option>
          <option>Eighth option</option>
        </select>

      </form>


    </div>
    </div>

    </div>


    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.validate.js"></script>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/assets/js/unicorn.form_validation.js"></script>


  </s:layout-component>

  <s:layout-component name="scriptComponent">


  </s:layout-component>

</s:layout-render>