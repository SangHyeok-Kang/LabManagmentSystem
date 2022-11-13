package model;

import java.sql.*;
import static model.DBConnection.dbconnection;

public class Signup_model {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    String SQL;
    
    public boolean signup(String stunum, String pass,
                            String name, String phone_num, String email) {
        try {
            //Login_controller에서 사용자가 입력한 값을 넘겨 받아 user테이블에 값을 넘겨준다.
            SQL = "insert into student(stu_num, pass, name, ph_num, email, access, warning_num, end_limit) "
                    + "values('" + stunum + "','" + pass + "','" + name + "','" + phone_num + "','"
                    + email + "'," + "default" + "," + "default" + "," + "default" + ")";
            con = dbconnection.getConnection();
            st = con.prepareStatement(SQL);
            int addrow = st.executeUpdate(SQL);
            if(addrow == 1)
                return true;
            else
                return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean searchUser(String stunum){
        char a = stunum.charAt(0);
        try {
            if (a == 'S') {
                SQL = "select * from student where stu_num = '" + stunum + "'";
                st = dbconnection.getInstance().getConnection().createStatement();
                rs = st.executeQuery(SQL); 
                if (rs.next())  // 작성한 학번이 이미 존재할 시
                    return false;
            }
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
        return true;
    }
}
