/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userentry;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author amrwalid
 */
class User
{
    private int id;
    private String fname;
    private String mname;
    private String lname;
    private String email;
    private String phone;
    
    public void setID (int id)
    {
        this.id = id;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setFullName (String fnam,String mname,String lname)
    {
       this.fname = fnam;
       this.mname = mname;
       this.lname = lname;
    }
    
    public String getFName ()
    {
        return fname;
    }
        public String getMName ()
    {
        return mname;
    }
    public String getLName ()
    {
        return lname;
    }
    
    public void setEmail(String email)
    {
        this.email = email; 
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    
    public String getPhone()
    {
        return phone;
    }
}



class DataBase
{
    private final String url = "jdbc:postgresql://localhost:5432/java";
    private final String user = "postgres";
    private final String password = "amrwsk13";
    Connection conn = null;
    User userRetrive;
    Statement statement;
    
    public Connection connect() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
        return conn;
    }
    
    public User retriveFirstUser()
    {
        userRetrive = new User();
        
        try {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,0);
            String q = new String("SELECT * FROM userdata WHERE id = 1");
            ResultSet rs;
            rs = statement.executeQuery(q);
        
            while (rs.next())
            {
                userRetrive.setID(rs.getInt(1));
                userRetrive.setFullName(rs.getString(2), rs.getString(3), rs.getString(4));
                userRetrive.setEmail(rs.getString(5));
                userRetrive.setPhone(rs.getString(6));
            }
        
            statement.close();
            conn.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    return userRetrive;
    }
    
    public User retriveLastUser()
    {
        userRetrive = new User();
        try {
            statement = conn.createStatement();
                    String q = new String("SELECT * FROM userdata WHERE id = (SELECT count(id) FROM userdata)");
        ResultSet rs;
        rs = statement.executeQuery(q);
        
        while (rs.next())
        {
                userRetrive.setID(rs.getInt(1));
                userRetrive.setFullName(rs.getString(2), rs.getString(3), rs.getString(4));
                userRetrive.setEmail(rs.getString(5));
                userRetrive.setPhone(rs.getString(6));  
        }

        
        statement.close();
        conn.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    return userRetrive;        
    }
    
}


public class UserEntry extends Application {
    User u;
    @Override
    public void start(Stage primaryStage){
            
        ToolBar toolBar = new ToolBar();
        Button _new = new Button("New...");
        Button update = new Button("Update");
        Button delete = new Button("Delete");
        Button first = new Button("First");
        Button previous = new Button("Previous");
        Button next = new Button("Next");
        Button last = new Button("Last");
        toolBar.getItems().addAll(_new, update, delete, first, previous, next, last);
        /////////////////////////////////////////////////////////////////////////////
        Label id = new Label("ID:");
        Label fName = new Label("First Name:");
        Label mName = new Label("Middle Name:");
        Label lName = new Label("Last name:");
        Label eMail = new Label("E-Mail:");
        Label phone = new Label("phone:");  
        ////////////////////////////////////////////////////////////////////////////
        TextField _id = new TextField ();
        TextField _fName = new TextField ();
        TextField _mName = new TextField ();
        TextField _lName = new TextField ();
        TextField _eMail = new TextField ();
        TextField _phone = new TextField ();
        ///////////////////////////////////////////////////////////////////////////
        GridPane dataPane = new GridPane();
        dataPane.addRow(0, id, _id);
        dataPane.addRow(1, fName, _fName);
        dataPane.addRow(2, mName, _mName);
        dataPane.addRow(3, lName, _lName);
        dataPane.addRow(4, eMail, _eMail);
        dataPane.addRow(5, phone, _phone);
        dataPane.setHgap(10);
        ///////////////////////////////////////////////////////////////////////////
        FlowPane pane = new FlowPane();
        pane.getChildren().addAll(dataPane, toolBar);
        pane.setOrientation(Orientation.VERTICAL);
        pane.setVgap(20);
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.FULL)));
        ///////////////////////////////////////////////////////////////////////////
        Scene scene = new Scene(pane);       
        primaryStage.setTitle("User Data");
        primaryStage.setScene(scene);
        primaryStage.show();
        ///////////////////////////////////////////////////////////////////////////
        

        ///////////////////////////////////////////////////////////////////////////
        _new.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _id.setText("");
                _fName.setText("");
                _mName.setText("");
                _lName.setText("");
                _eMail.setText("");
                _phone.setText("");
            }
        });
        //////////////////////////////////////////////////////////////////////////
        update.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        //////////////////////////////////////////////////////////////////////////
        delete.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        //////////////////////////////////////////////////////////////////////////
        first.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DataBase app = new DataBase();
                app.connect();
                User u = app.retriveFirstUser();
                _id.setText(String.valueOf(u.getId()));
                _fName.setText(u.getFName());
                _mName.setText(u.getMName());
                _lName.setText(u.getLName());
                _eMail.setText(u.getEmail());
                _phone.setText(u.getPhone());
            }
        });
        //////////////////////////////////////////////////////////////////////////
        last.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DataBase app = new DataBase();
                app.connect();                
                User u = app.retriveLastUser();
                _id.setText(String.valueOf(u.getId()));                
                _fName.setText(u.getFName());
                _mName.setText(u.getMName());
                _lName.setText(u.getLName());
                _eMail.setText(u.getEmail());
                _phone.setText(u.getPhone());                
            }
        });
        //////////////////////////////////////////////////////////////////////////
        next.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        /////////////////////////////////////////////////////////////////////////
        previous.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);

    }
    
}
