package unclediga.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytes {

    private static String IN_FILE = "data/pushkin.txt";
    private static String OUT_FILE = "data/out2.txt";

    public static void main(String[] args) throws IOException {

        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        int cnt = 0;
        try {
            inputStream = new FileInputStream(IN_FILE);
            outputStream = new FileOutputStream(OUT_FILE);
            int b;
            while ((b = inputStream.read()) != -1) {
                outputStream.write(b);
                cnt++;
            }
        } finally {
            if (inputStream != null) {

                inputStream.close();
            }
            if (outputStream != null) {

                outputStream.close();
            }
            System.out.println("bytes r/w : " + cnt);

        }
    }
}
