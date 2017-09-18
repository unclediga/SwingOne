package unclediga.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytesBuffered {

    private static String IN_FILE = "data/pushkin.txt";
    private static String OUT_FILE = "data/out_buff.txt";

    public static void main(String[] args) throws IOException {

        BufferedInputStream inputStream = null;
        FileOutputStream outputStream = null;
        int cnt = 0;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(IN_FILE),8000);
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
