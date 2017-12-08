package unclediga.jfxbe;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static unclediga.jfxbe.Person.MOOD_TYPES;
import static unclediga.jfxbe.Person.MOOD_TYPES.*;


public class BossesAndEmployees extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 630, 250, Color.WHITE);

        Label lbCandidates = new Label("Boss");

        ObservableList<Person> bosses = setEmployees();
        ListView<Person> leaderListView = new ListView<Person>();
        leaderListView.setItems(bosses);

        leaderListView.setCellFactory((ListView<Person> param) -> {
            ListCell<Person> cell = new ListCell<Person>(){
                @Override
                protected void updateItem(Person item, boolean empty) {
                    super.updateItem(item, empty);
                    Tooltip tooltip = new Tooltip();
                    if(item != null){
                        setText(item.getLastName() + " " +item.getFirstName() );
                        tooltip.setText(item.getAliasName());
                        setTooltip(tooltip);
                    }
                }
            };
            return cell;
        });


        leaderListView.setMaxWidth(200);
        leaderListView.setMinWidth(200);
        leaderListView.setPrefWidth(150);
        leaderListView.setPrefHeight(Integer.MAX_VALUE);


        Label lbEmployees = new Label("Employees");
        TableView<Person> employeeTableView = new TableView<>();
        employeeTableView.setPrefHeight(Integer.MAX_VALUE);
        employeeTableView.setEditable(true);
        ObservableList<Person> teamMembers = FXCollections.observableArrayList();
        employeeTableView.setItems(teamMembers);
        leaderListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            // book variant
            if (observable != null && observable.getValue() != null) {
                teamMembers.clear();
                teamMembers.addAll(observable.getValue().employeesProperty());
            }

            // my variant (too work, but more difficult)
//            if(newValue != null){
//                teamMembers.clear();
//                teamMembers.addAll(newValue.employeesProperty());
//            }
        });

        TableColumn<Person, String> columnAliasName = new TableColumn<>();
        columnAliasName.setText("Alias name");
        columnAliasName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnAliasName.setCellValueFactory(new PropertyValueFactory<>("aliasName"));

        TableColumn<Person, String> columnFirstName = new TableColumn<>();
        columnFirstName.setText("First name");
        TableColumn<Person, String> columnLastName = new TableColumn<>();
        columnLastName.setText("Last name");
        TableColumn<Person, MOOD_TYPES> columnMood = new TableColumn<>();
        columnMood.setText("Mood");

        employeeTableView.getColumns().add(columnAliasName);
        employeeTableView.getColumns().add(columnFirstName);
        employeeTableView.getColumns().add(columnLastName);
        employeeTableView.getColumns().add(columnMood);

        gridPane.add(lbCandidates, 0, 0);
        gridPane.add(leaderListView, 0, 1);
        gridPane.add(lbEmployees, 1, 0);
        gridPane.add(employeeTableView, 1, 1);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        GridPane.setHalignment(lbCandidates, HPos.CENTER);
        GridPane.setHalignment(lbEmployees, HPos.CENTER);

        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private ObservableList<Person> setEmployees() {
        ObservableList<Person> people = FXCollections.observableArrayList();

        Person docX = new Person("Professor X", "Charles", "Xavier", Positive);
        docX.employeesProperty().add(new Person("Wolverine", "James", "Howlett", Angry));
        docX.employeesProperty().add(new Person("Cyclops", "Scott", "Summers", Happy));
        docX.employeesProperty().add(new Person("Storm", "Ororo", "Munroe", Positive));

        Person magneto = new Person("Magneto", "Max", "Eisenhardt", Sad);
        magneto.employeesProperty().add(new Person("Juggernaut", "Cain", "Marko", Angry));
        magneto.employeesProperty().add(new Person("Mystique", "Raven", "Darkhölme", Sad));
        magneto.employeesProperty().add(new Person("Sabretooth", "Victor", "Creed", Angry));

        Person biker = new Person("Mountain Biker", "Jonathan", "Gennick", Positive);
        biker.employeesProperty().add(new Person("MkHeck", "Mark", "Heckler", Happy));
        biker.employeesProperty().add(new Person("Hansolo", "Gerrit", "Grunwald", Positive));
        biker.employeesProperty().add(new Person("Doc", "José", "Pereda", Happy));
        biker.employeesProperty().add(new Person("Cosmonaut", "Sean", "Phillips", Positive));
        biker.employeesProperty().add(new Person("CarlFX", "Carl", "Dea", Happy));

        people.add(docX);
        people.add(magneto);
        people.add(biker);
        return people;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
