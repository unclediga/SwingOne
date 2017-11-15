package unclediga.javafx.applogic.scenegraphdemo;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SceneGraphMain extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.BLACK);

        Rectangle rectangle = new Rectangle(25, 25, 250, 250);
        rectangle.setFill(Color.BLUE);
        root.getChildren().add(rectangle);

        TranslateTransition translate =
                new TranslateTransition(Duration.millis(750));
        translate.setToX(390);
        translate.setToY(390);

        FillTransition fill = new FillTransition(Duration.millis(750));
        fill.setToValue(Color.RED);

        RotateTransition rotate = new RotateTransition(Duration.millis(750));
        rotate.setToAngle(360);

        ScaleTransition scale = new ScaleTransition(Duration.millis(750));
        scale.setToX(0.1);
        scale.setToY(0.1);

        ParallelTransition transition = new ParallelTransition(rectangle,
                translate, fill, rotate, scale);

        transition.setCycleCount(Timeline.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();

        stage.setTitle("Java FX Scene Graph Demo");
        stage.setScene(scene);
        stage.show();
    }
}
