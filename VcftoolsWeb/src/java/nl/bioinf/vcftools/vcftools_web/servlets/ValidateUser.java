package nl.bioinf.vcftools.vcftools_web.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Jeroen
 */
public class ValidateUser {

    public static boolean checkUserInformation(Connection connection,String name, String pass) {
        boolean exist = false;
        try {

            PreparedStatement ps = connection.prepareStatement("select * from users where name=? and password=?;");
            ps.setString(1, name);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            exist = rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public static boolean doesUserNameExist(Connection connection,String name) {
        boolean exist = false;
        try {
            PreparedStatement ps = connection.prepareStatement("select * from users where name=?;");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            exist = rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

}
