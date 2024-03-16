/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Stock;

import Admin.Cashier.AdminCashierController;
import Database.DatabaseHandler_AdminCashier;
import Database.DatabaseHandler_AdminStock;
import ezbills.EzHomeController;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class AdminStockController implements Initializable {

    @FXML
    private Button AdminProductAdd;
    @FXML
    private Button AdminProductUpdate;
    @FXML
    private TextField AdminProductID;
    @FXML
    private TextField AdminProductName;
    @FXML
    private TextField AdminProductAvbl;
    @FXML
    private TextField AdminProductAdded;
    @FXML
    private TextField AdminProductMrp;
    @FXML
    private TableColumn<EZ_Bills_AdminStock, String> IDTCol;
    @FXML
    private TableColumn<EZ_Bills_AdminStock, String> NameTCol;
    @FXML
    private TableColumn<EZ_Bills_AdminStock, String> QuanAvlTCol;
    @FXML
    private TableColumn<EZ_Bills_AdminStock, String> MrpTCol;
    @FXML
    private TableView<EZ_Bills_AdminStock> AdminStockTable;
    @FXML
    private Button AdminProductDelete;
    @FXML
    private TableColumn<EZ_Bills_AdminStock, String> LastAddTCol;
    
    ObservableList<AdminStockController.EZ_Bills_AdminStock> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    DatabaseHandler_AdminStock Handler;
    @FXML
    private MenuItem Editor;
    @FXML
    private MenuItem Refresh;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Handler = new DatabaseHandler_AdminStock();
        loadData();
        intCol();
    }    

    @FXML
    private void AddNewProduct(ActionEvent event) {
        String StockId = AdminProductID.getText();
        String StockName = AdminProductName.getText();
        String SStockQuan_avl = AdminProductAvbl.getText();
        String SStockQuan_add = AdminProductAdded.getText();
        String SStockMRP = AdminProductMrp.getText();
        int StockQuan_avl = Integer.parseInt(AdminProductAvbl.getText());
        int StockQuan_add  = Integer.parseInt(AdminProductAdded.getText());
        //int StockMRP = Integer.parseInt(AdminProductMrp.getText());
        
        //=======================//Stock Calculation\\======================\\
        
        int Stocks = StockQuan_avl + StockQuan_add;
        
        // ======================//VALIDATIONS\\============================\\
        Validation vd = new Validation();
        vd.CheckEmpty(StockId,StockName,SStockQuan_avl,SStockQuan_add,SStockMRP);
        vd.CheckID(StockId);
        vd.CheckQuan_add(SStockQuan_add);
        vd.CheckQuan_avl(SStockQuan_avl);
        vd.CheckMrp(SStockMRP);
        
        if(vd.CheckQuan_addPattern(SStockQuan_add) == false){
            setEmptyQuanAdd();
        }
        if(vd.CheckIdPattern(StockId) == false){
            setEmptyId();
        }
        if(vd.CheckQuan_avlPattern(SStockQuan_avl) == false){
            setEmptyQuanAvl();
        }
        if(vd.CheckMrpPattern(SStockMRP) == false){
            setEmptytMrp();
        }
        if(vd.CheckQuan_addPattern(SStockQuan_add) == true && vd.CheckIdPattern(StockId) == true && vd.CheckQuan_avlPattern(SStockQuan_avl) == true && vd.CheckMrpPattern(SStockMRP) == true){
            String qu = "INSERT INTO EZ_Bills_AdminStock VALUES("+
                "'" + StockId + "'," +
                "'" + StockName +  "'," +
                "'" + Stocks +"'," +
                "'" + SStockQuan_add +"'," +
                "'" + SStockMRP +"'"+")"; 
        
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
    private void UpdateProduct(ActionEvent event) {
        String StockId = AdminProductID.getText();
        String StockName = AdminProductName.getText();
        String StockQuan_avl = AdminProductAvbl.getText();
        String StockQuan_add  = AdminProductAdded.getText();
        String StockMRP = AdminProductMrp.getText();
        int iStockQuan_add  = Integer.parseInt(AdminProductAdded.getText());
        int Stocks;
         //=======================//Stock Calculation\\======================\\
         
         StockCalculation st = new StockCalculation();
         
         Stocks = st.getStockAvl(StockId);
         
         Stocks += iStockQuan_add;
        
        // ======================= VALIDATIONS==============================\\
        Validation vd = new Validation();
        vd.CheckEmpty(StockId,StockName,StockQuan_avl,StockQuan_add,StockMRP);
        vd.CheckID(StockId);
        vd.CheckQuan_add(StockQuan_add);
        vd.CheckQuan_avl(StockQuan_avl);
        
        String UpName = "UPDATE EZ_Bills_AdminStock SET Name = '"+StockName+"' WHERE ID = '"+StockId+"'";
        Handler.execAction(UpName);
        
        String UpQuan_avl = "UPDATE EZ_Bills_AdminStock SET Quan_avl = '"+Stocks+"' WHERE ID = '"+StockId+"'";
        Handler.execAction(UpQuan_avl);
        
        String UpQuan_add = "UPDATE EZ_Bills_AdminStock SET Quan_add = '"+StockQuan_add+"' WHERE ID = '"+StockId+"'";
        Handler.execAction(UpQuan_add);
        
        String UpMRP = "UPDATE EZ_Bills_AdminStock SET MRP = '"+StockMRP+"' WHERE ID = '"+StockId+"'";
        Handler.execAction(UpMRP);
                
        setEmptyAll();
        loadData();
        
    }

    @FXML
    private void DeleteProduct(ActionEvent event) {
        String StockId = AdminProductID.getText();
        
        String deleteAll = "DELETE FROM EZ_Bills_AdminStock WHERE ID = '"+StockId+"'";
        Handler.execAction(deleteAll);
        setEmptyAll();
        loadData();
    }
    private void loadData() {
        list.clear();
        String qu = "SELECT * FROM EZ_Bills_AdminStock";
        ResultSet rs = Handler.execQuery(qu);
        try{
            while (rs.next()){
                String ID = rs.getString("ID");
                String Name = rs.getString("Name");
                String Quan_avl = rs.getString("Quan_avl");
                String Quan_add = rs.getString("Quan_add");
                String MRP = rs.getString("MRP");
                                
                list.add(new AdminStockController.EZ_Bills_AdminStock(ID,Name,Quan_avl, Quan_add, MRP));
            }
        }catch (SQLException ex){
            Logger.getLogger(EzHomeController.class.getName()).log(Level.SEVERE, null,ex);
        }
        
        AdminStockTable.getItems().setAll(list);
    }
    public static class EZ_Bills_AdminStock{
        private final SimpleStringProperty ID;
        private final SimpleStringProperty Name;
        private final SimpleStringProperty Quan_avl;
        private final SimpleStringProperty Quan_add;
        private final SimpleStringProperty MRP;
        
        
        EZ_Bills_AdminStock(String ID,String Name,String Quan_avl,String Quan_add,String MRP){
            this.ID = new SimpleStringProperty(ID);
            this.Name = new SimpleStringProperty(Name);
            this.Quan_avl = new SimpleStringProperty(Quan_avl);
            this.Quan_add = new SimpleStringProperty(Quan_add); 
            this.MRP = new SimpleStringProperty(MRP); 
        }   

        public String getID() {
            return ID.get();
        }

        public String getName() {
            return Name.get();
        }

        public String getQuan_avl() {
            return Quan_avl.get();
        }

        public String getQuan_add() {
            return Quan_add.get();
        }

        public String getMRP() {
            return MRP.get();
        }
    }
     private void intCol() {
   
        IDTCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        NameTCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        QuanAvlTCol.setCellValueFactory(new PropertyValueFactory<>("Quan_avl"));
        LastAddTCol.setCellValueFactory(new PropertyValueFactory<>("Quan_add"));
        MrpTCol.setCellValueFactory(new PropertyValueFactory<>("MRP"));
        
    }

    
    @FXML
     public void Editor(){
        EZ_Bills_AdminStock SelectedForUpdate = AdminStockTable.getSelectionModel().getSelectedItem();
        if(SelectedForUpdate == null){
            return;
        }
        inflateUI(SelectedForUpdate);
            
    }
     public void inflateUI(AdminStockController.EZ_Bills_AdminStock ez){
                AdminProductID.setText(ez.getID());
                AdminProductName.setText(ez.getName());
                AdminProductAvbl.setText(ez.getQuan_avl());
                AdminProductAdded.setText(ez.getQuan_add());
                AdminProductMrp.setText(ez.getMRP());
        }
        public void setEmptyAll(){
             setEmptyId();
             setEmptyName();
             setEmptyQuanAvl();
             setEmptyQuanAdd();
             setEmptytMrp();
             }
     public void setEmptyId(){
        AdminProductID.setText(null);
     }
     public void setEmptyName(){
         AdminProductName.setText(null);
     }
     public void setEmptyQuanAvl(){
         AdminProductAvbl.setText(null);
     }
     public void setEmptyQuanAdd(){
         AdminProductAdded.setText(null);
     }
     public void setEmptytMrp(){
         AdminProductMrp.setText(null);
     }

}
