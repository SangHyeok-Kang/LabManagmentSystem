package model.State;

import model.State.State;
import java.sql.*;
import static model.DBConnection.dbconnection;

public class AcceptAccessState implements State {
    String reser_num;
    String SQL;
    private Connection con = null;
    private Statement st =  null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;
    
    public AcceptAccessState(String resernum){
        this.reser_num = resernum;
    }
    public void updateState(){
        String access = "y";
        
        try {
            //예약 상태가 승인 되었을 때 승인 상태로 바꿔주는 SQL문
            SQL = "update reservation set access = ?" + "where reser_num = ?";
            con = dbconnection.getConnection();
            st = con.prepareStatement(SQL);
            pstmt.setString(1,access);
            pstmt.setString(2,reser_num);
            st.executeUpdate(SQL);

            rs.close();
            st.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }finally{
            try{
                if(pstmt !=null)pstmt.close();
                if(con!=null)con.close();
            } catch (SQLException e){ } 
                
        }
        
    }
}
