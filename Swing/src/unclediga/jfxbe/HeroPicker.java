package unclediga.jfxbe;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HeroPicker extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 500, 300, Color.WHITE);


        Label labelCandidates = new Label("Candidates");
        Label labelHeroes = new Label("Heroes");


        ObservableList<String> candidates = FXCollections.observableArrayList(
                "Superman",
                "Spiderman",
                "Wolverine",
                "Police",
                "Fire Rescue",
                "Soldiers",
                "Dad & Mom",
                "Doctor",
                "Politician",
                "Pastor",
                "Teacher"
        );

        ListView<String> listViewCandidates = new ListView();
        listViewCandidates.setItems(candidates);

        ObservableList<String> heroes = FXCollections.observableArrayList();
        ListView<String> listViewHeroes = new ListView();
        listViewHeroes.setItems(heroes);

        Button sendRight = new Button(" > ");
        sendRight.setOnAction(event -> {

            String potential = listViewCandidates.getSelectionModel().getSelectedItem();
            if (potential != null) {
                listViewCandidates.getSelectionModel().clearSelection();
                heroes.add(potential);
                candidates.remove(potential);
            }

        });
        Button sendLeft = new Button(" < ");
        sendLeft.setOnAction(event -> {
            String potential = listViewHeroes.getSelectionModel().getSelectedItem();

            if (potential != null) {
                heroes.remove(potential);
                candidates.add(potential);
                listViewHeroes.getSelectionModel().clearSelection();
            }
        });

        VBox buttonBox = new VBox(sendRight, sendLeft);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);


        GridPane gridPane = new GridPane();
        gridPane.add(labelCandidates, 0, 0);
        gridPane.add(labelHeroes, 2, 0);
        gridPane.add(listViewCandidates, 0, 1);
        gridPane.add(buttonBox, 1, 1);
        gridPane.add(listViewHeroes, 2, 1);

        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10));

        gridPane.setPrefHeight(Double.MAX_VALUE);
        ColumnConstraints column1 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        ColumnConstraints column2 = new ColumnConstraints(50);
        ColumnConstraints column3 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
        column1.setHgrow(Priority.ALWAYS);
        column3.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(column1, column2, column3);

        root.setCenter(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("HeroPicker: Creating and Working with ObservableLists");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
