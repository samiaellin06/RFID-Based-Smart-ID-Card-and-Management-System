/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author falah
 */
public class library {
    
    
    int id;
  static  String uid;
    static String bookid;
  static int status;
    
    
    void getuid()
    {
        uid=GetData.getuid();
    }
    
    void getbook()
    {
        bookid=GetData.getuid();
    }
    
    
    
    int IssueDatabase(library issue)
    {
         try
        {
             String driver="com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            
            Connection addstu=DriverManager.getConnection("jdbc:mysql://localhost:3306/smartID","root","12345");
            System.out.println("connected");
          PreparedStatement checkin=(PreparedStatement)addstu.prepareStatement("select id from student where uid=?");
           checkin.setString(1, uid);
           
            ResultSet checkIndex=checkin.executeQuery();
            checkIndex.next();
          
                     id= checkIndex.getInt(1);
                     System.out.println(id);

          

                     int stat=0;

            PreparedStatement checkLog=addstu.prepareStatement("select book1stat from library where uid=?");
            checkLog.setString(1, uid);
            System.out.println(uid);
            ResultSet checkStat=checkLog.executeQuery();
            
            if(checkStat.next()){
               stat = checkStat.getInt(1);
}
            
            System.out.println(stat);
            if(stat==1)
                
            {
                checkLog=addstu.prepareStatement("select book2stat from library where uid=?");
                checkLog.setString(1, uid);
             int stat2 =0;   
            if(checkStat.next()){
             stat2 = checkStat.getInt(1);}
                if(stat2==1)
                {
                    return 0;
                }
               else
                {
                    Statement add=addstu.prepareStatement(" update  library set book2=?,book2stat=1 where uid =?");
                     ((PreparedStatement) add).setString(1,bookid);
            ((PreparedStatement) add).setString(2,uid);
            int st=((PreparedStatement) add).executeUpdate();
             Statement libstamp=addstu.prepareStatement(" insert into librarytimestamp values(default,?,?,?,CURRENT_TIMESTAMP)");
              ((PreparedStatement) libstamp).setString(1,uid);
              ((PreparedStatement) libstamp).setInt(2,id);
              ((PreparedStatement) libstamp).setString(3,bookid);
             int st2=((PreparedStatement) libstamp).executeUpdate();
              return 1;
              
            
            
            
                }
                
            }
            
            
            
            Statement add=addstu.prepareStatement(" update  library set book1=?,book1stat=1 where uid =?");
                     ((PreparedStatement) add).setString(1,bookid);
                     //((PreparedStatement) add).setInt(2,1);
            ((PreparedStatement) add).setString(2,uid);
            int st=((PreparedStatement) add).executeUpdate();
             Statement libstamp=addstu.prepareStatement(" insert into librarytimestamp values(default,?,?,?,CURRENT_TIMESTAMP)");
              ((PreparedStatement) libstamp).setString(1,uid);
              ((PreparedStatement) libstamp).setInt(2,id);
              ((PreparedStatement) libstamp).setString(3,bookid);
              int st2=((PreparedStatement) libstamp).executeUpdate();
              return 1;
            
            
            
            
            




           



        }catch (Exception ex)
        {

            ex.printStackTrace();
        }
         
         
         return 0;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
