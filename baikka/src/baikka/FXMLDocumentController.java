package baikka;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import org.omg.CORBA.INITIALIZE;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TreeTableColumn<Model, String> column_salary_table_workers;

    @FXML
    private JFXTextField nameTF;

    @FXML
    private Label genderLB;

    @FXML
    private JFXTextField ageTF;

    @FXML
    private TreeTableColumn<Model, String> column_age_table_workers;

    @FXML
    private TreeTableColumn<Model, String> column_gender_table_workers;

    @FXML
    private JFXTextField searchTF;

    @FXML
    private JFXTreeTableView<Model> table_workers;

    @FXML
    private JFXComboBox<String> genderCombo;

    @FXML
    private Label ageLB;

    @FXML
    private JFXTextField salaryTF;

    @FXML
    private TreeTableColumn<Model, String> column_name_table_workers;

    @FXML
    private Label salaryLB;

    @FXML
    private Label nameLB;

    ObservableList<Model> list_workers;
    ObservableList<Model_Food_items> list_food_items;
    ObservableList<model_order_id> list_orders_id;
    ObservableList<model_order_details> list_orders_details;

    @FXML
    private AnchorPane anchor_pane_workers, anchor_pane_food_items;
    @FXML
    private JFXButton btn_food_items;
    @FXML
    private JFXButton btn_orders;
    @FXML
    private JFXButton btn_workers;
    @FXML
    private JFXTextField searchTF_food_items;
    @FXML
    private TreeTableColumn<Model_Food_items, String> column_name_table_food_items;
    @FXML
    private TreeTableColumn<Model_Food_items, String> column_price_table_food_items;
    @FXML
    private TreeTableColumn<Model_Food_items, String> column_avail_table_food_items;
    @FXML
    private TreeTableColumn<Model_Food_items, String> column_catagory_table_food_items;
    @FXML
    private JFXTreeTableView<Model_Food_items> table_food_items;
    @FXML
    private JFXTextField nameTF_food_items;
    @FXML
    private JFXTextField priceTF_food_items;
    @FXML
    private JFXComboBox<String> availCombo_food_items;
    @FXML
    private JFXComboBox<String> catagoryCombo_food_items;
    @FXML
    private Label nameLB_food_items;
    @FXML
    private Label priceLB_food_items;
    @FXML
    private Label availLB_food_items;
    @FXML
    private Label catagoryLB_food_items;
    @FXML
    private AnchorPane anchor_pane_orders;

    @FXML
    private JFXTreeTableView<model_order_details> table_orders_details;

    @FXML
    private TreeTableColumn<model_order_details, String> column_name_table_orders_details;

    @FXML
    private TreeTableColumn<model_order_details, String> column_ppu_table_orders_details;

    @FXML
    private TreeTableColumn<model_order_details, String> column_quantity_table_orders_details;

    @FXML
    private TreeTableColumn<model_order_details, String> column_price_table_orders_details;

    @FXML
    private JFXTreeTableView<model_order_id> table_orders_id;

    @FXML
    private TreeTableColumn<model_order_id, String> column_orders_id;

    @FXML
    private void btn_refreshed_pressed(ActionEvent event) {
        list_orders_id.clear();
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/projectile";
        ///project already connected
        String query = "SELECT * FROM `cart_table`;";
        // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement statement;
        try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
            statement = (Statement) connection.createStatement();
            // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
            ResultSet result;
            result = statement.executeQuery(query);
            String p = "";
            while (result.next()) {// result.next();

                //String name=result.getString("id");
                //System.out.println("id is "+result.getInt("Password")+" name is "+result.getString("Username"));
                //   System.out.println("id is "+result.getString("name"));
                if (result.getString("Id").equals(p)) {

                } else {
                    p = result.getString("Id");
                    list_orders_id.addAll(new model_order_id(p));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class TimerClassMine extends TimerTask {

        public void run() {
            list_orders_id.clear();
            String username = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/projectile";
            ///project already connected
            String query = "SELECT * FROM `cart_table`;";
            // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
                statement = (Statement) connection.createStatement();
                // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
                ResultSet result;
                result = statement.executeQuery(query);
                String p = "";
                while (result.next()) {// result.next();

                    //String name=result.getString("id");
                    //System.out.println("id is "+result.getInt("Password")+" name is "+result.getString("Username"));
                    //   System.out.println("id is "+result.getString("name"));
                    if (result.getString("Id").equals(p)) {

                    } else {
                        p = result.getString("Id");
                        list_orders_id.addAll(new model_order_id(p));
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public PageFormat getPageFormat(PrinterJob pj) {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double middleHeight = 30.0;
        double headerHeight = 2.0;
        double footerHeight = 2.0;
        double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
        double height = convert_CM_To_PPI(headerHeight + middleHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(
                0,
                10,
                width,
                height - convert_CM_To_PPI(1)
        );   //define boarder size    after that print area width is about 180 points

        pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
        pf.setPaper(paper);

        return pf;
    }

    protected static double convert_CM_To_PPI(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }

    public class BillPrintable implements Printable {

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException {

            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;

                double width = pageFormat.getImageableWidth();

                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                ////////// code by alqama//////////////
                FontMetrics metrics = g2d.getFontMetrics(new Font("Arial", Font.BOLD, 7));
                //    int idLength=metrics.stringWidth("000000");
                //int idLength=metrics.stringWidth("00");
                int idLength = metrics.stringWidth("000");
                int amtLength = metrics.stringWidth("000000");
                int qtyLength = metrics.stringWidth("00000");
                int priceLength = metrics.stringWidth("000000");
                int prodLength = (int) width - idLength - amtLength - qtyLength - priceLength - 17;

                //    int idPosition=0;
                //    int productPosition=idPosition + idLength + 2;
                //    int pricePosition=productPosition + prodLength +10;
                //    int qtyPosition=pricePosition + priceLength + 2;
                //    int amtPosition=qtyPosition + qtyLength + 2;
                int productPosition = 0;
                int discountPosition = prodLength + 5;
                int pricePosition = discountPosition + idLength + 10;
                int qtyPosition = pricePosition + priceLength + 4;
                int amtPosition = qtyPosition + qtyLength;

                try {
                    /*Draw Header*/
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 15;
                    int headerRectHeighta = 40;

                    ///////////////// Product names Get ///////////
                    //String pn1a = pn1.getText();
                    //String pn2a = pn2.getText();
                    //String pn3a = pn3.getText();
                    //String pn4a = pn4.getText();
                    ///////////////// Product names Get ///////////
                    ///////////////// Product price Get ///////////
                    //int pp1a = Integer.valueOf(pp1.getText());
                    //int pp2a = Integer.valueOf(pp2.getText());
                    //int pp3a = Integer.valueOf(pp3.getText());
                    //int pp4a = Integer.valueOf(pp4.getText());
                    //int sum = pp1a + pp2a + pp3a + pp4a;
                    ///////////////// Product price Get ///////////
                    LocalDate date = java.time.LocalDate.now();
                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                    Date date1 = new Date();

                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                    g2d.drawString("     =====================================", 12, y);
                    y += yShift;
                    g2d.drawString("              AUST CANTEEN             ", 12, y);
                    y += yShift;
                    g2d.drawString("     =====================================", 12, y);
                    y += headerRectHeight;

                    g2d.drawString("                                       ", 10, y);
                    y += yShift;
                    g2d.drawString("             141 & 142, Love Road,        ", 10, y);
                    y += yShift;
                    g2d.drawString("             Dhaka-1208,Bangladesh        ", 10, y);
                    y += yShift;
                    g2d.drawString("                     89119                ", 10, y);
                    y += yShift;
                    g2d.drawString("                 702-262-4852             ", 10, y);
                    y += yShift;
                    g2d.drawString("                                          ", 10, y);
                    y += yShift;
                    g2d.drawString("                         DATE:" + date, 10, y);
                    y += yShift;
                    g2d.drawString("                           TIME:" + formatter.format(date1), 10, y);
                    y += yShift;
                    g2d.drawString("                                          ", 10, y);
                    y += yShift;
                    g2d.drawString("     -------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString("         DESCRIPTION  QTY  Rate  AMOUNT ", 10, y);
                    y += yShift;
                    g2d.drawString("     -------------------------------------", 10, y);
                    y += yShift;
                    //PRINTING  items
                    String a, b, c, d;
                    int sum = 0, flag;
                    for (int i = 0; i < list_orders_details.size(); i++) {
                        a = String.format("%18s", column_name_table_orders_details.getCellData(i));
                        b = String.format("%5s", column_quantity_table_orders_details.getCellData(i));
                        c = String.format("%6s", column_ppu_table_orders_details.getCellData(i));
                        d = String.format("%8s", column_price_table_orders_details.getCellData(i));
                        g2d.drawString(a + b + c + d + "", 10, y);
                        y += yShift;
                        System.out.println(a + b + c + d + "");
                        flag = Integer.parseInt(column_price_table_orders_details.getCellData(i));
                        sum += flag;
                    }
                    String g = String.format("%10s", Integer.toString(sum));

                    g2d.drawString("     _____________________________________", 10, y);
                    y += yShift;
                    g2d.drawString("     Total :               " + g, 10, y);
                    y += yShift;
                    g2d.drawString("                                          ", 10, y);
                    y += yShift;
                    g2d.drawString("     Please write your complain here :    ", 10, y);
                    y += yShift;
                    g2d.drawString("     _____________________________________", 10, y);
                    y += yShift;
                    g2d.drawString("     _____________________________________", 10, y);
                    y += yShift;
                    g2d.drawString("     _____________________________________", 10, y);
                    y += yShift;
                    g2d.drawString("                                          ", 10, y);
                    y += yShift;
                    g2d.drawString("     Thank You For Choosing Our Service.  ", 10, y);
                    y += yShift;
                    g2d.drawString("     Please Check Your Food Infront Of    ", 10, y);
                    y += yShift;
                    g2d.drawString("     Our Delivery Guy.", 10, y);
                    y += yShift;

//            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
//            g2d.drawString("Customer Shopping Invoice", 30,y);y+=yShift; 
                } catch (Exception r) {
                    r.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }

    @Override

    public void initialize(URL location, ResourceBundle resources) {

        genderCombo.getItems().addAll("Male", "Female");
        availCombo_food_items.getItems().addAll("AVAILABLE", "NOT AVAILABLE");
        catagoryCombo_food_items.getItems().addAll("LUNCH", "BREAKFAST", "SNACKS", "BEVERAGE", "DESSERT");

        column_name_table_workers.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().name;
            }
        });

        column_age_table_workers.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().age;
            }
        });

        column_gender_table_workers.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().gender;
            }
        });

        column_salary_table_workers.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().salary;
            }
        });

        column_name_table_food_items.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model_Food_items, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model_Food_items, String> param) {
                return param.getValue().getValue().name;
            }
        });
        column_price_table_food_items.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model_Food_items, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model_Food_items, String> param) {
                return param.getValue().getValue().price;
            }
        });
        column_avail_table_food_items.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model_Food_items, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model_Food_items, String> param) {
                return param.getValue().getValue().avail;
            }
        });
        column_catagory_table_food_items.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model_Food_items, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model_Food_items, String> param) {
                return param.getValue().getValue().catagory;
            }
        });
        column_name_table_orders_details.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<model_order_details, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<model_order_details, String> param) {
                return param.getValue().getValue().name;
            }
        });
        column_ppu_table_orders_details.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<model_order_details, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<model_order_details, String> param) {
                return param.getValue().getValue().ppu;
            }
        });
        column_quantity_table_orders_details.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<model_order_details, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<model_order_details, String> param) {
                return param.getValue().getValue().quantity;
            }
        });
        column_price_table_orders_details.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<model_order_details, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<model_order_details, String> param) {
                return param.getValue().getValue().price;
            }
        });
        column_orders_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<model_order_id, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<model_order_id, String> param) {
                return param.getValue().getValue().id;
            }
        });

        list_workers = FXCollections.observableArrayList();
        list_food_items = FXCollections.observableArrayList();
        list_orders_details = FXCollections.observableArrayList();
        list_orders_id = FXCollections.observableArrayList();

        TreeItem<Model> root = new RecursiveTreeItem<Model>(list_workers, RecursiveTreeObject::getChildren);
        table_workers.setRoot(root);
        table_workers.setShowRoot(false);

        TreeItem<Model_Food_items> root_food_items = new RecursiveTreeItem<Model_Food_items>(list_food_items, RecursiveTreeObject::getChildren);
        table_food_items.setRoot(root_food_items);
        table_food_items.setShowRoot(false);

        TreeItem<model_order_details> root_order_details = new RecursiveTreeItem<model_order_details>(list_orders_details, RecursiveTreeObject::getChildren);
        table_orders_details.setRoot(root_order_details);
        table_orders_details.setShowRoot(false);

        TreeItem<model_order_id> root_order_id = new RecursiveTreeItem<model_order_id>(list_orders_id, RecursiveTreeObject::getChildren);
        table_orders_id.setRoot(root_order_id);
        table_orders_id.setShowRoot(false);

        list_workers.addAll(new Model("Jack", "42", "Male", "10000"));
        String username1 = "root";
        String password1 = "";
        String url = "jdbc:mysql://localhost/projectile";
        ///project already connected

        String query = "SELECT * FROM `breakfast`;";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement statement;
        try (Connection connection = (Connection) DriverManager.getConnection(url, username1, password1)) {
            statement = (Statement) connection.createStatement();
            //  statement.executeUpdate(query);
            ResultSet result;
            result = statement.executeQuery(query);
            while (result.next()) {// result.next();
                list_food_items.addAll(new Model_Food_items(result.getString("food"), result.getString("price"), result.getString("avail"), "BREAKFAST"));

            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        query = "SELECT * FROM `lunch`;";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // statement;
        try (Connection connection = (Connection) DriverManager.getConnection(url, username1, password1)) {
            statement = (Statement) connection.createStatement();
            //  statement.executeUpdate(query);
            ResultSet result;
            result = statement.executeQuery(query);
            while (result.next()) {// result.next();
                list_food_items.addAll(new Model_Food_items(result.getString("food"), result.getString("price"), result.getString("avail"), "LUNCH"));

            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        query = "SELECT * FROM `dessert`;";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // statement;
        try (Connection connection = (Connection) DriverManager.getConnection(url, username1, password1)) {
            statement = (Statement) connection.createStatement();
            //  statement.executeUpdate(query);
            ResultSet result;
            result = statement.executeQuery(query);
            while (result.next()) {// result.next();
                list_food_items.addAll(new Model_Food_items(result.getString("food"), result.getString("price"), result.getString("avail"), "DESSERT"));

            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //view worker info
        query = "SELECT * FROM `worker_info`;";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // statement;
        try (Connection connection = (Connection) DriverManager.getConnection(url, username1, password1)) {
            statement = (Statement) connection.createStatement();
            //  statement.executeUpdate(query);
            ResultSet result;
            result = statement.executeQuery(query);
            while (result.next()) {// result.next();
                // list_food_items.addAll(new Model_Food_items(result.getString("food"), result.getString("price"), result.getString("avail"), "DESSERT"));
                list_workers.addAll(new Model(result.getString("name"), result.getString("age"), result.getString("gender"), result.getString("salary")));
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        refresh_list_orders_id();

        searchTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                table_workers.setPredicate(new Predicate<TreeItem<Model>>() {
                    @Override
                    public boolean test(TreeItem<Model> modelTreeItem) {
                        return modelTreeItem.getValue().name.getValue().contains(newValue) | modelTreeItem.getValue().age.getValue().contains(newValue);
                    }
                });
            }
        });
        searchTF_food_items.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                table_food_items.setPredicate(new Predicate<TreeItem<Model_Food_items>>() {
                    @Override
                    public boolean test(TreeItem<Model_Food_items> modelTreeItem) {
                        return modelTreeItem.getValue().name.getValue().contains(newValue);
                    }
                });
            }
        });

        table_workers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showDetails(newValue);
        });
        table_food_items.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showDetails_food_items(newValue);
        });
        table_orders_id.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            list_orders_details.clear();
            String select = newValue.getValue().getId();
            System.out.println(select);
            String username = "root";
            String password = "";
            String url3 = "jdbc:mysql://localhost/projectile";
            ///project already connected
            String query3 = "SELECT * FROM `cart_table`;";
            // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement statement3;
            try (Connection connection = (Connection) DriverManager.getConnection(url3, username, password)) {
                statement3 = (Statement) connection.createStatement();
                // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
                ResultSet result;
                result = statement3.executeQuery(query3);
                while (result.next()) {// result.next();

                    //String name=result.getString("id");
                    //System.out.println("id is "+result.getInt("Password")+" name is "+result.getString("Username"));
                    //   System.out.println("id is "+result.getString("name"));
                    if (result.getString("Id").equals(select)) {
                        //System.out.println("id " + result.getString("Id") + "food " + result.getString("food"));
                        list_orders_details.addAll(new model_order_details(result.getString("food"), result.getString("ppu"), result.getString("orderqua"), result.getString("total")));
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

    @FXML
    void addAction(ActionEvent event) throws ClassNotFoundException, SQLException {

        list_workers.addAll(new Model(nameTF.getText(), ageTF.getText(), genderCombo.getSelectionModel().getSelectedItem(), salaryTF.getText()));

        String username1 = "root";
        String password1 = "";
        String url = "jdbc:mysql://localhost/projectile";
        ///project already connected
        //String query = "SELECT * FROM `worker_list`;";
        String query = "INSERT INTO `worker_info` (`name`, `age`, `gender`, `salary`) VALUES ('" + nameTF.getText() + "', '" + ageTF.getText() + "','" + genderCombo.getSelectionModel().getSelectedItem() + "', '" + salaryTF.getText() + "');";
        /*INSERT INTO `worker_info`(`name`, `age`, `gender`, `salary`) VALUES */

        Class.forName("com.mysql.jdbc.Driver");
        Statement statement;
        try (Connection connection = (Connection) DriverManager.getConnection(url, username1, password1)) {
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            ResultSet result;

            statement.close();
        }

    }

    @FXML
    void deleteAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        int index = table_workers.getSelectionModel().getSelectedIndex();
        //DELETE FROM `worker_info` WHERE name='jackma'
        String d = column_name_table_workers.getCellData(index);
        String username1 = "root";
        String password1 = "";
        String url = "jdbc:mysql://localhost/projectile";
        ///project already connected
        //String query = "SELECT * FROM `worker_list`;";
        String query = "DELETE FROM `worker_info` WHERE name='" + d + "'";
        /*INSERT INTO `worker_info`(`name`, `age`, `gender`, `salary`) VALUES */

        Class.forName("com.mysql.jdbc.Driver");
        Statement statement;
        try (Connection connection = (Connection) DriverManager.getConnection(url, username1, password1)) {
            statement = (Statement) connection.createStatement();
            statement.executeUpdate(query);
            ResultSet result;

            statement.close();
        }

        list_workers.remove(index);
        clearFields();
    }

    public void showDetails(TreeItem<Model> treeItem) {

        nameTF.setText(treeItem.getValue().getName());
        nameLB.setText(treeItem.getValue().getName());

        ageTF.setText(treeItem.getValue().getAge());
        ageLB.setText(treeItem.getValue().getAge());

        genderCombo.getSelectionModel().select(treeItem.getValue().getGender());
        genderLB.setText(treeItem.getValue().getGender());

        salaryTF.setText(treeItem.getValue().getSalary());
        salaryLB.setText(treeItem.getValue().getSalary());

    }

    public void refresh_list_orders_id() {
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/projectile";
        ///project already connected
        String query = "SELECT * FROM `cart_table`;";
        // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement statement;
        try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
            statement = (Statement) connection.createStatement();
            // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
            ResultSet result;
            result = statement.executeQuery(query);
            String p = "";
            while (result.next()) {// result.next();

                //String name=result.getString("id");
                //System.out.println("id is "+result.getInt("Password")+" name is "+result.getString("Username"));
                //   System.out.println("id is "+result.getString("name"));
                if (result.getString("Id").equals(p)) {

                } else {
                    p = result.getString("Id");
                    list_orders_id.addAll(new model_order_id(p));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showDetails_food_items(TreeItem<Model_Food_items> treeItem) {
        nameTF_food_items.setText(treeItem.getValue().getName());
        nameLB_food_items.setText(treeItem.getValue().getName());

        priceTF_food_items.setText(treeItem.getValue().getPrice());
        priceLB_food_items.setText(treeItem.getValue().getPrice());

        availCombo_food_items.getSelectionModel().select(treeItem.getValue().getAvail());
        availLB_food_items.setText(treeItem.getValue().getAvail());

        catagoryCombo_food_items.getSelectionModel().select(treeItem.getValue().getCatagory());
        catagoryLB_food_items.setText(treeItem.getValue().getCatagory());

    }

    public void clearFields() {
        nameTF.setText("");
        nameLB.setText("");
        ageTF.setText("");
        ageLB.setText("");
        genderLB.setText("");
        genderCombo.getSelectionModel().select("");
        salaryTF.setText("");
        salaryLB.setText("");

    }

    @FXML
    void clearAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void editAction(ActionEvent event) {
        int index1 = table_workers.getSelectionModel().getSelectedIndex();
        String p1 = column_name_table_workers.getCellData(index1);
        TreeItem<Model> treeItem = table_workers.getSelectionModel().getSelectedItem();
        Model m = new Model(nameTF.getText(), ageTF.getText(), genderCombo.getSelectionModel().getSelectedItem(), salaryTF.getText());

        treeItem.setValue(m);
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/projectile";
        String x = nameTF_food_items.getText();
        // System.out.println("food "+x);
        //UPDATE `worker_info` SET `name`='sgaj',`age`='56',`gender`='Female',`salary`='1234' WHERE name='jackma'
        String query = "UPDATE `worker_info` SET `name`='" + nameTF.getText() + "',`age`='" + ageTF.getText() + "',`gender`='" + genderCombo.getSelectionModel().getSelectedItem() + "',`salary`='" + salaryTF.getText() + "' WHERE name='" + p1 + "'";
        // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement statement;
        try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
            statement = (Statement) connection.createStatement();
            // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
            ResultSet result;
            statement.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btn_food_items_pressed(ActionEvent event) {
        anchor_pane_food_items.toFront();
    }

    @FXML
    private void btn_orders_pressed(ActionEvent event) {
        anchor_pane_orders.toFront();
    }

    @FXML
    private void btn_workers_pressed(ActionEvent event) {
        anchor_pane_workers.toFront();
    }

    @FXML
    private void addAction_food_items(ActionEvent event) throws ClassNotFoundException, SQLException {
        list_food_items.addAll(new Model_Food_items(nameTF_food_items.getText(), priceTF_food_items.getText(), availCombo_food_items.getSelectionModel().getSelectedItem(), catagoryCombo_food_items.getSelectionModel().getSelectedItem()));
        String ui = catagoryCombo_food_items.getSelectionModel().getSelectedItem();
        if (ui.equals("BREAKFAST")) {
            String username1 = "root";
            String password1 = "";
            String url = "jdbc:mysql://localhost/projectile";
            ///project already connected
            //String query = "SELECT * FROM `worker_list`;";
            String query = "INSERT INTO `breakfast`(`food`, `price`, `avail`, `total`) VALUES ('" + nameTF_food_items.getText() + "', '" + priceTF_food_items.getText() + "','" + availCombo_food_items.getSelectionModel().getSelectedItem() + "','" + "0" + "' );";
            /*INSERT INTO `worker_info`(`name`, `age`, `gender`, `salary`) VALUES */

            Class.forName("com.mysql.jdbc.Driver");
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username1, password1)) {
                statement = (Statement) connection.createStatement();
                statement.executeUpdate(query);
                ResultSet result;
                statement.close();
            }
        } else if (ui.equals("LUNCH")) {

            String username1 = "root";
            String password1 = "";
            String url = "jdbc:mysql://localhost/projectile";
            ///project already connected
            //String query = "SELECT * FROM `worker_list`;";
            String query = "INSERT INTO `lunch`(`food`, `price`, `avail`, `total`) VALUES ('" + nameTF_food_items.getText() + "', '" + priceTF_food_items.getText() + "','" + availCombo_food_items.getSelectionModel().getSelectedItem() + "','" + "0" + "' );";
            /*INSERT INTO `worker_info`(`name`, `age`, `gender`, `salary`) VALUES */

            Class.forName("com.mysql.jdbc.Driver");
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username1, password1)) {
                statement = (Statement) connection.createStatement();
                statement.executeUpdate(query);
                ResultSet result;
                statement.close();
            }

        } else if (ui.equals("BEVERAGE")) {

            String username1 = "root";
            String password1 = "";
            String url = "jdbc:mysql://localhost/projectile";
            ///project already connected
            //String query = "SELECT * FROM `worker_list`;";
            String query = "INSERT INTO `beverage`(`food`, `price`, `avail`, `total`) VALUES ('" + nameTF_food_items.getText() + "', '" + priceTF_food_items.getText() + "','" + availCombo_food_items.getSelectionModel().getSelectedItem() + "','" + "0" + "' );";
            /*INSERT INTO `worker_info`(`name`, `age`, `gender`, `salary`) VALUES */

            Class.forName("com.mysql.jdbc.Driver");
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username1, password1)) {
                statement = (Statement) connection.createStatement();
                statement.executeUpdate(query);
                ResultSet result;
                statement.close();
            }

        } else if (ui.equals("DESSERT")) {

            String username1 = "root";
            String password1 = "";
            String url = "jdbc:mysql://localhost/projectile";
            ///project already connected
            //String query = "SELECT * FROM `worker_list`;";
            String query = "INSERT INTO `dessert`(`food`, `price`, `avail`, `total`) VALUES ('" + nameTF_food_items.getText() + "', '" + priceTF_food_items.getText() + "','" + availCombo_food_items.getSelectionModel().getSelectedItem() + "','" + "0" + "' );";
            /*INSERT INTO `worker_info`(`name`, `age`, `gender`, `salary`) VALUES */

            Class.forName("com.mysql.jdbc.Driver");
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username1, password1)) {
                statement = (Statement) connection.createStatement();
                statement.executeUpdate(query);
                ResultSet result;
                statement.close();
            }

        } else if (ui.equals("SNACKS")) {

            String username1 = "root";
            String password1 = "";
            String url = "jdbc:mysql://localhost/projectile";
            ///project already connected
            //String query = "SELECT * FROM `worker_list`;";
            String query = "INSERT INTO `snaks`(`food`, `price`, `avail`, `total`) VALUES ('" + nameTF_food_items.getText() + "', '" + priceTF_food_items.getText() + "','" + availCombo_food_items.getSelectionModel().getSelectedItem() + "','" + "0" + "' );";
            /*INSERT INTO `worker_info`(`name`, `age`, `gender`, `salary`) VALUES */

            Class.forName("com.mysql.jdbc.Driver");
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username1, password1)) {
                statement = (Statement) connection.createStatement();
                statement.executeUpdate(query);
                ResultSet result;
                statement.close();
            }

        }
    }

    @FXML
    private void deleteAction_food_items(ActionEvent event) {
        int index = table_food_items.getSelectionModel().getSelectedIndex();
        String h = catagoryCombo_food_items.getSelectionModel().getSelectedItem();
        if (h.equals("BREAKFAST")) {
            String p = column_name_table_food_items.getCellData(index);
            System.out.println(p);
            String username = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/projectile";
            ///project already connected
            String query = "DELETE FROM `breakfast` WHERE food='" + p + "';";
            // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
                statement = (Statement) connection.createStatement();
                // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
                ResultSet result;
                statement.executeUpdate(query);

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            list_food_items.remove(index);

            clearFields();
        }
        if (h.equals("LUNCH")) {
            String p = column_name_table_food_items.getCellData(index);
            System.out.println(p);
            String username = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/projectile";
            ///project already connected
            String query = "DELETE FROM `lunch` WHERE food='" + p + "';";
            // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
                statement = (Statement) connection.createStatement();
                // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
                ResultSet result;
                statement.executeUpdate(query);

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            list_food_items.remove(index);

            clearFields();
        } else if (h.equals("DESSERT")) {
            String p = column_name_table_food_items.getCellData(index);
            System.out.println(p);
            String username = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/projectile";
            ///project already connected
            String query = "DELETE FROM `dessert` WHERE food='" + p + "';";
            // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
                statement = (Statement) connection.createStatement();
                // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
                ResultSet result;
                statement.executeUpdate(query);

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            list_food_items.remove(index);

            clearFields();
        } else if (h.equals("BEVERAGE")) {
            String p = column_name_table_food_items.getCellData(index);
            System.out.println(p);
            String username = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/projectile";
            ///project already connected
            String query = "DELETE FROM `beverage` WHERE food='" + p + "';";
            // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
                statement = (Statement) connection.createStatement();
                // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
                ResultSet result;
                statement.executeUpdate(query);

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            list_food_items.remove(index);

            clearFields();
        } else if (h.equals("SNACKS")) {
            String p = column_name_table_food_items.getCellData(index);
            System.out.println(p);
            String username = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/projectile";
            ///project already connected
            String query = "DELETE FROM `snaks` WHERE food='" + p + "';";
            // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
                statement = (Statement) connection.createStatement();
                // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
                ResultSet result;
                statement.executeUpdate(query);

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            list_food_items.remove(index);

            clearFields();
        }

    }

    @FXML
    private void editAction_food_items(ActionEvent event) {
        int index1 = table_food_items.getSelectionModel().getSelectedIndex();
        String h1 = catagoryCombo_food_items.getSelectionModel().getSelectedItem();
        String p1 = column_name_table_food_items.getCellData(index1);
        System.out.println("food " + p1 + "h = " + h1);
        TreeItem<Model_Food_items> treeItem = table_food_items.getSelectionModel().getSelectedItem();

        Model_Food_items m = new Model_Food_items(nameTF_food_items.getText(), priceTF_food_items.getText(), availCombo_food_items.getSelectionModel().getSelectedItem(), catagoryCombo_food_items.getSelectionModel().getSelectedItem());
        treeItem.setValue(m);
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/projectile";
        if (h1.equals("BREAKFAST")) {
            String x = nameTF_food_items.getText();
            // System.out.println("food "+x);
            String query = "UPDATE `breakfast` SET `food`='" + nameTF_food_items.getText() + "',`price`='" + priceTF_food_items.getText() + "',`avail`='" + availCombo_food_items.getSelectionModel().getSelectedItem() + "',`total`='" + 0 + "' WHERE food='" + p1 + "'";
            // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
                statement = (Statement) connection.createStatement();
                // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
                ResultSet result;
                statement.executeUpdate(query);

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (h1.equals("LUNCH")) {
            //  String p = column_name_table_food_items.getCellData(index);

            ///project already connected
            //UPDATE `breakfast` SET `food`='jumuna',`price`='5',`avail`='Available',`total`='0' WHERE food='vaji'
            String x = nameTF_food_items.getText();
            // System.out.println("food "+x);
            String query = "UPDATE `lunch` SET `food`='" + nameTF_food_items.getText() + "',`price`='" + priceTF_food_items.getText() + "',`avail`='" + availCombo_food_items.getSelectionModel().getSelectedItem() + "',`total`='" + 0 + "' WHERE food='" + p1 + "'";
            // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
                statement = (Statement) connection.createStatement();
                // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
                ResultSet result;
                statement.executeUpdate(query);

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (h1.equals("DESSERT")) {
            //  String p = column_name_table_food_items.getCellData(index);

            ///project already connected
            //UPDATE `breakfast` SET `food`='jumuna',`price`='5',`avail`='Available',`total`='0' WHERE food='vaji'
            String x = nameTF_food_items.getText();
            // System.out.println("food "+x);
            String query = "UPDATE `dessert` SET `food`='" + nameTF_food_items.getText() + "',`price`='" + priceTF_food_items.getText() + "',`avail`='" + availCombo_food_items.getSelectionModel().getSelectedItem() + "',`total`='" + 0 + "' WHERE food='" + p1 + "'";
            // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
                statement = (Statement) connection.createStatement();
                // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
                ResultSet result;
                statement.executeUpdate(query);

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (h1.equals("SNACKS")) {
            //  String p = column_name_table_food_items.getCellData(index);

            ///project already connected
            //UPDATE `breakfast` SET `food`='jumuna',`price`='5',`avail`='Available',`total`='0' WHERE food='vaji'
            String x = nameTF_food_items.getText();
            // System.out.println("food "+x);
            String query = "UPDATE `snaks` SET `food`='" + nameTF_food_items.getText() + "',`price`='" + priceTF_food_items.getText() + "',`avail`='" + availCombo_food_items.getSelectionModel().getSelectedItem() + "',`total`='" + 0 + "' WHERE food='" + p1 + "'";
            // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
                statement = (Statement) connection.createStatement();
                // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
                ResultSet result;
                statement.executeUpdate(query);

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (h1.equals("BEVERAGE")) {
            //  String p = column_name_table_food_items.getCellData(index);

            ///project already connected
            //UPDATE `breakfast` SET `food`='jumuna',`price`='5',`avail`='Available',`total`='0' WHERE food='vaji'
            String x = nameTF_food_items.getText();
            // System.out.println("food "+x);
            String query = "UPDATE `beverage` SET `food`='" + nameTF_food_items.getText() + "',`price`='" + priceTF_food_items.getText() + "',`avail`='" + availCombo_food_items.getSelectionModel().getSelectedItem() + "',`total`='" + 0 + "' WHERE food='" + p1 + "'";
            // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Statement statement;
            try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
                statement = (Statement) connection.createStatement();
                // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
                ResultSet result;
                statement.executeUpdate(query);

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void clearAction_food_items(ActionEvent event) {
        nameTF_food_items.setText("");
        nameLB_food_items.setText("");

        priceTF_food_items.setText("");
        priceLB_food_items.setText("");

        availCombo_food_items.getSelectionModel().select("");
        availLB_food_items.setText("");

        catagoryCombo_food_items.getSelectionModel().select("");
        catagoryLB_food_items.setText("");
    }

    @FXML
    void btn_admin_accept_pressed(ActionEvent event) {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(), getPageFormat(pj));
        try {
            pj.print();

        } catch (PrinterException ex) {
            ex.printStackTrace();
        }

        //System.out.println(column_orders_id.getCellData(index));
    }

    @FXML
    void btn_admin_cancel_pressed(ActionEvent event) {
        int index = table_orders_id.getSelectionModel().getSelectedIndex();
        String p = column_orders_id.getCellData(index);
        System.out.println(p);
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/projectile";
        ///project already connected
        String query = "DELETE FROM `cart_table` WHERE Id='" + p + "';";
        // String query = "INSERT INTO `student` (`Username`, `Password`) VALUES ('tou', 'iop39');";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement statement;
        try (Connection connection = (Connection) DriverManager.getConnection(url, username, password)) {
            statement = (Statement) connection.createStatement();
            // statement.executeUpdate("insert into student values('" + textbox.getText() + "','" + textbox2.getText() + "');");
            ResultSet result;
            statement.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        list_orders_id.remove(index);
        list_orders_details.clear();

    }

}
