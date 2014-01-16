package nl.bioinf.vcftools.vcftools_web.servlets;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nl.bioinf.vcftools.vcftools_web.db.DbConnector;
import nl.bioinf.vcftools.vcftools_web.pojo.UserModel;
import nl.vcftools.vcftools_web.dao.Dao;
import nl.vcftools.vcftools_web.dao.DaoFactory;
import nl.vcftools.vcftools_web.dao.DaoMysqlImpl;

public class LoginServlet extends HttpServlet {
    private Connection connection;
    private DaoMysqlImpl dao;

    /**
     * Makes contact with the database.
     */

    public LoginServlet() {
        connection = DbConnector.getConnection();
	dao = new DaoMysqlImpl();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //getInitParameter(null);
        //String userName = request.getParameter("username");
        String firstVisit = "yes";
        //get or create session object
        HttpSession session = request.getSession();
        RequestDispatcher view;

        //System.err.println("HOI! het gaat fout");
        //System.out.println("HALLO! het gaat goed");

//        List<User> persons = new ArrayList<User>();
//        persons.add(new User("Henk", "Honk", "hh@mail.com", "admin"));
//        persons.add(new User("Anita", "Aantjes", "Anita@libelle.nl", "user"));
//        persons.add(new User("John", "Doe", "jd@fbi.org", "guest"));
//        request.setAttribute("persons", persons);

        if (session.getAttribute("username") != null) {
            //existing session
            firstVisit = "No";
            view = request.getRequestDispatcher("listUser.jsp");
        } else {
            
            //new session; try to log in
            UserModel user = loginUser(
                    request.getParameter("username"), 
                   request.getParameter("password"));
            if(user == null){
                request.setAttribute("error", "Could not log in with the given username and password");
                view = request.getRequestDispatcher("login.jsp");
            }else{
                session.setAttribute("webuser", user);
                view = request.getRequestDispatcher("home.jsp");
            }
        }
        request.setAttribute("first", firstVisit);
        view.forward(request, response);
    
        

        
//        // Gets the value of the pressed button.
//        String userinput = request.getParameter("userinput");
//        String name = request.getParameter("username");
//        String pass = request.getParameter("password");
//        // If user presses the button login
//        if (userinput.equals("Login")) {
//            if (ValidateUser.checkUserInformation(connection,name, pass)) {
//                RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
//                rs.forward(request, response);
//            } else {
//                RequestDispatcher rs = request.getRequestDispatcher("error.html");
//                rs.forward(request, response);
//            }
//
//        }// Else user pressed the register button.
//        else {
//            // To prevent duplicate usernames, since the usernames are the primary key.
//            if (ValidateUser.doesUserNameExist(connection,name)) {
//                RequestDispatcher rs = request.getRequestDispatcher("error.html");
//                rs.forward(request, response);
//            } else {
//                CreateUser.insertNewUser(connection,name, pass);
//                RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
//                rs.forward(request, response);
//            }
//        }
    }
    private UserModel loginUser(String user, String pass) {
        UserModel u = null;
        Dao dao = DaoFactory.getInstance(DaoFactory.DbType.MYSQL);
        String url = "jdbc:mysql://mysql.bin/Jlodewijk";
        String dbPass = "jeroen";
        String dbUser = "jlodewijk";
        dao.connect(url, dbUser, dbPass);
        u = dao.getUser(user, pass);
        return u;
    }



}


