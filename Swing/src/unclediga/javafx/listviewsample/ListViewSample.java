package unclediga.javafx.listviewsample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ListViewSample extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox pane = new VBox();
        Scene scene = new Scene(pane);
        primaryStage.setWidth(200);
        primaryStage.setHeight(200);
        primaryStage.setTitle("List View Sample");
        primaryStage.setScene(scene);

        Label label = new Label("description");
//        label.setLayoutX(10);
//        label.setLayoutY(115);
        label.setFont(Font.font("Verdana",20));

        ListView<String> listView = new ListView<>();

        ObservableList<String> data = FXCollections.observableArrayList(
                "chocolate", "salmon", "gold", "coral", "darkorchid",
                "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
                "blueviolet", "brown");
        listView.setItems(data);


//        listView.setCellFactory(ComboBoxListCell.forListView("a","b"));
        listView.setCellFactory((ListView<String> l) -> new ColorRectCell());

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                label.setText(newValue);
                label.setTextFill(Color.web(newValue));
            }
        });


        VBox.setVgrow(listView, Priority.ALWAYS);
        pane.getChildren().addAll(listView,label);
        primaryStage.show();

    }


    class ColorRectCell extends ListCell<String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Rectangle rectangle = new Rectangle(100, 20);
            if(item != null){
                rectangle.setFill(Color.web(item));
                setGraphic(rectangle);
            }else{
                setGraphic(null);
            }
        }
    }
}
