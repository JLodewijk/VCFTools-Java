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
        // Gets the value of the pressed button.
        String userinput = request.getParameter("userinput");
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        // If user presses the button login
        if (userinput.equals("Login")) {
            if (ValidateUser.checkUserInformation(name, pass)) {
                RequestDispatcher rs = request.getRequestDispatcher("welcome.html");
                rs.forward(request, response);
            } else {
                RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
                rs.forward(request, response);
            }

        }// Else user pressed the register button.
        else {
            // To prevent duplicate usernames, since the usernames are the primary key.
            if (ValidateUser.doesUserNameExist(name)) {
                RequestDispatcher rs = request.getRequestDispatcher("error.html");
                rs.forward(request, response);
            } else {
                CreateUser.insertNewUser(name, pass);
                RequestDispatcher rs = request.getRequestDispatcher("welcome.html");
                rs.forward(request, response);
            }
        }
    }
}
