package model.State;

import model.State.State;
import java.sql.*;
import static model.DBConnection.dbconnection;

public class AcceptAccessState implements State {

    String reser_num;
    String SQL;
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;
    String access = "y";

    public AcceptAccessState(String resernum) {
        this.reser_num = resernum;
    }

    public boolean updateState() {
        try {
            //예약 상태가 승인 되었을 때 승인 상태로 바꿔주는 SQL문
            SQL = "update reservation set access = '" + access + "' where reser_num = '" + reser_num + "'";
            con = dbconnection.getConnection();
            st = con.prepareStatement(SQL);
            st.executeUpdate(SQL);

            rs.close();
            st.close();
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
