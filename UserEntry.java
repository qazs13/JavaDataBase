package userentry;

import java.sql.*;
import javafx.application.Application;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;




public class UserEntry extends Application {
    
    
    @Override
    public void start(Stage primaryStage) {
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
        
        DataBase db = new DataBase();
        ///////////////////////////////////////////////////////////////////////////
        _new.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                db.insertUser(_fName.getText()
                        , _mName.getText()
                        , _lName.getText()
                        , _eMail.getText()
                        , _phone.getText());
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
                db.update(Integer.parseInt(_id.getText())
                        , _fName.getText()
                        , _mName.getText()
                        , _lName.getText()
                        , _eMail.getText()
                        , _phone.getText());
            }
        });
        //////////////////////////////////////////////////////////////////////////
        delete.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                db.deleteUser(Integer.parseInt(_id.getText()));
            }
        });
        //////////////////////////////////////////////////////////////////////////
        first.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserData userData = db.retriveFirstOrLast("first");
                _id.setText(String.valueOf(userData.getId()));
                _fName.setText(userData.getFirstName());
                _mName.setText(userData.getMiddleName());
                _lName.setText(userData.getLastName());
                _eMail.setText(userData.getEmail());
                _phone.setText(userData.getPhone());                
            }
        });
        //////////////////////////////////////////////////////////////////////////
        last.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserData userData = db.retriveFirstOrLast("last");
                _id.setText(String.valueOf(userData.getId()));
                _fName.setText(userData.getFirstName());
                _mName.setText(userData.getMiddleName());
                _lName.setText(userData.getLastName());
                _eMail.setText(userData.getEmail());
                _phone.setText(userData.getPhone());                    
            }
        });
        //////////////////////////////////////////////////////////////////////////
        next.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserData userData = db.nextOrPrevious(Integer.parseInt(_id.getText()),"next");
                _id.setText(String.valueOf(userData.getId()));
                _fName.setText(userData.getFirstName());
                _mName.setText(userData.getMiddleName());
                _lName.setText(userData.getLastName());
                _eMail.setText(userData.getEmail());
                _phone.setText(userData.getPhone());   
            }
        });
        /////////////////////////////////////////////////////////////////////////
        previous.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserData userData = db.nextOrPrevious(Integer.parseInt(_id.getText()),"prev");
                _id.setText(String.valueOf(userData.getId()));
                _fName.setText(userData.getFirstName());
                _mName.setText(userData.getMiddleName());
                _lName.setText(userData.getLastName());
                _eMail.setText(userData.getEmail());
                _phone.setText(userData.getPhone());   
            }
        });
    }

    
    
    public static void main(String[] args) {
        
        /*UserData userData = db.retriveFirstOrLast("last");
                              db.nextOrPrevious(2,"prev");
        System.out.println(userData.getId());
        System.out.println(userData.getFirstName());
        System.out.println(userData.getMiddleName());
        System.out.println(userData.getLastName());
        System.out.println(userData.getEmail());
        System.out.println(userData.getPhone());*/
        /**/
        /**/
        
        Application.launch(args);
    }
    
}
