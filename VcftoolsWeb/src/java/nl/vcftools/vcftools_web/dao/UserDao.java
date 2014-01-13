package nl.vcftools.vcftools_web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.bioinf.vcftools.vcftools_web.pojo.UserModel;

/**
 *
 * @author Jeroen
 */
public class UserDao {

    private Connection connection;

    /**
     * Makes contact with the database.
     */
    public UserDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://mysql.bin/Jlodewijk", "jlodewijk", "jeroen");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
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
}
