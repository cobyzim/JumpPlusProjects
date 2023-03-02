package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AccountDAO accountDAO = new AccountDAOImpl();
		List<String> errList = new ArrayList<>();

		try {

			int userId = Integer.parseInt(request.getParameter("id"));
			String password = request.getParameter("password");

			Account account = accountDAO.getAccount(userId, password);

			if (account != null && account.getCustName() != null) {
				request.setAttribute("custId", userId);
				
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			} else {
				errList.add("Invalid Credentials! Try Again or Create and Account");
				request.setAttribute("errList", errList);
				
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			errList.add("Please Make Sure to Input Your Id and Password");
			request.setAttribute("errList", errList);
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
