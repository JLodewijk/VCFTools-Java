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

    public static boolean checkUserInformation(String name, String pass) {
        boolean exist = false;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/thema10", "jlodewijk", "Hallo");
            PreparedStatement ps = connection.prepareStatement("select * from users where name=? and password=?;");
            ps.setString(1, name);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            exist = rs.next();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

}
