import java.sql.Connection;
public class test {
    public static void main(String[] args) {
        DBConnect d=new DBConnect();
        Connection con=d.GetDB();
        

    }
}
