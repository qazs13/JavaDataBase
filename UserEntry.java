package userentry;

import java.sql.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;




public class UserEntry extends Application {
    
    
    @Override
    public void start(Stage primaryStage) {
    }

    
    
    public static void main(String[] args) {
        DataBase db = new DataBase();
        UserData userData = /*db.retriveFirstOrLast("last");*/
                              db.nextOrPrevious(2,"prev");
        System.out.println(userData.getId());
        System.out.println(userData.getFirstName());
        System.out.println(userData.getMiddleName());
        System.out.println(userData.getLastName());
        System.out.println(userData.getEmail());
        System.out.println(userData.getPhone());
                              
        /*db.update(3, "Rim", " ", "Amr", "RimAmr@gmail.com", "01282123441");*/
        /*db.insertUser("Mostafa", "Mohamed", "Rashad", "MOstafaElrawy@gmail.com", "0122223333");*/
        /*db.deleteUser(4);*/

    }
    
}
