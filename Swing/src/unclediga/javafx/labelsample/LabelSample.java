package unclediga.javafx.labelsample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LabelSample extends Application{

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Label Sample");
        primaryStage.setWidth(400);
        primaryStage.setHeight(180);

        Label label1 = new Label("Search");
        label1.setFont(Font.font("PT Sans", FontWeight.BOLD, 30));
        label1.setTextFill(Color.BLUE);
        label1.setTextAlignment(TextAlignment.JUSTIFY);
        Image image = new Image(LabelSample.class.getResourceAsStream("labels.jpg"));
        label1.setGraphic(new ImageView(image));

        Label label2 = new Label("Value");
        label2.setFont(Font.font("Cambria", FontWeight.BOLD, 26));
        label2.setRotate(270);
        label2.setTranslateY(50);

        Label label3 = new Label("A label that need to be wrapped");
        label3.setWrapText(true);
        label3.setPrefWidth(100);
        label3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                label3.setScaleX(1.5);
                label3.setScaleY(1.5);
            }
        });
        label3.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                label3.setScaleX(1.0);
                label3.setScaleY(1.0);
            }
        });

        HBox hBox = new HBox(10);

        hBox.getChildren().add(label1);
        hBox.getChildren().add(label2);
        hBox.getChildren().add(label3);

        ((Group) scene.getRoot()).getChildren().add(hBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
