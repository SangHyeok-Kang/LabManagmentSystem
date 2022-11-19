package model.State;

import model.State.State;
import java.sql.*;
import static model.DBConnection.dbconnection;

public class LeaveLabState implements State {

    String reser_num, end_time;
    String SQL;
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;
    String access = "e";

    public LeaveLabState(String resernum) {
        this.reser_num = resernum;
    }

    public void updateState() {
        try {
            SQL = "update reservation set access = '" + access + "'where reser_num = '" + reser_num + "'";
            con = dbconnection.getConnection();
            st = con.prepareStatement(SQL);
            int addrow = st.executeUpdate(SQL);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
