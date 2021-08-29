package baikka;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class model_order_id extends RecursiveTreeObject<model_order_id> {
    StringProperty id;

    public model_order_id(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }
    public StringProperty idProperty() {
        return id;
    }
    
    
}
