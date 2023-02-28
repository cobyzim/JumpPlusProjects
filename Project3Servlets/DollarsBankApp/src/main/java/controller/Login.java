package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import model.AccountDAO;
import model.AccountDAOImpl;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO accountDAO = new AccountDAOImpl();
		
		int userId = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");
		
		Account account = accountDAO.getAccount(userId, password);
		
		if (account != null && account.getCustName() != null) {
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
		
		
		
		
		
	}

}
