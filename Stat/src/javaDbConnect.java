
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohsin
 */
public class javaDbConnect {
    
    //with static we do not have to create the object of the class we can simply connect by using the class
    private static Connection connection=null;
    
    
    public static Connection dbConnect(){
    
        try{
        
        Class.forName("org.sqlite.JDBC"); //it shows which data base we are trying to connect
        connection = DriverManager.getConnection("jdbc:sqlite:EasyStat.sqlite"); //database from the folder
            return connection;
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);    
            return null;
            }
        }
    }
