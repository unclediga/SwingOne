package unclediga.javafx.applogic.collectionsdemo;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.Map;

public class ObservableMapDemo {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();

        ObservableMap<String,String> observableMap = FXCollections.observableMap(map);
        observableMap.addListener(new MapChangeListener<String,String>() {
             @Override
              public void onChanged(Change change) {

                 System.out.println("map was changed " + change);
             }
        });


        observableMap.put("key 1", "value 1");
        observableMap.put("key 2", "value 2");
        map.put("key 1", "value 1");
        map.put("key 2", "value 2");

        System.out.println("map size " + map.size());
    }
}
