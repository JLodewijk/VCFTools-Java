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

/**
 *
 * @author Jeroen
 */
public class DaoMysqlImpl implements Dao {

    private static final DaoMysqlImpl instance = new DaoMysqlImpl();
    private static final String FETCH_USER = "fetch_user";
    private static final String INSERT_USER = "insert_user";
    private Connection connection;
    private HashMap<String, PreparedStatement> preparedStatements = new HashMap<String, PreparedStatement>();
    

    /**
     * Makes contact with the database.
     */
    private DaoMysqlImpl() {
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
     * @return 
     */
    @Override
    public void connect() {
	this.connection = null;
	try {
	    String driver = "com.mysql.jdbc.Driver";
	    String dbUrl = "jdbc:mysql://mysql.bin/Jlodewijk";
	    String dbUser = "jlodewijk";
	    String dbPass = "jeroen";
//                String dbUrl = "jdbc:mysql://127.0.0.1:3306/thema10";
//                String dbUser = "jl";
//                String dbPass = "hallo";
	    Class.forName(driver);
	    this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
        
    

    private void prepareStatements() throws SQLException {
	PreparedStatement ps;
	String sql = "select * from users where name=? and password=?;";
	ps = this.connection.prepareStatement(sql);
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
	boolean exist = false;
	try {

	    PreparedStatement ps = this.connection.prepareStatement("select * from users where name=? and password=?;");
	    ps.setString(1, uName);
	    ps.setString(2, uPass);
	    ResultSet rs = ps.executeQuery();
	    exist = rs.next();

	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return exist;
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

	    PreparedStatement ps = this.connection.prepareStatement("insert into users (name,password,role) values (?, ?, ?)");
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
	    PreparedStatement ps = this.connection
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
	    PreparedStatement ps = this.connection
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
	    Statement st = this.connection.createStatement();
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
	    PreparedStatement ps = this.connection.
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
		this.connection.close();
	    } catch (SQLException ex) {
	    }
	}
    }
}
