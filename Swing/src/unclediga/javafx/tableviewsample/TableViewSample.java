package unclediga.javafx.tableviewsample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;


public class TableViewSample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        /// DATA /////////
        ObservableList<Person> data = FXCollections.observableArrayList();
        data.addAll(new Person("Jacob", "Smith", "jacob.smith@example.com"),
                new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
                new Person("Ethan", "Williams", "ethan.williams@example.com"),
                new Person("Emma", "Jones", "emma.jones@example.com"),
                new Person("Michael", "Brown", "michael.brown@example.com")
        );
        //// VIEW ///////////

        Scene scene = new Scene(new Group(), 450, 500);

        Label label = new Label("Address Book");
        label.setFont(Font.font("Arial", 26));

        TableView<Person> tableView = new TableView<>();

        TableColumn<Person,String> column1 = new TableColumn<>("First Name");
        TableColumn column2 = new TableColumn("Last Name");
        TableColumn column3 = new TableColumn("e-mail");
//        TableColumn column4 = new TableColumn("primary");
//        TableColumn column5 = new TableColumn("secondary");

        // Editing cell EXAMPLE #1
        // The default implementation of the TextField control
        // requires that users press the Enter key to commit the edit.

        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        column1.setCellFactory(TextFieldTableCell.forTableColumn());
        column1.setMinWidth(100);
        column1.setOnEditCommit(event -> {
            event.getTableView().getItems().get(
                    event.getTablePosition().getRow()).setFirstName(event.getNewValue());
        });

        // Editing cell EXAMPLE #2
        // not requires press the Enter key for commit.
        // focus out - is commit
        // WORKING NOT GOOD. SOME FOCUS PROBLEMS

        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//        column2.setCellFactory(TextFieldTableCell.forTableColumn());
        column2.setMinWidth(100);

        Callback<TableColumn<Person,String>,TableCell<Person,String>> cellFactory =
                (TableColumn<Person,String> p) -> new EditingCell();
        column2.setCellFactory(cellFactory);

//        column3.getColumns().addAll(column4, column5);

        column3.setMinWidth(200);
        column3.setCellValueFactory(new PropertyValueFactory<>("email"));
        column3.setCellFactory(TextFieldTableCell.<Person>forTableColumn());


        tableView.getColumns().addAll(column1, column2, column3);
        tableView.setEditable(true);
        tableView.setItems(data);


        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setPrefWidth(column1.getPrefWidth());

        final TextField addLastName = new TextField();
        addLastName.setPromptText("Last Name");
        addLastName.setPrefWidth(column2.getPrefWidth());

        final TextField addEmail = new TextField();
        addEmail.setPromptText("e-mail");
        addEmail.setPrefWidth(column3.getPrefWidth());

        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            data.add(new Person(
                    addFirstName.getText(),
                    addLastName.getText(),
                    addEmail.getText()));
            addFirstName.clear();
            addLastName.clear();
            addEmail.clear();
        });

        HBox hbox = new HBox();
        hbox.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);


        VBox box = new VBox(10);
        box.setPadding(new Insets(10, 0, 0, 10));

        box.getChildren().addAll(label, tableView,hbox);
        ((Group) scene.getRoot()).getChildren().add(box);


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class EditingCell extends TableCell<Person,String> {

        private TextField textField;

        public EditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()){
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.setText(getString());
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText(getString());
            setGraphic(null);
        }

        @Override
        protected void updateItem(String item, boolean empty) {

            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);

            } else {
                if (isEditing()) {
                    if (textField != null)
                        textField.setText(getString());
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }


        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.focusedProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        if (!newValue)
                            commitEdit(textField.getText());
                    }
            );
        }
    }
}

