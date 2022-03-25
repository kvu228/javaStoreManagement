/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connectDB;
import java.sql.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.Scanner;
import javax.swing.JOptionPane;


/**
 *
 * @author User
 */
public class ConnectDB {

    //Attribute
    //Thay đổi thông số để connect với 
    
    String conUser = "sa";
    String conPassword = "sa123";
    String conServerName = "ACER-KIETVU";
    String conPortNumber = "1433";
    String conDatabaseName = "QLYBANHANG";
    Connection con;
    String error;
    
    //Contruction

    public ConnectDB() {
    }
    

    
    public void connectToDB() {
        // TODO code application logic here
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(this.conUser);
        ds.setPassword(this.conPassword);
        ds.setServerName(this.conServerName);
        ds.setPortNumber(Integer.parseInt(this.conPortNumber));
        ds.setDatabaseName(this.conDatabaseName);
       
        try {
            // Execute a stored procedure that returns some data.
            Connection con = ds.getConnection();

            // Iterate through the data in the result set and display it.
            if (con != null) {
                this.con = con;
                System.out.println("Thanh cong");

            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException ex) {
            this.setError(ex.toString());
            System.out.println(this.getError());
        }

    }
    
    
    //Methods
    //Thực thi câu lệnh SELECT
    public ResultSet excuteQueryGetTable(String cauTruyVanSQL){
        try {
            Statement stmt = this.con.createStatement();           
            ResultSet rs = stmt.executeQuery(cauTruyVanSQL);
            return rs;
        } catch (SQLException ex) {
            this.setError(ex.toString());
            System.out.println(this.getError());
        }
        
            
        return null;
    }
    //Thực thi INSERT, DELETE, UPDATE
    public void excuteQueryUpdateDB(String cauTruyVanSQL){
       
        try {
            Statement stmt = this.con.createStatement();
            stmt.executeUpdate(cauTruyVanSQL);
            
        } catch (SQLException ex) {
            this.setError(ex.toString());
            System.out.println(this.getError());
        }
        
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Connection getCon() {
        return con;
    }

    public void closeConnect(){
        try {
            if(this.con != null)
                this.setError(null);
                this.con.close();
        }
        catch (SQLException ex) {
            this.setError(ex.toString());
            System.out.println(this.getError());
        }
    }
    

}
