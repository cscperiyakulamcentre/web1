import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class UpdatePassword extends Frame implements ActionListener{
    Label l1,l2,l0,l5;
    TextField t0,t1,t2;
    Button b1;
    Connection con;
    public UpdatePassword()
    {
        setLayout(null);
        l0=new Label("username");
        l0.setBounds(100, 100, 100, 40);
        add(l0);
        t0 =new TextField();
        t0.setBounds(200, 100, 100, 40);
        add(t0);
        l1=new Label("New Password");
        l1.setBounds(100, 160, 100, 40);
        add(l1);
        t1 =new TextField();
        t1.setBounds(200, 160, 100, 40);
        add(t1);
        // password
        l2=new Label("Confirm Password");
        l2.setBounds(100, 220, 100, 40);
        add(l2);
        t2 =new TextField();
        t2.setBounds(200, 220, 100, 40);
        add(t2);
        //Submit button
        b1= new Button("submit");
        b1.setBounds(150,280, 50,30);
        add(b1);
        b1.addActionListener(this);
        //to ouput
        l5=new Label();
        l5.setBounds(100, 310, 100, 40);
        add(l5);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we)  
            {
                    dispose();
            }          
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String str=e.getActionCommand();
        String t0x,t1x,msg="";
        if(str.equals("submit"))
        {
            if(t1.getText().equals(t2.getText()))
            {
                try{
                    con=DBConnect.GetDB();
                    Statement stmt=con.createStatement();
                    PreparedStatement pre;
                    t0x=t0.getText();
                    t1x=t1.getText();
                    ResultSet rs = stmt.executeQuery("Select * from login where username= '"+t0x+"'");            
                    if(rs.next())
                    {
                        String qry="UPDATE login SET passw=? WHERE username=?";
                        pre=con.prepareStatement(qry);
                        pre.setString(1, t1x);
                        pre.setString(2, t0x);
                        pre.executeUpdate();
                        msg="account updated";
                    }
                }  
                catch(Exception ee){
                    System.out.println("Connection error:"+ee);
                }
            }
            l5.setText(msg);
        }
    }
}
