package unclediga.javafx.applogic.propertybind;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;

public class InvalidationListenerExample {

    public static void main(String[] args) {

        Bill bill1 = new Bill();
        Bill bill2 = new Bill();
        Bill bill3 = new Bill();

        NumberBinding total = Bindings.add(
                bill1.amountDueProperty().add(bill2.amountDueProperty()),
                bill3.amountDueProperty());

        total.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                System.out.println("The binding Invalid");
            }
        });

        // binding good
        bill1.setAmountDue(100.0);
        // binding invalid...
        bill2.setAmountDue(200.0);
        // binding invalid (but listener is silent )
        bill3.setAmountDue(300.0);

        System.out.println("total = " + total.getValue());
        // binding good
        bill1.setAmountDue(0);
        // binding invalid...
        System.out.println("total = " + total.getValue());
        // binding good

    }
}
