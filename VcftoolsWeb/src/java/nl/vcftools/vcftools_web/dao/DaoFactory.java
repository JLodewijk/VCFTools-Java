/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.vcftools.vcftools_web.dao;


/**
 *
 * @author michiel
 */
public class DaoFactory {
    public static Dao getInstance(DbType type){
        switch(type){
            case MYSQL: return DaoMysqlImpl.getInstance();
            default: throw new UnsupportedOperationException("type " + type + " si not supported yet!");
        }
    }
    
    public enum DbType{
        MYSQL,
        HADOOP,
        ORACLE;
    }
}
