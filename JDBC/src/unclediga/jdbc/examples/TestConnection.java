package unclediga.jdbc.examples;

import oracle.jdbc.driver.OracleDriver;
import unclediga.config.ConfigManager;

import java.sql.*;

public class TestConnection {

    public static void main(String[] args) throws SQLException {

        if(!ConfigManager.readConfig()){
            System.err.println("Can't read config info");
            System.exit(1);
        }


        DriverManager.registerDriver(new OracleDriver());

        Connection connection = DriverManager.getConnection(ConfigManager.getDbURI(),ConfigManager.getDbUser(),ConfigManager.getDbPass());
        if(connection != null){
            System.out.println("Connection is good!");
            String query = "SELECT object_name " +
                    "FROM user_objects " +
                    "WHERE object_type = ?" +
                    "AND object_name like ?";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1,"TABLE");
            st.setString(2,"%EMP%");

            ResultSet rs = st.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
            rs.close();
            st.close();
            connection.close();
        }else{
            System.out.println("No...");
        }
    }

}
