import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;;

public class sigupframe extends Frame implements ActionListener  {
    Label l1,l2,l3,l4,l5;
    TextField t1,t2,t3,t4;
    Button submit,close;
    String msg=" ";
    Choice dropdown;
    public sigupframe()
    {
        
        setLayout(null);
        l1=new Label("USERNAME");
        l1.setBounds(100, 100, 100, 40);
        add(l1);
        t1 =new TextField();
        t1.setBounds(200, 100, 100, 40);
        add(t1);
        // password
        l2=new Label("Password");
        l2.setBounds(100, 160, 100, 40);
        add(l2);
        t2 =new TextField();
        t2.setBounds(200, 160, 100, 40);
        add(t2);
        // security question
        l3=new Label("security Question");
        l3.setBounds(100, 220, 100, 40);
        add(l3);
        // check box(for questions)
        dropdown=new Choice();
        dropdown.add("What's your favourite colour?");
        dropdown.add("what is you pet?");
        dropdown.add("which is your favourite place?");
        dropdown.add("who is you bestfriend's nickname?");
        dropdown.add("best award you got ?");
        add(dropdown);
        //dropdown.addItemListener(this);
        dropdown.setBounds(200, 225, 200, 40);
        /*t3 =new TextField();
        t3.setBounds(200, 220, 100, 40);
        add(t3);*/
        // answer
        l4=new Label("answer");
        l4.setBounds(100, 280, 100, 40);
        add(l4);
        t4 =new TextField();
        t4.setBounds(200, 280, 100, 40);
        add(t4);
        // submit
        submit= new Button("Submit");
        submit.setBounds(140, 340, 50, 40);
        add(submit);
        submit.addActionListener(this);
        // close button           
        close= new Button("Close");
        close.setBounds(210, 340, 50, 40);
        add(close);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });
     
        //label 3
        l5= new Label();
        l5.setBounds(100,400, 150, 50);
        add(l5);
        // dispose
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
        Connection con;
        PreparedStatement pre;
        if(str.equals("Submit"))
        {
            String t1x,t2x,t3x,t4x;
            try{
                con=DBConnect.GetDB();
                Statement stmt=con.createStatement();
            t1x=t1.getText();
            t2x=t2.getText();
            t3x=dropdown.getSelectedItem();
            t4x=t4.getText();
            ResultSet rs = stmt.executeQuery("Select * from login where username= '"+t1x+"'");
            if(rs.next())
            {
                msg="username already present";
            }
            else
            {
                    String qry="INSERT INTO login values (?,?,?,?)";
                    pre=con.prepareStatement(qry);
                    pre.setString(1, t1x);
                    pre.setString(2, t2x);
                    pre.setString(3, t3x);
                    pre.setString(4, t4x);
                    pre.executeUpdate();
                    msg="account created";
            }
            
            con.close(); 
            
        }
        catch( Exception ee)
        {
                System.out.println("connection error"+ ee);
        }
        
        }
        l5.setText(msg);
       
        

    }

    public static void main(String[] args) {
        sigupframe f = new sigupframe();
    f.setSize(500,500);
    f.setVisible(true);  
    }
}
