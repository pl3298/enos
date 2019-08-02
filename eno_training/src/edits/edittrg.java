package edits;

import java.io.IOException;
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

import matrix.db.BusinessObject;
import matrix.db.Context;
import matrix.db.Relationship;
import matrix.db.RelationshipType;
import matrix.util.MatrixException;
import matrix.util.StringList;


@WebServlet("/edittrg")
public class edittrg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		StringList selectbusStmts = new StringList();
		selectbusStmts.addElement("name");
		selectbusStmts.addElement("id");

		StringList selectrelStmts = new StringList();
		String typepattern = "A_Trainer";
		String relpattern = "A_Training-Trainer";
		
		String id =request.getParameter("id");
		HttpSession session = request.getSession();
		Context existingContext = (Context) session.getAttribute("context");
		String name = request.getParameter("name");
		String topic =request.getParameter("topic");
		String duration = request.getParameter("duration");
		String trainerwithid = request.getParameter("trainerName");
		String[] s1 = trainerwithid.split(">");
		String trainer = s1[0];
		String trainerid = s1[1];
		RelationshipType rt =new RelationshipType("A_Training-Trainer");
		System.out.println(trainerid);
		try {
			BusinessObject bo = new BusinessObject(id);
			
			bo.setAttributeValue(existingContext, "A_Topic", topic);
			bo.setAttributeValue(existingContext, "A_Duration", duration); 
			String s12 = "";
			System.out.println("attributes updated");
			
			DomainObject d = new DomainObject(bo);
			//get related bo
			System.out.println("perform related atts");
			MapList mpl = d.getRelatedObjects(existingContext, relpattern, typepattern, selectbusStmts, selectrelStmts,false, true, (short) 1, "", "", 1);
			System.out.println("got related atts");
			Iterator it = mpl.iterator();
			while (it.hasNext()) {
				Map map = (Map) it.next();
				s12 = (String) map.get("id");
				System.out.println("rel att id"+s12);
			}
			if (s12.equals("")) {
			
			}else {
				
				System.out.println("rel att id after loop"+s12);
				BusinessObject object = new BusinessObject(s12);
				 bo.disconnect(existingContext, rt, true, object);
				 System.out.println("disconnected to existing relation");
			}
			 BusinessObject bo1=new BusinessObject(trainerid);
			 Relationship dr = bo.connect(existingContext,  rt, true, bo1) ;
			 System.out.println("connected to new relation");
			
		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		 request.getRequestDispatcher("/WEB-INF/trainingspage.jsp").include(request, response);
		
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
