<%@include file="/includes/taglibInclude.jsp" %>

<s:layout-render name="/templates/general.jsp">

	<s:layout-component name="htmlHead">
		<style type="text/css">
			div.addOption, div.removeOption {
				background: none repeat scroll 0 0 #F5F5F5;
				border: 1px solid #CCCCCC;
				border-radius: 5px;
				left: 0;
				padding: 5px;
				position: relative;
				top: 0;
			}
		</style>
	</s:layout-component>

	<s:layout-component name="content">
		<s:useActionBean beanclass="com.ds.action.affiliate.CompanyAffiliateGroupAction" var="companyAffiliateGroupAction"/>
		<div class="content-outer">
			<div class="col_12">
				<div id="page-heading">
					<c:choose>
						<c:when test="${companyAffiliateGroupAction.companyAffiliateGroupId != null}">
							<h4>Edit Group</h4>
						</c:when>
						<c:otherwise>
							<h4>Create Group</h4>
						</c:otherwise>
					</c:choose>
				</div>

				<s:form beanclass="com.ds.action.affiliate.CompanyAffiliateGroupAction" class="vertical">
					<div class="col_6">

						<s:label name="Name"/>
						<s:text name="companyAffiliateGroupDTO.name" class="check-empty auto-adjust"/>

						<s:label name="Description"/>
						<s:textarea name="companyAffiliateGroupDTO.description" class="check-empty auto-adjust"/>

						<s:label name="Default"/>
						<s:checkbox name="companyAffiliateGroupDTO.defaultGroup" class="check-empty auto-adjust"/>

						<div id="groupDiv" class="multiSelect checkMultiSelect">
							<div class="col_5">
								<fieldset class="availField">
									<legend><em>Available Values</em></legend>
									<div class="selectable" style="width:314px;">
										<select name="left-select" id="left-select" size="5" multiple="multiple" style="width:300px;">
											<c:forEach items="${companyAffiliateGroupAction.availableAffiliates}" var="availVal">
												<option value="${availVal}">
														${companyAffiliateGroupAction.companyAffiliateDescMap[availVal]}
												</option>
											</c:forEach>
										</select>
									</div>
								</fieldset>
							</div>

							<div class="col_2 center">
								<div class="addOption" style="cursor:pointer;">
									<strong>>></strong>
								</div>
								<div class="clear" style="margin-top:10px;"></div>
								<div class="removeOption" style="cursor:pointer;">
									<strong><<</strong>
								</div>
							</div>

							<div class="col_5">
								<fieldset class="selectField">
									<legend><em>Selected Values</em></legend>
									<div class="selectable" style="width:314px;">
										<select name="right-select" id="right-select" size="5" multiple="multiple" style="width:300px;">
											<c:forEach items="${companyAffiliateGroupAction.assignedAffiliates}" var="selVal">
												<option value="${selVal}" class="selectedValues">
														${companyAffiliateGroupAction.companyAffiliateDescMap[selVal]}
												</option>
											</c:forEach>
										</select>
									</div>
								</fieldset>
							</div>
						</div>


					</div>

					<div class="clear"></div>

					<div class="col_2">
						<s:hidden name="companyAffiliateGroupId" value="${companyAffiliateGroupAction.companyAffiliateGroupId}"/>
						<s:hidden name="companyAffiliateGroupDTO.companyAffiliateGroupId"
						          value="${companyAffiliateGroupAction.companyAffiliateGroupDTO.companyAffiliateGroupId}"/>
						<s:hidden name="companyShortName" value="${companyAffiliateGroupAction.companyShortName}"/>
						<s:submit name="saveCompanyAffiliateGroup" value="Save Changes" class="button blue small" id="submitBtn"/>
					</div>


					<div class="col_2">
						<s:link beanclass="com.ds.action.affiliate.CompanyAffiliateSearchAction"
						        class="button blue small"><span class="icon white small" data-icon=":"></span>Back</s:link>
					</div>
				</s:form>
			</div>
		</div>
	</s:layout-component>
	<s:layout-component name="scriptComponent">
		<script type="text/javascript">
			function sendSelectedOptions() {
				$("select#left-select option:selected").each(function () {
					var o1 = new Option($(this).text(), $(this).text());
					var class_name = $(this).attr("class");
					var actual_val = $(this).attr("value");
					class_name += " selectedValues";
					o1.setAttribute("class", class_name);
					o1.setAttribute("value", actual_val);

					$("select#right-select").append(o1);
				});

				$("select#left-select option:selected").remove();

				sortDropDownListByText('right-select');

			}

			function removeSelectedOptions() {
				$("select#right-select option:selected").each(function () {
					var o1 = new Option($(this).text(), $(this).text());
					var actual_val = $(this).attr("value");
					o1.setAttribute("value", actual_val);

					$("select#left-select").append(o1);
				});

				$("select#right-select option:selected").remove();

				sortDropDownListByText('left-select');

			}

			function sortDropDownListByText(selectID) {
				// Loop for each select element on the page.
				$("select#" + selectID).each(function() {

					// Keep track of the selected option.
					var selectedValue = $(this).val();

					// Sort all the options by text. I could easily sort these by val.
					$(this).html($("option", $(this)).sort(function(a, b) {
						return a.text == b.text ? 0 : a.text < b.text ? -1 : 1
					}));

					// Select one option.
					$(this).val(selectedValue);
				});
			}

			$(document).ready(function() {
				$('div.addOption').click(function() {
					sendSelectedOptions();
				});

				$('div.removeOption').click(function() {
					removeSelectedOptions();
				});

				$('#submitBtn').click(function() {
					if ($('#groupDiv').find('.selectedValues').length > 0) {
						var ctr = -1;
						$('#groupDiv').find('.selectedValues').each(function() {
							ctr++;
							$(this).parents("form").append("<input type='hidden' name='assignedAffiliates[" + ctr + "]' value='" + $(this).attr("value") + "'>");
						});
					}
				});
			});
		</script>
	</s:layout-component>

</s:layout-render>
