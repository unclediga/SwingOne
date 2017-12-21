package unclediga.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static String dbURI;
    private static String dbUser;
    private static String dbPass;

    public static String getDbURI() {
        return dbURI;
    }

    public static String getDbUser() {
        return dbUser;
    }

    public static String getDbPass() {
        return dbPass;
    }

    public static boolean readConfig() {


        Properties properties = new Properties();
        try {
            properties.load(new FileReader("c:/TEMP/poi/config-example.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        dbURI = properties.getProperty("jdbc-uri");
        dbUser = properties.getProperty("jdbc-user");
        dbPass = properties.getProperty("jdbc-pass");

        return true;
    }
}
