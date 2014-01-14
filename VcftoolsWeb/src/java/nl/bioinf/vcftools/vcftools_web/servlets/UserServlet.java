package nl.bioinf.vcftools.vcftools_web.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.vcftools.vcftools_web.dao.UserDaoMysqlImpl;
import nl.bioinf.vcftools.vcftools_web.pojo.UserModel;

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
    private final UserDaoMysqlImpl dao;

    /**
     * Calls the UserDaoMysqlImpl to make contact and getting information out of the
 database.
     */
    public UserServlet() {
        dao = new UserDaoMysqlImpl();
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
        System.out.println("kdsalhfdkla");
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
        } else if (button.equalsIgnoreCase("change")){
            UserServlet.action = changePassword;
            
        } else {
            UserServlet.action = insertUser;
        }

        RequestDispatcher view = request.getRequestDispatcher(UserServlet.action);
        view.forward(request, response);
    }

    /**
     * Gets form information and sents it to the UserDaoMysqlImpl.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
