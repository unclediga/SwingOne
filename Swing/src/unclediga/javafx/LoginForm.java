package unclediga.javafx;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LoginForm extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("JavaFX Tutorial : LoginForm");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setAlignment(Pos.CENTER);

        Text sceneTitle = new Text("Welcome!");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));

        Label lb_UserName = new Label("User name:");
        TextField userName = new TextField();
        Label lb_Password = new Label("Password:");
        TextField password = new TextField();

        Text textInfo = new Text("Info text");

        HBox hbox = new HBox(10);
        Button but = new Button("Sign in");
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.getChildren().add(but);
        but.setOnAction(event -> {
            textInfo.setText("Hi for " + userName.getText());
            textInfo.setFill(Color.FIREBRICK);
        });


        grid.add(sceneTitle, 0, 0, 2, 1);
        grid.add(lb_UserName, 0, 1);
        grid.add(userName, 1, 1);
        grid.add(lb_Password, 0, 2);
        grid.add(password, 1, 2);

        grid.add(hbox, 1, 4);

        grid.add(textInfo, 0, 6);
        grid.setColumnSpan(textInfo, 2);
        grid.setHalignment(textInfo, HPos.RIGHT);


        Scene scene = new Scene(grid, 300, 275);

        primaryStage.setScene(scene);
        ////////////////////////////////////////////////////////////////
        //  for CSS styling
        ////////////////////////////////////////////////////////////////
        scene.getStylesheets().add
                (LoginForm.class.getResource("Login.css").toExternalForm());

        textInfo.setId("actiontarget");
        sceneTitle.setId("welcome-text");


        primaryStage.show();

    }
}
