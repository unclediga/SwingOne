package unclediga.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sorter {

    private static final String fileName = "data/input.txt";

    public static void main(String[] args) throws IOException {

        System.out.println(Paths.get(fileName).toAbsolutePath());

        String[] array = loadAndSort(new FileReader(fileName));
        for (String s : array) {
            System.out.println(s);
        }

    }

    public static String[] loadAndSort(Reader in) throws IOException {
        BufferedReader reader = new BufferedReader(in);
        String line;
        List<String> lines = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            if (!line.isEmpty()) {
                lines.add(line);
            }
        }
        reader.close();

        String[] array = lines.toArray(new String[lines.size()]);
        Arrays.sort(array);
        return array;
    }


}
