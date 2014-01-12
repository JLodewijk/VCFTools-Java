package nl.bioinf.vcftools.vcftools_web.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.vcftools.vcftools_web.dao.TableDao;
import nl.bioinf.vcftools.vcftools_web.pojo.UserModel;

/**
 *
 * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
 */
public class TableServlet extends HttpServlet {

    private static final String insertUser = "/user.jsp";
    private static final String editUser = "/editUser.jsp";
    private static final String allUsers = "/listUser.jsp";
    private static String action;
    private final TableDao dao;

    /**
     * Calls the TableDao to make contact and getting information out of the
     * database.
     */
    public TableServlet() {
        dao = new TableDao();
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
            TableServlet.action = allUsers;
            request.setAttribute("users", dao.getAllUsers());
        } else if (button.equalsIgnoreCase("edit")) {
            TableServlet.action = editUser;
            String name = request.getParameter("name");
            UserModel user = dao.getName(name);
            request.setAttribute("user", user);
        } else if (button.equalsIgnoreCase("listUser")) {
            TableServlet.action = allUsers;
            request.setAttribute("users", dao.getAllUsers());
        } else {
            TableServlet.action = insertUser;
        }

        RequestDispatcher view = request.getRequestDispatcher(TableServlet.action);
        view.forward(request, response);
    }

    /**
     * Gets form information and sents it to the TableDao.
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
        if (TableServlet.action.equalsIgnoreCase(insertUser)) {
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
