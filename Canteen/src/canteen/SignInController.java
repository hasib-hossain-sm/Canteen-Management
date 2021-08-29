/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteen;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class SignInController implements Initializable {

    @FXML
    private TextField textbox;
    @FXML
    private TextField textbox2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void signInPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        boolean s = false;
            String username1 = "root";
            String password1 = "";
            String url = "jdbc:mysql://localhost/projectile";
            ///project already connected
           String query="SELECT * FROM `info`;";
           // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

            Class.forName("com.mysql.jdbc.Driver");
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username1, password1)) {
                statement = (Statement) connection.createStatement();
               // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
                ResultSet result;
                result=statement.executeQuery(query);
                  while(result.next())
            {// result.next();
                
            //String name=result.getString("id");
      if(textbox.getText().equals(result.getString("username"))&&textbox2.getText().equals(result.getString("password")))
                    {
                        s=true;
                        break;
                    }
              //  System.out.println("id is "+result.getInt("password")+" name is "+result.getString("username"));
                //   System.out.println("id is "+result.getString("name"));
                
            }
                  
               statement.close();
        
        
        if (s==false) {
            String title = "Sorry ";
            String message = "Username Or Password not found.";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(4000));
        }else {
            String title = "Congratulations ";
            String message = "You've successfully Signed In";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.FADE;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(4000));

            Parent home_page_parent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        }
            }
    }

}
