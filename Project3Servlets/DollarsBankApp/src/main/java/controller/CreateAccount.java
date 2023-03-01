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
import regex.Regex;

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
		List<String> errList = new ArrayList<>();
		
		try {
			String name = request.getParameter("name");
			boolean validName = Regex.validateName(name);
			if (!validName) {
				errList.add("Invalid Name! (i.e. Big Bubba)");
				request.setAttribute("errList", errList);
				request.getRequestDispatcher("createAccount.jsp").forward(request, response);
			}
			
			
			String address = request.getParameter("address");
			boolean validAddress = Regex.validateAddress(address);
			if (!validAddress) {
				errList.add("Invalid Address! (i.e. 123 Street)");
				request.setAttribute("errList", errList);
				request.getRequestDispatcher("createAccount.jsp").forward(request, response);
			}
			
			
			
			int phone = Integer.parseInt(request.getParameter("phone"));
			boolean validPhone = Regex.validatePhoneNumber(phone);
			if (!validPhone) {
				errList.add("Invalid Phone Number! (i.e. 1234567890)");
				request.setAttribute("errList", errList);
				request.getRequestDispatcher("createAccount.jsp").forward(request, response);
			}
			
			// First Check if Id is Valid Number
			int id = Integer.parseInt(request.getParameter("id"));
			boolean validIdFormat = Regex.validateUserId(id);
			if (!validIdFormat) {
				errList.add("Invalid Id Format (i.e. 15)");
				request.setAttribute("errList", errList);
				request.getRequestDispatcher("createAccount.jsp").forward(request, response);
			}
			
			// Then Check if Id already in DB
			boolean accountExists = false;
			accountExists = accountDAO.doesAccountExist(id);
			if (accountExists) {
				errList.add("Account Already Exists With This Id! Please Choose Another!");
				request.setAttribute("errList", errList);
				request.getRequestDispatcher("createAccount.jsp").forward(request, response);
			}
			
			
			String password = request.getParameter("password");
			boolean validPassword = Regex.validatePassword(password);
			if (!validPassword) {
				errList.add("Invalid Password! Must have 8 Characters With Upper, Lower, Number, and Special");
				request.setAttribute("errList", errList);
				request.getRequestDispatcher("createAccount.jsp").forward(request, response);
			}
			
			
			
			double initialDeposit = Double.parseDouble(request.getParameter("initialdeposit"));
			boolean validInitialDeposit = Regex.validateInitialDeposit(initialDeposit);
			if (!validInitialDeposit) {
				errList.add("Invalid Initial Deposit! Must be Valid Positive Amount");
				request.setAttribute("errList", errList);
				request.getRequestDispatcher("createAccount.jsp").forward(request, response);
			}
			
			
			else {
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
				// TODO: Instantiate new account and add it to db if all is well
			}
			
		}
		catch(NumberFormatException e) {
			errList.add("Please fill in all of the fields!");
			request.setAttribute("errList", errList);
			
			request.getRequestDispatcher("createAccount.jsp").forward(request, response);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
