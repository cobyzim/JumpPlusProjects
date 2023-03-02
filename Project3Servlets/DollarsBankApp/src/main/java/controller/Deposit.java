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
 * Servlet implementation class Deposit
 */
@WebServlet("/deposit")
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deposit() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO accountDAO = new AccountDAOImpl();
		TransactionDAO transDAO = new TransactionDAOImpl();
		
		// retrieve deposit amount
		double depositAmount = Double.parseDouble(request.getParameter("newdeposit"));
		
		// get logged in id from jsp
		int userId = Integer.parseInt(request.getParameter("custId"));
		
		// Use this id to update account balance in db
		Account account = accountDAO.getAccountById(userId);
		
		double newBalance = account.getCustBalance() + depositAmount;
		
		accountDAO.updateBalance(newBalance, userId);
		
		// Make new transaction
		Transaction transaction = new Transaction();
		transaction.setCustUserId(userId);
		transaction.setTransaction("Deposit of $" + Double.toString(depositAmount));
		int currentTransNum = transDAO.getCurrentTransactionNumber(userId);
		int newTransNum = currentTransNum + 1;
		transaction.setTransCounter(newTransNum);
		
		transDAO.insertTransaction(transaction);
		
		request.setAttribute("custId", userId);
		
		request.getRequestDispatcher("welcome.jsp").forward(request, response);
	}

}
