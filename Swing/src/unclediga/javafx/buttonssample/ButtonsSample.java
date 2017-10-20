package unclediga.javafx.buttonssample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class ButtonsSample extends Application {

    private static Color color = Color.web("#464646");
    private static DropShadow shadow = new DropShadow();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Buttons Sample");
        primaryStage.setWidth(300);
        primaryStage.setHeight(190);
        scene.getStylesheets().add(getClass().getResource("ColorStyle.css").toExternalForm());

        Label label = new Label();
        label.setFont(Font.font("Arial",22));
        label.setTextFill(color);

        Image imageAccept = new Image(getClass().getResourceAsStream("ok.png"));
        Image imageDecline = new Image(getClass().getResourceAsStream("not.png"));

        Button button1 = new Button("Accept");
        button1.setGraphic(new ImageView(imageAccept));
        button1.getStyleClass().add("button1");

        button1.setOnAction(event -> {label.setText("Accept");});

        Button button2 = new Button("Accept");
        button2.setGraphic(new ImageView(imageAccept));
        button2.setOnAction(event -> {label.setText("Accept");});
        Button button3 = new Button("Decline");
        button3.setGraphic(new ImageView(imageDecline));
        button3.setOnAction(event -> {label.setText("Decline");});
        button3.addEventHandler(MouseEvent.MOUSE_ENTERED,event -> {button3.setEffect(shadow);});
        button3.addEventHandler(MouseEvent.MOUSE_EXITED,event -> {button3.setEffect(null);});



        Button button4 = new Button();
        button4.setGraphic(new ImageView(imageAccept));
        button4.setOnAction(event -> {label.setText("Accept");});

        Button button5 = new Button();
        button5.setGraphic(new ImageView(imageDecline));
        button5.setOnAction(event -> {label.setText("Decline");});

        VBox vBox = new VBox();
        //vBox.setLayoutX(20);
        vBox.setSpacing(10);


        HBox hbox2 = new HBox();
        hbox2.setSpacing(25);
        hbox2.setAlignment(Pos.BOTTOM_CENTER);
        hbox2.getChildren().add(button2);
        hbox2.getChildren().add(button3);
        hbox2.getChildren().add(label);

        HBox hbox3 = new HBox();
        hbox3.setSpacing(10);
        hbox3.getChildren().add(button4);
        hbox3.getChildren().add(button5);

        vBox.getChildren().add(button1);
        vBox.getChildren().add(hbox2);
        vBox.getChildren().add(hbox3);

        Group root = (Group) scene.getRoot();
        root.getChildren().add(vBox);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
