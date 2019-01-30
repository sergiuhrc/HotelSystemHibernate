package controllers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectorDB {

    private final static String url = "jdbc:mysql://localhost:3306/hotelsystem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final static String username = "root";
    private final static String password = "";
    Connection connection;

    public ConnectorDB() throws SQLException {

            try {

                this.connection = DriverManager.getConnection(url, username, password);


            } catch (SQLException e) {
                switch (JOptionPane.showConfirmDialog(null, "Try again for reconnection?", "Can not connect to databases", JOptionPane.YES_NO_OPTION)) {
                    case JOptionPane.YES_OPTION:

                        break;
                    case JOptionPane.NO_OPTION:

                        break;
                    default:


                        throw new IllegalStateException("Cannot acces to this database", e);

                }

            }



    }



    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
