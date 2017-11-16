package unclediga.javafx.applogic.propertybind;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class LowLevBindingExample {

    public static void main(String[] args) {

        DoubleProperty d1 = new SimpleDoubleProperty(1);
        DoubleProperty d2 = new SimpleDoubleProperty(2);
        DoubleProperty d3 = new SimpleDoubleProperty(3);
        DoubleProperty d4 = new SimpleDoubleProperty(4);


        DoubleBinding binding = new DoubleBinding() {

            {
                super.bind(d1, d2, d3, d4);
            }


            @Override
            protected double computeValue() {
                return d1.get() + d2.get() + d3.get() + d4.get();
            }
        };

        System.out.println("binding = " + binding.get());
        d1.set(1000);
        System.out.println("binding = " + binding.get());

    }
}
