package unclediga.myjfx.cell;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.HashMap;


public class MyTableCell2 extends Application {

    private final Image img_ace = new Image("img/ace.gif");
    private Image img_king = null;
    private Image img_queen = null;
    private Image img_jack = null;
    private ObservableList<Image> imgList;
    private ComboBox choiceBox = new ComboBox();
    private HashMap<String, Image> imgMap = null;
    private ObservableList valList;


    @Override
    public void start(Stage primaryStage) throws Exception {


        img_king = new Image("img/king.gif");
        img_queen = new Image("img/queen.gif");
        img_jack = new Image("img/jack.gif");

//        img = new Image(getClass().getResourceAsStream("img/ace.gif"));

        imgList = FXCollections.observableArrayList(
                img_ace,
                img_king,
                img_queen,
                img_jack
        );

        imgMap = new HashMap<>();
        imgMap.put("ACE", img_ace);
        imgMap.put("KING", img_king);
        imgMap.put("QUEEN", img_queen);
        imgMap.put("JACK", img_jack);

        choiceBox.setItems(imgList);


        ObservableList<Pair> data = FXCollections.observableArrayList(
                new Pair("id 1", new Boolean(true)),
                new Pair("id 2", null),
                new Pair("id A", "ACE"),
                new Pair("id K", "KING"),
                new Pair("id Q", "QUEEN"),
                new Pair("id J", "JACK")
        );

        TableView<Pair> tableView = new TableView<>(data);
        tableView.setEditable(true);
        tableView.setPrefWidth(150);
        TableColumn<Pair, String> col1 = new TableColumn<>("Pair key");
        TableColumn<Pair, String> col2 = new TableColumn<>("Pair val");
        TableColumn<Pair, String> col3 = new TableColumn<>("Val std");
        TableColumn<Pair, Object> col4 = new TableColumn<>("Val custom");

        col1.setPrefWidth(60);
        col2.setPrefWidth(50);
        col3.setPrefWidth(50);
        col4.setPrefWidth(50);
        tableView.getColumns().addAll(col1, col2, col3, col4);


//        col1.setCellValueFactory(new Col1ValueFactory());
//        col2.setCellValueFactory(new Col2ValueFactory());
//        col2.setCellFactory(new Col1Factory());


        /////////// COLUMN 1 //////////////////////
        col1.setCellValueFactory(new PropertyValueFactory<Pair, String>("key"));

        /////////// COLUMN 2 //////////////////////
        col2.setCellValueFactory(new PropertyValueFactory<Pair, String>("val"));

        /////////// COLUMN 3 //////////////////////

        valList = FXCollections.observableArrayList(
                "ACE",
                "KING",
                "QUEEN",
                "JACK"
        );

        col3.setCellValueFactory(new PropertyValueFactory<>("val"));
        col3.setCellFactory(ChoiceBoxTableCell.forTableColumn(valList));
        col3.setOnEditCommit(event -> {
            event.getRowValue().setVal(event.getNewValue());
            System.out.println("SET COL3 VAL=" + event.getNewValue());
        });

        /////////// COLUMN 4 //////////////////////


        col4.setCellValueFactory(new PropertyValueFactory<>("val"));

        col4.setCellFactory(new Col4CellFactory());
        col4.setOnEditCommit(event -> {
            event.getRowValue().setVal(event.getNewValue());
            System.out.println("SET VAL COL4 : " + event.getNewValue());
        });


        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setVgrow(tableView, Priority.ALWAYS);
        Scene scene = new Scene(vBox, 300, 500);
        vBox.getChildren().add(tableView);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//    class String2Image extends StringConverter<Image> {
//
//        @Override
//        public String toString(Image object) {
//            if (object == null) {
//                return "";
//            } else if (img_ace == object) {
//                return "Ace";
//            } else if (img_king == object) {
//                return "King";
//            } else if (img_queen == object) {
//                return "Queen";
//            } else if (img_jack == object) {
//                return "<Jack>";
//            }
//            return "";
//        }
//
//        @Override
//        public Image fromString(String string) {
//            if ("Ace".equals(string)) {
//                return img_ace;
//            } else if ("King".equals(string)) {
//                return img_king;
//            } else if ("Queen".equals(string)) {
//                return img_queen;
//            } else if ("<Jack>".equals(string)) {
//                return img_jack;
//            } else return null;
//
//        }
//    }

    public static void main(String[] args) {
        launch(args);
    }


    private class Col4CellFactory implements Callback<TableColumn<Pair, Object>, TableCell<Pair, Object>> {

        @Override
        public TableCell<Pair, Object> call(TableColumn<Pair, Object> param) {


            return new TableCell<Pair, Object>() {

                private ComboBox comboBox;

                @Override
                public void startEdit() {
                    super.startEdit();
                    System.out.println("startEdit -> cell idx : " + getIndex());
                    System.out.println("startEdit -> cell item : " + getItem());
                    comboBox = new ComboBox(valList);
                    comboBox.setCellFactory(param1 -> new ImageListCell());
                    comboBox.setButtonCell(new ImageListCell());
                    comboBox.getSelectionModel().select(getItem());

                    comboBox.selectionModelProperty().addListener((observable, oldValue, newValue) -> {
                        System.out.println("change old " + oldValue + " new " + newValue);
                    });

                    comboBox.setOnAction(event -> {
                        System.out.println("action " + event.toString());
                        commitEdit(comboBox.getValue());
                    });

//                    comboBox.focusedProperty().addListener(
//                            (observable, oldValue, newValue) -> {
//                                if (!newValue){
//
//                                    System.out.println("focus: old "+oldValue + " new "+newValue);
//                                    System.out.println("combo val "+comboBox.getValue());
//                                    commitEdit(comboBox.getValue());
//                                }
//                            }
//                    );


                    setGraphic(comboBox);
                    setText(null);
                }

                @Override
                public void cancelEdit() {
                    super.cancelEdit();
                    comboBox = null;
                    updateItem(getItem(), false);
                    System.out.println("cancelEdit");
                }

                @Override
                public void commitEdit(Object newValue) {
                    super.commitEdit(newValue);
                    comboBox = null;
                    setItem(newValue);
                    updateItem(newValue, false);
                    System.out.println("commitEdit with Val:" + newValue);
                }

                @Override
                protected void updateItem(Object item, boolean empty) {

                    super.updateItem(item, empty);

//                    System.out.println("updateItem");
                    if (item != null && item instanceof String) {
//      String val = new String2Image().toString(item);
//                        ((Pair) getTableRow().getItem()).setVal(val);
                        setGraphic(new ImageView(imgMap.get(item)));
                        setText(null);

//                    } else if(empty) {
//                        setGraphic(null);
//                        setText("<..>");
                    } else {
                        super.updateItem(null, empty);
                        setGraphic(null);
                        setText(null);
                    }
                }
            };
        }
    }

    private class ImageListCell extends ListCell<String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
//            System.out.println("ListCell -> item "+item);;
            setGraphic(null);
            setText(null);
            if (item != null) {
                setGraphic(new ImageView(imgMap.get(item)));
                setText(item);
            }
        }
    }
}

