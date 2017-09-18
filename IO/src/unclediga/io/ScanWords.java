package unclediga.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ScanWords {

    private static String IN_FILE = "data/pushkin.txt";

    public static void main(String[] args) throws IOException {


        FileReader reader = null;
        Scanner scanner = null;
        try {
            reader = new FileReader(IN_FILE);
            scanner = new Scanner(reader);
            //scanner.useDelimiter("а"); // делит по букве и проглатывает её
            while (scanner.hasNext()){
                String str = scanner.next();
                System.out.println(str);
            }


        }finally {
            if(scanner != null) scanner.close();
            if (reader != null) reader.close();

        }



    }
}
