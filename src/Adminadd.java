/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Adminadd {
    
    
    static final int pass=12345;
    static final int adminid=161;
    
    
    int id;
    int password;
   public Adminadd(int id,int password)
   {
       this.id=id;
       this.password=password;
   }
   public Adminadd(int id)
   {
       this.id=id;
   }
  public  static boolean checkPass(int passAd)
  {
      if(passAd==pass)
          return true;
      else return false;
  }

    Adminadd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
  
  
  void adduser(Adminadd user)
  {
      
       try
        {
            String driver="com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            
            Connection addstu=DriverManager.getConnection("jdbc:mysql://localhost:3306/smartID","root","12345");
            System.out.println("connected");
          






            Statement add=addstu.prepareStatement("insert into userlist values (default,?,?)");
            
            ((PreparedStatement) add).setInt(1,id);
            ((PreparedStatement) add).setInt(2,password);
          
            int st=((PreparedStatement) add).executeUpdate();


        }catch (Exception ex)
        {

            ex.printStackTrace();
        }
      
  }
  
  
  
  
  void updateuser(Adminadd user)
  {
      
       try
        {
            String driver="com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            
            Connection addstu=DriverManager.getConnection("jdbc:mysql://localhost:3306/smartID","root","12345");
            System.out.println("connected");
         






            
             Statement add=addstu.prepareStatement("update userlist set pas= ? where id =?");
             ((PreparedStatement) add).setInt(1,password);
            ((PreparedStatement) add).setInt(2,id);
           
          
            int st=((PreparedStatement) add).executeUpdate();
            System.out.println("done");


        }catch (Exception ex)
        {

            ex.printStackTrace();
        }
  }
  
  
  
  void deletuser(Adminadd user)
  {
      try
        {
            String driver="com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            
            Connection addstu=DriverManager.getConnection("jdbc:mysql://localhost:3306/smartID","root","12345");
            System.out.println("connected");
         






            
             Statement add=addstu.prepareStatement("delete  from userlist where id=?");
             ((PreparedStatement) add).setInt(1,id);
            
           
          
            int st=((PreparedStatement) add).executeUpdate();
            System.out.println("done");


        }catch (Exception ex)
        {

            ex.printStackTrace();
        }
  }
  
  
  
  static boolean checkadmin(int id,int passad)
  {
      if(id==adminid && passad==pass)
      {
           return true;
      }
      return false;
  }
  
   
    
}
