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
import model.Transaction;
import model.TransactionDAO;
import model.TransactionDAOImpl;

/**
 * Servlet implementation class Withdrawal
 */
@WebServlet("/withdrawal")
public class Withdrawal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Withdrawal() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO accountDAO = new AccountDAOImpl();
		TransactionDAO transDAO = new TransactionDAOImpl();
		
		// retrieve withdrawal amount
		double withdrawalAmount = Double.parseDouble(request.getParameter("withdrawal"));
		
		int userId = Integer.parseInt(request.getParameter("custId"));
		
		Account account = accountDAO.getAccountById(userId);
		
		double newBalance = account.getCustBalance() - withdrawalAmount;
		
		if (newBalance < 0) {
			request.setAttribute("custId", userId);
			request.setAttribute("errorMsg", "Cannot Withdraw More Than Is In Your Account!");
			request.getRequestDispatcher("withdrawal.jsp").forward(request, response);
		}
		else {
			accountDAO.updateBalance(newBalance, userId);
			
			Transaction transaction = new Transaction();
			transaction.setCustUserId(userId);
			transaction.setTransaction("Withdrawal of $" + Double.toString(withdrawalAmount));
			
			transDAO.insertTransaction(transaction);
			
			request.setAttribute("custId", userId);
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		}
	}

}
