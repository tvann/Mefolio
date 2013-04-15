<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="userList.title" /></title>
<meta name="menu" content="AdminMenu" />
<!-- Need for the param value of the tag <fmt> -->
<c:set var="delObject" scope="request">
	<fmt:message key="userList.user" />
</c:set>

<script src="../scripts/lib/jquery-1.8.2.min.js"></script>
<script  type="text/javascript">
	// When click on the parent select box
	$(document).ready(function() {
		$("#selectAll").click(function() {
			var parentVal = $("#selectAll").prop('checked');
			$(".bidonForCheck").prop('checked', function(i, value) {
				if (parentVal == true)
					return true;
				else
					return false;
			});
		});
	});
	
	//When click on any child select box
	$(document).ready(function() {
		$(".bidonForCheck").click(function() {
			var allChilds = new Array();
			$(".bidonForCheck").prop('checked', function(i, value) {
				allChilds[i] = value;
				//if one child is unchecked then the parent must be unchecked
				if (value == false)
					$("#selectAll").prop('checked', false);								
			});
			//Process the cas if all childen are checked
			var pCheck = true;
 			for (j = 0; j < allChilds.length; j++) {
				//alert(j + ": " + allChilds[j]);
				if (allChilds[j] == false){
					pCheck = false;
					break;
				}
			}
 			//Then the parent must be checked
 			if (pCheck==true)
 				$("#selectAll").prop('checked', true);
		});
	});

	<!--
	function deleteMe(id, fullName) {
		var msgDelConfirm = "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
		var conf = confirm(msgDelConfirm + " " + fullName + " [userId: " + id
				+ "]");
		if (conf == true) {
			var myUrl = '${ctx}' + "/admin/users/delete?id=" + id;
			window.location = myUrl;
		}
	}
//-->
</script>
</head>


<c:if test="${not empty searchError}">
	<div class="alert alert-error fade in">
		<a href="#" data-dismiss="alert" class="close">&times;</a>
		<c:out value="${searchError}" />
	</div>
</c:if>

<div class="span10">
	<h2>
		<fmt:message key="userList.heading" />
	</h2>

	<form method="get" action="${ctx}/admin/users" id="searchForm"
		class="form-search">
		<div id="search" class="input-append">
			<input type="text" size="20" name="q" id="query" value="${param.q}"
				placeholder="<fmt:message key="search.enterTerms"/>"
				class="input-medium search-query" />
			<button id="button.search" class="btn" type="submit">
				<i class="icon-search"></i>
				<fmt:message key="button.search" />
			</button>
		</div>
	</form>

	<div id="actions" class="form-actions">
		<a class="btn btn-primary"
			href="<c:url value='/userform?method=Add&from=list'/>"> <i
			class="icon-plus icon-white"></i> <fmt:message key="button.add" /></a> <a
			class="btn" href="<c:url value='/mainMenu'/>"> <i class="icon-ok"></i>
			<fmt:message key="button.done" /></a>
	</div>

	<display:table name="userList" cellspacing="0" cellpadding="0"
		requestURI="" defaultsort="1" id="users" pagesize="25"
		class="table table-condensed table-striped table-hover" export="true">
		
		<display:column title="<input type='checkbox' id='selectAll'/>"
			style="width: 1%">
			<input type="checkbox" class="bidonForCheck" id="select" value="${users.id}*${users.fullName}"/>
		</display:column>
		
		<display:column property="username" escapeXml="true" sortable="true"
			titleKey="user.username" style="width: 25%" url="/userform?from=list"
			paramId="id" paramProperty="id" />
		<display:column property="fullName" escapeXml="true" sortable="true"
			titleKey="activeUsers.fullName" style="width: 34%" />
		<display:column property="email" sortable="true" titleKey="user.email"
			style="width: 25%" autolink="true" media="html" />
		<display:column property="email" titleKey="user.email"
			media="csv xml excel pdf" />
		<display:column sortProperty="enabled" sortable="true"
			titleKey="user.enabled" style="width: 16%; padding-left: 15px"
			media="html">
			<input type="checkbox" disabled="disabled"
				<c:if test="${users.enabled}">checked="checked"</c:if> />
		</display:column>
		<display:column titleKey="button.delete"
			style="width: 16%;  padding-center: 15px; text-align:center">
			<img src="../images/iconDelete.png" title="<fmt:message key="button.delete"/>"
				onclick="deleteMe('${users.id}','${users.fullName}');"></img>
		</display:column>


		<display:column property="enabled" titleKey="user.enabled"
			media="csv xml excel pdf" />
		<display:setProperty name="paging.banner.item_name">
			<fmt:message key="userList.user" />
		</display:setProperty>
		<display:setProperty name="paging.banner.items_name">
			<fmt:message key="userList.users" />
		</display:setProperty>
		<display:setProperty name="export.excel.filename"
			value="User List.xls" />
		<display:setProperty name="export.csv.filename" value="User List.csv" />
		<display:setProperty name="export.pdf.filename" value="User List.pdf" />
	</display:table>
</div>
