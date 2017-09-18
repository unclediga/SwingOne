package unclediga.io;

import java.io.*;

public class CopyCharacters {

    private static String IN_FILE = "data/pushkinUTF8.txt";
    private static String OUT_FILE = "data/out3.txt";

    public static void main(String[] args) throws IOException {

        Reader reader = null;
        Writer writer = null;
        int cnt;
        try {
            reader = new FileReader(IN_FILE);
            writer = new FileWriter(OUT_FILE);

            int c;
            cnt = 0;
            while ((c = reader.read()) != -1) {
                writer.write(c);
                cnt++;
            }

        } finally {
            if (reader != null) reader.close();

            if (writer != null) writer.close();

        }

        System.out.println("chars r/w : " + cnt);

    }
}
