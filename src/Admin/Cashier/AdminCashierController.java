/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Cashier;

import Database.DatabaseHandler_AdminCashier;
import ezbills.EzHomeController;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ST
 */
public class AdminCashierController implements Initializable {

    @FXML
    private Button AdminCashierAdd;
    @FXML
    private Button AdminCashierUpdate;
    @FXML
    private Button AdminCashierDelete;
    @FXML
    private TextField AdminCashierID;
    @FXML
    private TextField AdminCashierName;
    @FXML
    private TextField AdminCashierNo;
    @FXML
    private TextField AdminCashierEmail;
    @FXML
    private TextField AdminCashierPwd;
    
    ObservableList<EZ_Bills_AdminCashier> list = FXCollections.observableArrayList();
    
    @FXML
    private TableView<EZ_Bills_AdminCashier> AdminCashierTable;
    
    @FXML
    private TableColumn<EZ_Bills_AdminCashier, String> IDTCol;
    @FXML
    private TableColumn<EZ_Bills_AdminCashier, String> NameTCol;
    @FXML
    private TableColumn<EZ_Bills_AdminCashier, String> NoTCol;
    @FXML
    private TableColumn<EZ_Bills_AdminCashier, String> AddTCol;
    @FXML
    private TableColumn<EZ_Bills_AdminCashier, String> EmailTCol;
    @FXML
    private TableColumn<EZ_Bills_AdminCashier, String> PwdTCol;
    @FXML
    private TextField AdminCashierAddress;

