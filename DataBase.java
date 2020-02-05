package userentry;

import java.sql.*;

public class DataBase {
    private Connection connection = null;
    private final String url = "jdbc:postgresql://localhost/java";
    private final String user = "postgres";
    private final String password = "amrwsk13";
    
    UserData userData = new UserData();
    Statement satement;
    PreparedStatement preparedStatment;
    String query;
    ResultSet result;
    DataBase db;
    
    public Connection connect()
    {
        try
        {
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("Connection Is Made Successfully");
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            return connection;
        }
    }
    
    public UserData retriveFirstOrLast (String nodeNumber)
    {
        try
        {
            db = new DataBase();
            connection = db.connect();
            satement = connection.createStatement();
            query = new String("SELECT * FROM userdata ORDER BY id ASC");
            result = satement.executeQuery(query);
            
            while (result.next())
            {
                if (result.isFirst() && nodeNumber.equals("first"))
                {
                    userData.setFullData(result.getInt(1)
                            , result.getString(2)
                            , result.getString(3)
                            , result.getString(4)
                            , result.getString(5)
                            , result.getString(6));                    
                }
                else if (result.isLast() && nodeNumber.equals("last"))
                {
                    userData.setFullData(result.getInt(1)
                            , result.getString(2)
                            , result.getString(3)
                            , result.getString(4)
                            , result.getString(5)
                            , result.getString(6));                    
                }
            }
            satement.close();
            connection.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            System.out.println("Get Firs Or Last");
            return userData;
        }
    }
    
    public void update(int id , String fname,String mname,String lname, String email, String phone)
    {
        try
        {
            db = new DataBase();
            connection = db.connect();
            preparedStatment = connection.prepareStatement("UPDATE userdata SET fname = ?"
                    + ", mname = ?"
                    + ", lname = ?"
                    + ", email = ?"
                    + ",phone = ?"
                    + "WHERE id = ?");
            preparedStatment.setString(1, fname);
            preparedStatment.setString(2, mname);
            preparedStatment.setString(3, lname);
            preparedStatment.setString(4, email);
            preparedStatment.setString(5, phone);
            preparedStatment.setInt(6, id);
            preparedStatment.executeUpdate();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            System.out.println("Update Is Done Successfully");
        }
    }
    
    public void deleteUser(int id)
    {
        try
        {
            db = new DataBase();
            connection = db.connect();
            preparedStatment = connection.prepareCall("DELETE FROM userdata WHERE id = ?");
            preparedStatment.setInt(1, id);
            preparedStatment.executeUpdate();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            System.out.println("User Is DELETED!");
        }
    }
    
    public void insertUser(String fname,String mname,String lname, String email, String phone)
    {
        try
        {
            db = new DataBase();
            connection = db.connect();
            preparedStatment = connection.prepareCall("INSERT INTO userdata (fname,mname,lname,email,phone) VALUES "
                    + "(?,?,?,?,?)");
            preparedStatment.setString(1, fname);
            preparedStatment.setString(2, mname);
            preparedStatment.setString(3, lname);
            preparedStatment.setString(4, email);
            preparedStatment.setString(5, phone);
            preparedStatment.executeUpdate();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            System.out.println("User IS Inserted !");
        }
    }
    
    public UserData nextOrPrevious(int id, String command)
    {
        try
        {
            db = new DataBase();
            connection = db.connect();
            if (command.equals("next"))
            {
                id+=1;
                preparedStatment = connection.prepareStatement("SELECT * FROM userdata WHERE id = ? AND ? <= (SELECT COUNT(id) from userdata) LIMIT 1");
            }else
            {
                id-=1;
                preparedStatment = connection.prepareStatement("SELECT * FROM userdata WHERE id = ? AND ? >= 1 LIMIT 1");
            }
            preparedStatment.setInt(1, id);
            preparedStatment.setInt(2, id);
            result = preparedStatment.executeQuery();
            while (result.next())
            {
                userData.setFullData(result.getInt(1)
                        , result.getString(2)
                        , result.getString(3)
                        , result.getString(4)
                        , result.getString(5)
                        , result.getString(6));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            System.out.println("Next Or Previous Node !");
            return userData;
        }
    }
}
