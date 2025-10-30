import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
public class forgetpassword extends Frame implements ActionListener{
    Label l1,l2,l3,l4,l5;
    TextField answer,t1,t4;
    String msg="";
    String answerfromsql,question;
    Button b1,b2;
    public forgetpassword()
    {
        setLayout(null);
        l1=new Label("Username");
        l1.setBounds(100, 100, 100, 40);
        add(l1);
        t1 =new TextField();
        t1.setBounds(200, 100, 100, 40);
        add(t1);
        b1=new Button("ok");
        b1.setBounds(330, 100, 40, 40);
        b1.addActionListener(this);
        add(b1);
        // password
        l2=new Label("Security Question is?");
        l2.setBounds(100, 160, 120, 40);
        add(l2);
        l3 =new Label();
        l3.setBounds(210, 160, 150, 40);
        add(l3);
        l4=new Label("answer");
        l4.setBounds(100, 210, 100, 40);
        add(l4);
        t4 =new TextField();
        t4.setBounds(200, 210, 100, 40);
        add(t4);
        b2=new Button("submit");
        b2.setBounds(310, 210 , 40, 40);
        add(b2);
        b2.addActionListener(this);
        // to print the output
        l5=new Label();
        l5.setBounds(100,260,150,40);
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
        String okString=e.getActionCommand();
        if(okString.equals("ok"))
        {
        String t1x;
        try{
            Connection con=DBConnect.GetDB();
            Statement stmt=con.createStatement();
            t1x=t1.getText();
            ResultSet rs = stmt.executeQuery("Select * from login where username= '"+t1x+"'");
            if(rs.next())
            {
                question=rs.getString(3);
                answerfromsql=rs.getString(4);
                l3.setText(question);
               
            }   
        else
        {
            msg="username didnot exist";
        }    
        l5.setText(msg);
    }
    catch(Exception ee)
    {
        System.out.println(ee);
    }
   } 
   else if(okString.equals("submit"))
   {
        System.out.println(answerfromsql);
       String tocheck=t4.getText(); 
       System.err.println(tocheck);             
       if(tocheck.equals(answerfromsql))
       {
           UpdatePassword f=new UpdatePassword();
           f.setSize(400,400);
           f.setVisible(true);
           msg="your answer is correct";
       }
       else
       {
       msg="your answer is wrong";
       }
       l5.setText(msg);
   }
}
}