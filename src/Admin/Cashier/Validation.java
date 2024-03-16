/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Cashier;

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
    String No;
    String Address;
    String Email;
    String Pwd;
    
    public void CheckEmpty(String ID,String Name,String No,String Address,String Email,String Pwd){
        this.ID = ID;
        this.Name = Name;
        this.No = No;
        this.Address = Address;
        this.Email = Email;
        this.Pwd = Pwd;
    
        if(ID.isEmpty() || Name.isEmpty() || No.isEmpty() || Address.isEmpty() || Email.isEmpty() || Pwd.isEmpty()){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill the All Fields!");
            alert.showAndWait();
            //acc.setEmptyAll();
            return;
        }
        
    }
    public void CheckID(String ID){
        this.ID = ID;
        if(CheckIdPattern(ID) == false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter first letter C and 4 numbers for the ID and don't use Letters!");
            alert.showAndWait();
            return;
        }
               
    }
    public boolean CheckIdPattern(String ID){
        this.ID = ID;
        Pattern p = Pattern.compile("(C)?[0-9]{4}"); 
        Matcher m = p.matcher(ID); 
        return (m.find() && m.group().equals(ID)); 
        
    }

    public void CheckNo(String No){
        this.No = No;
        if(CheckNoPattern(No) == false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter Correct Mobile Number!");
            alert.showAndWait();
            return;
        }        
    }
    public boolean CheckNoPattern(String No){
        this.No = No;        
        Pattern p = Pattern.compile("(0)?[7][0-9]{8}"); 
        Matcher m = p.matcher(No); 
        return (m.find() && m.group().equals(No)); 
    }

    public void CheckEmail(String Email){
        this.Email = Email;
        
        if(CheckEmailPattern(Email) == false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter Correct Email Adress!");
            alert.showAndWait();
            return;
        }
    }
    public boolean CheckEmailPattern(String Email){
        this.Email = Email;
        Pattern p = Pattern.compile("^(.+)@(.+)(.)?(.+)$");
        Matcher m = p.matcher(Email);
        return (m.find() && m.group().equals(Email));       
    } 
}
