<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../includes/taglibInclude.jsp" %>
<s:layout-definition>
  <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  <html>
  <head>
    <title>
        <%--<c:choose>
          <c:when test="${hk:isNotBlank(pageTitle)}">
            ${pageTitle}
          </c:when>
          <c:otherwise>
            <c:if test="${hk:isNotBlank(topHeading)}">
              ${topHeading}
            </c:if>
          </c:otherwise>
        </c:choose>--%>
      | HealthKart.com
    </title>
    <s:layout-component name="htmlHead"/>
      <%--<link href="../assets/css/grid.css" rel="stylesheet" type="text/css"/>--%>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap-responsive.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/docs.css" type="text/css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/google-code-prettify/prettify.css" type="text/css"/>

     <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/application.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap-affix.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap-alert.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap-button.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap-carousel.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap-collapse.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap-dropdown.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap-modal.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap-popover.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap-scrollspy.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap-tab.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap-tooltip.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap-transition.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap-typeahead.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/google-code-prettify/prettify.js"></script>

  </head>
  <body>
  <s:layout-component name="modal"/>
    <%--<div id="container" class="container_24">--%>

 <s:layout-component name="header">
    <s:layout-render name="/templates/header.jsp"/>
  </s:layout-component>

  <h1><s:layout-component name="heading"/></h1>

  <s:layout-component name="messages">
    <s:errors/>
    <%--<div id="error-messages" ></div>
    <div class="alert messages "><s:messages key="generalMessages"/></div>--%>
  </s:layout-component>
                                               
  <s:layout-component name="content">
  </s:layout-component>
              
  <s:layout-component name="scriptComponent"/>
  <%--<s:layout-render name="/templates/footer.jsp"/>--%>

    <%--</div>--%>

  </body>
  </html>
</s:layout-definition>