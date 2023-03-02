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
import model.Transaction;
import model.TransactionDAO;
import model.TransactionDAOImpl;

/**
 * Servlet implementation class Transfer
 */
@WebServlet("/transfer")
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO accountDAO = new AccountDAOImpl();
		TransactionDAO transDAO = new TransactionDAOImpl();
		
		List<String> errList = new ArrayList<>();
		
		int userId = Integer.parseInt(request.getParameter("custId"));
		
		// Retrieve id to transfer to
		int transferId = Integer.parseInt(request.getParameter("id"));
		
		// Check if Id in DB
		boolean accountExists = false;
		accountExists = accountDAO.doesAccountExist(transferId);
		if (!accountExists) {
			errList.add("No Account With That Id!");
			request.setAttribute("custId", userId);
			request.setAttribute("errList", errList);
			request.getRequestDispatcher("transfer.jsp").forward(request, response);
		}
		else {
			// Retrieve transfer amount
			double transferAmount = Double.parseDouble(request.getParameter("trans"));
			
			Account account = accountDAO.getAccountById(userId);
			Account transferAccount = accountDAO.getAccountById(transferId);
			
			double newAccountOneBalance = account.getCustBalance() - transferAmount;
			
			if (newAccountOneBalance < 0) {
				errList.add("You Don't Have Enough In Your Account To Transfer This Much!");
				request.setAttribute("custId", userId);
				request.setAttribute("errList", errList);
				request.getRequestDispatcher("transfer.jsp").forward(request, response);
			}
			else {
				accountDAO.updateBalance(newAccountOneBalance, userId);
				
				Transaction transaction = new Transaction();
				transaction.setCustUserId(userId);
				transaction.setTransaction("Transfer sent of $" + Double.toString(transferAmount) + " to User With Id: " + Integer.toString(transferId));
				transDAO.insertTransaction(transaction);
				
				double newAccountTwoBalance = transferAccount.getCustBalance() + transferAmount;
				
				accountDAO.updateBalance(newAccountTwoBalance, transferId);
				
				Transaction transactionTwo = new Transaction();
				transactionTwo.setCustUserId(transferId);
				transactionTwo.setTransaction("Transfer received of $" + Double.toString(transferAmount) + " from User With Id: " + Integer.toString(userId));
				transDAO.insertTransaction(transactionTwo);
				
				request.setAttribute("custId", userId);
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
				
			}
			
			
		}
		
	}

}
