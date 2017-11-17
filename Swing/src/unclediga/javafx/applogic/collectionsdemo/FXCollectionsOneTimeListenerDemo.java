package unclediga.javafx.applogic.collectionsdemo;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FXCollectionsOneTimeListenerDemo {


    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("D");
        list.add("C");
        list.add("B");
        list.add("A");

        ObservableList<String> observableList = FXCollections.observableList(list);
        observableList.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                /* Error,  Why ??? */
//                if (c.wasPermutated()) {
//                    System.out.println("cange list is " + c.getList());
//                }
                System.out.println("change happened! " + c);
            }
        });


        // will have ONLY ONE change message
        System.out.println("sort by FXCollections");
        FXCollections.sort(observableList);
        System.out.println(list);
        // will have ONE change messages
        list.set(0, "D");
        list.set(1, "C");
        list.set(2, "B");
        list.set(3, "A");
        System.out.println("sort by Collections");
        Collections.sort(observableList);
        System.out.println(list);

        // will have ONLY ONE change message
        System.out.println("reverse by FXCollections");
        FXCollections.reverse(observableList);
        System.out.println(list);
        // will have FOUR change messages
        list.set(0, "D");
        list.set(1, "C");
        list.set(2, "B");
        list.set(3, "A");
        System.out.println("reverse by Collections");
        Collections.reverse(observableList);
        System.out.println(list);
    }

}
