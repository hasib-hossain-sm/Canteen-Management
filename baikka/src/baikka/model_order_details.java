package baikka;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class model_order_details extends RecursiveTreeObject<model_order_details> {
    StringProperty name,ppu,quantity,price;

    public model_order_details(String name, String ppu, String quantity, String price) {
        this.name = new SimpleStringProperty(name);
        this.ppu = new SimpleStringProperty(ppu);
        this.quantity = new SimpleStringProperty(quantity);
        this.price = new SimpleStringProperty(price);
    }
    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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
    public String getQuantity() {
        return quantity.get();
    }

    public StringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

    public String getPpu() {
        return ppu.get();
    }

    public StringProperty ppuProperty() {
        return ppu;
    }

    public void setPpu(String ppu) {
        this.ppu.set(ppu);
    }
}
