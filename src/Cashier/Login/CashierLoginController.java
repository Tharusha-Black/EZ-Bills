/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cashier.Login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ST
 */
public class CashierLoginController implements Initializable {

    @FXML
    private StackPane AdminLoginMainPane;
    @FXML
    private Button CashierLoginBtn;
    @FXML
    private TextField CashierLoginUNTxtBox;
    @FXML
    private PasswordField CashierLoginPWDBox;
    @FXML
    private Button CashierLoginBackBtn;
    @FXML
    private ImageView CashierShowPwd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LoadCashierMainFrame(ActionEvent event) {
    }

    @FXML
    private void LoadBackMenu(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("/ezbills/EzHome.fxml"));
            AdminLoginMainPane.getChildren().setAll(pane);
    }

    @FXML
    private void ShowPwd(MouseEvent event) {
    }
    
}
