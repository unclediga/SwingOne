package unclediga.javafx.radiobuttonsample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RadioButtonSample extends Application {

    private final ImageView icon = new ImageView();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(new Group(), 250, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Radio button sample");
        primaryStage.show();


        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));

        HBox hbox = new HBox();
        hbox.setSpacing(50);
        hbox.setPadding(new Insets(20, 10, 10, 20));


        RadioButton rb1 = new RadioButton("Home");
        rb1.setUserData("Home.jpg");
        RadioButton rb2 = new RadioButton("Calendar");
        rb2.setUserData("Calendar.jpg");
        // not fire listener :(
        rb2.setSelected(true);
        rb2.requestFocus();

        RadioButton rb3 = new RadioButton("Contacts");
        rb3.setUserData("Contacts.jpg");
        ToggleGroup group = new ToggleGroup();
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb3.setToggleGroup(group);

        RadioButton rb4 = new RadioButton("Labeled");
        rb4.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/unclediga/javafx/buttonssample/ok.png"))));


        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                System.out.printf("old %s new %s\n", oldValue == null ? "" : oldValue.getUserData(), newValue.getUserData());
                if (group.getSelectedToggle() != null) {
                    icon.setImage(new Image(this.getClass().getResourceAsStream((String) newValue.getUserData())));
                }

            }
        });


        vbox.getChildren().addAll(rb1, rb2, rb3,rb4);
        hbox.getChildren().addAll(vbox, icon);
        ((Group) scene.getRoot()).getChildren().add(hbox);

    }
}
