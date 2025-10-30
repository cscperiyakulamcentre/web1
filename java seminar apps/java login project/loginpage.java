import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginpage extends Frame implements ActionListener
{
    Label l1,l2,l3;
    TextField t1,t2;
    Button login,close,Signup,forget;
    String msg=" ";
    public loginpage(String title)
    {
            //  loginid
        super(title);
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
        // forget password?
        forget=new Button("Forget Password?");
        forget.setBounds(200, 210, 150, 20);
        forget.setBackground(Color.white);
        forget.setForeground(Color.green);
      //  forget.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        add(forget);
        forget.addActionListener(this);
        // login button
        Button login= new Button("LOGIN");
        login.setBounds(140, 240, 50, 40);
        add(login);
        login.addActionListener(this);
        // close button           
        Button close= new Button("Close");
        close.setBounds(210, 240, 50, 40);
        add(close);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });
        // button to create account
        Button signup= new Button("Signup");
        signup.setBounds(280,240 , 50,40);
        add(signup);
        signup.addActionListener(this);
        //l3
        l3= new Label();
        l3.setBounds(100,260, 150, 50);
        add(l3);
        //dispose
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we)  
            {
                    dispose();
            }          
        });
}
public void actionPerformed(ActionEvent ae) {
    // Handle the event here
    String str=ae.getActionCommand();
    if(str.equals("LOGIN"))
    {
    String t1x,t2x,password;
    try{
        Connection con=DBConnect.GetDB();
    Statement stmt=con.createStatement();
    t1x=t1.getText();
    t2x=t2.getText();
    ResultSet rs = stmt.executeQuery("Select passw from login where username= '"+t1x+"'");
    if(rs.next())
    {
        password=rs.getString(1);
        if(password.equals(t2x))
        {
            msg="login SUCESS";
        }
        else
        {
            msg="password incorrect";
        }
        
    }
    else
    {
        msg="login failure incorrect username";
    }
        
    l3.setText(msg);
}

catch(Exception e)
{
    System.out.println("connection error"+e);
}
}
else if(str.equals("Signup"))   
{
    sigupframe f = new sigupframe();
    f.setSize(500,500);
    f.setVisible(true);  
}
else if(str.equals("Forget Password?"))
{
    forgetpassword gh=new forgetpassword();
    gh.setSize(500,500);
    gh.setVisible(true);
}
}
public static void main(String[] args) {
    loginpage lp=new loginpage("Login");
    lp.setSize(400,300);
    lp.setVisible(true);
}
}
    
        
     
    


