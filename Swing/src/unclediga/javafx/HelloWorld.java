package unclediga.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWorld extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button button = new Button();
        button.setText("Say 'Hello, World!'");

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(button);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello, World!");
            }
        });

        Scene scene = new Scene(stackPane,300,250);

        primaryStage.setTitle("Java tutorial Hello world");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
