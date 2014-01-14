package nl.vcftools.vcftools_web.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.bioinf.vcftools.vcftools_web.pojo.UserModel;
import nl.bioinf.vcftools.vcftools_web.db.DbConnector;

/**
 *
 * @author Jeroen
 */
public class UserDaoMysqlImpl implements UserDao {

    private static final UserDaoMysqlImpl instance = new UserDaoMysqlImpl();
    private static final String FETCH_USER = "fetch_user";
    private static final String INSERT_USER = "insert_user";

    private HashMap<String, PreparedStatement> preparedStatements = new HashMap<String, PreparedStatement>();
    private Connection connection;

    /**
     * Makes contact with the database.
     */
    public UserDaoMysqlImpl() {
        
    }
    public static UserDaoMysqlImpl getInstance(){
        return instance;
    }
    @Override
    public void connect(String url, String user, String pass) throws IOException, FileNotFoundException {
        if (connection != null) {
            return;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("connection established");
            prepareStatements();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void prepareStatements() throws SQLException {
        PreparedStatement ps;
        String sql = "select * from users where name=? and password=?;";
        ps = connection.prepareStatement(sql);
        preparedStatements.put(FETCH_USER, ps);

//        sql = "insert into users (name, password) values (?,?)";
//        ps = connection.prepareStatement(sql);
//        preparedStatements.put(INSERT_USER, ps);

    }

    /**
     * Adds a user to the database.
     *
     * @param user
     */
    public void addUser(UserModel user) {
        try {

            PreparedStatement ps = connection
                    .prepareStatement("insert into users (name,password,role) values (?, ?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoMysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Delete a user in the database.
     *
     * @param name
     */
    public void deleteUser(String name) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("delete from users where name=?");
            ps.setString(1, name);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDaoMysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param user
     */
    public void updateUser(UserModel user) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("update users set password=?, role=? "
                            + "where name=?");
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getRole());
            ps.setString(3, user.getName());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoMysqlImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    @Override
    public UserModel getUser(String uName, String uPass) throws IOException {
        UserModel u = null;
        try {
            PreparedStatement ps = preparedStatements.get(FETCH_USER);
            ps.setString(1, uName);
            ps.setString(2, uPass);
           
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new UserModel(uName, uPass, "admin");
            }

        } catch (SQLException ex) {
            //Logger.getLogger(HelloWebDaoMySQLImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return u;
    }

    /**
     * Gets all the current user in the database.
     *
     * @return
     */
    public List<UserModel> getAllUsers() {
        List<UserModel> users = new ArrayList<UserModel>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from users");
            while (rs.next()) {
                UserModel user = new UserModel();
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Gets the username, password and role
     *
     * @param name
     * @return
     */
    public UserModel getName(String name) {
        UserModel user = new UserModel();
        try {
            PreparedStatement ps = connection.
                    prepareStatement("select * from users where name=?");
            ps.setString(1, name);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void disconnect() throws IOException {
        try {
            for (PreparedStatement ps : preparedStatements.values()) {
                ps.close();
            }
        } catch (SQLException ex) {
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
            }
        }
    }
}
