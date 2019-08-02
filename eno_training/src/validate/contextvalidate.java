package validate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.matrixone.apps.domain.util.ContextUtil;

import matrix.db.Context;
import matrix.util.MatrixException;

@WebServlet("/contextvalidate")
public class contextvalidate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
    	int t=0;
    	Context context = null;
    	try {
			context = new Context("localhost");			
			context.setUser(request.getParameter("context"));
			context.setPassword(request.getParameter("password"));
			context.setVault(request.getParameter("vault"));
			context.connect();
			System.out.println("connected to "+ context.getUser());
						
						
		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			 t=1;
		}
    	
    	if(t==1) {
    		System.out.println("<a>wrong cedentials</a>");
    		 request.getRequestDispatcher("/WEB-INF/login.jsp").include(request, response);	
    	}
    	else
    	{
    		session.setAttribute("context", context);
    		request.getRequestDispatcher("/WEB-INF/trainingspage.jsp").include(request, response);	
    		
    	}
    	
    	
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
