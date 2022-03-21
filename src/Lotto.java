import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class Lotto {

    private static Vector<String> numbers;

    public static void main(String[] args) throws Exception {
        // A kapcsolat ellenőrzésére kell csak
        // new ConnectDatabase();

        numbers = new Vector<>();
        Reader reader = new Reader();
        numbers = reader.readFile();
        ConnectDatabase connDb = new ConnectDatabase();
        Connection conn = connDb.getConnection();
        // numbers.forEach(rows -> System.out.println(rows)); // forEach-nek a lambdas megoldasa

        /*
         * for (String name : names) {
         *      System.out.println(name);
         * }
         * 
         * ez a kettő ugyanaz csak a második az lambdás
         * 
         * names.forEach(name -> {
         *      System.out.println(name);
         * });
         */
        numbers.forEach(rows -> rowsToDatabase(conn, rows)); // adatbázisba írás ciklussal
    }

    private static void rowsToDatabase(Connection conn, String row) {

        String sql = "INSERT INTO drawed (draw) VALUES ('" + row + "');"; // amikor stringet írunk ki akkor aposztrofok között kell lennie
                                                                          
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            /*printStackTrace() Ez egy nagyon egyszerű, de nagyon hasznos eszköz a kivételek diagnosztizálására. 
            Megmondja, hogy mi történt, és a kódban hol történt ez a hiba, ha egyátalán történt valami.*/
            ex.printStackTrace();
        }

    }
}
