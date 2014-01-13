/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.vcftools.vcftools_web.dao;

import java.io.IOException;
import java.util.List;
import nl.bioinf.vcftools.vcftools_web.pojo.UserModel;

/**
 *
 * @author mhroelfes
 */
public interface UserDao {
    public void connect(String url, String user, String pass) throws IOException;
    public UserModel getUser(String uName, String uPass) throws IOException ;
    public void addUser(UserModel user);
    public void deleteUser(String name);
    public void updateUser(UserModel user);
    public List<UserModel> getAllUsers();
    public UserModel getName(String name);
    public void disconnect() throws IOException;
}
