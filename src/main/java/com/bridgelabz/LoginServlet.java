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
				@WebInitParam(name = "user", value = "Sakshat"),
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
		String user = request.getParameter("user");
		String password = request.getParameter("password");

		//getting servlet config init parameters
		String userID = getServletConfig().getInitParameter("user");
		String checkPassword = getServletConfig().getInitParameter("password");

		if(userID.equals(user) && checkPassword.equals(password)) {
			request.setAttribute("user", user);
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