package unclediga.jfxbe;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Person {
    public Person(String aliasName, String firstName, String lastName, MOOD_TYPES mood) {
        setAliasName(aliasName);
        setFirstName(firstName);
        setLastName(lastName);
        setMood(mood);
    }

    public enum MOOD_TYPES{
        Happy,
        Sad,
        Angry,
        Positive
    }
    private StringProperty aliasName;
    private StringProperty firstName;
    private StringProperty lastName;
    private ObjectProperty<MOOD_TYPES> mood;

    private ObservableList<Person> employees = FXCollections.observableArrayList();


    public String getAliasName() {
        return aliasName.get();
    }

    public StringProperty aliasNameProperty() {
        if(aliasName == null){
            aliasName = new SimpleStringProperty();
        }
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        aliasNameProperty().set(aliasName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        if(firstName == null){
            firstName = new SimpleStringProperty();
        }
        return firstName;
    }

    public void setFirstName(String firstName) {
        firstNameProperty().set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        if(lastName == null){
            lastName = new SimpleStringProperty();
        }
        return lastName;
    }

    public void setLastName(String lastName) {
        lastNameProperty().set(lastName);
    }

    public MOOD_TYPES getMood() {
        return mood.get();
    }

    public ObjectProperty<MOOD_TYPES> moodProperty() {
        if(mood == null){
            mood = new SimpleObjectProperty<>(MOOD_TYPES.Happy);
        }
        return mood;
    }

    public void setMood(MOOD_TYPES mood) {
        moodProperty().set(mood);
    }

    public ObservableList<Person> employeesProperty() {
        return employees;
    }
}
