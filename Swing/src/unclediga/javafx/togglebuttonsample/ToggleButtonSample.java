package unclediga.javafx.togglebuttonsample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ToggleButtonSample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Toggle Button Sample");
        primaryStage.setHeight(180);
        primaryStage.setWidth(250);

        VBox vbox = new VBox();
        HBox hbox = new HBox();

        Scene scene = new Scene(new Group(vbox));
        primaryStage.setScene(scene);
        scene.getStylesheets().add("unclediga/javafx/togglebuttonsample/ControlStyle.css");

        Rectangle rect = new Rectangle();
        rect.setHeight(50);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLUE);
        rect.setStrokeWidth(2);
        rect.setArcWidth(15);
        rect.setArcHeight(15);


        ToggleGroup group = new ToggleGroup();
        group.selectedToggleProperty().addListener(
                (observable, oldValue, newValue) ->
                {
                    rect.setFill((Color) newValue.getUserData());
                });

        ToggleButton tb1 = new ToggleButton("Minor");
        tb1.setToggleGroup(group);
        tb1.setUserData(Color.LIGHTGREEN);
        tb1.getStyleClass().add("toggle-button1");
        tb1.setSelected(true);

        ToggleButton tb2 = new ToggleButton("Major");
        tb2.setToggleGroup(group);
        tb2.setUserData(Color.LIGHTBLUE);
        tb2.getStyleClass().add("toggle-button2");

        ToggleButton tb3 = new ToggleButton("Critical");
        tb3.setToggleGroup(group);
        tb3.setUserData(Color.SALMON);
        tb3.getStyleClass().add("toggle-button3");

        hbox.getChildren().addAll(tb1, tb2, tb3);
        vbox.getChildren().addAll(new Label("Priority:"), hbox, rect);
        vbox.setPadding(new Insets(20, 10, 10, 20));

        primaryStage.show();
        rect.setWidth(hbox.getWidth());

    }
}
