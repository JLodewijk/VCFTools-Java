/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.vcftools_web.servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Jeroen
 */
public class CreateUser {


    public static void insertNewUser(Connection connection,String name, String pass) {
        try {

            PreparedStatement ps = connection.prepareStatement("insert into users (name, password, role) values (?,?,?);");
            ps.setString(1, name);
            ps.setString(2, pass);
	    ps.setString(3, "3");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
