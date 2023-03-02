package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TransactionDAO;
import model.TransactionDAOImpl;

/**
 * Servlet implementation class Transactions
 */
@WebServlet("/transactions")
public class Transactions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transactions() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TransactionDAO transDAO = new TransactionDAOImpl();
		
		int userId = Integer.parseInt(request.getParameter("custId"));
		
		List<String> transList = transDAO.getFiveMostRecentTransactions(userId);
		
		request.setAttribute("transList", transList);
		
		
		
		
		
	}

}
