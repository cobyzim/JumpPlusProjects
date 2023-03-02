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
 * Servlet implementation class CreateAccount
 */
@WebServlet("/createAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccount() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO accountDAO = new AccountDAOImpl();
		TransactionDAO transDAO = new TransactionDAOImpl();
		
		List<String> errList = new ArrayList<>();
		
		try {
			
			String name = request.getParameter("name");	
			String address = request.getParameter("address");
			int phone = Integer.parseInt(request.getParameter("phone"));
			
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			// Check if Id already in DB
			boolean accountExists = false;
			accountExists = accountDAO.doesAccountExist(id);
			if (accountExists) {
				errList.add("Account Already Exists With This Id! Please Choose Another!");
				request.setAttribute("errList", errList);
				request.getRequestDispatcher("createAccount.jsp").forward(request, response);
			}
						
			else {
				String password = request.getParameter("password");
				double initialDeposit = Double.parseDouble(request.getParameter("initialdeposit"));
				
				
				// Instantiate new account and add it to db if all is well
				Account account = new Account();
				account.setCustName(name);
				account.setCustAddress(address);
				account.setCustPhone(phone);
				account.setCustUserId(id);
				account.setCustPassword(password);
				account.setCustInitialDeposit(initialDeposit);
				account.setCustBalance(initialDeposit);
				
				accountDAO.insertAccount(account);
				
				// Instantiate new transaction and add it to transaction table
				Transaction transaction = new Transaction();
				transaction.setCustUserId(id);
				transaction.setTransaction("Initial Deposit of $" + Double.toString(initialDeposit));
				transaction.setTransCounter(1);
				
				transDAO.insertTransaction(transaction);
				
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
		}
		catch(NumberFormatException e) {
			errList.add("Please fill in all of the fields!");
			request.setAttribute("errList", errList);
			
			request.getRequestDispatcher("createAccount.jsp").forward(request, response);
		}	
		
	}

}
