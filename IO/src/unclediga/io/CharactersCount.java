package unclediga.io;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CharactersCount {

    private static String IN_FILE = "res/data/pushkin.txt";
    private static String OUT_FILE = "res/data/cnt.txt";


    public static void main(String[] args) throws IOException {

        if(args.length > 0){
            IN_FILE = "data/" + args[0];
        }

        Reader reader = null;
        Writer writer = null;
        int cnt = 0;
        try {
            reader = new FileReader(IN_FILE);

            int c;
            while ((c = reader.read()) != -1) {
                cnt++;
                System.out.println("symbol = " + Character.toString((char) c));
            }


        } catch (FileNotFoundException e) {
            System.err.println("user.dir = " + System.getProperty("user.dir"));
            e.printStackTrace();
        } finally {
            if (reader != null) reader.close();


        }

        System.out.println("chars r/w : " + cnt);

    }
}
