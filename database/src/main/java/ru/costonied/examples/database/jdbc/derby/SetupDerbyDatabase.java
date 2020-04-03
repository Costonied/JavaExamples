package ru.costonied.examples.database.jdbc.derby;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Setup the JavaDB (Derby database).
 * The DDL statements taken from book "OCP Java SE8 Programmer II"
 */
public class SetupDerbyDatabase {

    private static String URL = "jdbc:derby:./target/db/zoo;create=true";

    private static String sqlCreateTableSpecies =
            "CREATE TABLE species (" +
                    "id INTEGER PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "num_acres DECIMAL)";

    private static String sqlCreateTableAnimal =
            "CREATE TABLE animal (" +
                    "id INTEGER  PRIMARY KEY," +
                    "species_id INTEGER," +
                    "name VARCHAR(255)," +
                    "date_born TIMESTAMP)";

    public static void main(String[] args) throws Exception {

        try(Connection conn = DriverManager.getConnection(URL);
            Statement statement = conn.createStatement()) {

            DerbyHelper.executeUpdate(statement, sqlCreateTableSpecies);
            DerbyHelper.executeUpdate(statement, sqlCreateTableAnimal);

            DerbyHelper.executeUpdate(statement, "INSERT INTO species VALUES (1, 'African Elephant', 7.5)");
            DerbyHelper.executeUpdate(statement, "INSERT INTO species VALUES (2, 'Zebra', 1.2)");
            DerbyHelper.executeUpdate(statement, "INSERT INTO animal VALUES (1, 1, 'Elsa', '2001-05-06 02:15:00')");
            DerbyHelper.executeUpdate(statement, "INSERT INTO animal VALUES (2, 2, 'Zelda', '2002-08-15 09:12:00')");
            DerbyHelper.executeUpdate(statement, "INSERT INTO animal VALUES (3, 1, 'Ester', '2002-09-09 10:36:00')");
            DerbyHelper.executeUpdate(statement, "INSERT INTO animal VALUES (4, 1, 'Eddie', '2010-06-08 01:24:00')");
            DerbyHelper.executeUpdate(statement, "INSERT INTO animal VALUES (5, 2, 'Zoe', '2005-11-12 03:44:00')");
        }
    }
}
