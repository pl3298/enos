package extractlist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.matrixone.apps.domain.DomainObject;
import com.matrixone.apps.domain.util.MapList;

import matrix.db.AttributeList;
import matrix.db.BusinessObject;
import matrix.db.BusinessObjectItr;
import matrix.db.BusinessObjectList;
import matrix.db.BusinessObjectWithSelect;
import matrix.db.BusinessObjectWithSelectList;
import matrix.db.Context;
import matrix.db.Query;
import matrix.util.MatrixException;
import matrix.util.StringList;


@WebServlet("/traininglist")
public class traininglist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
			 
			 PrintWriter out = response.getWriter();
				HttpSession session = request.getSession();
				Context context = (Context) session.getAttribute("context");
				
				
				StringList selectbusStmts = new StringList(); 
			//	selectbusStmts.addElement("policy");
				selectbusStmts.addElement("name");
				
			    StringList selectrelStmts = new StringList();
			  //  selectrelStmts.addElement("attribute[Quantity]");
			    String typepattern = "A_Trainer";
			    String relpattern = "A_Training-Trainer";
			 
			Query query = new Query();
			query.setBusinessObjectType("A_Training");
			query.setBusinessObjectName("*");
			query.setBusinessObjectRevision("*");
			BusinessObjectList bol = query.evaluate(context); // BusinessObject bo =null
			BusinessObjectItr boi=new BusinessObjectItr(bol);
			while(boi.next()){
				  BusinessObject bo=boi.obj();
				DomainObject d=new DomainObject(bo);
				String s12= null;
				
				 try{ 
				    	MapList mpl = d.getRelatedObjects(context, relpattern,typepattern, selectbusStmts,selectrelStmts,false,true,(short) 1,"","",1);
				    
				      Iterator it = mpl.iterator();
				      while(it.hasNext())  {
				        Map map=(Map)it.next();
				        s12= (String) map.get("name");
				        
				      //  out.println("Trainer : " + map.get("name"));

				      }
				      
				 }
				     catch (Exception ex)
				     {
				    	 out.print("no related ones");
				     }
		
			//AttributeList al = bo.getAttributeValues(context,slist);
			//	al.iterator().next().toString();
				//  A_Training at = new A_Training();
				 // System.out.println(at.getTopic(context));
			out.print(bo.getName()+" _____duration=	"+bo.getAttributeValues(context, "A_Duration").getValue()+"____topic="+bo.getAttributeValues(context, "A_Topic").getValue()+" _____ trainer = "+s12+"</br>");
			
			}
	

//			Iterable<BusinessObjectWithSelect> bo = bosl.getIterator();
//			Iterator<BusinessObjectWithSelect> ibo = bo.iterator();
//			while (ibo.hasNext()) {
//		
//				out.println(ibo.next().toString()+"</br>");
//			}
		}
	catch (MatrixException e) {
			e.printStackTrace();
		}
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
