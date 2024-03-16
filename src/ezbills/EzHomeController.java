/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezbills;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author ST
 */
public class EzHomeController implements Initializable {

    @FXML
    private StackPane EzBillMainPane;
    @FXML
    private Button HomeAdminBtn;
    @FXML
    private Button HomeCashierBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LoadAdminLogin(ActionEvent event) throws IOException {
            StackPane pane = FXMLLoader.load(getClass().getResource("/Admin/Login/AdminLogin.fxml"));
            EzBillMainPane.getChildren().setAll(pane);
           
    }

    @FXML
    private void LoadCashierLogin(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("/Cashier/Login/CashierLogin.fxml"));
            EzBillMainPane.getChildren().setAll(pane);
    }
    
}
