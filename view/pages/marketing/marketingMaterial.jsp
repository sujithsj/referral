<%@ page import="com.ds.constants.EnumMarketingMaterialType" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

<s:layout-component name="content">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/marketing/textAd.css" type="text/css"/>
    <s:layout-render name="/includes/companyHeader.jsp"/>
    <s:layout-render name="/includes/companySideBar.jsp"/>

    <s:useActionBean beanclass="com.ds.action.marketing.MarketingMaterialSearchAction" var="mmSearchAction"/>
    <div id="content">
    <s:layout-render name="/includes/companyContentHeader.jsp" headerLabel="Ads"/>
    <div id="breadcrumb">
        <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
        <a href="#" class="current">Ads</a>
    </div>

    <c:set var="referalAdType" value="<%=EnumMarketingMaterialType.ReferalAd.getId()%>"/>

    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span2 pull-right">
                <s:link beanclass="com.ds.action.marketing.MarketingMaterialAction"
                        event="createOrEditMarketingMaterial" class="btn btn-inverse">
                    <i class="icon-plus-sign icon-white"></i>&nbsp;Add new Ad
                </s:link>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title">
								<span class="icon">
									<i class=" icon-search"></i>
								</span>
                        <h5>Search Ads</h5>
                            <span class="label label-success tip-left" title="Total Ads">${mmSearchAction.totalAdCount}</span>
                        <%--<span class="label label-success tip-left" title="Total Ads">4</span>--%>
                    </div>
                    <div class="widget-content">
                        <s:form beanclass="com.ds.action.marketing.MarketingMaterialSearchAction" id="mmSearchForm"
                                class="form-inline">
                            <div class="input-prepend">
                                <span class="add-on"><i class="icon-tag"></i></span><s:text name="title"
                                                                                            placeholder="Title"/>
                            </div>
                            <div class="input-prepend">
                                <span class="add-on"><i class="icon-tag"></i></span><s:text name="landingPage"
                                                                                            placeholder="LandingPage"/>
                            </div>

                            <s:hidden name="type" id="mmType"/>

                            <div class="btn-group">
                                <a class="btn btn-inverse mmType" href="#"
                                   type="<%=EnumMarketingMaterialType.ALL.getId()%>">All</a>
                                <a class="btn btn-inverse mmType" href="#"
                                   type="<%=EnumMarketingMaterialType.Banner.getId()%>">Banner&nbsp;<span
                                        class="label label-success"
                                        style="position:absolute; top:-8px;right:3px;">${mmSearchAction.totalBannerAds}</span></a>
                                <a class="btn btn-inverse mmType" href="#"
                                   type="<%=EnumMarketingMaterialType.TextAd.getId()%>">Text
                                    Ads&nbsp;<span
                                            class="label label-success"
                                            style="position:absolute; top:-8px;right:3px;">${mmSearchAction.totalTextAds}</span></a>
                                <a class="btn btn-inverse mmType" href="#"
                                   type="<%=EnumMarketingMaterialType.ReferalAd.getId()%>">Referal
                                    Ads&nbsp;<span
                                            class="label label-success"
                                            style="position:absolute; top:-8px;right:3px;">${mmSearchAction.totalReferalAds}</span></a>
                            </div>

                            <%--<div class="navbar navbar-inverse">
                          <ul class="nav btn-group">
                           <li class="btn btn-mini btn-inverse"><a class=" mmType" href="#" type="<%=EnumMarketingMaterialType.ALL.getId()%>">All</a></li>
                            <li class="btn btn-mini btn-inverse"><a class=" mmType" href="#" type="<%=EnumMarketingMaterialType.Banner.getId()%>">Banner&nbsp;<span
                                class="label label-important" style="position:absolute; top:-8px;right:3px;">5</span></a></li>
                            <li class="btn btn-mini btn-inverse"><a class=" mmType" href="#" type="<%=EnumMarketingMaterialType.ReferalAd.getId()%>">Text Ads</a></li>
                          </ul>
                            </div>--%>
                            <s:submit name="searchMarketingMaterial" class="btn btn-success">Search</s:submit>
                        </s:form>
                    </div>
                </div>
            </div>

        </div>
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title">
								<span class="icon">
									<i class="icon-filter"></i>
								</span>
                        <h5>Campaigns</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>Title</th>
                                <th>Type</th>
                                <th>Landing Page</th>
                                <th style="width: 10%">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${mmSearchAction.marketingMaterials}" var="marketingMaterail">
                                <tr>
                                    <td>${marketingMaterail.title}</td>
                                    <td>${marketingMaterail.marketingMaterialType.type}</td>
                                    <td>${marketingMaterail.landingPageUrl}</td>
                                    <td>
                                        <c:if test="${marketingMaterail.marketingMaterialType.id != referalAdType}">
                                            <div class="btn-group">
                                                <s:link beanclass="com.ds.action.marketing.MarketingMaterialAction"
                                                        event="createOrEditMarketingMaterial" class="btn tip-bottom"
                                                        title="Edit">
                                                    <i class="icon-edit"></i>
                                                    <s:param name="marketingMaterialId"
                                                             value="${marketingMaterail.id}"/>
                                                </s:link>
                                                <a href="#" class="shareAd btn tip-bottom" title="Share this ad"
                                                   mmId="${marketingMaterail.id}">
                                                    <i class="icon-share"></i>
                                                </a>
                                                <a href="#" class="preview btn tip-bottom" title="Preview"
                                                   mmId="${marketingMaterail.id}">
                                                    <i class="icon-screenshot"></i>
                                                </a>
                                            </div>
                                        </c:if>
                                    </td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="row-fluid">
                        <div class="span3">
                            <s:layout-render name="/layouts/paginationResultCount.jsp"
                                             paginatedBean="${mmSearchAction}"/>
                        </div>
                        <div class="span9">
                            <s:layout-render name="/layouts/pagination.jsp" paginatedBean="${mmSearchAction}"/>
                        </div>

                    </div>

                </div>
            </div>
        </div>
        <s:layout-render name="/includes/footer.jsp"/>
    </div>

    <div id="previewModal" class="modal hide fade modalBox" tabindex="-1" role="dialog"
         aria-labelledby="adPreviewModalLabel"
         aria-hidden="true">
        <div class="modal-header modalHeader">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="adPreviewModalLabel">Ad Preview</h3>
        </div>
        <div class="modal-body" id="adPreviewModalContent" align="center">

        </div>
        <div class="modal-footer">
            <button class="btn btn-inverse" data-dismiss="modal" aria-hidden="true">Close</button>
        </div>
    </div>

    <div id="shareModal" class="modal hide fade modalBox" tabindex="-1" role="dialog"
         aria-labelledby="adShareModalLabel"
         aria-hidden="true">
        <div class="modal-header modalHeader">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="adShareModalLabel">Ad Sharing Code</h3>
        </div>
        <div class="modal-body" style="margin-right:10px; text-align:center;overflow-y:hidden;">
            <textarea rows="20" readonly="true" style="width:650px;cursor:copy;" onclick="this.select()"
                      id="adShareModalContent"></textarea>
        </div>
        <div class="modal-footer">
            <button class="btn btn-inverse" data-dismiss="modal" aria-hidden="true">Close</button>
        </div>
    </div>

