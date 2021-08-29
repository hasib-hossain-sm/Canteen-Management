package baikka;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Model_Food_items extends RecursiveTreeObject<Model_Food_items> {


    StringProperty name , price ,avail ,catagory;

    public Model_Food_items( String name ,String price ,String avail ,String catagory){

        this.name=new SimpleStringProperty(name);
        this.price=new SimpleStringProperty(price);
        this.avail=new SimpleStringProperty(avail);
        this.catagory=new SimpleStringProperty(catagory);
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
    
    public String getAvail() {
        return avail.get();
    }

    public StringProperty availProperty() {
        return avail;
    }

    public void setAvail(String avail) {
        this.avail.set(avail);
    }

    public String getCatagory() {
        return catagory.get();
    }

    public StringProperty catagoryProperty() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory.set(catagory);
    }

    
}

