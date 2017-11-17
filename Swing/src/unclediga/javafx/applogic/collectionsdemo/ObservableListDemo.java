package unclediga.javafx.applogic.collectionsdemo;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ObservableListDemo {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        ObservableList<String> observableList = FXCollections.observableList(list);
        observableList.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {

                System.out.println("List was changed " + c);
            }
        });

        // listener work
        observableList.add("first");
        observableList.add("second");
        // listener NOT work
        list.add("first");
        list.add("second");

        System.out.println("list size = " + list.size());

    }


}
