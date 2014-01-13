/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.bioinf.vcftools.vcftools_web.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import nl.bioinf.vcftools.vcftools_web.db.DbConnector;

/**
 *
 * @author Jeroen
 */
public class CreateUser {

    private Connection connection;

    /**
     * Makes contact with the database.
     */
    public CreateUser() {
        connection = DbConnector.getConnection();
    }

    public void insertNewUser(String name, String pass) {
        try {

            PreparedStatement ps = connection.prepareStatement("insert into users (name, password) values (?,?);");
            ps.setString(1, name);
            ps.setString(2, pass);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
