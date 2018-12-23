package controllers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectorDB {

    private final static String url = "jdbc:mysql://localhost:3306/hotelsystem";
    private final static String username = "root";
    private final static String password = "";
    Connection connection;

    public ConnectorDB() throws SQLException {
        boolean confirm = true;

        do {
            try {
                this.connection = DriverManager.getConnection(url, username, password);

                confirm = false;
            } catch (SQLException e) {
                switch (JOptionPane.showConfirmDialog(null, "Try again for reconnection?", "Can not connect to databases", JOptionPane.YES_NO_OPTION)) {
                    case JOptionPane.YES_OPTION:

                        break;
                    case JOptionPane.NO_OPTION:
                        confirm = false;
                        break;
                    default:
                        confirm = false;

                        throw new IllegalStateException("Cannot acces to this database", e);

                }

            }

        } while (confirm);

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
