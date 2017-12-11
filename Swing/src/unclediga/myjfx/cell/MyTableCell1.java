package unclediga.myjfx.cell;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;



public class MyTableCell1 extends Application {

    private Image img_ace = null;
    private Image img_king = null;

    @Override
    public void start(Stage primaryStage) throws Exception {


        img_ace = new Image("img/ace.gif");
        img_king = new Image("img/king.gif");
//        img = new Image(getClass().getResourceAsStream("img/ace.gif"));
        ObservableList<Pair> data = FXCollections.observableArrayList(
                new Pair("id 1", new Boolean(true)),
                new Pair("id 2", null),
                new Pair("id 3", new Boolean(false)),
                new Pair("id 4", new Boolean(true)),
                new Pair("id A", "ACE"),
                new Pair("id K", "KING")
        );

        TableView<Pair> tableView = new TableView<>(data);
        tableView.setEditable(true);
        tableView.setPrefWidth(200);
        TableColumn<Pair, String> col1 = new TableColumn<>("column 1");
        TableColumn<Pair, String> col2 = new TableColumn<>("column 2");
        TableColumn<Pair, String> col3 = new TableColumn<>("column 3");
        col1.setPrefWidth(60);
        col2.setPrefWidth(60);
        col3.setPrefWidth(60);
        tableView.getColumns().addAll(col1, col2,col3);


        col1.setCellValueFactory(new Col1ValueFactory());
        col2.setCellValueFactory(new Col2ValueFactory());
        col2.setCellFactory(new Col1Factory());

        col3.setCellValueFactory(new PropertyValueFactory<Pair, String>("key"));
        col3.setCellFactory(TextFieldTableCell.forTableColumn());



        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setVgrow(tableView, Priority.ALWAYS);
        Scene scene = new Scene(vBox, 300, 500);
        vBox.getChildren().add(tableView);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class Col1ValueFactory implements Callback<TableColumn.CellDataFeatures<Pair, String>, ObservableValue<String>> {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Pair, String> param) {
            if(param.getValue() != null){
                return new SimpleStringProperty(param.getValue().getKey());
            }
            return null;
        }
    }

    private class Col2ValueFactory implements Callback<TableColumn.CellDataFeatures<Pair, String>, ObservableValue<String>> {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Pair, String> param) {
            if(param.getValue() != null){
                return new SimpleObjectProperty(param.getValue().getVal());
            }
            return null;
        }
    }

    private class Col1Factory implements Callback<TableColumn<Pair, String>, TableCell<Pair,String>> {
        @Override
        public TableCell call(TableColumn<Pair, String> param) {

            return new TableCell(){
                @Override
                protected void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);

                    if(item == null){
                        return;
                    }
                    if(item instanceof Boolean) {
                        setGraphic(null);
                        setText((Boolean)item ? "YES" : "NO");
                    }else if(item instanceof String){
                        String s = (String) item;
                        if (s.equals("ACE")) {
                            setGraphic(new ImageView(img_ace));
                        }else if (s.equals("KING")){
                            setGraphic(new ImageView(img_king));
                        }else{
                            setGraphic(null);
                        }
                        setText("");
                    }else{
                        setGraphic(null);
                        setText("not Bool");
                    }


                }
            };
        }
    }
}