</s:layout-component>
<s:layout-component name="scriptComponent">

    <script type="text/javascript">

        $(document).ready(function() {

            $('.mmType').click(function(event) {
                var type = $(this).attr('type');
                $("#mmType").val(type);
                var searchForm = $("#mmSearchForm")[0];
                var actionUrl = searchForm.action;
                actionUrl += '?searchMarketingMaterial';
                searchForm.action = actionUrl;
                searchForm.submit();
            });

            $.each($(".mmType"), function(index, value) {
                var type = $(this).attr('type');
                var selType = $("#mmType").val();
                if (selType === type) {
                    $(this).addClass('disabled');
                }
            });

            $(".shareAd").click(function(event) {
                jQuery.globalEval = function() {
                };
                var mmId = $(this).attr('mmId');
                DS.Ajax.getJson("/api/mm/" + mmId + "/share/999", function(response) {

                    //alert(response.sc);
                    $("#shareModal").modal();
                    document.getElementById("adShareModalContent").innerHTML = response.sc;

                })
            });


            $(".preview").click(function(event) {

                var mmId = $(this).attr('mmId');
                DS.Ajax.getJson("/api/mm/" + mmId + "/preview/", function(response) {

                    $("#previewModal").modal();
                    $("#adPreviewModalContent").html(response.sc);
                    //alert(response.sc);
                })
            });


        });
    </script>
</s:layout-component>
</s:layout-render>