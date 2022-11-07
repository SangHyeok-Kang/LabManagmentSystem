package model;

import java.sql.*;
import static model.DBConnection.dbconnection;

public class Signup_model {
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    String SQL;
    
    public boolean signup(String stunum, String pass, 
                    String name, String phone_num, String email){
         try {
            //Login_controller에서 사용자가 입력한 값을 넘겨 받아 user테이블에 값을 넘겨준다.
            SQL = "insert into student(stu_num, pass, name, ph_num, email, access, warning_num, end_limit) "
                    + "values('" + stunum + "','" + pass + "','" + name + "','" + phone_num + "','" 
                    + email +"'," + "default" + "," + "default" +  "," + "default" + ")";
            con = dbconnection.getConnection();
            st = con.prepareStatement(SQL);
            int addrow = st.executeUpdate(SQL);
            System.out.println("저장 성공");       
            return true;     
        }catch (SQLException e) {
             System.out.println("입력 실패");
            return false;
        }       
    }
}
