package baikka;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Model extends RecursiveTreeObject<Model> {


    StringProperty name , age ,gender ,salary;

    public Model( String name ,String age ,String gender ,String salary){

        this.name=new SimpleStringProperty(name);
        this.age=new SimpleStringProperty(age);
        this.gender=new SimpleStringProperty(gender);
        this.salary=new SimpleStringProperty(salary);
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

    public String getAge() {
        return age.get();
    }

    public StringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }
    
    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getSalary() {
        return salary.get();
    }

    public StringProperty salaryProperty() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary.set(salary);
    }

    
}
