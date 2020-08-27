

import java.sql.*;


public class AddStudent {

    String uid;
    String name;
    int  id;
    double cgpa;
    int bus_route;
    long depCode;
    int semester;
    String printOut;


    public AddStudent(String name, int id, double cgpa, int bus_route, long depCode, int semester) {
        //this.uid = uid;
        this.name = name;
        this.id = id;
        this.cgpa = cgpa;
        this.bus_route = bus_route;
        this.depCode = depCode;
        this.semester = semester;





    }
    
    public AddStudent()
    {
        
    }
     public AddStudent(int bus_route)
    {
        this.bus_route=bus_route;
        
    }
    


    public void studentConnector()
    {
       uid=GetData.getuid();
        //uid="111";
        
        
        try
        {
            String driver="com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            
            Connection addstu=DriverManager.getConnection("jdbc:mysql://localhost:3306/smartID","root","12345");
            System.out.println("connected");
           PreparedStatement checkin=(PreparedStatement)addstu.prepareStatement("select  max(stuindex) from student");
            ResultSet checkIndex=checkin.executeQuery();
            checkIndex.next();
           int in=checkIndex.getInt(1);
           in++;






            Statement add=addstu.prepareStatement("insert into student values (?,?,?,?,?,?,?,?)");
            ((PreparedStatement) add).setInt(1,in);
            ((PreparedStatement) add).setString(2,uid);
            ((PreparedStatement) add).setString(3,name);
            ((PreparedStatement) add).setInt(4,id);
            ((PreparedStatement) add).setFloat(5, (float) cgpa);
            ((PreparedStatement) add).setInt(6,bus_route);
            ((PreparedStatement) add).setLong(7,depCode);
            ((PreparedStatement) add).setInt(8,semester);
            Statement addlib=addstu.prepareStatement("insert into library values (default,?,?,?,?,?)");
            ((PreparedStatement) addlib).setString(1,uid);
            ((PreparedStatement) addlib).setInt(2,0);
            ((PreparedStatement) addlib).setInt(3,0);
            ((PreparedStatement) addlib).setInt(4,0);
            ((PreparedStatement) addlib).setInt(5,0);
            
            
            
            
            
            
            
            
            int st=((PreparedStatement) add).executeUpdate();
            int st2=((PreparedStatement) addlib).executeUpdate();


        }catch (Exception ex)
        {

            ex.printStackTrace();
        }
    }
    
    
    void getDetails()
    {
        uid=GetData.getuid();
        System.out.println(uid);
        //uid="2680";
        
        try
        {
            String driver="com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            
            Connection addstu=DriverManager.getConnection("jdbc:mysql://localhost:3306/smartID","root","12345");
            System.out.println("connected");
          PreparedStatement checkin=(PreparedStatement)addstu.prepareStatement("select * from student,enrollment where student.uid=?  and enrollment.uid=?");
           checkin.setString(1, uid);
           checkin.setString(2, uid);
            ResultSet checkIndex=checkin.executeQuery();
            while(checkIndex.next())
            {
                uid=checkIndex.getString("uid");
                name=checkIndex.getString("name");
                id=checkIndex.getInt("id");
                cgpa=checkIndex.getDouble("cgpa");
                semester=checkIndex.getInt("semester");
                depCode=checkIndex.getInt("depCode");
                bus_route=checkIndex.getInt("bus_route");
                
                int semester=checkIndex.getInt("sem");
                String subject1=checkIndex.getString("sub1");
                String subject1sec=checkIndex.getString("sub1sec");
                String subject2=checkIndex.getString("sub2");
                String subject2sec=checkIndex.getString("sub2sec");
                String subject3=checkIndex.getString("sub3");
                String subject3sec=checkIndex.getString("sub3sec");
                String lab1=checkIndex.getString("lab1");
                String lab1sec=checkIndex.getString("lab1sec");
                  String lab2=checkIndex.getString("lab2");
                 String lab2sec=checkIndex.getString("lab2sec");
                   String lab3=checkIndex.getString("lab3");
                   String lab3sec=checkIndex.getString("lab3sec");
                
                
                
                printOut="UID:"+uid+"\nName:"+name+"\nID:"+(int)id+"\nCGPA:"+(double)cgpa+"\nSemester:"+(int)semester+"\nDepartment:"+(int)depCode+"\nBus route:"+(int)bus_route;
                printOut=printOut+"\nSemester:"+(int)semester+"\nSubject 1: "+subject1+"\nsection : "+subject1sec+"\nSubject 2:"+subject2+"\nSection:"+subject2sec+"\nSubject 3:"+subject3+"\nSection :"+subject3sec+"\nlab 1:"+lab1+"\nSection:"+lab1sec;
                System.out.println(printOut);
                
                
            }
            
        }catch (Exception ex)
        {

            ex.printStackTrace();
        }
    }
    
    
    void changeBus()
    {
         uid=GetData.getuid();
        System.out.println(bus_route);
        //uid="2680";
        
        try
        {
            String driver="com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            
            Connection addstu=DriverManager.getConnection("jdbc:mysql://localhost:3306/smartID","root","12345");
            System.out.println("connected");
          Statement add=addstu.prepareStatement(" update  student set bus_route =? where uid =?");
                     ((PreparedStatement) add).setInt(1,bus_route);
            ((PreparedStatement) add).setString(2,uid);
           int st=((PreparedStatement) add).executeUpdate();
  
        }catch (Exception ex)
        {

            ex.printStackTrace();
        }
    }

}
