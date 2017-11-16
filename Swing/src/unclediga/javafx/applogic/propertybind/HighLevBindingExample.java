package unclediga.javafx.applogic.propertybind;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.math.BigDecimal;

public class HighLevBindingExample {

    public static void main(String[] args) {

        IntegerProperty n1 = new SimpleIntegerProperty(1);
        IntegerProperty n2 = new SimpleIntegerProperty(2);

        // var 1
        NumberBinding sum = n1.add(n2);
        System.out.println("sum value is "+sum.getValue());
        n1.set(2);
        System.out.println("sum value is "+sum.getValue());

        //  var 2
        NumberBinding sum2 = Bindings.add(n1, n2);
        System.out.println("sum2 value is "+sum2.getValue());
        n1.set(3);
        System.out.println("sum2 value is "+sum2.getValue());

        // combine var 1 + var 2
        IntegerProperty n3 = new SimpleIntegerProperty(10);
        IntegerProperty n4 = new SimpleIntegerProperty(20);
        NumberBinding total = Bindings.add(n1.multiply(n2), n3.multiply(n4));
        System.out.printf("n1=%d n2=%d n3=%d n4=%d  total=%d", n1.get(), n2.get(), n3.get(), n4.get(),total.getValue());
        n1.set(6);
        n4.set(13);
        System.out.printf("n1=%d n2=%d n3=%d n4=%d  total=%d", n1.get(), n2.get(), n3.get(), n4.get(), total.getValue());
    }
}
