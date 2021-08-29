/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 *
 * @author USER
 */
public class MainPageController implements Initializable {

    @FXML
    private ImageView drawerimage;
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private AnchorPane pane3;
    @FXML
    private AnchorPane pane4;
    @FXML
    private AnchorPane opacityPane;
    @FXML
    private AnchorPane drawerPane;
    @FXML
    private AnchorPane mypane;
    @FXML
    private JFXButton btn_lunch;
    @FXML
    private ImageView img_lunch;
    @FXML
    private JFXButton btn_breakfast;
    @FXML
    private ImageView img_breakfast;
    @FXML
    private JFXButton btn_snacks;
    @FXML
    private ImageView img_snacks;
    @FXML
    private JFXButton btn_dessert;
    @FXML
    private ImageView img_dessert;
    @FXML
    private JFXButton btn_bevarage;
    @FXML
    private ImageView img_bevarage;
    @FXML
    private AnchorPane anchor_pane_lunch;
    @FXML
    private AnchorPane anchor_pane_dessert;
    @FXML
    private AnchorPane anchor_pane_breakfast;
    @FXML
    private AnchorPane anchor_pane_snacks;
    @FXML
    private AnchorPane anchor_pane_beverage;
    @FXML
    private AnchorPane anchor_pane_main_page;
    @FXML
    private TreeTableColumn<Model, String> column_food_table_lunch;
    @FXML
    private TreeTableColumn<Model, String> column_price_table_lunch;
    @FXML
    private TreeTableColumn<Model, String> column_avail_table_lunch;
    @FXML
    private TreeTableColumn<Model, String> column_quantity_table_lunch;
    @FXML
    private TreeTableView<Model> table_lunch;

    @FXML
    private TreeTableView<Model> table_beverage;
    @FXML
    private TreeTableColumn<Model, String> column_food_table_beverage;
    @FXML
    private TreeTableColumn<Model, String> column_price_table_beverage;
    @FXML
    private TreeTableColumn<Model, String> column_avail_table_beverage;
    @FXML
    private TreeTableColumn<Model, String> column_quantity_table_beverage;
    @FXML
    private TreeTableView<Model> table_breakfast;
    @FXML
    private TreeTableColumn<Model, String> column_food_table_breakfast;
    @FXML
    private TreeTableColumn<Model, String> column_price_table_breakfast;
    @FXML
    private TreeTableColumn<Model, String> column_avail_table_breakfast;
    @FXML
    private TreeTableColumn<Model, String> column_quantity_table_breakfast;
    @FXML
    private TreeTableView<Model> table_dessert;
    @FXML
    private TreeTableColumn<Model, String> column_food_table_dessert;
    @FXML
    private TreeTableColumn<Model, String> column_price_table_dessert;
    @FXML
    private TreeTableColumn<Model, String> column_avail_table_dessert;
    @FXML
    private TreeTableColumn<Model, String> column_quantity_table_dessert;
    @FXML
    private TreeTableView<Model> table_snacks;
    @FXML
    private TreeTableColumn<Model, String> column_food_table_snacks;
    @FXML
    private TreeTableColumn<Model, String> column_price_table_snacks;
    @FXML
    private TreeTableColumn<Model, String> column_avail_table_snacks;
    @FXML
    private TreeTableColumn<Model, String> column_quantity_table_snacks;

