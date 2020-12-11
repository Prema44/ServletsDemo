package com.bridgelabz;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		description = "Login Servlet Testing",
		urlPatterns = {"/LoginServlet"},
		initParams = {
				@WebInitParam(name = "username", value = "Sakshat"),
				@WebInitParam(name = "password", value = "Password")
		}
)
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Getting request parameters
		String user = request.getParameter("username");
		String password = request.getParameter("password");

		//getting servlet config init parameters
		String userID = getServletConfig().getInitParameter("username");
		String checkPassword = getServletConfig().getInitParameter("password");

		String nameReg = "^[A-Z][A-z\\s]{3,}$";
		String passReg = "(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-z0-9!@#$%^&*]{8,}$";
		if(!user.matches(nameReg)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color = red>UserName should start with capital letter and has minimum 3 characters</font>");
			rd.include(request, response);
		}
		else if(!password.matches(passReg)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color = red>Invalid Password !!</font>");
			rd.include(request, response);
		}
		else if(userID.equals(user) && checkPassword.equals(password)) {
			request.setAttribute("username", user);
			request.getRequestDispatcher("LoginSuccess.jsp").include(request, response);
		}
		else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color = red>Incorrect User Name or Password!!</font>");
			rd.include(request, response);
		}
	}

} 