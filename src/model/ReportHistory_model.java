package model;

import java.sql.*;
import static model.DBConnection.dbconnection;
/**
 *
 * @author 20183125 송준섭
 * 클래스 사용 용도 : 학생 경고 부여 메소드
 */
public class ReportHistory_model {

    int count = 0;
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    String SQL;

    //경고 부여 메소드
    public void GiveLimit(String stdno) {
        try {
            SQL = "select warning_num from student where stu_num = '" + stdno + "'";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);
            if (rs.next()) {
                count = rs.getInt("warning_num");
            }
            st.close();
            rs.close();
            if (count == 0) {
                SQL = "update student set warning_num = 1, end_limit = DATE_ADD(now(), INTERVAL 1 DAY) where stu_num = '" + stdno + "';";
                con = dbconnection.getConnection();
                st = con.prepareStatement(SQL);
                st.executeUpdate(SQL);
                st.close();
            } else if (count == 1) {
                SQL = "update student set warning_num = 2, end_limit = DATE_ADD(now(), INTERVAL 1 MONTH) where stu_num = '" + stdno + "';";
                con = dbconnection.getConnection();
                st = con.prepareStatement(SQL);
                st.executeUpdate(SQL);
                st.close();
            } else if (count >= 2) {
                SQL = "update student set warning_num = 3, end_limit = DATE_ADD(now(), INTERVAL 1 YEAR) where stu_num = '" + stdno + "';";
                con = dbconnection.getConnection();
                st = con.prepareStatement(SQL);
                st.executeUpdate(SQL);
                st.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
