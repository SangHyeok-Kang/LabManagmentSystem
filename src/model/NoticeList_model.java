/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.*;
import static model.DBConnection.dbconnection;
/**
 *
 * @author pkm30
 */
public class NoticeList_model {
      private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    String SQL;
    String notice_num = null;
    String writer_num = null;
    String title = null;
    String contents = null;
    String date = null;
    String NoticeType = null;

    public boolean notice(int num) { //공지사항
        
        
        try {
            
                SQL = "select * from board where board_num = '"+num+"' and category = 'abc'";  
                st = dbconnection.getInstance().getConnection().createStatement(); 
                rs = st.executeQuery(SQL);  //  db연결
              
                    if(rs.next()){
                    contents = rs.getString("contents"); 
                    notice_num = rs.getString("board_num");
                    writer_num = rs.getString("man_num");
                    title = rs.getString("title");
                    date = rs.getString("date");
                    NoticeType = rs.getString("category");       //db값 받아오기
                        System.out.println(notice_num +"\t" +writer_num+"\t"+title+"\n"+contents+"\n"+date+"\n"+NoticeType); //출력
                
                    return true;
                    }
                    else{ //없는 번호, 카테고리
                        
                    System.out.println("no");
                    return false;
                    }
              //  }
           

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
     public boolean inquiry(int num) { //문의글
        
        
        try {
            
                 SQL = "select * from board where board_num = '"+num+"' and category = 'bsd'";  
                st = dbconnection.getInstance().getConnection().createStatement(); 
                rs = st.executeQuery(SQL);  //  db연결
             
                    if(rs.next()){
                    contents = rs.getString("contents"); 
                    notice_num = rs.getString("board_num");
                    writer_num = rs.getString("man_num");
                    title = rs.getString("title");
                    date = rs.getString("date");
                    NoticeType = rs.getString("category");
                    System.out.println(notice_num +"\t" +writer_num+"\t"+title+"\n"+contents+"\n"+date+"\n"+NoticeType); 
                
                    return true;
                    }
                    else{
                        
                    System.out.println("NO");
                    return false;
                    }
              //  }
           

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
