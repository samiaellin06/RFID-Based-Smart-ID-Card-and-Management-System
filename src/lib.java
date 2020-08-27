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
import java.sql.Timestamp;

/**
 *
 * @author falah
 */
public class lib {
    
    
    int id;
  static  String uid;
    static String bookid;
  static int status;
  static String returnbook;
   static String returnid;
   
   static String printout;
    
    
    void getuid()
    {
        uid=GetData.getuid();
    }
    
    void getbook()
    {
        bookid=GetData.getuid();
    }
    
    
    
    int IssueDatabase(lib issue)
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
             Statement libstamp=addstu.prepareStatement(" insert into librarytimestamp values(default,?,?,?,CURRENT_TIMESTAMP,?)");
              ((PreparedStatement) libstamp).setString(1,uid);
              ((PreparedStatement) libstamp).setInt(2,id);
              ((PreparedStatement) libstamp).setString(3,bookid);
               ((PreparedStatement) libstamp).setTimestamp(4,null);
             int st2=((PreparedStatement) libstamp).executeUpdate();
              return 1;
              
            
            
            
                }
                
            }
            
            else
            {
            
            
            
            Statement add=addstu.prepareStatement(" update  library set book1=?,book1stat=1 where uid =?");
                     ((PreparedStatement) add).setString(1,bookid);
                     //((PreparedStatement) add).setInt(2,1);
            ((PreparedStatement) add).setString(2,uid);
            int st=((PreparedStatement) add).executeUpdate();
             Statement libstamp=addstu.prepareStatement(" insert into librarytimestamp values(default,?,?,?,CURRENT_TIMESTAMP,?)");
              ((PreparedStatement) libstamp).setString(1,uid);
              ((PreparedStatement) libstamp).setInt(2,id);
              ((PreparedStatement) libstamp).setString(3,bookid);
               ((PreparedStatement) libstamp).setTimestamp(4,null);
             
              int st2=((PreparedStatement) libstamp).executeUpdate();
              return 1;}
            
            
            
            
            




           



        }catch (Exception ex)
        {

            ex.printStackTrace();
        }
         
         
         return 0;
    }
    
    
    
    
  static  void Returnbook()
    {
         String id=null;
        
        try
        {
            String driver="com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            
            Connection addstu=DriverManager.getConnection("jdbc:mysql://localhost:3306/smartID","root","12345");
            System.out.println("connected");
          PreparedStatement checkin=(PreparedStatement)addstu.prepareStatement("select book1 from library where uid=?");
           checkin.setString(1, returnid);
           // System.out.println(printout);
            //checkin.setString(2, uid);
            ResultSet checkIndex=checkin.executeQuery();
             if(checkIndex.next()){
                    id=checkIndex.getString(1);
             }
               
            
         
           if (id==returnbook)
           {
               Statement add=addstu.prepareStatement(" update  library set book1=?,book1stat=0 where uid =?");
                     ((PreparedStatement) add).setString(1,null);
                     //((PreparedStatement) add).setInt(2,1);
            ((PreparedStatement) add).setString(2,returnid);
            int st=((PreparedStatement) add).executeUpdate();
              Statement libstamp=addstu.prepareStatement(" update  librarytimestamp set rehistory=? where uid=? and bookid=?");
               //((PreparedStatement) libstamp).setTimestamp(1,now());
              ((PreparedStatement) libstamp).setString(1,returnid);
              ((PreparedStatement) libstamp).setString(2,bookid);
              //((PreparedStatement) libstamp).setString(3,bookid);
               //((PreparedStatement) libstamp).setTimestamp(4,null);
             
              int st2=((PreparedStatement) libstamp).executeUpdate();
           }
          






            
            
            
            
            
            
            
            
            
            

        }catch (Exception ex)
        {

            ex.printStackTrace();
        }
        
        
        
        
        
        
    }
    
    
    void getdetails()
    {
        
       uid=GetData.getuid();
        System.out.println(uid);
        //printout=null;
        //uid="2680";
        String mem=" ";
        
        try
        {
            String driver="com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            
            Connection addstu=DriverManager.getConnection("jdbc:mysql://localhost:3306/smartID","root","12345");
            System.out.println("connected");
          PreparedStatement checkin=(PreparedStatement)addstu.prepareStatement("select student.id,bookid,history,rehistory from student,librarytimestamp where student.uid=? and librarytimestamp.uid=?");
           checkin.setString(1, uid);
           // System.out.println(printout);
            checkin.setString(2, uid);
            ResultSet checkIndex=checkin.executeQuery();
            while(checkIndex.next())
            {
                //uid=checkIndex.getString("uid");
                //name=checkIndex.getString("name");
               int id=checkIndex.getInt("id");
               String bookno=checkIndex.getString("bookid");
               Timestamp history=checkIndex.getTimestamp("history");
               Timestamp returndate=checkIndex.getTimestamp("rehistory");
               //Timestamp time=checkIndex.getTimestamp("history");
               mem=printout;
                printout=mem+"ID:"+id+"\nBook no:"+bookno+"\nIssuedate:"+history+"\nReturn date:"+returndate;
                
                
                System.out.println(printout);
                
                
            }
          






            
            
            
            
            
            
            
            
            
            

        }catch (Exception ex)
        {

            ex.printStackTrace();
        }
        
        
        
        
        
        
        
        
    }
    
    
    
    static void setid(String id)
    {
        returnid=id;
    }
    
     static void setbook(String id)
    {
        returnbook=id;
    }
    
    
    
    
    
    
    
    
}
