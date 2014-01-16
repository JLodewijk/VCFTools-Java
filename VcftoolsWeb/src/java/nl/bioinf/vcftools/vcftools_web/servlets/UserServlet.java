package nl.bioinf.vcftools.vcftools_web.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.vcftools.vcftools_web.dao.DaoMysqlImpl;
import nl.bioinf.vcftools.vcftools_web.pojo.UserModel;
import nl.vcftools.vcftools_web.dao.Dao;
import nl.vcftools.vcftools_web.dao.DaoFactory;

/**
 *
 * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
 */
public class UserServlet extends HttpServlet {

    private static final String insertUser = "/user.jsp";
    private static final String editUser = "/editUser.jsp";
    private static final String allUsers = "/listUser.jsp";
    private static final String changePassword = "/changePw.jsp";
    private static String action;
    Dao dao = DaoFactory.getInstance(DaoFactory.DbType.MYSQL);

    /**
     * Calls the DaoMysqlImpl to make contact and getting information out of the
 database.
     */
    private UserServlet() {
    }
    


    /**
     * Sends request to list all user and gets the user request.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Action is the button that has been pressed
        String button = request.getParameter("action");
        if (button.equalsIgnoreCase("delete")) {
            String name = request.getParameter("name");
            dao.deleteUser(name);
            UserServlet.action = allUsers;
            request.setAttribute("users", dao.getAllUsers());
        } else if (button.equalsIgnoreCase("edit")) {
            UserServlet.action = editUser;
            String name = request.getParameter("name");
            UserModel user = dao.getName(name);
            request.setAttribute("user", user);
        } else if (button.equalsIgnoreCase("listUser")) {
            UserServlet.action = allUsers;
            request.setAttribute("users", dao.getAllUsers());
        } else {
            UserServlet.action = insertUser;
        }

        RequestDispatcher view = request.getRequestDispatcher(UserServlet.action);
        view.forward(request, response);
    }

    /**
     * Gets form information and sends it to the DaoMysqlImpl.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//request.setAttribute("users", dao.getAllUsers());
        UserModel user = new UserModel();
        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));
        user.setRole(request.getParameter("role"));
        String name = request.getParameter("name");
        if (UserServlet.action.equalsIgnoreCase(insertUser)) {
            user.setName(name);
            dao.addUser(user);
        } else {
            user.setName(name);
            dao.updateUser(user);
        }
        RequestDispatcher view = request.getRequestDispatcher(allUsers);
        request.setAttribute("users", dao.getAllUsers());
        view.forward(request, response);
    }
}
