package unclediga.javafx.choiceboxsample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ChoiceBoxSample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(new Group(), 300, 200);
        scene.setFill(Color.ALICEBLUE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Choice Box Sample");

        primaryStage.show();

        final String[] greetings = new String[]{"Hello", "Привет!"};
        ObservableList<Object> choices = FXCollections.observableArrayList("English", "Русский", new Separator(), "Nothing");
        ChoiceBox cb = new ChoiceBox();
        cb.setItems(choices);
        cb.getItems().addAll("Nothing all");
        cb.getItems().addAll("Nothing always");

        Label label = new Label();
        label.setFont(Font.font("Arial", 25));

        cb.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int idx = newValue.intValue();
            if(!(idx > greetings.length)){
                label.setText(greetings[newValue.intValue()]);
            }
        });


        HBox hbox = new HBox();
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(20, 0, 0, 20));

        hbox.getChildren().addAll(cb, label);
        ((Group) scene.getRoot()).getChildren().add(hbox);


    }
}
