package add;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.matrixone.apps.domain.DomainObject;
import com.matrixone.apps.domain.util.MapList;

import matrix.db.BusinessObject;
import matrix.db.BusinessObjectItr;
import matrix.db.BusinessObjectList;
import matrix.db.Context;
import matrix.db.Query;
import matrix.db.Relationship;
import matrix.db.RelationshipType;
import matrix.util.MatrixException;

@WebServlet("/addtrainingtodb")
public class addtrainingtodb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String name = request.getParameter("trainingname");
		String topic =request.getParameter("topic");
		String duration = request.getParameter("duration");
		String trainerwithid = request.getParameter("trainerName");
		
		String[] s1 = trainerwithid.split(">");
		String trainer = s1[0];
		String trainerid = s1[1];
		System.out.println(trainer);
		
//		System.out.println("tainerid"+trainerid);
		HttpSession session = request.getSession();
		Context existingContext = (Context) session.getAttribute("context");

		try {
			BusinessObject bo = new BusinessObject("A_Training", name, "A", "eService Production", "A_TrainingPolicy");

			bo.create(existingContext, "A_TrainingPolicy");
			

			System.out.println("training created");
			
			bo.setAttributeValue(existingContext, "A_Topic", topic);
			bo.setAttributeValue(existingContext, "A_Duration", duration);
			
			System.out.println("attributes set");
			
			RelationshipType rt =  new RelationshipType("A_Training-Trainer");
			
			
//
//			  Query q=new Query();
//			  q.setBusinessObjectType("A_Trainer");
//			  q.setBusinessObjectName(trainer);
//			  q.setBusinessObjectRevision("*");
//			  
//
//			  BusinessObjectList bolist=q.evaluate(existingContext);
//			  
//			   
//			  BusinessObjectItr boi=new BusinessObjectItr(bolist);
//	
//			  
//			  BusinessObject bo1=boi.obj();
//		
//		 
//			  String trainerid = bo1.getObjectId();
//					  

			   
// 			  request.setAttribute("trainerid", bo1.getObjectId());
//			  System.out.println( bo1.getObjectId());
			  
			
			
			
//	   		  System.out.println("   "+ trainerid);
			
			BusinessObject bo1=new BusinessObject(trainerid);
			
			Relationship dr = bo.connect(existingContext,  rt, true, bo1) ;
		
			
			System.out.println("training trainer connected");
			
			
			
			

		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			System.out.println("errror in connecting");
		}
		
		
		request.getRequestDispatcher("WEB-INF/trainingspage.jsp").forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
