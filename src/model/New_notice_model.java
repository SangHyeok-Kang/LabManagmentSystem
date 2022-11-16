package model;

import java.sql.*;
import static model.DBConnection.dbconnection;

/**
 *
 * @author pkm30
 */
public class New_notice_model {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    String SQL;

    public boolean insertNotice(String category, String man_num, String title, String contents) {

        try {

            SQL = "select * from board";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);  //  db연결
            //  SQL = "select count(*) from board ";

            //  if (rs.next()) {
            //    System.out.println("이미 존재하는 게시글.");
            //  return false;} 
            // else {
            SQL = "insert into board(board_num, man_num, title, contents, date, category) "
                    + "values((Select IfNULL(max(board_num)+1,1)From board b),'" + man_num + "','" + title + "','" + contents + "'," //select ifnull ~~ 은 자동 카운트 +1 
                    + "now()" + ",'" + category + "')";   //db에 값 넣기
            con = dbconnection.getConnection();
            st = con.prepareStatement(SQL);
            int addrow = st.executeUpdate(SQL);
            System.out.println("save 성공");
            return true;
            //  }

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
