/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import javax.management.Query;
import javax.swing.JOptionPane;


/**
 *
 * @author ST
 */
public final class DatabaseHandler_AdminCashier {
    private static DatabaseHandler_AdminCashier handler;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3308/ezbills";
    private static Connection conn = null;
    private static Statement stat = null;

    public static void getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public DatabaseHandler_AdminCashier(){
        createConnection();
        setupDeadline();
    }
    void createConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(DB_URL, "root","");
        }catch (Exception e){
                e.printStackTrace();
        }
    }
    
    void setupDeadline(){
        String TABLE_NAME = "EZ_Bills_AdminCashier";
        try{
            stat = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if (tables.next()){
               
                System.out.print("Table" + TABLE_NAME + "already exists.ready for go ");
                //stat.execute("DROP TABLE DRS");
            }
            else {
                
                stat.execute("CREATE TABLE " + TABLE_NAME + "("
                        + "     ID varchar(10) primary key,\n"
                        + "     Name varchar(30),\n"
                        + "     Mobile_No varchar(15),\n"
                        + "     Address varchar(50),\n"
                        + "     Email varchar(20),\n"
                        + "     Password varchar(15)\n"
                        + " )" );
            }
        } catch(SQLException e){
            System.err.println(e.getMessage() + " ...Setup database" );
        }finally{
            
        }
    }
    public ResultSet execQuery(String Query){
        ResultSet result;
        try{
            stat = conn.createStatement();
            result = stat.executeQuery(Query);
        }catch (SQLException ex){
            System.out.println("Exception at execQurey:DataHandler" + ex.getLocalizedMessage());
            return null;
        }finally{
        }
        return result;
    }
    public boolean execAction(String qu){
        try{
            stat = conn.createStatement();
            stat.execute(qu);
            return true;
        }catch (SQLException ex){
            JOptionPane.showConfirmDialog(null, ex.getMessage(), "Error occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at WxecQuery:DataHandler "+ex.getLocalizedMessage());
            return false;
        }finally{
        }
    }
    
    
}
