/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Stock;

import Admin.Cashier.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

/**
 *
 * @author ST
 */
public class Validation {
    AdminCashierController acc = new AdminCashierController();
    String ID;
    String Name;
    String Quan_avl;
    String Quan_add;
    String MRP;
        
    public void CheckEmpty(String ID,String Name,String Quan_avl,String Quan_add,String MRP){
        this.ID = ID;
        this.Name = Name;
        this.Quan_avl = Quan_avl;
        this.Quan_add = Quan_add;
        this.MRP = MRP;
           
        if(ID.isEmpty() || Name.isEmpty() || Quan_avl.isEmpty() || Quan_add.isEmpty() || MRP.isEmpty()){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill the All Fields!");
            alert.showAndWait();
            return;
        }
        
    }
    public void CheckID(String ID){
        this.ID = ID;
        if(CheckIdPattern(ID) == false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter first letter P and 5 numbers for the ID and don't use Letters!");
            alert.showAndWait();
            return;
        }
               
    }
    public boolean CheckIdPattern(String ID){
        this.ID = ID;
        Pattern p = Pattern.compile("(P)?[0-9]{5}"); 
        Matcher m = p.matcher(ID); 
        return (m.find() && m.group().equals(ID)); 
        
    }

    public void CheckQuan_avl(String Quan_avl){
        this.Quan_avl = Quan_avl;
        if(CheckQuan_avlPattern(Quan_avl) == false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Don't use letterss!");
            alert.showAndWait();
            return;
        }        
    }
    public boolean CheckQuan_avlPattern(String Quan_avl){
        this.Quan_avl = Quan_avl;        
        Pattern p = Pattern.compile("[0-9]*"); 
        Matcher m = p.matcher(Quan_avl); 
        return (m.find() && m.group().equals(Quan_avl)); 
    }

    public void CheckQuan_add(String Quan_add){
        this.Quan_add = Quan_add;
        
        if(CheckQuan_addPattern(Quan_add) == false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Don't use letterss!");
            alert.showAndWait();
            return;
        }
    }
    public boolean CheckQuan_addPattern(String Quan_add){
        this.Quan_add = Quan_add;
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(Quan_add);
        return (m.find() && m.group().equals(Quan_add));       
    } 
    public void CheckMrp(String MRP){
        this.MRP = MRP;
        
        if(CheckQuan_addPattern(MRP) == false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Don't use letterss!");
            alert.showAndWait();
            return;
        }
    }
    public boolean CheckMrpPattern(String MRP){
        this.MRP = MRP;
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(MRP);
        return (m.find() && m.group().equals(MRP));       
    } 
}