    /**
     * Initializes the controller class.
     */
    DatabaseHandler_AdminCashier Handler;
    @FXML
    private MenuItem TableMenuEdit;
    @FXML
    private MenuItem TableMenuRefresh;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Handler = new DatabaseHandler_AdminCashier();
        loadData();
        intCol();
    } 
    public TextField getAdminCashierID() {
        return AdminCashierID;
    }

    public void setAdminCashierID(TextField AdminCashierID) {
        this.AdminCashierID = AdminCashierID;
    }

    public TextField getAdminCashierName() {
        return AdminCashierName;
    }

    public void setAdminCashierName(TextField AdminCashierName) {
        this.AdminCashierName = AdminCashierName;
    }

    public TextField getAdminCashierNo() {
        return AdminCashierNo;
    }

    public void setAdminCashierNo(TextField AdminCashierNo) {
        this.AdminCashierNo = AdminCashierNo;
    }

    public TextField getAdminCashierEmail() {
        return AdminCashierEmail;
    }

    public void setAdminCashierEmail(TextField AdminCashierEmail) {
        this.AdminCashierEmail = AdminCashierEmail;
    }

    public TextField getAdminCashierPwd() {
        return AdminCashierPwd;
    }

    public void setAdminCashierPwd(TextField AdminCashierPwd) {
        this.AdminCashierPwd = AdminCashierPwd;
    }

    public TextField getAdminCashierAddress() {
        return AdminCashierAddress;
    }

    public void setAdminCashierAddress(TextField AdminCashierAddress) {
        this.AdminCashierAddress = AdminCashierAddress;
    }

    @FXML
    private void AddNewCashier(ActionEvent event) {
        String CashierId = AdminCashierID.getText();
        String CashierName = AdminCashierName.getText();
        String CashierNo = AdminCashierNo.getText();
        String CashierAddress  = AdminCashierAddress.getText();
        String CashierEmail = AdminCashierEmail.getText();
        String CashierPassword = AdminCashierPwd.getText();
        
        // ======================= VALIDATIONS==============================\\
        Validation vd = new Validation();
        vd.CheckEmpty(CashierId,CashierName,CashierNo,CashierAddress,CashierEmail,CashierPassword);
        vd.CheckID(CashierId);
        vd.CheckNo(CashierNo);
        vd.CheckEmail(CashierEmail);
        
        if(vd.CheckEmailPattern(CashierEmail) == false){
            setDefaultEmail();
        }
        if(vd.CheckIdPattern(CashierId) == false){
            setEmptyId();
        }
        if(vd.CheckNoPattern(CashierNo) == false){
            setEmptyNo();
        }
        if(vd.CheckEmailPattern(CashierEmail) == true && vd.CheckIdPattern(CashierId) == true && vd.CheckNoPattern(CashierNo) == true){
            String qu = "INSERT INTO EZ_Bills_AdminCashier VALUES("+
                "'" + CashierId + "'," +
                "'" + CashierName +  "'," +
                "'" + CashierNo +"'," +
                "'" + CashierAddress +"'," +
                "'" + CashierEmail +"'," +
                "'" + CashierPassword +"'"+")"; 
        
            System.out.println(qu);
        
              if (Handler.execAction(qu)){
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setContentText("SUCCESS");
                  alert.showAndWait();
                  setEmptyAll();
                 loadData();
            
                  }else{
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setContentText("FAIL");
                       alert.showAndWait();
                    }
        
        
                 }

    }
    @FXML
    private void UpdateCashier(ActionEvent event) {
        String CashierId = AdminCashierID.getText();
        String CashierName = AdminCashierName.getText();
        String CashierNo = AdminCashierNo.getText();
        String CashierAddress  = AdminCashierAddress.getText();
        String CashierEmail = AdminCashierEmail.getText();
        String CashierPassword = AdminCashierPwd.getText();
        
         // ======================= VALIDATIONS==============================\\
         
        Validation vd2 = new Validation();
        vd2.CheckEmpty(CashierId,CashierName,CashierNo,CashierAddress,CashierEmail,CashierPassword);
        vd2.CheckID(CashierId);
        vd2.CheckEmail(CashierEmail);
        
        String UpName = "UPDATE EZ_Bills_AdminCashier SET Name = '"+CashierName+"' WHERE ID = '"+CashierId+"'";
        Handler.execAction(UpName);
        
        String UpNo = "UPDATE EZ_Bills_AdminCashier SET Mobile_No = '"+CashierNo+"' WHERE ID = '"+CashierId+"'";
        Handler.execAction(UpNo);
        
        String UpAddress = "UPDATE EZ_Bills_AdminCashier SET Address = '"+CashierAddress+"' WHERE ID = '"+CashierId+"'";
        Handler.execAction(UpAddress);
        
        String UpEmail = "UPDATE EZ_Bills_AdminCashier SET Email = '"+CashierEmail+"' WHERE ID = '"+CashierId+"'";
        Handler.execAction(UpEmail);
        
        String UpPwd = "UPDATE EZ_Bills_AdminCashier SET Password = '"+CashierPassword+"' WHERE ID = '"+CashierId+"'";
        Handler.execAction(UpPwd);
        setEmptyAll();
        loadData();
    }

    @FXML
    private void DeleteCashier(ActionEvent event) {
        String CashierId = AdminCashierID.getText();
        
        String deleteAll = "DELETE FROM EZ_Bills_AdminCashier WHERE ID = '"+CashierId+"'";
        Handler.execAction(deleteAll);
        setEmptyAll();
        loadData();
    }
    private void loadData() {
        list.clear();
        String qu = "SELECT * FROM EZ_Bills_AdminCashier";
        ResultSet rs = Handler.execQuery(qu);
        try{
            while (rs.next()){
                String ID = rs.getString("ID");
                String Name = rs.getString("Name");
                String Mobile_No = rs.getString("Mobile_No");
                String Address = rs.getString("Address");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                
                list.add(new AdminCashierController.EZ_Bills_AdminCashier(ID,Name,Mobile_No, Address, Email, Password));
            }
        }catch (SQLException ex){
            Logger.getLogger(EzHomeController.class.getName()).log(Level.SEVERE, null,ex);
        }
        
        AdminCashierTable.getItems().setAll(list);
    }
    
    private void refreshData(ActionEvent event) {
        loadData();
        
    }

    @FXML
    private void LoadData(ActionEvent event) {
    }
    public static class EZ_Bills_AdminCashier{
        private final SimpleStringProperty ID;
        private final SimpleStringProperty Name;
        private final SimpleStringProperty Mobile_No;
        private final SimpleStringProperty Address;
        private final SimpleStringProperty Email;
        private final SimpleStringProperty Password;
        
        EZ_Bills_AdminCashier(String ID,String Name,String Mobile_No,String Address,String Email,String Password){
            this.ID = new SimpleStringProperty(ID);
            this.Name = new SimpleStringProperty(Name);
            this.Mobile_No = new SimpleStringProperty(Mobile_No);
            this.Address = new SimpleStringProperty(Address); 
            this.Email = new SimpleStringProperty(Email); 
            this.Password = new SimpleStringProperty(Password); 
        }   
       

        public String getID() {
            return ID.get();
        }

        public String getName() {
            return Name.get();
        }

        public String getMobile_No() {
            return Mobile_No.get();
        }

        public String getAddress() {
            return Address.get();
        }

        public String getEmail() {
            return Email.get();
        }

        public String getPassword() {
            return Password.get();
        }

    }
    
    
    private void intCol() {
   
        IDTCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        NameTCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        NoTCol.setCellValueFactory(new PropertyValueFactory<>("Mobile_No"));
        AddTCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
        EmailTCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        PwdTCol.setCellValueFactory(new PropertyValueFactory<>("Password"));
    }
    @FXML
    public void Editor(){
        EZ_Bills_AdminCashier SelectedForUpdate = AdminCashierTable.getSelectionModel().getSelectedItem();
        if(SelectedForUpdate == null){
            return;
        }
        inflateUI(SelectedForUpdate);
            
    }
     public void inflateUI(AdminCashierController.EZ_Bills_AdminCashier ez){
                AdminCashierID.setText(ez.getID());
                AdminCashierName.setText(ez.getName());
                AdminCashierNo.setText(ez.getMobile_No());
                AdminCashierAddress.setText(ez.getAddress());
                AdminCashierEmail.setText(ez.getEmail());
                AdminCashierPwd.setText(ez.getPassword());

        }
     public void setEmptyAll(){
        setEmptyId();
        setEmptyName();
        setEmptyNo();
        setEmptyAdd();
        AdminCashierEmail.setText(null);
        setEmptyPwd();
     }
     public void setEmptyId(){
        AdminCashierID.setText(null);
     }
     public void setEmptyName(){
         AdminCashierName.setText(null);
     }
     public void setEmptyNo(){
         AdminCashierNo.setText(null);
     }
     public void setEmptyAdd(){
         AdminCashierAddress.setText(null);
     }
     public void setDefaultEmail(){
         AdminCashierEmail.setText("Deafualt@EzBills.Com");
     }
     public void setEmptyPwd(){
         AdminCashierPwd.setText(null);
     }
    
    

}
