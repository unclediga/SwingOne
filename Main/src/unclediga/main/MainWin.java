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
import javafx.scene.text.TextAlignment;
import javafx.stage.*;
import javafx.util.StringConverter;
import unclediga.data.DataFactory;
import unclediga.data.ExcelPerson;
import unclediga.poi.ExcelParser;

import java.io.File;


public class MainWin extends Application {

    private SimpleIntegerProperty totalCountExcelProperty = new SimpleIntegerProperty(0);
    private SimpleDoubleProperty totalSumExcelProperty = new SimpleDoubleProperty(0.0);
    private SimpleStringProperty errMsgProperty = new SimpleStringProperty();
    private File initPathExcel = new File("C:/TEMP");
    private File initPathXml = new File("C:/TEMP");
    private SimpleObjectProperty<File> fileExcelProperty = new SimpleObjectProperty<>(initPathExcel);
    private SimpleObjectProperty<File> fileXmlProperty = new SimpleObjectProperty<>(initPathXml);
    private ObservableList<ExcelPerson> l;
    private Stage detailsStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button butOpenExcel = new Button("...");
        butOpenExcel.setMinSize(60, 20);
        butOpenExcel.setFont(Font.font(10));
        butOpenExcel.setOnAction(event -> {
            FileChooser dialog = new FileChooser();
            dialog.setInitialDirectory(initPathExcel);
            File file = dialog.showOpenDialog(new Popup());
            if (file != null) {
                fileExcelProperty.set(file);
                initPathExcel = file.getParentFile();
            }
        });

        Button butOpenXml = new Button("...");
        butOpenXml.setMinSize(60, 20);
        butOpenXml.setFont(Font.font(10));
        butOpenXml.setOnAction(event -> {
            FileChooser dialog = new FileChooser();
            dialog.setInitialDirectory(initPathExcel);
            File file = dialog.showOpenDialog(new Popup());
            if (file != null) {
                fileXmlProperty.set(file);
                initPathXml = file.getParentFile();
            }
        });

        Button butProcessExcel = new Button("Process Excel");
        butProcessExcel.setMinSize(60, 20);
        butProcessExcel.setFont(Font.font(10));
        butProcessExcel.setOnAction(event -> {
            try {
                l.clear();
                l.addAll(ExcelParser.parse(fileExcelProperty.getValue()));
            } catch (Exception e) {
                errMsgProperty.setValue("Error process Excel");
                e.printStackTrace();
            }
        });

        Button butProcessXml = new Button("Process XML");
        butProcessXml.setMinSize(60, 20);
        butProcessXml.setFont(Font.font(10));
        butProcessXml.setOnAction(event -> {
            try {
                l.clear();
                l.addAll(ExcelParser.parse(fileExcelProperty.getValue()));
            } catch (Exception e) {
                errMsgProperty.setValue("Error process XML");
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

        Button butShowDetails = new Button("det");
        butShowDetails.setMinSize(60, 20);
        butShowDetails.setFont(Font.font(10));
        butShowDetails.setOnAction(event -> {
            System.out.println("Show");
            detailsStage.show();
        });

        Label lbExcelFilePath = new Label("<..file path..>");
        lbExcelFilePath.setPrefWidth(600);
        lbExcelFilePath.setTextFill(Color.BLUE);
        lbExcelFilePath.setFont(Font.font("Courier New", 12));
        lbExcelFilePath.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
        lbExcelFilePath.textProperty().bind(fileExcelProperty.asString());


        Label lbXmlFilePath = new Label("<..file path..>");
        lbXmlFilePath.setPrefWidth(600);
        lbXmlFilePath.setTextFill(Color.BLUE);
        lbXmlFilePath.setFont(Font.font("Courier New", 12));
        lbXmlFilePath.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN)));
        lbXmlFilePath.textProperty().bind(fileXmlProperty.asString());

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

        tableView.getColumns().addAll(col1, col2, col3, col4, col5);

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

        Text txtExcelCount = new Text();
        txtExcelCount.setFont(Font.font("Consolas", FontWeight.BOLD, 12));
        txtExcelCount.textProperty().bind(new SimpleStringProperty("Работников: ").concat(totalCountExcelProperty.asString()));
        Text txtExcelSum = new Text();
        txtExcelSum.textProperty().bind(new SimpleStringProperty("Сумма: ").concat(totalSumExcelProperty.asString()));
        txtExcelSum.setFont(Font.font("Consolas", FontWeight.BOLD, 12));

        Text txtErrMsg = new Text();
        txtErrMsg.setFont(Font.font("Consolas", FontWeight.BOLD, 12));
        txtErrMsg.textProperty().bind(errMsgProperty);
        txtErrMsg.setTextAlignment(TextAlignment.LEFT);

        errMsgProperty.setValue("Error in super-puper\nSecond string\nThird");


        tableView.setItems(l);


        VBox detaisVbox = new VBox(10, tableView);
        Scene detailsScene = new Scene(detaisVbox, 600, 300);
        detailsStage.setScene(detailsScene);


        HBox hbox1 = new HBox();
        hbox1.setSpacing(1);
//        hbox.setPadding(new Insets(10));
        hbox1.setAlignment(Pos.CENTER_LEFT);
        hbox1.getChildren().addAll(lbExcelFilePath, butOpenExcel);

        HBox hbox11 = new HBox();
        hbox11.setSpacing(10);
        hbox11.setPadding(new Insets(10));
        hbox11.setAlignment(Pos.CENTER_RIGHT);
        hbox11.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        hbox11.getChildren().addAll(txtExcelCount, txtExcelSum);


        HBox hbox2 = new HBox();
        hbox2.setSpacing(1);
//        hbox.setPadding(new Insets(10));
        hbox2.setAlignment(Pos.CENTER_LEFT);
        hbox2.getChildren().addAll(lbXmlFilePath, butOpenXml);


        HBox hbox3 = new HBox();
        hbox3.setSpacing(10);
        hbox3.setPadding(new Insets(10));
        hbox3.setAlignment(Pos.CENTER_LEFT);
        hbox3.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
        hbox3.getChildren().addAll(txtErrMsg);


        GridPane filePane = new GridPane();
        filePane.setHgap(5);
        filePane.setVgap(10);
        filePane.setPadding(new Insets(3, 20, 3, 20));

        Label lbExcel = new Label("Файл Excel:");
        Label lbXml = new Label("Файл XML:");

        filePane.add(lbExcel, 0, 0);
        filePane.add(hbox1, 0, 1);
        filePane.add(hbox11, 0, 2);
        filePane.add(lbXml, 0, 3);
        filePane.add(hbox2, 0, 4);
        filePane.add(hbox3, 0, 5);

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(5);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.getChildren().addAll(butProcessExcel, butProcessXml);


        BorderPane mainPane = new BorderPane(filePane, null, null, buttonBox, null);


        Scene scene = new Scene(mainPane, 600, 265);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private ObservableList<ExcelPerson> getData() {
        ObservableList<ExcelPerson> m = FXCollections.observableArrayList();
        m.addListener((InvalidationListener) observable -> {
                    totalCountExcelProperty.set(DataFactory.getCount());
                    totalSumExcelProperty.set(DataFactory.getSum());
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
