package com.revature.reim.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.reim.dao.EmployeeDao;
import com.revature.reim.dao.EmployeeDaoImpl;
import com.revature.reim.model.Employee;
import com.revature.reim.model.Reimbursement;

/**
 * Servlet implementation class EmployeeProfile
 */
public class EmployeeProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String uname=(String) session.getAttribute("username");
//		System.out.println(uname);
		EmployeeDao ed = new EmployeeDaoImpl();
		int empId= ed.getEmpId(uname);
//		System.out.println(empId);
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
			if(empId==0) {
				pw.print("invalid employee id");
			} 
		else {
			Employee e=ed.viewProfile(empId);
			String empString = om.writeValueAsString(e);
			empString = "{\"employee\":"+empString+"}";
			pw.print(empString);
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname=request.getParameter("firstName");
		String lname=request.getParameter("lastName");
		String username=request.getParameter("email");
		String password=request.getParameter("password");
		EmployeeDaoImpl emp = new EmployeeDaoImpl();
		int empId=emp.getEmpId(username);
		int result=emp.changeProfile(empId, password, fname, lname, username);
		System.out.println(result);
		response.sendRedirect("employee");
	}

}
