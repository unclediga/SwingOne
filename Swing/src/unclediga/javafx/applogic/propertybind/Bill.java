package unclediga.javafx.applogic.propertybind;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

class Bill {
    private DoubleProperty amountDue = new SimpleDoubleProperty();

    public double getAmountDue() {
        return amountDue.get();
    }

    public void setAmountDue(double amountDue) {
        this.amountDue.set(amountDue);
    }

    public DoubleProperty amountDueProperty() {
        return amountDue;
    }
}
