package add;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.SessionListener;

import matrix.db.BusinessObject;
import matrix.db.Context;
import matrix.util.MatrixException;

@WebServlet("/addtrainertodb")
public class addtrainertodb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		HttpSession session = request.getSession();
		Context existingContext = (Context) session.getAttribute("context");

		try {
			BusinessObject bo = new BusinessObject("A_Trainer", name, "A", "eService Production", "A_TrainerPolicy");

		//	System.out.println(request.getSession());

			bo.create(existingContext, "A_TrainerPolicy");

			System.out.println("trainer created");

		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/trainingspage.jsp").forward(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
