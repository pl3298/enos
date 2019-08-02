<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import=" java.io.IOException"%>
<%@ page import=" java.io.PrintWriter"%>
<%@ page import=" java.util.Iterator"%>
<%@ page import=" java.util.Map"%>

<%@ page import=" javax.servlet.ServletException"%>
<%@ page import=" javax.servlet.annotation.WebServlet"%>
<%@ page import=" javax.servlet.http.HttpServlet"%>
<%@ page import=" javax.servlet.http.HttpServletRequest"%>
<%@ page import=" javax.servlet.http.HttpServletResponse"%>
<%@ page import=" javax.servlet.http.HttpSession"%>

<%@ page import=" com.matrixone.apps.domain.DomainObject"%>
<%@ page import=" com.matrixone.apps.domain.util.MapList"%>

<%@ page import=" matrix.db.AttributeList"%>
<%@ page import=" matrix.db.BusinessObject"%>
<%@ page import=" matrix.db.BusinessObjectItr"%>
<%@ page import=" matrix.db.BusinessObjectList"%>
<%@ page import=" matrix.db.BusinessObjectWithSelect"%>
<%@ page import=" matrix.db.BusinessObjectWithSelectList"%>
<%@ page import=" matrix.db.Context"%>
<%@ page import=" matrix.db.Query"%>
<%@ page import=" matrix.util.MatrixException"%>
<%@ page import=" matrix.util.StringList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}

DIV.table {
	display: table;
}

FORM.tr, DIV.tr {
	display: table-row;
}

INPUT.td, SPAN.td {
	display: table-cell;
}
</style>
</head>
<body>
	<a>to edit click on training name</a>
	<!-- <div class="table">-->
	<table>
		<tr>
			
			<th>Training</th>
			<th>Topic</th>

			<th>Duration</th>


			<th>Trainer</th>




		</tr>
		<%
			Context context = (Context) session.getAttribute("context");

			StringList selectbusStmts = new StringList();
			selectbusStmts.addElement("name");
			selectbusStmts.addElement("id");

			StringList selectrelStmts = new StringList();
			String typepattern = "A_Trainer";
			String relpattern = "A_Training-Trainer";

			Query query = new Query();
			query.setBusinessObjectType("A_Training");
			query.setBusinessObjectName("*");
			query.setBusinessObjectRevision("*");
			BusinessObjectList bol = query.evaluate(context); // BusinessObject bo =null
			BusinessObjectItr boi = new BusinessObjectItr(bol);
			while (boi.next()) {
				BusinessObject bo = boi.obj();
				DomainObject d = new DomainObject(bo);
				String s12 = null;

				MapList mpl = d.getRelatedObjects(context, relpattern, typepattern, selectbusStmts, selectrelStmts,
						false, true, (short) 1, "", "", 1);

				Iterator it = mpl.iterator();
				while (it.hasNext()) {
					Map map = (Map) it.next();
					s12 = (String) map.get("name");
				}
				
		%>


		<tr>
			
			<td><a href="edit_bo?id=<%=bo.getObjectId()%>&name=<%=bo.getName()%>" > <%=bo.getName()%></a></td>
		<td><%=bo.getAttributeValues(context, "A_Topic").getValue()%></td>
			<td><%=bo.getAttributeValues(context, "A_Duration").getValue()%></td>			
			<td><%=s12%></td>

		</tr>



		<!--  

	-->


		<%
			}
		%>
	</table>

<!--  
	<a href="contextsetpage"><button>Back</button></a>
	<a href="deletetraining"><button>Delete Training BO</button></a>
	<a href="showtraining"><button>Show Training BO</button></a>
	
	
	
-->



<a href= "abcd">click here to go back to home page</a>









</body>
</html>