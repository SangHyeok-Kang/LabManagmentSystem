package model;

import java.sql.*;
import static model.DBConnection.dbconnection;

/**
 *
 * @author 클래스 사용 용도 : 관리자 실습실 사용 현황 조회
 */
public class m_CurLab_model {
    String[][] currentlab = new String[160][5];
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    int number = 0;
    String SQL;

    String[][] CurrentLab(int lab_num) {
        try {
                SQL = "SELECT * "
                        + " from reservation "
                        + " where hour(now()) >= hour(start_time)"
                        + " and hour(end_time) > hour(now())"
                        + " and reser_date = DATE_FORMAT(now(),'%Y-%m-%d')"
                        + " and lab_num = '" + lab_num + "'"
                        + " and (access != 'x' or access != 'e')";
                st = dbconnection.getInstance().getConnection().createStatement();
                rs = st.executeQuery(SQL);
                while (rs.next()) {
                    currentlab[number][0] = rs.getString("reser_num");
                    currentlab[number][1] = rs.getString("stu_num");
                    currentlab[number][2] = rs.getString("seat_num");
                    currentlab[number][3] = rs.getString("start_time");
                    currentlab[number][4] = rs.getString("end_time");
                    number++;
                }
                rs.close();
                st.close();
        } catch (SQLException e) {
            System.out.println(e + SQL);
        }
        return currentlab;
    }
}
