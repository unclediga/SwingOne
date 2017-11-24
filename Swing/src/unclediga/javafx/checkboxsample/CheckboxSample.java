package unclediga.javafx.checkboxsample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CheckboxSample extends Application{

    private final Rectangle rect = new Rectangle(90, 30);
    private final String[] names = new String[]{"Security", "Project", "Chart"};
    private final Image[] images = new Image[names.length];
    private final ImageView[] icons = new ImageView[names.length];
    private final CheckBox[] cbs = new CheckBox[names.length];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Checkbox Sample");
        primaryStage.setHeight(150);
        primaryStage.setWidth(250);
        Scene scene = new Scene(new Group());

        rect.setArcHeight(10);
        rect.setArcWidth(10);
        rect.setFill(Color.BLACK);

        for (int i = 0; i < 3; i++) {
            CheckBox cb = cbs[i] = new CheckBox(names[i]);
            Image image = images[i] = new Image(getClass().getResourceAsStream(names[i] + ".png"));
            ImageView icon = icons[i] = new ImageView(image);
            cb.selectedProperty().addListener((observable, oldValue, newValue) ->
            {
                icon.setImage(newValue ? image : null);

            });
        }


        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(5, 0, 0, 0));
        vbox.getChildren().addAll(cbs);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(icons);

        StackPane stack = new StackPane();
        stack.getChildren().add(rect);
        stack.getChildren().add(hbox);
        stack.setAlignment(Pos.TOP_CENTER);

        HBox root = new HBox();
        root.setSpacing(40);
        root.setPadding(new Insets(20, 10, 10, 20));
        root.getChildren().addAll(vbox, stack);

        ((Group) scene.getRoot()).getChildren().add(root);

        primaryStage.setScene(scene);
        primaryStage.show();




    }
}
