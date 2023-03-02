package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerChoices
 */
@WebServlet("/customerChoices")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Welcome() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String radio = request.getParameter("radios");
		int userId = Integer.parseInt(request.getParameter("custId"));
		
		// Take action based on selected button group
		if (radio.equals("deposit")) {
			request.setAttribute("custId", userId);
			request.getRequestDispatcher("deposit.jsp").forward(request, response);
		}
		if (radio.equals("withdrawal")) {
			request.setAttribute("custId", userId);
			request.getRequestDispatcher("withdrawal.jsp").forward(request, response);
		}
		if (radio.equals("transfer")) {
			request.setAttribute("custId", userId);
			request.getRequestDispatcher("transfer.jsp").forward(request, response);
		}
		if (radio.equals("transactions")) {
			request.setAttribute("custId", userId);
			request.getRequestDispatcher("transactions.jsp").forward(request, response);
		}
		if (radio.equals("info")) {
			request.setAttribute("custId", userId);
			request.getRequestDispatcher("info.jsp").forward(request, response);
		}
		if (radio.equals("exit")) {
			request.setAttribute("custId", userId);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
