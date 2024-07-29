package entities;

import db.MyConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class Admin 
{
    int id;
    String username;
    String pwd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Admin() {
        
    }

    public Admin(int id, String username, String pwd) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
    }

    public static Admin entityMapping(ResultSet result) throws Exception {
        Admin admin = new Admin(result.getInt("id"), result.getString("username"), result.getString("pwd"));
        return admin;
    }    

    public static Admin login (String username, String pwd) throws Exception
    {
        String sql="select * from admin where username=? and pwd=encode(digest(?,'sha256'),'hex')";

        Admin rep=null;

        Connection connection=null;
        PreparedStatement ps=null;

        try {
            connection=MyConnect.getConnection();
            ps=connection.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, pwd);

            ResultSet result=ps.executeQuery();

            if (result.next())
            {
                rep=entityMapping(result);
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
}