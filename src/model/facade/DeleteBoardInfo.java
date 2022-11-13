package model.facade;

import java.sql.*;
import static model.DBConnection.dbconnection;

public class DeleteBoardInfo { 
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    String SQL;

    public boolean deleteInfo(String stunum) {
        try {
            SQL = "delete from board where stu_num = '" + stunum + "'";
            con = dbconnection.getConnection();
            st = con.prepareStatement(SQL);
            st.executeUpdate(SQL);
            System.out.println("save 성공");
            return true;
            
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
