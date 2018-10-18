package com.revature.reim.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.reim.dao.EmployeeDaoImpl;
import com.revature.reim.dao.ManagerDao;
import com.revature.reim.dao.ManagerDaoImpl;

/**
 * Servlet implementation class ManagerServlet
 */
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Views/Manager.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String reim=request.getParameter("reimId");
		int reimId=Integer.parseInt(reim);
		System.out.println(reim);
		
		String action=request.getParameter("action");
		int actionInt=Integer.parseInt(action);
		
		EmployeeDaoImpl emp = new EmployeeDaoImpl();
		ManagerDao man= new ManagerDaoImpl();
		HttpSession session = request.getSession(false);
		String uname=(String) session.getAttribute("username");
		System.out.println(uname);

		int empId=emp.getEmpId(uname);
//		int result =emp.submitReimbursement(empId, amount);
		int result =man.resolveRequest(reimId, empId, actionInt);
		
		System.out.println(result);
		response.sendRedirect("manager");
		
		
	}

}
