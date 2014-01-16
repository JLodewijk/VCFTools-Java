package nl.vcftools.vcftools_web.dao;

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
public class DaoMysqlImpl implements Dao {

    private static final DaoMysqlImpl instance = new DaoMysqlImpl();
    private static final String FETCH_USER = "fetch_user";
    private static final String INSERT_USER = "insert_user";

    private HashMap<String, PreparedStatement> preparedStatements = new HashMap<String, PreparedStatement>();
    private Connection connection;

    /**
     * Makes contact with the database.
     */
    public DaoMysqlImpl() {
	connection = DbConnector.getConnection();
    }

    public static DaoMysqlImpl getInstance() {
	return instance;
    }

    /**
     * Makes contact with the database.
     *
     * @param url is the url of the db
     * @param user is the username that corrosponds with the db.
     * @param pass is the password that corrosponds with user of that db.
     */
    @Override
    public void connect(String url, String user, String pass) {
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
     * Checks if the user exist in the db.
     *
     * @param uName is the username of the user performing the login.
     * @param uPass is the password of the user performing the login.
     */
    @Override
    public boolean getUser(String uName, String uPass) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean doesUserNameExist(String name) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Adds a user to the database.
     *
     * @param user
     */
    public void addUser(UserModel user) {
	try {

	    PreparedStatement ps = connection.prepareStatement("insert into users (name,password,role) values (?, ?, ?)");
	    ps.setString(1, user.getName());
	    ps.setString(2, user.getPassword());
	    ps.setString(3, user.getRole());
	    ps.executeUpdate();

	} catch (SQLException ex) {
	    Logger.getLogger(DaoMysqlImpl.class
		    .getName()).log(Level.SEVERE, null, ex);
	}

    }

    /**
     * Delete a user in the database.
     *
     * @param name is the username
     */
    public void deleteUser(String name) {
	try {
	    PreparedStatement ps = connection
		    .prepareStatement("delete from users where name=?");
	    ps.setString(1, name);
	    ps.executeUpdate();

	} catch (SQLException ex) {
	    Logger.getLogger(DaoMysqlImpl.class
		    .getName()).log(Level.SEVERE, null, ex);
	}
    }

    /**
     * Takes an existing entry and changes the username, password and role of that user.
     *
     * @param user is the name of the user that is being updated
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
	    Logger.getLogger(DaoMysqlImpl.class
		    .getName()).log(Level.SEVERE, null, ex);
	}

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
	    ResultSet rs = st.executeQuery("select * from users;");
	    while (rs.next()) {
		UserModel user = new UserModel();
		user.setName(rs.getString("name"));
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
     * @param name is the username
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
    public void disconnect() {
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
