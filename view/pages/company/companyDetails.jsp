<%@ page import="com.ds.constants.EnumMarketingMaterialType" %>
<%@ page import="com.ds.constants.FileManageType" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

    <s:layout-component name="content">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/marketing/textAd.css"
              type="text/css"/>
        <s:layout-render name="/includes/companyHeader.jsp"/>
        <s:layout-render name="/includes/companySideBar.jsp"/>

        <s:useActionBean beanclass="com.ds.action.company.CompanyAction" var="companyAction"/>
        <div id="content">

            <s:layout-render name="/includes/companyContentHeader.jsp" includeHeaderBtnGrp="false"
                             headerLabel="Company Settings"/>


            <s:form beanclass="com.ds.action.company.CompanyAction" class="form-horizontal" id="companyProfileForm">
                <div class="container-fluid">

                    <div class="row-fluid">
                        <div class="span6">
                            <div class="widget-box">
                                <div class="widget-title">
								<span class="icon">
									<i class="icon-file"></i>
								</span>
                                    <h5>Company Profile</h5>
                                </div>
                                <div class="widget-content nopadding">
                                    <div class="control-group">
                                        <s:label class="control-label" name="Company Name"/>
                                        <div class="controls">
                                            <s:text name="companyDTO.name" class="required"/>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <s:label class="control-label" name="Company Description"/>
                                        <div class="controls">
                                            <s:text name="companyDTO.description"/>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <s:label class="control-label" name="Company Website"/>
                                        <div class="controls">
                                            <s:text name="companyDTO.url" class="required"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <c:if test="${companyAction.imageId !=null}">
                            <div class="span6">
                                <div class="widget-box">
                                    <div class="widget-title">
                                        <a href="#collapseExistingImageWidget" data-toggle="collapse">
								<span class="icon">
									<i class="icon-magnet"></i>
								</span>
                                            <h5>Existing Image</h5>
                                        </a>
                                    </div>
                                    <div class="collapse in" id="collapseExistingImageWidget">
                                        <div class="widget-content">
                                            <img src="/getImage?imageId=${companyAction.imageId}" alt="CompanyLogo">
                                            <br/><br/>
                                            <s:link beanclass="com.ds.action.core.FileManageAction"
                                                    event="deleteCompanyLogo"
                                                    class="btn btn-danger"><i
                                                    class="icon-hand-left icon-white"></i>&nbsp;Delete Image
                                                <s:param name="companyShortName"
                                                         value="${companyAction.companyShortName}"/>
                                            </s:link>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>

                    <div class="row-fluid">
                        <s:hidden name="companyShortName" id="companyShortName"/>
                        <s:submit name="updateCompany" value="Save Changes" class="btn btn-success"/>

                        <s:link beanclass="com.ds.action.employee.UserSearchAction"
                                class="btn btn-inverse"><i class="icon-hand-left icon-white"></i>&nbsp;Back to Users</s:link>
                    </div>
                </div>
            </s:form>
            
            <c:if test="${companyAction.imageId eq null}">
                
                <div class="container-fluid" id="imageContainer" >
                    <div class="row-fluid">
                        <div class="span5">
                            <div class="widget-box">
                                <div class="widget-title">
                                    <a href="#collapseImageWidget" data-toggle="collapse">
								<span class="icon">
									<i class="icon-magnet"></i>
								</span>
                                        <h5>Upload New Banner Image</h5>
                                    </a>
                                </div>
                                <div class="collapse in" id="collapseImageWidget">
                                    <div class="widget-content">
                                        <form action="/fileUpload" multipart="1" method="post"
                                              enctype="multipart/form-data"
                                              id="companyLogoUploadForm">
                                            <input type="file" name="file" class="formelement" style="width: 312px;"
                                                   id="marketing_tool_banner">
                                            <input type="hidden" name="fileManageType"
                                                   value="<%=FileManageType.COMPANY_LOGO%>">
                                            <input type="hidden" name="identifier"
                                                   value="${companyAction.companyShortName}">
                                            <input type="submit" value="&nbsp;Upload" class="btn btn-success"><i
                                                class="icon-hand-left icon-white"></i></input>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </c:if>

            <s:layout-render name="/includes/footer.jsp"/>
        </div>
        </div>

        </div>


        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.validate.js"></script>
        <%--<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/marketing/mmCrud.js"></script>--%>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/select2.min.js"></script>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/assets/js/unicorn.form_validation.js"></script>


    </s:layout-component>

    <s:layout-component name="scriptComponent">


    </s:layout-component>

</s:layout-render>