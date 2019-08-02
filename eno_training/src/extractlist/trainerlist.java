package extractlist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.matrixone.apps.domain.DomainObject;

import matrix.db.BusinessObject;
import matrix.db.BusinessObjectItr;
import matrix.db.BusinessObjectList;
import matrix.db.Context;
import matrix.db.Query;
import matrix.util.MatrixException;

@WebServlet("/trainerlist")
public class trainerlist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 try {
			 String s = null;
			 String[] s1;
			 PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			Context context = (Context) session.getAttribute("context");
			Query query = new Query();
			query.setBusinessObjectType("A_Trainer");
			query.setBusinessObjectName("*");
			
			BusinessObjectList bol = query.evaluate(context); // BusinessObject bo =null
			BusinessObjectItr boi=new BusinessObjectItr(bol);
			while(boi.next()){
				  BusinessObject bo=boi.obj();
				DomainObject d=new DomainObject(bo);
		
			out.print(bo.getName()+"</br>" );
			
			}
			
//			
//			Iterable<BusinessObject> bo = bol.getIterator();
//			Iterator<BusinessObject> ibo = bo.iterator();
//			while (ibo.hasNext()) {
//				
//				s=ibo.next().toString();
//				//s1 = s.split(".");
//				out.println(ibo.next().toString()+"</br>");
//			}
		} catch (MatrixException e) {
			e.printStackTrace();
		}
		
		 request.getRequestDispatcher("/WEB-INF/trainingspage.jsp").include(request, response);
		 
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
