package unclediga.io;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class DirList1 {

    static final String initPath = "C:/TEMP";

    public static void main(String[] args) throws Exception {

        printDir(initPath, 0);

    }

    public static void prn(int level, String msg) {
        System.out.println("                 ".substring(0, level) + msg);
    }

    private static void printDir(String path, int level) throws Exception {

        File file = new File(path);
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                prn(level, "[D] " + f.getCanonicalPath());
                printDir(f.getCanonicalPath(),level + 1);
            } else {
                prn(level, "[F] " + f.getCanonicalPath());
            }
        }
    }
}
