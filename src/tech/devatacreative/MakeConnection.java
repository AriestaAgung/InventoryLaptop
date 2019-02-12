package tech.devatacreative;

import javax.sql.rowset.JdbcRowSet;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MakeConnection {
    public Statement statement;
    public  ResultSet result;
    public Connection connection;
    public PreparedStatement preparedStatement;

    public void  makeConnection() {

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost/toko", "root", "");
            statement = connection.createStatement();
//            result = statement.execute();
//            result = statement.executeQuery("SELECT * FROM laptop");

//            return connection;


        } catch (SQLException e) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, e);
//            return null;
        }
    }
}
