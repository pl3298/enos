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
</head>
<body>
<form action="addtrainingtodb">

Training name : <input type="text" name="trainingname"></br>

Topic : <input type="text" name="topic"></br>

duration : <input type="text" name="duration">



Trainer<input list="A_Training" name="trainerName"><br>
  <datalist id="A_Training">
  <%
  Context context=(Context)session.getAttribute("context");
  System.out.println(context.getUser());
  Query q=new Query();
  q.setBusinessObjectType("A_Trainer");
  q.setBusinessObjectName("*");
  q.setBusinessObjectRevision("*");
  

  BusinessObjectList bolist=new BusinessObjectList();
  bolist=q.evaluate(context);

  BusinessObjectItr boi=new BusinessObjectItr(bolist);
  while(boi.next()){
		
		  BusinessObject bo=boi.obj();
		//  request.setAttribute("trainerid", bo.getObjectId());
		 // System.out.println( bo.getObjectId());
		 
  %>

    <option value="<%=bo.getName() %>><%=bo.getObjectId() %>">

  <%
  }
  
  %>
  </datalist><br>


<button type="submit">add training</button>
</form>
</body>
</html>