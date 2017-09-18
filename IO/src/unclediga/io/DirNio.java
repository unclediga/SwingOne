package unclediga.io;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


public class DirNio {

    private static FileWriter fileWriter;


    public static void main(String[] args) throws IOException {

//        for (int i = 0; i < args.length; i++) {
//            System.out.println("arg " + args[i]);
//        }
        fileWriter = new FileWriter("dir.txt");

        if (args.length < 3 || !args[1].equals("-name")) {
            usage();
            System.exit(1);
        }

        final Path startPath = Paths.get(args[0]);
        //Спасаюсь от IDE
        String pattern = args[2].replace('\'',' ').trim();
        System.out.println("pattern "+pattern);
        final Finder visitor = new Finder(pattern);
        Files.walkFileTree(
                startPath,
                visitor
        );

        System.out.println(visitor.getResults());

        if (fileWriter != null) {
            fileWriter.close();
        }

    }

    private static void usage() {
        System.out.println("java DirNio <path> " +
                "-name \"<glob:pattern>\"");

    }

    private static class Finder extends SimpleFileVisitor<Path> {
        private final PathMatcher matcher;
        private long cnt = 0;
        private double val = 0.0;

        public Finder(String pattern) {

            this.matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            if (exc instanceof AccessDeniedException) {
                System.err.println("Ошибочка по аксессу" + file);
                return FileVisitResult.CONTINUE;
            }
            return FileVisitResult.CONTINUE;

        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

            System.out.println("[" + dir.getFileName() + "] -----------------------");
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

            Path fileName = null;

            fileName = file.getFileName();

            if (fileName != null && matcher.matches(fileName)) {
//                System.out.println(file.getFileName());
                cnt++;
                val += attrs.size();
                fileWriter.write(fileName.toString()+" : " + attrs.size()+"\n");
            }
            return FileVisitResult.CONTINUE;

        }

        public String getResults() {

            double v = val / 1024 / 1024;

            return String.format("Files : %d %10.2f Мб", cnt, v);

        }
    }
}
