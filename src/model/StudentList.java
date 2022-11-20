package model;

import static model.DBConnection.dbconnection;
import java.sql.*;

public class StudentList {

    public static int count = 0;
    public int number = 0;
    public String[][] stdlist;
    
    String SQL;
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    //학생 수 저장 메소드
    public void CountStd() {
        try {
            SQL = "select count(*) from student";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);
            if (rs.next()) {
                count = rs.getInt(1);
                System.out.println(count);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    //학생 리스트 출력 메소드
    public String[][] StdList() {
        stdlist = new String[count][6];       
        try {
            SQL = "select * from student";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);
            while(rs.next()) {
                System.out.println(number);
                stdlist[number][0] = rs.getString("stu_num");
                stdlist[number][1] = rs.getString("name");
                stdlist[number][2] = rs.getString("ph_num");
                stdlist[number][3] = rs.getString("email");
                stdlist[number][4] = rs.getString("access");
                stdlist[number][5] = rs.getString("end_limit");
                number++;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return stdlist;
    }
}
