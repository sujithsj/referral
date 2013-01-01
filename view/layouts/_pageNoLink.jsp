<%@ page import="com.ds.web.action.BasePaginatedAction" %>
<%@ page import="com.ds.web.action.PaginationConstants" %>
<%@ page import="net.sourceforge.stripes.util.bean.BeanUtil" %>
<%@ page import="net.sourceforge.stripes.util.bean.EvaluationException" %>
<%@ page import="net.sourceforge.stripes.util.bean.ParseException" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/taglibInclude.jsp"%>
<s:layout-definition>
  <%
    BasePaginatedAction basePaginatedAction = (BasePaginatedAction) pageContext.getAttribute("paginatedBean");
    String pageNoStr = pageContext.getAttribute("pageNo").toString();
    Integer pageNo = Integer.parseInt(pageNoStr);
    if (basePaginatedAction.getPageNo() != pageNo) {
  %>

  <li><s:link beanclass="<%=basePaginatedAction.getClass().getName()%>" event="<%=basePaginatedAction.getEventParam()%>">
    <%
      if (basePaginatedAction.getParamSet() != null && !basePaginatedAction.getParamSet().isEmpty()) {
        for (String param : basePaginatedAction.getParamSet()) {
          String paramValue = "";
          try {
            Object paramValueObj = BeanUtil.getPropertyValue(param, basePaginatedAction);
            if (paramValueObj == null) continue;
            if (paramValueObj instanceof List) {
              List paramValueList = (List) paramValueObj;
              for (Object value : paramValueList) {
                %>
                <s:param name="<%=param%>" value="<%=value.toString()%>"/>
                <%
              }
            } else if(paramValueObj instanceof Date){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                paramValue = sdf.format(paramValueObj);
                 %>
                  <s:param name="<%=param%>" value="<%=paramValue%>"/>
                <%
             }else {
              paramValue = paramValueObj.toString();
              %>
              <s:param name="<%=param%>" value="<%=paramValue%>"/>
              <%
            }
          } catch (ParseException e) {
    %>
      Unable to render link correctly. check your paramSet in the actionBean
    <%
          } catch (EvaluationException e) {
    %>
      Unable to render link correctly. check your paramSet in the actionBean
    <%
          }
        }
      }
    %>
    <s:param name="<%=PaginationConstants.pageNo%>" value="${pageNo}"/>
    <%
      if (!StringUtils.isBlank(request.getParameter(PaginationConstants.perPage))) {
    %>
    <s:param name="<%=PaginationConstants.perPage%>" value="<%=request.getParameter(PaginationConstants.perPage)%>"/>
    <%
      }
    %>
    ${pageText}
  </s:link>
  </li>
  <%
    } else {
  %>
  <li class="active">
  <%--<a class="pagi_link current">--%>
  <span>${pageText}</span>
  <%--</a>--%>
    </li>
  <%
    }
  %>
</s:layout-definition>