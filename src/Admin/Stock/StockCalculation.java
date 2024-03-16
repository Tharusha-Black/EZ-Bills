/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Stock;

/**
 *
 * @author ST
 */


import Database.DatabaseHandler_AdminStock;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockCalculation {
    DatabaseHandler_AdminStock Handler = new DatabaseHandler_AdminStock();
    String ID;
    int QuanAvl;
    int QuanAdd;
    
    public int getStockAvl(String ID){
        this.ID = ID;
        ResultSet rs;
         String q = "SELECT Quan_avl FROM EZ_Bills_AdminStock WHERE ID = '"+ID+"'";
         rs = Handler.execQuery(q);
        try {
            if(rs.next()){
            QuanAvl = Integer.parseInt(rs.getString("Quan_avl"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockCalculation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return QuanAvl;
    }
   
}
