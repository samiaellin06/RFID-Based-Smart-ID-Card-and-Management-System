
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
import java.sql.*;

public class attendanceDatabase {

    static String dep;
    static String course;
    static String section;
    String uid;
    static String printout;

    public void AttandanceConnector() {

        //uid=GetData.getuid();
        //uid="111";
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            Connection addstu = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartID", "root", "12345");
            System.out.println("connected");

            for (int i = 0; i < student.arr.size(); i++) {
                Statement add = addstu.prepareStatement("insert into attendancetime values (default,?,?,?,?,NOW())");
                ((PreparedStatement) add).setString(1, student.arr.get(i));
                ((PreparedStatement) add).setString(2, dep);
                ((PreparedStatement) add).setString(3, course);
                ((PreparedStatement) add).setString(4, section);
                int st = ((PreparedStatement) add).executeUpdate();
            }

            //int st2=((PreparedStatement) addlib).executeUpdate();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    void details() {

        uid = GetData.getuid();
        System.out.println(uid);
        //uid="2680";

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            Connection addstu = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartID", "root", "12345");
            System.out.println("connected");
            PreparedStatement checkin = (PreparedStatement) addstu.prepareStatement("select id,dep,subject,section,history from student,attendancetime where student.uid=? and attendancetime.uid=?");
            checkin.setString(1, uid);
            System.out.println(printout);
            checkin.setString(2, uid);
            ResultSet checkIndex = checkin.executeQuery();
            while (checkIndex.next()) {
                //uid=checkIndex.getString("uid");
                //name=checkIndex.getString("name");
                int id = checkIndex.getInt("id");
                String department = checkIndex.getString("dep");
                String subject = checkIndex.getString("subject");
                String sectiond = checkIndex.getString("section");
                Timestamp time = checkIndex.getTimestamp("history");
                printout = "ID:" + id + "\ndepartment:" + department + "\nSubject:" + subject + "\nSection:" + sectiond + "\nTime:" + time;

                System.out.println(printout);

            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

}
