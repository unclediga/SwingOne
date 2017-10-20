package unclediga.javafx.tableviewsample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class TableViewSample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        /// DATA /////////
        ObservableList<Person> data = FXCollections.observableArrayList();
        data.addAll(
                new Person("Arron", "Bailey", null),
                new Person("Rosalyn", "McDonald", null),
                new Person("Daniel", "Cooper", null),
                new Person("Mitchell", "Evans", null),
                new Person("Joshua", "Harmon", null),
                new Person("Jennifer", "Carpenter", null),
                new Person("Maude", "Hicks", null),
                new Person("Peter", "McKinney", null));

        //// VIEW ///////////

        // TODO  No Editable !
        // TODO  No e-mail !




        Scene scene = new Scene(new Group(), 450, 500);

        Label label = new Label("Address Book");
        label.setFont(Font.font("Arial", 26));

        TableView tableView = new TableView();

        TableColumn<Person,String> column1 = new TableColumn("First Name");
        TableColumn column2 = new TableColumn("Last Name");
        TableColumn column3 = new TableColumn("e-mail");
        TableColumn column4 = new TableColumn("primary");
        TableColumn column5 = new TableColumn("secondary");

        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        column1.setCellFactory(TextFieldTableCell.forTableColumn());

        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        column2.setCellFactory(TextFieldTableCell.forTableColumn());

        column3.getColumns().addAll(column4,column5);
        tableView.getColumns().addAll(column1, column2, column3);
        tableView.setEditable(true);
        tableView.setItems(data);

        VBox box = new VBox(15);
        box.setPadding(new Insets(15));

        box.getChildren().addAll(label, tableView);
        ((Group) scene.getRoot()).getChildren().add(box);





        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

