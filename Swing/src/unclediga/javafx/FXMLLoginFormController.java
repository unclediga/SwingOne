package unclediga.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class FXMLLoginFormController {

    @FXML
    private Text actiontarget;

    @FXML
    private void handleSubmitButtonAction(ActionEvent actionEvent) {
        actiontarget.setText("Hello!");

    }
}
