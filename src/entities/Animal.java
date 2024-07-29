package entities;
import db.MyConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Animal {
    int id;
    String nom;
    int idProprietaire;
    Proprietaire proprietaire;

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

    public int getIdProprietaire() {
        return idProprietaire;
    }

    public void setIdProprietaire(int idProprietaire) {
        this.idProprietaire = idProprietaire;
    }

    public Proprietaire getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Proprietaire proprietaire) {
        this.proprietaire = proprietaire;
    }

    public Animal() {

    }

    public Animal(int id, String nom, int idProprietaire) {
        this.id = id;
        this.nom = nom;
        this.idProprietaire = idProprietaire;
    }

    public void insert() throws Exception {
        String sql = "INSERT INTO animal (nom, idProprietaire) VALUES (?, ?)";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = MyConnect.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setString(1, this.nom);
            ps.setInt(2, this.idProprietaire);

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
        String sql = "UPDATE animal SET nom=?, idProprietaire=? WHERE id=?";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = MyConnect.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setString(1, this.nom);
            ps.setInt(2, this.idProprietaire);
            ps.setInt(3, this.id);

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

    public static Animal entityMapping(ResultSet result) throws Exception {
        Animal animal = new Animal(result.getInt("id"), result.getString("nom"), result.getInt("idProprietaire"));
        animal.setProprietaire(Proprietaire.getById(animal.getIdProprietaire()));
        return animal;
    }

    public static Animal[] getAll() throws Exception {
        String sql = "SELECT * FROM animal order by id";

        Connection connection = null;
        PreparedStatement ps = null;

        ArrayList<Animal> list = new ArrayList<>();

        try {
            connection = MyConnect.getConnection();
            ps = connection.prepareStatement(sql);

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                list.add(Animal.entityMapping(result));
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

        return list.toArray(new Animal[0]);
    }

    public static Animal getById(int id) throws Exception {
        String sql = "SELECT * FROM animal WHERE id=?";
        Animal animal = null;

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = MyConnect.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet result = ps.executeQuery();

            if (result.next()) {
                animal = Animal.entityMapping(result);
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

        return animal;
    }

    public static void delete(int id) throws Exception {
        String sql = "DELETE FROM animal WHERE id=?";

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

    public static Animal[] getNotTakenAnimal (Timestamp start, Timestamp end) throws Exception {
        String sql = "select * from animal where id not in (select idAnimal from detailsitting where \r\n" + //
                        "((? >= debut and ? <= fin) or (? >= debut and ? <= fin)) \r\n" + //
                        "or (( debut >= ? and debut <= ?) or ( fin >= ? and fin <= ?)));";

        Connection connection = null;
        PreparedStatement ps = null;

        ArrayList<Animal> list = new ArrayList<>();

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
                list.add(Animal.entityMapping(result));
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

        return list.toArray(new Animal[0]);
    }

    public static boolean isNotTaken (int idAnimal, Timestamp start, Timestamp end)
    {
        Animal[] animaux = new Animal[0];

        try {
            animaux = getNotTakenAnimal(start, end);
        } catch (Exception e) { 
            e.printStackTrace();
        }

        for (int i = 0; i < animaux.length; i++) {
            if (animaux[i].getId() == idAnimal) {
                return true;
            }
        }

        return false;
    }
}
