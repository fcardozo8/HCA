package DB;


import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Recipe 07-02 - Creating a Connection
 *
 * @author juneau
 */
public final class DBConnection {
    // Change the values of these variables to match that of your
    // database

    private static String hostname = "localhost";
    private static String port = "3306";
    private static String database = "mydb";
    private static String username = "root";
    private static String password = "";

    private static Connection conn = null;
    protected static PreparedStatement pstm = null;
    private static ResultSet rs = null;

    public static Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://" + hostname + ":"
                + port + "/" + database;
        conn = DriverManager.getConnection(jdbcUrl, username, password);
        System.out.println("Successfully connected");
        return conn;
    }

    public static void main(String[] args) {
        try {
            DBConnection.getConnection();

            String sql = "select p.name AS Nombre";
            sql+="      , l.language AS Idioma";
            sql+="      from country p, countrylanguage l";
            sql+="      where p.code = l.countrycode;";

            pstm = conn.prepareStatement(sql);

            rs = pstm.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("Nombre")+ ", " +rs.getString("Idioma"));
            }
        } catch (SQLException e) {
            System.err.println("Connection Exception: " + e);
        } finally {
            try{

                System.out.println( conn.getClass().getName() );
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();
                if(conn!=null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}