import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    public Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://Documents and Settings/user/Moje dokumenty/sqlite/lol";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    ArrayList <String> towary = new ArrayList<String>();
    ArrayList <String> opinie = new ArrayList<String>();

    public void selectAll(){
        String sqlTowary = "SELECT id, Marka, Model, DodatkoweUwagi, Typ FROM towary";
        String sqlOpinie = "SELECT wady, Zalety, PodsumowanieOpinii, LiczbaGwiazdek, Data, idTowaru, Autor, Polecam, opiniaPrzydatna, opiniaNiePrzydatna FROM opinie";

        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sqlTowary);
            // loop through the result set
            while (rs.next()) {
                towary.add(
                rs.getInt("id") +  "\t" +
                        rs.getString("Marka") + "\t" +
                        rs.getString("Model")+ "\t" +
                        rs.getString("DodatkoweUwagi")+ "\t" +
                        rs.getString("Typ")
                );
            }
            Statement stmtO  = conn.createStatement();
            ResultSet rsO    = stmt.executeQuery(sqlOpinie);

            while (rs.next()) {
                towary.add(
                        rs.getInt("id") +  "\t" +
                                rs.getString("wady") + "\t" +
                                rs.getString("Zalety")+ "\t" +
                                rs.getString("PodsumowanieOpinii")+ "\t" +
                                rs.getString("Data")+ "\t" +
                                rs.getString("idTowaru")+ "\t" +
                                rs.getString("Autor")+ "\t" +
                                rs.getString("Polecam")+ "\t" +
                                rs.getString("opiniaPrzydatna")+ "\t" +
                                rs.getString("opiniaNiePrzydatna")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete() {
        String sqlOpinie = "DELETE FROM opinie";
        String sqlTowary = "DELETE FROM towary";

        try  {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sqlOpinie);
            // set the corresponding param

            // execute the delete statement
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement(sqlTowary);
            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(String id, String marka, String model, String dodatkoweUwagi,
                                 String typ, String wady, String zalety, String podsumowanie,
                                 String liczbaGwiazdek, String data, String autor, String polecam,
                                 String opiniaPrzydatna, String opiniaNieprzdatna) {
        String sqlTowary = "INSERT INTO towary(id, Marka, Model, DodatkoweUwagi, Typ) VALUES(?,?,?,?,?)";
        String sqlOpinie = "INSERT INTO opinie(wady, Zalety, PodsumowanieOpinii, LiczbaGwiazdek, Data, idTowaru, Autor, Polecam, opiniaPrzydatna, opiniaNiePrzydatna) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sqlTowary);
            pstmt.setString(1, id);
            pstmt.setString(2, marka);
            pstmt.setString(3, model);
            pstmt.setString(4, dodatkoweUwagi);
            pstmt.setString(5, typ);
            pstmt.executeUpdate();

            PreparedStatement pstmtOpinie = conn.prepareStatement(sqlOpinie);
            pstmtOpinie.setString(1, wady);
            pstmtOpinie.setString(2, zalety);
            pstmtOpinie.setString(3, podsumowanie);
            pstmtOpinie.setString(4, liczbaGwiazdek);
            pstmtOpinie.setString(5, data);
            pstmtOpinie.setString(6, id);
            pstmtOpinie.setString(7, autor);
            pstmtOpinie.setString(8, polecam);
            pstmtOpinie.setString(9, opiniaPrzydatna);
            pstmtOpinie.setString(10, opiniaNieprzdatna);
            pstmtOpinie.executeUpdate();


conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

}
