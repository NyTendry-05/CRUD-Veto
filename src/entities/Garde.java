package entities;
import db.MyConnect;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class Garde {
    int id;
    String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Garde() {

    }

    public Garde(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public void insert() throws Exception {
        String sql = "INSERT INTO garde (nom) VALUES (?)";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = MyConnect.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setString(1, this.nom);

            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void update() throws Exception {
        String sql = "UPDATE garde SET nom=? WHERE id=?";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = MyConnect.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setString(1, this.nom);
            ps.setInt(2, this.id);

            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
    }

    public static Garde entityMapping(ResultSet result) throws Exception {
        Garde garde = new Garde(result.getInt("id"), result.getString("nom"));
        return garde;
    }

    public static Garde[] getAll() throws Exception {
        String sql = "SELECT * FROM garde order by id";

        Connection connection = null;
        PreparedStatement ps = null;

        ArrayList<Garde> list = new ArrayList<>();

        try {
            connection = MyConnect.getConnection();
            ps = connection.prepareStatement(sql);

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                list.add(Garde.entityMapping(result));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                throw e;
            }
        }

        return list.toArray(new Garde[0]);
    }

    public static Garde getById(int id) throws Exception {
        String sql = "SELECT * FROM garde WHERE id=?";
        Garde garde = null;

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = MyConnect.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet result = ps.executeQuery();

            if (result.next()) {
                garde = Garde.entityMapping(result);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                throw e;
            }
        }

        return garde;
    }

    public static void delete(int id) throws Exception {
        String sql = "DELETE FROM garde WHERE id=?";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = MyConnect.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                throw e;
            }
        }
    }

    public static Garde[] getAvailableGarde (Timestamp start, Timestamp end) throws Exception {
        String sql = "select * from garde where id not in (select idGarde from detailsitting where \r\n" + //
                        "((? >= debut and ? <= fin) or (? >= debut and ? <= fin)) \r\n" + //
                        "or (( debut >= ? and debut <= ?) or ( fin >= ? and fin <= ?)));";

        Connection connection = null;
        PreparedStatement ps = null;

        ArrayList<Garde> list = new ArrayList<>();

        System.out.println(start);
        System.out.println(end);

        try {
            connection = MyConnect.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setTimestamp(1, start);
            ps.setTimestamp(2, start);
            ps.setTimestamp(3, end);
            ps.setTimestamp(4, end);
            ps.setTimestamp(5, start);
            ps.setTimestamp(6, end);
            ps.setTimestamp(7, start);
            ps.setTimestamp(8, end);

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                list.add(Garde.entityMapping(result));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                throw e;
            }
        }

        return list.toArray(new Garde[0]);
    }

    public static boolean isAvailable (int idGarde, Timestamp start, Timestamp end)
    {
        Garde[] gardes = new Garde[0];

        try {
            gardes = getAvailableGarde(start, end);
        } catch (Exception e) { 
            e.printStackTrace();
        }

        for (int i = 0; i < gardes.length; i++) {
            if (gardes[i].getId() == idGarde) {
                return true;
            }
        }

        return false;
    }
}
