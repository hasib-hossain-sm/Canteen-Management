package canteen;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model extends RecursiveTreeObject<Model> {

    StringProperty food, price, avail, quantity;

    public Model(String food, String price, String avail, String quantity) {
        this.food = new SimpleStringProperty(food);
        this.price = new SimpleStringProperty(price);
        this.avail = new SimpleStringProperty(avail);
        this.quantity = new SimpleStringProperty(quantity);
    }

    public String getFood() {
        return food.get();
    }
    
    public StringProperty foodProperty() {
        return food;
    }

    public void setFood(String food) {
        this.food.set(food);
    }

    public String getPrice() {
        return price.get();
    }
    
    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getAvail() {
        return avail.get();
    }
    
    public StringProperty availProperty() {
        return avail;
    }

    public void setAvail(String avail) {
        this.avail.set(avail);
    }

    public String getQuantity() {
        return quantity.get();
    }
    
    public StringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }
    
    
    
}
