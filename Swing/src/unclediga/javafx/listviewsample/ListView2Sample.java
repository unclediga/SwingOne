package unclediga.javafx.listviewsample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ListView2Sample  extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        StackPane pane = new StackPane();
        Scene scene = new Scene(pane);
        primaryStage.setWidth(200);
        primaryStage.setHeight(250);
        primaryStage.setTitle("List View - 2 Sample");
        primaryStage.setScene(scene);

        ListView<String> listView = new ListView<>();
        listView.setEditable(true);
        listView.setPrefSize(200,150);

        ObservableList<String> data = FXCollections.observableArrayList();
        for (int i = 0; i < 20; i++) {
            data.add("anonym");
        }
        listView.setItems(data);


        ObservableList names = FXCollections.observableArrayList();
        names.addAll(
                "Adam", "Alex", "Alfred", "Albert",
                "Brenda", "Connie", "Derek", "Donny",
                "Lynne", "Myrtle", "Rose", "Rudolph",
                "Tony", "Trudy", "Williams", "Zach"
        );

//        listView.setCellFactory(ComboBoxListCell.forListView("a","b"));
        listView.setCellFactory(ComboBoxListCell.forListView(names));

        // compare data, what we are see and what real data are contained in list variable "data".

        Button button = new Button();
        button.setText("show data");
        button.setOnAction(event -> {

            for (int i = 0; i < 20; i++) {
                System.out.println("i:"+i+":"+data.get(i));
            }

        });


        pane.getChildren().add(listView);
        pane.getChildren().add(button);
        primaryStage.show();
    }
}
