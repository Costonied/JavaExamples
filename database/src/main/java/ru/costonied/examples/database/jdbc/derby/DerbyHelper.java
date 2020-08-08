package ru.costonied.examples.database.jdbc.derby;


import java.sql.Statement;
import java.sql.SQLException;

/**
 * Helper to work with Derby DB
 */
public class DerbyHelper {

    /*
       The list of SQL state errors.
       Full list here http://db.apache.org/derby/docs/10.8/ref/rrefexcept71493.html
    */
    private static final String SQLSTATE_ALREADY_EXISTS = "X0Y32";
    private static final String SQLSTATE_DUPLICATE_KEY_VALUE = "23505";

    /**
     * Wrapper under java.sql.Statement.executeUpdate()
     * Useful feature:
     *   - if table not exists then exception will be processed
     * @param sql SQL statement
     * @return result of java.sql.Statement.executeUpdate()
     *         or 0 if Exception was caught
     */
    public static int executeUpdate(Statement statement, String sql) throws SQLException {
        try {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            switch (e.getSQLState()) {
                case SQLSTATE_ALREADY_EXISTS:
                    System.out.println("[WARN] Entity already exists for SQL: " + sql);
                    return 0;
                case SQLSTATE_DUPLICATE_KEY_VALUE:
                    System.out.println("[WARN] Duplicate key value for SQL: " + sql);
                    return 0;
                default:
                    System.out.println("[ERROR] Exception for SQL: " + sql);
                    throw e;
            }
        }
    }
}
