package ru.costonied.examples.database.jdbc.derby;


import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 * Example showing how to work with ResultSet
 */
public class ResultSetExample {

    public static void main(String[] args) throws SQLException {

        try(Connection conn = DriverManager.getConnection("jdbc:derby:./target/db/zoo");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM animal")) {

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }

        }

    }
}
