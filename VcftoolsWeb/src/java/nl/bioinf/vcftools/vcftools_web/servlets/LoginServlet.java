package nl.bioinf.vcftools.vcftools_web.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Get user information
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        if (ValidateUser.checkUserInformation(name, pass)) {
            RequestDispatcher rs = request.getRequestDispatcher("welcome.html");
            rs.forward(request, response);
        } else {
            RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
            rs.forward(request, response);
        }

    }
}
