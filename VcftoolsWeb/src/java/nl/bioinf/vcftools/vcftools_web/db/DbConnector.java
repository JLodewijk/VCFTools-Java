/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.vcftools_web.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Jeroen Lodewijk <j.lodewijk@st.hanze.nl>
 */
public class DbConnector {

    private static Connection connection = null;
   /**
     * Makes contact with the database, cam be used by all the scripts.
     */
    public static Connection getConnection() {
	if (connection != null) {
	    return connection;
	} else {
	    try {
		Properties prop = new Properties();
		String driver = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://mysql.bin/Jlodewijk";
		String dbUser = "jlodewijk";
		String dbPass = "jeroen";
		Class.forName(driver);
		connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
	    } catch (ClassNotFoundException e) {
		e.printStackTrace();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    return connection;
	}

    }
}
