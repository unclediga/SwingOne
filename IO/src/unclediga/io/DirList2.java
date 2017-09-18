package unclediga.io;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirList2 {

    static final String initPath = "C:/TEMP";

    public static void main(String[] args) throws Exception {

        printDir(initPath, 0);

    }

    public static void prn(int level, String msg) {
        System.out.println("                 ".substring(0, level) + msg);
    }

    private static void printDir(String path, int level) throws Exception {

        Path pa = Paths.get(path);
        DirectoryStream<Path> stream = Files.newDirectoryStream(pa);

        for (Path p : stream) {
//            File f = p.toFile();
//            if (f.isDirectory()) {

            if (Files.isDirectory(p)) {
//                prn(level, "[D] " + f.getCanonicalPath());
//                printDir(f.getCanonicalPath(),level + 1);
                prn(level, "[D] " + p);
                printDir(p.toAbsolutePath().toString(),level + 1);

            }else{
                prn(level, "[F] " + p);

            }
        }
        stream.close();

    }
}
