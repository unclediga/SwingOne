package unclediga.jdbc;

public class JDBCUtil {

    public static boolean init() {
        return true;
    }

    public static String findSSNbyAcc(String acc) {
        return acc != null ? acc.substring(9,15) + "-" + acc.substring(15) : "no";
    }

    public static void close() {

    }
}
