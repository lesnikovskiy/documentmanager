<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Document Manager</title>
	<style type="text/css">
		fieldset { width:450px; border-radius:5px; }
		#docform label { display:block; text-align:right; width:150px; margin:3px; float: left; clear: both; }
		#docform input { display:block; float:right; width:200px; margin:3px; }
		#docform input[type=submit] { clear:both; }
		.clear {clear:both;}
	</style>
</head>
<body>
	<h2>Document manager</h2>
	
	<h3>Add new document</h3>
	<fieldset>
		<legend>Document upload</legend>
		<form:form action="save" id="docform" commandName="document" enctype="multipart/form-data">
			<form:errors path="*" cssClass="error"/>
			
			<form:label path="name">Name:</form:label>
			<form:input path="name"/>
			
			<form:label path="description">Description:</form:label>
			<form:input path="description"/>
			
			<form:label path="content">Content:</form:label>
			<input type="file" name="file" id="file" />
			
			<input type="submit" value="Add Document" />
		</form:form>
	</fieldset>
	
	<div>
		<c:if test="${!empty documentList}">
			<table class="data">
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th>&nbsp;</th>
				</tr>
				<c:forEach var="doc" items="${documentList}">
					<tr>
						<td>${doc.name}</td>
						<td>${doc.description}</td>
						<td>
							<a href="${pageContext.request.contextPath}/download/${doc.id}">download</a>
							<a href="${pageContext.request.contextPath}/remove/${doc.id}">remove</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>
