// Source code is decompiled from a .class file using FernFlower decompiler.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


class first {
  

   public static void main(String[] var0) {
      System.out.println("csc");

try {
        Class.forName("com.mysql.jdbc.Driver");
         Connection var1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "");
         Statement var2 = var1.createStatement();
         ResultSet var3 = var2.executeQuery("select * from login");

         while(var3.next()) {
            System.out.println(var3.getString(1));
            System.out.println(var3.getString(2));
         }

         var1.close();
      } catch (Exception var4) {
         System.out.println(var4);
      }
   }
}
