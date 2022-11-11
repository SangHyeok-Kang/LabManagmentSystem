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

        char a = stunum.charAt(0);
        try {
            if (a == 'S') {
                SQL = "select * from student where stu_num = '" + stunum + "'";
                st = dbconnection.getInstance().getConnection().createStatement();
                rs = st.executeQuery(SQL);

                if (rs.next()) {
                    System.out.println("이미 존재하는 학생입니다.");
                    return false;
                } else {
                    //Login_controller에서 사용자가 입력한 값을 넘겨 받아 user테이블에 값을 넘겨준다.
                    SQL = "insert into student(stu_num, pass, name, ph_num, email, access, warning_num, end_limit) "
                            + "values('" + stunum + "','" + pass + "','" + name + "','" + phone_num + "','"
                            + email + "'," + "default" + "," + "default" + "," + "default" + ")";
                    con = dbconnection.getConnection();
                    st = con.prepareStatement(SQL);
                    int addrow = st.executeUpdate(SQL);
                    System.out.println("저장 성공");
                    return true;
                }
            } else {
                System.out.println("잘못된 학번입니다 다시 입력해주세요");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("잘못된 정보를 입력하셨습니다. 다시 입력해주세요");
            return false;
        }
    }
}
