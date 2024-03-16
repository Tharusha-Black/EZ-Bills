/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ST
 */
public class AdminLoginController implements Initializable {

    @FXML
    private StackPane AdminLoginMainPane;
    @FXML
    private Button AdminLoginBackBtn;
    @FXML
    private Button AdminLoginBtn;
    @FXML
    private TextField AdminLoginUNTxtBox;
    @FXML
    private PasswordField AdminLoginPWDBox;
    @FXML
    private ImageView LoginShowPwd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
   private void LoadAdminMainFrame(ActionEvent event) throws IOException {
        StackPane pane = FXMLLoader.load(getClass().getResource("/Admin/MainFrame/AdminMainFrame.fxml"));
            AdminLoginMainPane.getChildren().setAll(pane);
   /* 
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Admin/MainFrame/AdminMainFrame.fxml"));
       
        Scene scene = new Scene(fxmlLoader.load(), 1280, 800);
        Stage stage = new Stage();
        stage.setTitle("EZ Bills");
        stage.setScene(scene);
        stage.show();
    /*/
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
