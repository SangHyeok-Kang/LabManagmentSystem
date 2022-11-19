package model.State;

import model.State.State;
import java.sql.*;
import static model.DBConnection.dbconnection;

public class WaitAccessState implements State {

    String reser_num;
    String SQL;
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;
    String access = "w";

    public WaitAccessState(String resernum) {
        this.reser_num = resernum;
    }

    public void updateState() {
        try {
            //예약 상태가 대기 되었을 때 대기 상태로 바꿔주는 SQL문
            SQL = "update reservation set access = '" + access + "' where reser_num = '" + reser_num + "'";
            con = dbconnection.getConnection();
            st = con.prepareStatement(SQL);
            int addrow = st.executeUpdate(SQL);

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
