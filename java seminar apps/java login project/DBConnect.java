import java.sql.*;
public class DBConnect {
    public static Connection GetDB() {
        Connection con=null;
        try {

        
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "");
   
         }catch(Exception e) {
            System.out.println("Error : "+e);
        }
    return con;
}
public static void main(String[] args) throws Exception {
    Connection con=DBConnect.GetDB();
    System.out.println("Connected Successfully..");
    
}
}