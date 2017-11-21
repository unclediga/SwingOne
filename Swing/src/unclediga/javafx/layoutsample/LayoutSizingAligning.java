package unclediga.javafx.layoutsample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LayoutSizingAligning extends Application {

    private final Button btnApply = new Button("Apply");
    private final Button btnCancel = new Button("Cancel");
    private final Button btnExit = new Button("Exit");


    public static void main(String[] args) {
        launch(LayoutSizingAligning.class,args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        btnExit.setStyle("-fx-font-size: 14pt;");


        Tab tabSize = new Tab();
        tabSize.setText("Size");
        tabSize.setContent(sizingSample());
        Tab tabAlign = new Tab();
        tabAlign.setText("Align");
        tabAlign.setContent(alignSample());

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(tabSize, tabAlign);

        Scene scene = new Scene(tabPane,300,400);
        primaryStage.setTitle("Sizing and Aligning");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private Pane sizingSample() {
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20,0,20,20));

        ListView<String> listView = new ListView<>();
        listView.setMaxHeight(Region.USE_COMPUTED_SIZE);
        listView.setPrefWidth(150.0);

        ObservableList<String> items = FXCollections.observableArrayList(
                "Hot dog", "Hamburger", "French fries",
                "Carrot sticks", "Chicken salad");

        listView.setItems(items);

        border.setLeft(listView);
        border.setRight(createButtonColumn());
        border.setBottom(createButtonRow());

        return border;
    }

    private Pane createButtonColumn() {
        VBox box = new VBox();
        box.setPadding(new Insets(0,20,10,20));
        box.setSpacing(10);

        Button btnAdd = new Button("Add");
        Button btnDelete = new Button("Delete");
        Button btnMoveUp = new Button("MoveUp");
        Button btnMoveDown = new Button("MoveDown");

        btnAdd.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
//        btnDelete.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btnDelete.setMaxSize(80.0,80.0);
        btnDelete.setPrefWidth(80.0);
        btnDelete.setMaxWidth(Control.USE_PREF_SIZE);

        btnMoveUp.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btnMoveDown.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btnMoveDown.setMinWidth(Control.USE_PREF_SIZE);

        box.getChildren().addAll(btnAdd, btnDelete, btnMoveUp,btnMoveDown);

        return box;
    }

    private Pane createButtonRow() {

        btnApply.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btnCancel.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        btnExit.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        TilePane box = new TilePane(Orientation.HORIZONTAL);
        box.setVgap(8.0);
        box.setHgap(10.0);
        box.setPadding(new Insets(20,10,20,0));



        box.getChildren().addAll(btnApply, btnCancel, btnExit);
        return box;
    }

    private Pane alignSample() {

        // DEFAULTS
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(12);

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);

        Label lbUser = new Label("User name:");
        TextField user = new TextField();
        Label lbPass = new Label("Password:");
        TextField pass = new TextField();

        Button btnSubmit = new Button("Submit");
        btnSubmit.setStyle("-fx-font-size: 15");
        Button btnClear = new Button("Clear");
        Button btnExit2 = new Button("Exit");

        hbButtons.getChildren().addAll(btnSubmit, btnClear, btnExit2);

        gridPane.add(lbUser,0,0);
        gridPane.add(lbPass,0,1);
        gridPane.add(user,1,0);
        gridPane.add(pass,1,1);
        gridPane.add(hbButtons,0,2,2,1);

        // TUNING
        gridPane.setAlignment(Pos.CENTER);
        hbButtons.setAlignment(Pos.CENTER);

        return gridPane;
    }
}
