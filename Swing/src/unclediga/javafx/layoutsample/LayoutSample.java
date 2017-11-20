package unclediga.javafx.layoutsample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LayoutSample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Layout Sample");
        stage.setWidth(800);
        stage.setHeight(500);


        BorderPane border = new BorderPane();
        Scene scene = new Scene(border);

        // TOP
        HBox hBox = addHBox();
        border.setTop(hBox);

        // LEFT
        border.setLeft(addVBox());

        addStackPane(hBox);

        // CENTER
        border.setCenter(addAnchorPane(addGridPane()));
        // RIGHT

        HBox rbox = new HBox();
        rbox.getChildren().addAll(addFlowPane(), addTilePane());
        border.setRight(rbox);

        stage.setScene(scene);
        stage.show();


    }

    private AnchorPane addAnchorPane(GridPane gridPane) {
        AnchorPane anchorPane = new AnchorPane();

        HBox box = new HBox();
        box.setPadding(new Insets(0,10,10,10));
        box.setSpacing(10);

        Button buttonSave = new Button("Save");
        Button buttonCancel = new Button("Cancel");
        box.getChildren().addAll(buttonSave, buttonCancel);

        anchorPane.getChildren().addAll(gridPane, box);
        AnchorPane.setTopAnchor(gridPane,10.0);
        AnchorPane.setBottomAnchor(box,10.0);
        AnchorPane.setRightAnchor(box,10.0);
        return anchorPane;
    }


    private HBox addHBox() {

        HBox box = new HBox();
        box.setSpacing(10);
        box.setPadding(new Insets(15, 12, 15, 12));
        box.setStyle("-fx-background-color: blue");

        Button button1 = new Button("Current");
        button1.setPrefSize(100, 20);
        Button button2 = new Button("Projected");
        button2.setPrefSize(100, 20);

        box.getChildren().add(button1);
        box.getChildren().add(button2);

        return box;
    }

    private VBox addVBox() {

        VBox box = new VBox();
        box.setSpacing(8);
        box.setPadding(new Insets(10));

        Text title = new Text("Data");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        box.getChildren().add(title);
        Hyperlink[] options = new Hyperlink[]{
                new Hyperlink("Sales"),
                new Hyperlink("Marketing"),
                new Hyperlink("Distribution"),
                new Hyperlink("Costs")
        };

        for (int i = 0; i < 4; i++) {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 10));
            box.getChildren().add(options[i]);
        }

        return box;
    }

    private FlowPane addFlowPane() {
        FlowPane flow = new FlowPane();

        flow.setVgap(4);
        flow.setHgap(4);
        flow.setPrefWrapLength(170);

        flow.setStyle("-fx-background-color: DAE6F3;");

        ImageView[] pages = new ImageView[9];

        for (int i = 1; i <= 8; i++) {
            pages[i] = new ImageView(new Image(LayoutSample.class.getResourceAsStream("chart_"+i+".png")));
            flow.getChildren().add(pages[i]);
        }

        return flow;
    }


    private TilePane addTilePane(){
        TilePane tilePane = new TilePane();
        tilePane.setVgap(4);
        tilePane.setHgap(4);
        tilePane.setPrefColumns(2);

        tilePane.setStyle("-fx-background-color: DAE6F3;");

        ImageView[] pages = new ImageView[9];

        for (int i = 1;i <=8; i++) {
            pages[i] = new ImageView(new Image(LayoutSample.class.getResourceAsStream("chart_" + i + ".png")));
            tilePane.getChildren().add(pages[i]);
        }

        return tilePane;

    }


    private GridPane addGridPane() {

        GridPane pane = new GridPane();
//        pane.setGridLinesVisible(true);
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(0,10,0,0));

        Text category = new Text("Sales");
        category.setFont(Font.font("Arial",FontWeight.BOLD,20));
        pane.add(category,1,0);

        Text chartTitle = new Text("Current year");
        chartTitle.setFont(Font.font("Arial",FontWeight.BOLD,20));
        pane.add(chartTitle,2,0);

        Text chartSubtitle = new Text("Goods and Services");
        pane.add(chartSubtitle,1,1,2,1);

        ImageView imageHouse = new ImageView(new Image(this.getClass().getResourceAsStream("house.png")));
        pane.add(imageHouse,0,0,1,2);

        ImageView piechart = new ImageView(new Image(this.getClass().getResourceAsStream("piechart.png")));
        pane.add(piechart,1,2,2,1);



        Text goodsPersent = new Text("Goods\n80%");
        GridPane.setValignment(goodsPersent, VPos.TOP);
        pane.add(goodsPersent,0,2,2,1);

        Text servicePersent = new Text("Services\n20%");
        GridPane.setValignment(servicePersent, VPos.BOTTOM);
        pane.add(servicePersent,3,2,2,1);

        return pane;

    }

    private void addStackPane(HBox box) {

        StackPane stack = new StackPane();

        Rectangle helpIcon = new Rectangle(30.0, 25.0);
        helpIcon.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop[]{
                        new Stop(0,Color.web("#4977A3")),
                        new Stop(0.5, Color.web("#B0C6DA")),
                        new Stop(1,Color.web("#9CB6CF")),}));
        helpIcon.setStroke(Color.web("#D0E6FA"));
        helpIcon.setArcHeight(3.5);
        helpIcon.setArcWidth(3.5);

        Text helpText = new Text("?");
        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        helpText.setFill(Color.WHITE);
        helpText.setStroke(Color.web("#7080A0"));
        StackPane.setMargin(helpText,new Insets(0,10,0,0));

        stack.setAlignment(Pos.CENTER_RIGHT);
        stack.getChildren().addAll(helpIcon, helpText);

        box.getChildren().add(stack);
        HBox.setHgrow(stack,Priority.ALWAYS);
    }
}
