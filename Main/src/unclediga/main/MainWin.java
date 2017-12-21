package unclediga.main;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import unclediga.data.DataFactory;
import unclediga.data.ExcelPerson;
import unclediga.poi.ExcelParser;

import java.io.File;


public class MainWin extends Application {

    private SimpleIntegerProperty totalCountProperty = new SimpleIntegerProperty(0);
    private SimpleDoubleProperty totalSumProperty = new SimpleDoubleProperty(0.0);
    private File initPath = new File("C:/TEMP");
    private SimpleObjectProperty<File> fileProperty = new SimpleObjectProperty<>(initPath);
    private ObservableList<ExcelPerson> l;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button butOpen = new Button("...");
        butOpen.setMinSize(60, 20);
        butOpen.setFont(Font.font(10));
        butOpen.setOnAction(event -> {
            FileChooser dialog = new FileChooser();
            dialog.setInitialDirectory(initPath);
            File file = dialog.showOpenDialog(new Popup());
            if(file != null){
                fileProperty.set(file);
                initPath = file.getParentFile();
            }
        });

        Button butProcess = new Button("Do it!");
        butProcess.setMinSize(60, 20);
        butProcess.setFont(Font.font(10));
        butProcess.setOnAction(event -> {
            try {
                l.clear();
                l.addAll(ExcelParser.parse(fileProperty.getValue()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button butCheckSSN = new Button("Check!");
        butCheckSSN.setMinSize(60, 20);
        butCheckSSN.setFont(Font.font(10));
        butCheckSSN.setOnAction(event -> {
            try {
                l.addAll(DataFactory.getCheckedItems());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Label lFilePath = new Label("<..file path..>");
        lFilePath.setPrefWidth(600);
        lFilePath.setTextFill(Color.BLUE);
        lFilePath.setFont(Font.font("Courier New", 12));
        lFilePath.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
        lFilePath.textProperty().bind(fileProperty.asString());

        TableView tableView = new TableView();
        TableColumn<ExcelPerson, String> col1 = new TableColumn<>("Fio");
        col1.setPrefWidth(100);
        TableColumn<ExcelPerson, String> col2 = new TableColumn<>("Dept");
        col2.setPrefWidth(100);
        TableColumn<ExcelPerson, String> col3 = new TableColumn<>("Acc");
        col3.setPrefWidth(200);
        TableColumn<ExcelPerson, Double> col4 = new TableColumn<>("Sum");
        col4.setPrefWidth(70);
        TableColumn<ExcelPerson, String> col5 = new TableColumn<>("SSN");
        col5.setPrefWidth(150);

        tableView.getColumns().addAll(col1, col2, col3, col4,col5);

        col1.setCellFactory(TextFieldTableCell.forTableColumn());
        col2.setCellFactory(TextFieldTableCell.forTableColumn());
        col3.setCellFactory(TextFieldTableCell.forTableColumn());
        col4.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                return object.toString();
            }

            @Override
            public Double fromString(String string) {
                return null;
            }
        }));

        col5.setCellFactory(TextFieldTableCell.forTableColumn());

        col4.setStyle("-fx-alignment: CENTER-RIGHT;");
//        ((TextField) col4.getCellFactory()).setAlignment(Pos.CENTER);

        col1.setCellValueFactory(new PropertyValueFactory<>("fio"));
        col2.setCellValueFactory(new PropertyValueFactory<>("dept"));
        col3.setCellValueFactory(new PropertyValueFactory<>("acc"));
        col4.setCellValueFactory(new PropertyValueFactory<>("sum"));
        col5.setCellValueFactory(new PropertyValueFactory<>("ssn"));


        l = getData();

//        SimpleListProperty<ExcelPerson> listProperty = new SimpleListProperty<>(l);

        Text txtCount = new Text();
        txtCount.setFont(Font.font("Consolas", FontWeight.BOLD, 14));
        txtCount.textProperty().bind(new SimpleStringProperty("count: ").concat(totalCountProperty.asString()));
        Text txtSum = new Text();
        txtSum.textProperty().bind(new SimpleStringProperty("sum: ").concat(totalSumProperty.asString()));
        txtSum.setFont(Font.font("Consolas", FontWeight.BOLD, 14));



        tableView.setItems(l);


        HBox hbox = new HBox();
        hbox.setSpacing(1);
        hbox.setPadding(new Insets(10));
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().addAll(butOpen, butProcess, butCheckSSN, lFilePath);


        HBox hbox2 = new HBox();
        hbox2.setSpacing(10);
        hbox2.setPadding(new Insets(10));
        hbox2.setAlignment(Pos.CENTER_RIGHT);
        hbox2.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, null, null)));
        hbox2.getChildren().addAll(txtCount, txtSum);

        BorderPane vbox = new BorderPane(tableView, hbox, null, hbox2, null);
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private ObservableList<ExcelPerson> getData() {
        ObservableList<ExcelPerson> m = FXCollections.observableArrayList();
        m.addListener((InvalidationListener) observable -> {
                    totalCountProperty.set(DataFactory.getCount());
                    totalSumProperty.set(DataFactory.getSum());
                }

        );
        // fired event
        m.setAll(DataFactory.getTestItems());
        return m;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