    ObservableList<Model> list_lunch, list_breakfast, list_dessert, list_snacks, list_beverage, list_cart;
    @FXML
    private AnchorPane anchor_pane_cart;
    @FXML
    private TreeTableView<Model> table_cart;
    @FXML
    private TreeTableColumn<Model, String> column_food_table_cart;
    @FXML
    private TreeTableColumn<Model, String> column_price_table_cart;
    @FXML
    private TreeTableColumn<Model, String> column_avail_table_cart;
    @FXML
    private TreeTableColumn<Model, String> column_quantity_table_cart;
    @FXML
    private ImageView image_cart;
    @FXML
    private AnchorPane order_status_pane;
    @FXML
    private Label label_order_status;
    @FXML
    private AnchorPane about_us_pane;
    @FXML
    private Label label_total_cart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        making_tables();
        String username12 = "root";
        String password12 = "";
        String urle = "jdbc:mysql://localhost/projectile";
        ///project already connected
        String query12 = "SELECT * FROM `lunch`;";
        //  String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement statement;
        try (Connection connectione = (Connection) DriverManager.getConnection(urle, username12, password12)) {
            statement = (Statement) connectione.createStatement();
            //  statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
            ResultSet result = null;

            result = statement.executeQuery(query12);
            while (result.next()) {// result.next();

                //String name=result.getString("id");
                if (result.getString("avail").equals("AVAILABLE")) {
                    list_lunch.addAll(new Model(result.getString("food"), result.getString("price"), result.getString("avail"), result.getString("total")));
                }
                // System.out.println("id is "+result.getString("food")+" name is "+result.getString("price"));
                //   System.out.println("id is "+result.getString("name"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        username12 = "root";
        password12 = "";
        urle = "jdbc:mysql://localhost/projectile";
        ///project already connected
        query12 = "SELECT * FROM `breakfast`;";
        //  String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement statement1;
        try (Connection connectione = (Connection) DriverManager.getConnection(urle, username12, password12)) {
            statement1 = (Statement) connectione.createStatement();
            //  statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
            ResultSet result = null;

            result = statement1.executeQuery(query12);
            while (result.next()) {// result.next();

                //String name=result.getString("id");
                if (result.getString("avail").equals("AVAILABLE")) {
                    list_breakfast.addAll(new Model(result.getString("food"), result.getString("price"), result.getString("avail"), result.getString("total")));
                }
                // System.out.println("id is "+result.getString("food")+" name is "+result.getString("price"));
                //   System.out.println("id is "+result.getString("name"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // list_breakfast.addAll(new Model("r", "30", "Available", "0"));
        username12 = "root";
        password12 = "";
        urle = "jdbc:mysql://localhost/projectile";
        ///project already connected
        query12 = "SELECT * FROM `dessert`;";
        //  String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement statement2;
        try (Connection connectione = (Connection) DriverManager.getConnection(urle, username12, password12)) {
            statement2 = (Statement) connectione.createStatement();
            //  statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
            ResultSet result = null;

            result = statement2.executeQuery(query12);
            while (result.next()) {// result.next();

                //String name=result.getString("id");
                if (result.getString("avail").equals("AVAILABLE")) {
                    list_dessert.addAll(new Model(result.getString("food"), result.getString("price"), result.getString("avail"), result.getString("total")));
                }
                // System.out.println("id is "+result.getString("food")+" name is "+result.getString("price"));
                //   System.out.println("id is "+result.getString("name"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //  list_dessert.addAll(new Model("o", "30", "Available", "0"));
        query12 = "SELECT * FROM `snaks`;";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement statement22;
        try (Connection connectione = (Connection) DriverManager.getConnection(urle, username12, password12)) {
            statement22 = (Statement) connectione.createStatement();
            //  statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
            ResultSet result = null;

            result = statement22.executeQuery(query12);
            while (result.next()) {// result.next();

                //String name=result.getString("id");
                if (result.getString("avail").equals("AVAILABLE")) {
                    list_snacks.addAll(new Model(result.getString("food"), result.getString("price"), result.getString("avail"), result.getString("total")));
                }
                // System.out.println("id is "+result.getString("food")+" name is "+result.getString("price"));
                //   System.out.println("id is "+result.getString("name"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //  list_snacks.addAll(new Model("v", "30", "Available", "0"));

        query12 = "SELECT * FROM `beverage`;";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement statement21;
        try (Connection connectione = (Connection) DriverManager.getConnection(urle, username12, password12)) {
            statement21 = (Statement) connectione.createStatement();
            //  statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
            ResultSet result = null;

            result = statement21.executeQuery(query12);
            while (result.next()) {// result.next();

                //String name=result.getString("id");
                if (result.getString("avail").equals("AVAILABLE")) {
                    list_beverage.addAll(new Model(result.getString("food"), result.getString("price"), result.getString("avail"), result.getString("total")));
                }
                // System.out.println("id is "+result.getString("food")+" name is "+result.getString("price"));
                //   System.out.println("id is "+result.getString("name"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //   list_beverage.addAll(new Model("cc", "30", "Available", "0"));
        opacityPane.setVisible(false);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), opacityPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), drawerPane);
        translateTransition.setByX(-600);
        translateTransition.play();

        //pane1.setStyle("-fx-background-image: url(\"/home/page/1.jpg\")");
        //pane2.setStyle("-fx-background-image: url(\"/home/page/2.jpg\")");
        //pane3.setStyle("-fx-background-image: url(\"/home/page/3.jpg\")");
        //pane4.setStyle("-fx-background-image: url(\"/home/page/4.jpg\")");
        Animation();

        img_lunch.setOnMouseClicked(event -> {
            //System.out.println("lunch image clicked.");
            anchor_pane_lunch.toFront();
        });
        img_dessert.setOnMouseClicked(event -> {
            //System.out.println("lunch image clicked.");
            anchor_pane_dessert.toFront();
        });
        img_breakfast.setOnMouseClicked(event -> {
            //System.out.println("lunch image clicked.");
            anchor_pane_breakfast.toFront();
        });
        img_snacks.setOnMouseClicked(event -> {
            //System.out.println("lunch image clicked.");
            anchor_pane_snacks.toFront();
        });
        img_bevarage.setOnMouseClicked(event -> {
            //System.out.println("lunch image clicked.");
            anchor_pane_beverage.toFront();
        });
        image_cart.setOnMouseClicked(event -> {
            //System.out.println("lunch image clicked.");
            show_total();
            anchor_pane_cart.toFront();
            //System.out.println(""+list_lunch.size());
            //for(int i=0;i<list_lunch.size();i++){
            //System.out.println(column_food_table_lunch.getCellData(i));
            //}
        });

        drawerimage.setOnMouseClicked(event -> {

            opacityPane.toFront();
            drawerPane.toFront();

            opacityPane.setVisible(true);
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), opacityPane);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), drawerPane);
            translateTransition1.setByX(+600);
            translateTransition1.play();
        });
        opacityPane.setOnMouseClicked(event -> {

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), opacityPane);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                opacityPane.setVisible(false);
            });

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), drawerPane);
            translateTransition1.setByX(-600);
            translateTransition1.play();
        });
    }

    public void Animation() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), pane4);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        fadeTransition.setOnFinished(event -> {
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(3), pane3);
            fadeTransition1.setFromValue(1);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(3), pane2);
                fadeTransition2.setFromValue(1);
                fadeTransition2.setToValue(0);
                fadeTransition2.play();

                fadeTransition2.setOnFinished(event2 -> {
                    FadeTransition fadeTransition3 = new FadeTransition(Duration.seconds(3), pane3);
                    fadeTransition3.setFromValue(0);
                    fadeTransition3.setToValue(1);
                    fadeTransition3.play();

                    fadeTransition3.setOnFinished(event3 -> {
                        FadeTransition fadeTransition4 = new FadeTransition(Duration.seconds(3), pane4);
                        fadeTransition4.setFromValue(0);
                        fadeTransition4.setToValue(1);
                        fadeTransition4.play();

                        fadeTransition4.setOnFinished(event5 -> {
                            Animation();
                        });
                    });
                });
            });
        });
    }
    public void show_total(){
        int sum=0;
        for (int i = 0; i < list_cart.size(); i++) {
            int a=Integer.parseInt(column_avail_table_cart.getCellData(i));
            sum+=a;
        }
        label_total_cart.setText("TOTAL :  "+sum);
        
    }
    public void making_tables() {

        //lunch:
        column_food_table_lunch.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().food;
            }
        });
        column_price_table_lunch.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().price;
            }
        });
        column_avail_table_lunch.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().avail;
            }
        });
        column_quantity_table_lunch.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().quantity;
            }
        });

        list_lunch = FXCollections.observableArrayList();

        TreeItem<Model> root_lunch = new RecursiveTreeItem<Model>(list_lunch, RecursiveTreeObject::getChildren);
        table_lunch.setRoot(root_lunch);
        table_lunch.setShowRoot(false);

        //breakfast:
        column_food_table_breakfast.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().food;
            }
        });
        column_price_table_breakfast.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().price;
            }
        });
        column_avail_table_breakfast.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().avail;
            }
        });
        column_quantity_table_breakfast.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().quantity;
            }
        });

        list_breakfast = FXCollections.observableArrayList();

        TreeItem<Model> root_breakfast = new RecursiveTreeItem<Model>(list_breakfast, RecursiveTreeObject::getChildren);
        table_breakfast.setRoot(root_breakfast);
        table_breakfast.setShowRoot(false);

        //beverage:
        column_food_table_beverage.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().food;
            }
        });
        column_price_table_beverage.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().price;
            }
        });
        column_avail_table_beverage.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().avail;
            }
        });
        column_quantity_table_beverage.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().quantity;
            }
        });

        list_beverage = FXCollections.observableArrayList();

        TreeItem<Model> root_beverage = new RecursiveTreeItem<Model>(list_beverage, RecursiveTreeObject::getChildren);
        table_beverage.setRoot(root_beverage);
        table_beverage.setShowRoot(false);

        //dessert:
        column_food_table_dessert.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().food;
            }
        });
        column_price_table_dessert.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().price;
            }
        });
        column_avail_table_dessert.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().avail;
            }
        });
        column_quantity_table_dessert.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().quantity;
            }
        });

        list_dessert = FXCollections.observableArrayList();

        TreeItem<Model> root_dessert = new RecursiveTreeItem<Model>(list_dessert, RecursiveTreeObject::getChildren);
        table_dessert.setRoot(root_dessert);
        table_dessert.setShowRoot(false);

        //snacks:
        column_food_table_snacks.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().food;
            }
        });
        column_price_table_snacks.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().price;
            }
        });
        column_avail_table_snacks.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().avail;
            }
        });
        column_quantity_table_snacks.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().quantity;
            }
        });

        list_snacks = FXCollections.observableArrayList();

        TreeItem<Model> root_snacks = new RecursiveTreeItem<Model>(list_snacks, RecursiveTreeObject::getChildren);
        table_snacks.setRoot(root_snacks);
        table_snacks.setShowRoot(false);

        //cart:
        column_food_table_cart.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().food;
            }
        });
        column_price_table_cart.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().price;
            }
        });
        column_avail_table_cart.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().avail;
            }
        });
        column_quantity_table_cart.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().quantity;
            }
        });

        list_cart = FXCollections.observableArrayList();

        TreeItem<Model> root_cart = new RecursiveTreeItem<Model>(list_cart, RecursiveTreeObject::getChildren);
        table_cart.setRoot(root_cart);
        table_cart.setShowRoot(false);

    }

    @FXML
    private void drawer_btn_1_pressed(ActionEvent event) {
        //System.out.println("drawer btn 1 pressed.");
        anchor_pane_main_page.toFront();
        opacityPane.toFront();
        drawerPane.toFront();

    }

    private void drawer_btn_7_pressed(ActionEvent event) throws ClassNotFoundException, SQLException {
        String username = "root";
        String password = "";
        int x = 0;
        String url = "jdbc:mysql://localhost/projectile";
        ///project already connected
        String query = "SELECT * FROM `order_id`;";
        Class.forName("com.mysql.jdbc.Driver");
        Statement statement;
        try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
            statement = (Statement) connection.createStatement();
            //  statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
            ResultSet result;
            result = statement.executeQuery(query);
            while (result.next()) {// result.next();
                //String name=result.getString("id");
                x = result.getInt("Id");
                x++;
                System.out.println(x + "");
            }
            query = "UPDATE `order_id` SET `Id`='" + Integer.toString(x) + "' WHERE 1";
            url = "jdbc:mysql://localhost/projectile";
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection3 = (Connection) DriverManager.getConnection(url, username, password)) {
                statement = (Statement) connection3.createStatement();
                statement.executeUpdate(query);
                // ResultSet result3;
                //  result = statement.executeQuery(query);

                //UPDATE `order_id` SET `Id`='' WHERE 1
            }
            for (int i = 0; i < list_cart.size(); i++) {
                String food1, ppu1, orderqua1, total1, Id1;
                Id1 = Integer.toString(x);
                food1 = column_food_table_cart.getCellData(i);
                ppu1 = column_price_table_cart.getCellData(i);
                total1 = column_avail_table_cart.getCellData(i);   //here avail == total price
                orderqua1 = column_quantity_table_cart.getCellData(i);

                System.out.print(food1 + " ");
                System.out.print(ppu1 + " ");
                System.out.print(column_avail_table_cart.getCellData(i) + "  ");
                System.out.print(column_quantity_table_cart.getCellData(i) + "  ");
                String url2 = "jdbc:mysql://localhost/projectile";
                String username2 = "root";
                String password2 = "";
                ///project already connected
                String query1 = "INSERT INTO `cart_table`(`Id`, `food`, `ppu`, `orderqua`, `total`) VALUES ('" + Id1 + "','" + food1 + "','" + ppu1 + "','" + orderqua1 + "','" + total1 + "');";
                //String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

                Class.forName("com.mysql.jdbc.Driver");
                Statement statement1;
                try (Connection connection1 = (Connection) DriverManager.getConnection(url2, username2, password2)) {
                    statement1 = (Statement) connection1.createStatement();
                    statement1.executeUpdate(query1);
                    //  ResultSet result1;
                    // result1 = statement1.executeQuery(query1);

                }

                //query = "INSERT INTO `cart_table`(`Id`, `food`, `ppu`, `orderqua`, `total`) VALUES ('Id','food','ppu','orderqua','total');";
                /*INSERT INTO `cart_table`(`Id`, `food`, `ppu`, `orderqua`, `total`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5])*/
            }

        }
    }

    @FXML
    private void btn_lunch_pressed(ActionEvent event) {
    }

    @FXML
    private void btn_breakfast_pressed(ActionEvent event) {
    }

    @FXML
    private void btn_snacks_pressed(ActionEvent event) {
    }

    @FXML
    private void btn_dessert_pressed(ActionEvent event) {
    }

    @FXML
    private void btn_bevarage_pressed(ActionEvent event) {
    }

    @FXML
    private void btn_add_lunch_pressed(ActionEvent event) {
        TreeItem<Model> treeItem = table_lunch.getSelectionModel().getSelectedItem();
        int t = Integer.parseInt(treeItem.getValue().getQuantity());
        t++;
        String s = Integer.toString(t);

        int k = Integer.parseInt(treeItem.getValue().getPrice());
        k *= t;

        Model m = new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), treeItem.getValue().getAvail(), s);
        treeItem.setValue(m);
        //System.out.println(list_cart.size()+"");
        for (int i = 0; i < list_cart.size(); i++) {
            if (column_food_table_cart.getCellData(i) == treeItem.getValue().getFood()) {
                list_cart.remove(i);

            }
        }
        if (t != 0) {
            list_cart.addAll(new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), Integer.toString(k), s));
        }
    }

    @FXML
    private void btn_remove_lunch_pressed(ActionEvent event) {
        TreeItem<Model> treeItem = table_lunch.getSelectionModel().getSelectedItem();
        int t = Integer.parseInt(treeItem.getValue().getQuantity());
        if (t > 0) {
            t--;
        }
        String s = Integer.toString(t);
        int k = Integer.parseInt(treeItem.getValue().getPrice());
        k *= t;
        Model m = new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), treeItem.getValue().getAvail(), s);
        treeItem.setValue(m);
        for (int i = 0; i < list_cart.size(); i++) {
            if (column_food_table_cart.getCellData(i) == treeItem.getValue().getFood()) {
                list_cart.remove(i);

            }
        }

        if (t != 0) {
            list_cart.addAll(new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), Integer.toString(k), s));
        }
    }

    @FXML
    private void btn_cart_lunch_pressed(ActionEvent event) {
        show_total();
        anchor_pane_cart.toFront();
    }

    @FXML
    private void btn_add_beverage_pressed(ActionEvent event) {
        TreeItem<Model> treeItem = table_beverage.getSelectionModel().getSelectedItem();
        int t = Integer.parseInt(treeItem.getValue().getQuantity());
        t++;
        String s = Integer.toString(t);
        int k = Integer.parseInt(treeItem.getValue().getPrice());
        k *= t;
        Model m = new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), treeItem.getValue().getAvail(), s);
        treeItem.setValue(m);
        //System.out.println(list_cart.size()+"");
        for (int i = 0; i < list_cart.size(); i++) {
            if (column_food_table_cart.getCellData(i) == treeItem.getValue().getFood()) {
                list_cart.remove(i);

            }
        }
        if (t != 0) {
            list_cart.addAll(new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), Integer.toString(k), s));
        }
    }

    @FXML
    private void btn_remove_beverage_pressed(ActionEvent event) {
        TreeItem<Model> treeItem = table_beverage.getSelectionModel().getSelectedItem();
        int t = Integer.parseInt(treeItem.getValue().getQuantity());
        if (t > 0) {
            t--;
        }
        String s = Integer.toString(t);
        int k = Integer.parseInt(treeItem.getValue().getPrice());
        k *= t;
        Model m = new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), treeItem.getValue().getAvail(), s);
        treeItem.setValue(m);
        for (int i = 0; i < list_cart.size(); i++) {
            if (column_food_table_cart.getCellData(i) == treeItem.getValue().getFood()) {
                list_cart.remove(i);

            }
        }

        if (t != 0) {
            list_cart.addAll(new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), Integer.toString(k), s));
        }
    }

    @FXML
    private void btn_cart_beverage_pressed(ActionEvent event) {
        show_total();
        anchor_pane_cart.toFront();
    }

    @FXML
    private void btn_add_breakfast_pressed(ActionEvent event) {
        TreeItem<Model> treeItem = table_breakfast.getSelectionModel().getSelectedItem();
        int t = Integer.parseInt(treeItem.getValue().getQuantity());
        t++;
        String s = Integer.toString(t);
        int k = Integer.parseInt(treeItem.getValue().getPrice());
        k *= t;
        Model m = new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), treeItem.getValue().getAvail(), s);
        treeItem.setValue(m);
        //System.out.println(list_cart.size()+"");
        for (int i = 0; i < list_cart.size(); i++) {
            if (column_food_table_cart.getCellData(i) == treeItem.getValue().getFood()) {
                list_cart.remove(i);

            }
        }
        if (t != 0) {
            list_cart.addAll(new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), Integer.toString(k), s));
        }
    }

    @FXML
    private void btn_remove_breakfast_pressed(ActionEvent event) {
        TreeItem<Model> treeItem = table_breakfast.getSelectionModel().getSelectedItem();
        int t = Integer.parseInt(treeItem.getValue().getQuantity());
        if (t > 0) {
            t--;
        }
        String s = Integer.toString(t);
        int k = Integer.parseInt(treeItem.getValue().getPrice());
        k *= t;
        Model m = new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), treeItem.getValue().getAvail(), s);
        treeItem.setValue(m);
        for (int i = 0; i < list_cart.size(); i++) {
            if (column_food_table_cart.getCellData(i) == treeItem.getValue().getFood()) {
                list_cart.remove(i);

            }
        }

        if (t != 0) {
            list_cart.addAll(new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), Integer.toString(k), s));
        }
    }

    @FXML
    private void btn_cart_breakfast_pressed(ActionEvent event) {
        show_total();
        anchor_pane_cart.toFront();
    }

    @FXML
    private void btn_add_dessert_pressed(ActionEvent event) {
        TreeItem<Model> treeItem = table_dessert.getSelectionModel().getSelectedItem();
        int t = Integer.parseInt(treeItem.getValue().getQuantity());
        t++;
        String s = Integer.toString(t);
        int k = Integer.parseInt(treeItem.getValue().getPrice());
        k *= t;
        Model m = new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), treeItem.getValue().getAvail(), s);
        treeItem.setValue(m);
        //System.out.println(list_cart.size()+"");
        for (int i = 0; i < list_cart.size(); i++) {
            if (column_food_table_cart.getCellData(i) == treeItem.getValue().getFood()) {
                list_cart.remove(i);

            }
        }
        if (t != 0) {
            list_cart.addAll(new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), Integer.toString(k), s));
        }
    }

    @FXML
    private void btn_remove_dessert_pressed(ActionEvent event) {
        TreeItem<Model> treeItem = table_dessert.getSelectionModel().getSelectedItem();
        int t = Integer.parseInt(treeItem.getValue().getQuantity());
        if (t > 0) {
            t--;
        }
        String s = Integer.toString(t);
        int k = Integer.parseInt(treeItem.getValue().getPrice());
        k *= t;
        Model m = new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), treeItem.getValue().getAvail(), s);
        treeItem.setValue(m);
        for (int i = 0; i < list_cart.size(); i++) {
            if (column_food_table_cart.getCellData(i) == treeItem.getValue().getFood()) {
                list_cart.remove(i);
            }
        }
        if (t != 0) {
            list_cart.addAll(new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), Integer.toString(k), s));
        }
    }

    @FXML
    private void btn_cart_dessert_pressed(ActionEvent event) {
        show_total();
        anchor_pane_cart.toFront();
    }

    @FXML
    private void btn_add_snacks_pressed(ActionEvent event) {
        TreeItem<Model> treeItem = table_snacks.getSelectionModel().getSelectedItem();
        int t = Integer.parseInt(treeItem.getValue().getQuantity());
        t++;
        String s = Integer.toString(t);
        int k = Integer.parseInt(treeItem.getValue().getPrice());
        k *= t;
        Model m = new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), treeItem.getValue().getAvail(), s);
        treeItem.setValue(m);
        //System.out.println(list_cart.size()+"");
        for (int i = 0; i < list_cart.size(); i++) {
            if (column_food_table_cart.getCellData(i) == treeItem.getValue().getFood()) {
                list_cart.remove(i);

            }
        }
        if (t != 0) {
            list_cart.addAll(new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), Integer.toString(k), s));
        }
    }

    @FXML
    private void btn_remove_snacks_pressed(ActionEvent event) {
        TreeItem<Model> treeItem = table_snacks.getSelectionModel().getSelectedItem();
        int t = Integer.parseInt(treeItem.getValue().getQuantity());
        if (t > 0) {
            t--;
        }
        String s = Integer.toString(t);
        int k = Integer.parseInt(treeItem.getValue().getPrice());
        k *= t;
        Model m = new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), treeItem.getValue().getAvail(), s);
        treeItem.setValue(m);
        for (int i = 0; i < list_cart.size(); i++) {
            if (column_food_table_cart.getCellData(i) == treeItem.getValue().getFood()) {
                list_cart.remove(i);

            }
        }

        if (t != 0) {
            list_cart.addAll(new Model(treeItem.getValue().getFood(), treeItem.getValue().getPrice(), Integer.toString(k), s));
        }
    }

    @FXML
    private void btn_cart_snacks_pressed(ActionEvent event) {
        show_total();
        anchor_pane_cart.toFront();
    }

    @FXML
    private void btn_confirm_table_cart(ActionEvent event) throws ClassNotFoundException, SQLException {
        String username = "root";
        String password = "";
        int x = 0;
        String url = "jdbc:mysql://localhost/projectile";
        ///project already connected
        String query = "SELECT * FROM `order_id`;";
        Class.forName("com.mysql.jdbc.Driver");
        Statement statement;
        try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
            statement = (Statement) connection.createStatement();
            //  statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
            ResultSet result;
            result = statement.executeQuery(query);
            while (result.next()) {// result.next();
                //String name=result.getString("id");
                x = result.getInt("Id");
                x++;
                System.out.println(x + "");
            }
            query = "UPDATE `order_id` SET `Id`='" + Integer.toString(x) + "' WHERE 1";
            url = "jdbc:mysql://localhost/projectile";
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection3 = (Connection) DriverManager.getConnection(url, username, password)) {
                statement = (Statement) connection3.createStatement();
                statement.executeUpdate(query);
                // ResultSet result3;
                //  result = statement.executeQuery(query);

                //UPDATE `order_id` SET `Id`='' WHERE 1
            }
            for (int i = 0; i < list_cart.size(); i++) {
                String food1, ppu1, orderqua1, total1, Id1;
                Id1 = Integer.toString(x);
                food1 = column_food_table_cart.getCellData(i);
                ppu1 = column_price_table_cart.getCellData(i);
                total1 = column_avail_table_cart.getCellData(i);   //here avail == total price
                orderqua1 = column_quantity_table_cart.getCellData(i);

                System.out.print(food1 + " ");
                System.out.print(ppu1 + " ");
                System.out.print(column_avail_table_cart.getCellData(i) + "  ");
                System.out.print(column_quantity_table_cart.getCellData(i) + "  ");
                String url2 = "jdbc:mysql://localhost/projectile";
                String username2 = "root";
                String password2 = "";
                ///project already connected
                String query1 = "INSERT INTO `cart_table`(`Id`, `food`, `ppu`, `orderqua`, `total`) VALUES ('" + Id1 + "','" + food1 + "','" + ppu1 + "','" + orderqua1 + "','" + total1 + "');";
                //String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

                Class.forName("com.mysql.jdbc.Driver");
                Statement statement1;
                try (Connection connection1 = (Connection) DriverManager.getConnection(url2, username2, password2)) {
                    statement1 = (Statement) connection1.createStatement();
                    statement1.executeUpdate(query1);
                    //  ResultSet result1;
                    // result1 = statement1.executeQuery(query1);

                }

                //query = "INSERT INTO `cart_table`(`Id`, `food`, `ppu`, `orderqua`, `total`) VALUES ('Id','food','ppu','orderqua','total');";
                /*INSERT INTO `cart_table`(`Id`, `food`, `ppu`, `orderqua`, `total`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5])*/
            }

        }
    }

    @FXML
    private void drawer_btn_order_status_pressed(ActionEvent event) {
        order_status_pane.toFront();
        String p = "3";
        if (p == "0") {
            label_order_status.setText("You Have To Confirm Your Order From Cart.");
        } else if (p == "1") {
            label_order_status.setText("Your Order Has Been Placed.");
        } else if (p == "2") {
            label_order_status.setText("Admin Has Accepted Your Order. The Food Is On The Way.");
        } else if (p == "3") {
            label_order_status.setText("Admin Has Cancelled Your Order. Sorry For The Inconvenience. Please Try Again.");
        }

    }

    @FXML
    private void drawer_btn_log_out_pressed(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        home_page_scene.setFill(Color.TRANSPARENT);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.initStyle(StageStyle.TRANSPARENT);
        app_stage.show();
    }

    @FXML
    private void drawer_btn_exit_pressed(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void drawer_btn_about_us_pressed(ActionEvent event) {
        about_us_pane.toFront();
    }

    @FXML
    private void btn_ok_order_status_pressed(ActionEvent event) {
        order_status_pane.toBack();
    }
}
