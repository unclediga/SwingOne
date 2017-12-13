package unclediga.myjfx.dialog;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;

public class MyFileDialog extends Application{

    private Label lbPath;

    @Override
    public void start(Stage primaryStage) throws Exception {

        HBox root = new HBox();
        Scene scene = new Scene(root, 350, 150);

        Button butOpenDialog = new Button("...");
        butOpenDialog.setPrefSize(15, 15);

        butOpenDialog.setOnAction(event -> {
            FileChooser dialog = new FileChooser();
            dialog.setInitialDirectory(new File("c:/TEMP"));
            File file = dialog.showOpenDialog(new Popup());
            if(file != null){
                lbPath.setText(file.getAbsolutePath());
            }
        });


        lbPath = new Label("<...path...>");
        lbPath.setTextFill(Color.BLACK);
        lbPath.setFont(Font.font("Lucida Console", FontWeight.EXTRA_LIGHT, 9));
        lbPath.setPrefWidth(350);
        lbPath.setMaxWidth(Double.MAX_VALUE);

        root.setPadding(new Insets(10));
        root.setSpacing(10);
        HBox.setHgrow(lbPath, Priority.ALWAYS);


        root.getChildren().addAll(lbPath, butOpenDialog);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
