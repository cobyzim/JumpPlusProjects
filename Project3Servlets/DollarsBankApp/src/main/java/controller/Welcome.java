package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import model.AccountDAO;
import model.AccountDAOImpl;
import model.TransactionDAO;
import model.TransactionDAOImpl;

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
		
		AccountDAO accountDAO = new AccountDAOImpl();
		TransactionDAO transDAO = new TransactionDAOImpl();
		
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
			List<String> transList = transDAO.getFiveMostRecentTransactions(userId);
			request.setAttribute("transList", transList);
			request.setAttribute("custId", userId);
			request.getRequestDispatcher("transactions.jsp").forward(request, response);
		}
		if (radio.equals("info")) {
			Account account = new Account();
			account = accountDAO.getAccountById(userId);
			String custName = account.getCustName();
			String custAddress = account.getCustAddress();
			int phoneNumber = account.getCustPhone();
			double balance = account.getCustBalance();
			request.setAttribute("custName", custName);
			request.setAttribute("custAddress", custAddress);
			request.setAttribute("phoneNumber", phoneNumber);
			request.setAttribute("balance", balance);
			
			request.setAttribute("custId", userId);
			request.getRequestDispatcher("info.jsp").forward(request, response);
		}
		if (radio.equals("exit")) {
			request.setAttribute("custId", userId);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
