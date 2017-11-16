package unclediga.javafx.applogic.propertybind;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ChangeListenerExample {

    public static void main(String[] args) {

        Bill electricBill = new Bill();

        electricBill.amountDueProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        System.out.printf("Electric Bill was changed from %f to %f\n",oldValue,newValue);
                    }
                }
        );

        System.out.printf("try change value to %f\n",100.0);
        electricBill.setAmountDue(100.0);
    }


}
