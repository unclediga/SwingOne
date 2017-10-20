package unclediga.javafx.tableviewsample;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private SimpleStringProperty firstName;
    private String lastName;
    private String email;

    public Person(String firstName, String lastName, String email) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = lastName;
        if (email != null) {
            this.email = email;
        }else{
            this.email = firstName.toLowerCase()+"."+lastName.toLowerCase()+"@example.com";
        }
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
