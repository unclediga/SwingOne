package unclediga.poi;

import java.util.ArrayList;
import java.util.List;

public class DataFactory {

    private static List<ExcelPerson> list = new ArrayList<>(100);


    public static void setItem(ExcelPerson p) {
        if (list == null) {
            list = new ArrayList<>(100);
        }
        list.add(p);
    }

    public static List<ExcelPerson> getItems() {
        return list;
    }

    public static Double getSum() {

        if (list == null) {
            return 0.0;
        }

        double sum = 0;

        for (ExcelPerson p : list) {
            sum += p.getSum();
        }
        return sum;
    }

    public static int getCount() {
        return list != null ? list.size() : 0;
    }

    public static List<ExcelPerson> getTestItems() {

        setItem(new ExcelPerson("Ivanov Ivan", "Ivanovo", "40701810000000000001", 101.0));
        setItem(new ExcelPerson("Petrov Ivan", "Petrovo", "40701810000000000002", 102.1));
        setItem(new ExcelPerson("Sidorov Ivan", "Sidorovo", "40701810000000000003", 333.33));

        return getItems();
    }

}
