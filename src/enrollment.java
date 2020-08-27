
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author falah
 */
public class enrollment {
    
      int sem;
      String uid;
        String sub1;
      
        String sub2;
       
        String sub3;
       
        String sec1;
      
        String sec2;
        
        String sec3;
       
        String lab1;
      
         String lab2;
       
         String lab3;
          String lab1sec;
      
         String lab2sec;
       
         String lab3sec;

    public enrollment(int sem,  String sub1, String sub2, String sub3, String sec1, String sec2, String sec3, String lab1, String lab2, String lab3, String lab1sec, String lab2sec, String lab3sec) {
        this.sem = sem;
        this.uid = uid;
        this.sub1 = sub1;
        this.sub2 = sub2;
        this.sub3 = sub3;
        this.sec1 = sec1;
        this.sec2 = sec2;
        this.sec3 = sec3;
        this.lab1 = lab1;
        this.lab2 = lab2;
        this.lab3 = lab3;
        this.lab1sec = lab1sec;
        this.lab2sec = lab2sec;
        this.lab3sec = lab3sec;
    }

   
    
    public enrollment()
    {
        
    }
    
    
    
    
    void setEnroll()

    {
        uid=GetData.getuid();
        //uid="111";
        
        
        try
        {
            String driver="com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            
            Connection addstu=DriverManager.getConnection("jdbc:mysql://localhost:3306/smartID","root","12345");
            System.out.println("connected");
           





            Statement add=addstu.prepareStatement("insert into enrollment values (default,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ((PreparedStatement) add).setString(1,uid);
            ((PreparedStatement) add).setInt(2,sem);    
            ((PreparedStatement) add).setString(3,sub1);
            ((PreparedStatement) add).setString(4,sec1);
            ((PreparedStatement) add).setString(5,sub2);
            ((PreparedStatement) add).setString(6, sec2);
             ((PreparedStatement) add).setString(7,sub3);
               ((PreparedStatement) add).setString(8,sec3);
                 ((PreparedStatement) add).setString(9,lab1);
                   ((PreparedStatement) add).setString(10,lab1sec);
                   ((PreparedStatement) add).setString(11,lab2);    
               ((PreparedStatement) add).setString(12,lab2sec);    
                   ((PreparedStatement) add).setString(13,lab3);
                   ((PreparedStatement) add).setString(14,lab3sec);
                     
                   
            
            
            
            
            
            
            
            
            
            int st=((PreparedStatement) add).executeUpdate();
          


        }catch (Exception ex)
        {

            ex.printStackTrace();
        }
    }
         
     
    
    
}
