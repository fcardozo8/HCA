package DB;


import java.sql.*;

/**
 * Recipe 07-02 - Creating a Connection
 *
 * @author juneau
 */
public final class DBConnectionINSERT {
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
            DBConnectionINSERT.getConnection();

            String sql = "INSERT INTO usuario (username, email, password, create_time, idusuario)";
            sql += "VALUES(?,?,?,?,?)";

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, "diebo");
            pstm.setString(2,"d35@gmail.com");
            pstm.setString(3,"123123");
            pstm.setDate(4, new Date(122313133));
            pstm.setInt(5, 1);
            int rtdo = pstm.executeUpdate();
            if(rtdo == 1){
                System.out.println("fila 1 insertada");
            }
            else{
                throw new RuntimeException(("No se pudo insertar la fila"));
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