/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.MainFrame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ST
 */
public class AdminMainFrameController implements Initializable {

    @FXML
    private StackPane AdminMainFrameMainPane;
    @FXML
    private ImageView AdminMainFrameHomeIcon;
    @FXML
    private Button AdminMainFrameCashier;
    @FXML
    private Button AdminMainFrameTransaction;
    @FXML
    private Button AdminMainFrameStock;
    @FXML
    private Button AdminMainFrameSales;
    @FXML
    private Button AdminMainFrameExit;
    @FXML
    private StackPane AdminMainFrameBodyPane;
    @FXML
    private Button AdminMainFrameNew;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LoadCashierTab(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("/Admin/Cashier/AdminCashier.fxml"));
            AdminMainFrameBodyPane.getChildren().setAll(pane);
       /* FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Admin/Cashier/AdminCashier.fxml"));
       
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);
        Stage stage = new Stage();
        stage.setTitle("EZ Bills");
        stage.setScene(scene);
        stage.show();*/
    }

    @FXML
    private void LoadTransactionTab(ActionEvent event) {
    }

    @FXML
    private void LoadStockTab(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("/Admin/Stock/AdminStock.fxml"));
            AdminMainFrameBodyPane.getChildren().setAll(pane);
    }

    @FXML
    private void LoadSalesTab(ActionEvent event) {
    }

    @FXML
    private void LoadExit(ActionEvent event) {
    }

    @FXML
    private void LoadHomePage(MouseEvent event) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("/ezbills/EzHome.fxml"));
            AdminMainFrameMainPane.getChildren().setAll(pane);
    }

    @FXML
    private void LoadSignUp(ActionEvent event) {
    }
    
}
