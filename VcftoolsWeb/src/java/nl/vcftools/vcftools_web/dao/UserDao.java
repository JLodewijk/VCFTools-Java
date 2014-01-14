/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.vcftools.vcftools_web.dao;

import java.util.List;
import nl.bioinf.vcftools.vcftools_web.pojo.UserModel;

/**
 *
 * @author mhroelfes
 */
public interface UserDao {
    public void connect(String url, String user, String pass);
    public UserModel getUser(String uName, String uPass);
    public void addUser(UserModel user);
    public void deleteUser(String name);
    public void updateUser(UserModel user);
    public List<UserModel> getAllUsers();
    public UserModel getName(String name);
    public void disconnect();
}
