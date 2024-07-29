package entities;
import db.MyConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
public class Proprietaire
{
    int id;
    String nom;
    String coordonnees;

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
    public String getCoordonnees() {
        return coordonnees;
    }
    public void setCoordonnees(String coordonnees) {
        this.coordonnees = coordonnees;
    }

    public Proprietaire() {
    
    }
    public Proprietaire(int id, String nom, String coordonnees) {
        this.id = id;
        this.nom = nom;
        this.coordonnees = coordonnees;
    }

    public void insert () throws Exception
    {
        String sql="insert into proprietaire values (default,?,?)";

        Connection connection=null;
        PreparedStatement ps=null;

        try {
            connection=MyConnect.getConnection();
            ps=connection.prepareStatement(sql);

            ps.setString(1, this.nom);
            ps.setString(2, this.coordonnees);

            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (connection!=null)
            {
                connection.close();
            }
            if (ps!=null)
            {
                ps.close();
            }
        }
    }

    public void update () throws Exception
    {
        String sql="update proprietaire set nom=?, coordonnees=? where id=?";

        Connection connection=null;
        PreparedStatement ps=null;

        try {
            connection=MyConnect.getConnection();
            ps=connection.prepareStatement(sql);

            ps.setString(1, this.nom);
            ps.setString(2, this.coordonnees);
            ps.setInt(3, this.id);

            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (connection!=null)
            {
                connection.close();
            }
            if (ps!=null)
            {
                ps.close();
            }
        }
    }

    public static Proprietaire entityMapping (ResultSet result) throws Exception
    {
        Proprietaire rep=new Proprietaire(result.getInt("id"),result.getString("nom"),result.getString("coordonnees"));

        return rep;
    }

    public static Proprietaire[] getAll() throws Exception
    {
        String sql="select * from proprietaire order by id";

        Connection connection=null;
        PreparedStatement ps=null;

        ArrayList<Proprietaire> list=new ArrayList<Proprietaire>();

        try {
            connection=MyConnect.getConnection();
            ps=connection.prepareStatement(sql);

            ResultSet result=ps.executeQuery();

            while (result.next())
            {
                list.add(Proprietaire.entityMapping(result));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (connection!=null)
            {
                connection.close();
            }
            if (ps!=null)
            {
                ps.close();
            }
        }

        return list.toArray(new Proprietaire[0]);
    }

    public static Proprietaire getById (int id) throws Exception
    {
        String sql="select * from proprietaire where id=?";
        Proprietaire rep=null;

        Connection connection=null;
        PreparedStatement ps=null;

        try {
            connection=MyConnect.getConnection();
            ps=connection.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet result=ps.executeQuery();

            if (result.next())
            {
                rep=Proprietaire.entityMapping(result);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (connection!=null)
            {
                connection.close();
            }
            if (ps!=null)
            {
                ps.close();
            }
        }

        return rep;
    }

    public static void delete (int id) throws Exception
    {
        String sql="delete from proprietaire where id=?";

        Connection connection=null;
        PreparedStatement ps=null;

        try {
            connection=MyConnect.getConnection();
            ps=connection.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (connection!=null)
            {
                connection.close();
            }
            if (ps!=null)
            {
                ps.close();
            }
        }
    }
}