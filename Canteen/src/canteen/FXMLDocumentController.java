/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteen;

import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author USER
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private VBox vbox;
    
    private  Parent fxml;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(vbox.getLayoutX()*20);
        t.play();
        t.setOnFinished((e)->{
            try {
                fxml= FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    

    @FXML
    private void open_signup(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(14);
        t.play();
        t.setOnFinished((e)->{
            try {
                fxml= FXMLLoader.load(getClass().getResource("SignUp.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void open_signin(ActionEvent event) {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1),vbox);
        t.setToX(vbox.getLayoutX()*20);
        t.play();
        t.setOnFinished((e)->{
            try {
                fxml= FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    
}
