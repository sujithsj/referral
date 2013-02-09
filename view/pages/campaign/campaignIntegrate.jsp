<%@ page import="com.ds.constants.EnumCampaignType" %>
<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

    <s:layout-component name="content">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/unicorn.main.css" type="text/css"/>
        <s:layout-render name="/includes/companyHeader.jsp"/>
        <s:layout-render name="/includes/companySideBar.jsp"/>

        <div id="content">
            <s:layout-render name="/includes/companyContentHeader.jsp" headerLabel="Campaigns"/>
            <div id="breadcrumb">
                <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
                <a href="#" class="current"> Integrate Campaign </a>
            </div>

            <div class="container-fluid">
                <s:useActionBean beanclass="com.ds.action.campaign.CampaignIntegrateAction" var="campaignIntegrateAction"/>

                <div class="row-fluid">
                    <div class="tabbable"> <!-- Only required for left/right tabs -->
                        <ul class="nav nav-pills">
                            <li class="active"><a href="#tab1" data-toggle="tab">API</a></li>
                            <li><a href="#tab2" data-toggle="tab">JS Snippet</a></li>
                            <li><a href="#tab2" data-toggle="tab">Image Snippet</a></li>
                            <li><a href="#tab2" data-toggle="tab">Form Snippet</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab1">
                                <p>TODO</p>
                            </div>
                            <div class="tab-pane" id="tab2">
                                <h3>Embeddable Javascript Snippet</h3>
                                Paste this javascript widget on the "success" page of the event you are tracking.
                                The example code is pre-filled with campaign information for you, but you will need to
                                alter the "mbsy_email" variable to correlate with your new customer.
                                Multiple campaign events can be triggered by specifying multiple campaigns separated by
                                commas in the "mbsy_campaign_uid" variable. The events will occur in the order provided.
                                See the table below the code for additional data you can pass us.


                                <textarea rows="20" readonly="true" style="width:650px;cursor:copy;"
                                          onclick="this.select()">
                                    <script type="text/javascript">

                                        <!--
                                        var rf_c_short_code = ${campaignIntegrateAction.companyShortName}; // Required
                                        var rf_campaign_uid = ${campaignIntegrateAction.campaignId}; // Required
                                        var rf_u_email = 'example@example.com'; // Required - must be replaced with customer's email
                                        var rf_revenue = '0.00'; // Optional - must be replaced with your sale value
                                        //-->
                                    </script>
                                    <script type="text/javascript"
                                            src="http://dev.healthkart.com/assets/js/jquery.min.js"></script>
                                    <script type="text/javascript"
                                            src="http://dev.healthkart.com/assets/js/track/conversion.js"></script>

                                </textarea>

                            </div>
                        </div>
                    </div>
                </div>
                <s:layout-render name="/includes/footer.jsp"/>
            </div>

        </div>


    </s:layout-component>
    <s:layout-component name="scriptComponent">

        <script type="text/javascript">

            $(document).ready(function() {
            });

        </script>
    </s:layout-component>

</s:layout-render>