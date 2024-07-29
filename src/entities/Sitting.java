package entities;
import db.MyConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import util.Util;
public class Sitting {
    int id;
    int idAnimal;
    Animal animal;
    int idGarde;
    Garde garde;
    Timestamp debut;
    Timestamp fin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public int getIdGarde() {
        return idGarde;
    }

    public void setIdGarde(int idGarde) {
        this.idGarde = idGarde;
    }

    public Garde getGarde() {
        return garde;
    }

    public void setGarde(Garde garde) {
        this.garde = garde;
    }

    public Timestamp getDebut() {
        return debut;
    }

    public String getFormattedDebut () {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(debut);
    }

    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }

    public Timestamp getFin() {
        return fin;
    }

    public String getFormattedFin () {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.format(fin);
    }

    public void setFin(Timestamp fin) {
        this.fin = fin;
    }

    public Sitting() {

    }

    public Sitting(int id, int idAnimal, int idGarde, Timestamp debut, Timestamp fin) {
        this.id = id;
        this.idAnimal = idAnimal;
        this.idGarde = idGarde;
        this.debut = debut;
        this.fin = fin;
    }

    public void insert() throws Exception {
        String sql = "INSERT INTO sitting (idAnimal, idGarde";

        if (this.debut != null) {
            sql += ", debut";
        }

        if (this.fin != null) {
            sql += ", fin";
        } 

        sql += ") VALUES (?, ?";

        if (this.debut != null) {
            sql += ", ?";
        }

        if (this.fin != null) {
            sql += ", ?";
        }

        sql += ")";

        if (this.fin == null || this.debut == null) {
            throw new Exception("Verifiez le format de vos dates!!");
        }

        if (!Garde.isAvailable(idGarde, debut, fin)) {
            throw new Exception("Ce garde est indisponible pour cette tranche horaire!");
        }

        if (!Animal.isNotTaken(idAnimal, debut, fin)) {
            throw new Exception("Cet animal est deja pris en charge dans cette tranche horaire!");
        }

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = MyConnect.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setInt(1, this.idAnimal);
            ps.setInt(2, this.idGarde);
            int paramId = 3;
            if (this.debut != null) {
                ps.setTimestamp(paramId, this.debut);
                paramId++;
            }
            if (this.fin != null) {
                ps.setTimestamp(paramId, this.fin);
            }

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
        String sql = "UPDATE sitting SET idAnimal=?, idGarde=?, debut=?, fin=? WHERE id=?";
    
        Connection connection = null;
        PreparedStatement ps = null;
    
        try {
            connection = MyConnect.getConnection();
            ps = connection.prepareStatement(sql);
    
            ps.setInt(1, this.idAnimal);
            ps.setInt(2, this.idGarde);
            ps.setTimestamp(3, this.debut);
            ps.setTimestamp(4, this.fin);
            ps.setInt(5, this.id);
    
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

    public static Sitting entityMapping(ResultSet result) throws Exception {
        Sitting sitting = new Sitting(result.getInt("id"), result.getInt("idAnimal"), result.getInt("idGarde"), result.getTimestamp("debut"), result.getTimestamp("fin"));
        sitting.setAnimal(Animal.getById(sitting.getIdAnimal()));
        sitting.setGarde(Garde.getById(sitting.getIdGarde()));
        return sitting;
    }

    public static Sitting[] getAll() throws Exception {
        String sql = "SELECT * FROM sitting order by id";

        Connection connection = null;
        PreparedStatement ps = null;

        ArrayList<Sitting> list = new ArrayList<>();

        try {
            connection = MyConnect.getConnection();
            ps = connection.prepareStatement(sql);

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                list.add(Sitting.entityMapping(result));
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

        return list.toArray(new Sitting[0]);
    }

    public static Sitting getById(int id) throws Exception {
        String sql = "SELECT * FROM sitting WHERE id=?";
        Sitting sitting = null;

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = MyConnect.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet result = ps.executeQuery();

            if (result.next()) {
                sitting = Sitting.entityMapping(result);
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

        return sitting;
    }

    public static void delete(int id) throws Exception {
        String sql = "DELETE FROM sitting WHERE id=?";

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

    public static Sitting[] getAll(String idAnimal, String idGarde, String debutMin, String debutMax) throws Exception {
        String sql = "SELECT * FROM sitting WHERE 1=1";

        if (idAnimal != null) {
            if (!idAnimal.isEmpty() && !idAnimal.equals("0")) {
                sql += " AND idAnimal = ?";
            }
        }

        if (idGarde != null) {
            if (!idGarde.isEmpty() && !idGarde.equals("0")) {
                sql += " AND idGarde = ?";
            }
        }

        if (debutMin != null) {
            if (!debutMin.isEmpty()) {
                sql += " AND debut >= ?";
            }
        }

        if (debutMax != null) {
            if (!debutMax.isEmpty()) {
                sql += " AND debut <= ?";
            }
        }

        sql += " ORDER BY id";

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ArrayList<Sitting> list = new ArrayList<>();

        try {
            connection = MyConnect.getConnection();
            ps = connection.prepareStatement(sql);

            int paramId = 1;

            if (idAnimal != null) {
                if (!idAnimal.isEmpty() && !idAnimal.equals("0")) {
                    ps.setInt(paramId++, Integer.parseInt(idAnimal));
                }
            }

            if (idGarde != null) {
                if (!idGarde.isEmpty() && !idGarde.equals("0")) {
                    ps.setInt(paramId++, Integer.parseInt(idGarde));
                }
            }

            if (debutMin != null) {
                if (!debutMin.isEmpty()) {
                    ps.setTimestamp(paramId++, Util.stringToTimestamp(debutMin));
                }
            }

            if (debutMax != null) {
                if (!debutMax.isEmpty()) {
                    ps.setTimestamp(paramId++, Util.stringToTimestamp(debutMax));
                }
            }

            result = ps.executeQuery();

            while (result.next()) {
                list.add(Sitting.entityMapping(result));
            }
        } finally {
            try {
                if (result != null) result.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new Exception("Error closing resources: " + e.getMessage());
            }
        }

        return list.toArray(new Sitting[0]);
    }

}
