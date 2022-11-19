/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.DBConnection.dbconnection;
/**
 *
 * @author pkm30, 20183150 김부성
 * 클래스 사용 용도 : 문의, 신고 출력 및 공지사항 출력 모델
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
    
    
    public ArrayList<Notice> notice() { //공지사항 출력
        DBConnection.getInstance().Initailize();
        ArrayList<Notice> list = new ArrayList<>();
        try {
            
            SQL = "select * from board";  
            st = dbconnection.getInstance().getConnection().createStatement();  //  db연결
            rs = st.executeQuery(SQL); 
            while(rs.next()){
                list.add(new Notice(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return list;
    }
    
    public ArrayList<Notice> inquriy() { //신고 및 문의 사항 출력
        DBConnection.getInstance().Initailize();
        ArrayList<Notice> list = new ArrayList<>();
        try {
            SQL = "select * from stu_limit";  
            st = dbconnection.getInstance().getConnection().createStatement();  //  db연결
            rs = st.executeQuery(SQL); 
            while(rs.next()){
                list.add(new Notice(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return list;
    }
    
    public boolean removeNotice(String num){
        int addrow = 0;
        DBConnection.getInstance().Initailize();
        String sql = "delete from board where board_num = '"+num+"'";
        con = dbconnection.getConnection();
         try {
             st = con.prepareStatement(sql);
             addrow = st.executeUpdate(sql);
    
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         return addrow == 1;
    }
    public boolean removeInquiry(String num){
        int addrow = 0;
        DBConnection.getInstance().Initailize();
        String sql = "delete from stu_limit where limit_num = '"+num+"'";
        con = dbconnection.getConnection();
         try {
             st = con.prepareStatement(sql);
             addrow = st.executeUpdate(sql);
    
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
         return addrow == 1;
    }
    /*
     public boolean inquiry(String cate) { //문의글 출력
        
        
        try {
            
                 SQL = "select * from board where category = '"+cate+"'" ;  
                st = dbconnection.getInstance().getConnection().createStatement(); 
                rs = st.executeQuery(SQL);  //  db연결
             
                    if(rs.next()){
                    contents = rs.getString("contents"); 
                    notice_num = rs.getString("board_num");
                    writer_num = rs.getString("man_num");
                    title = rs.getString("title");
                    date = rs.getString("date");
                    NoticeType = rs.getString("category");
                
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
     public boolean report(int limit_num) { //신고글 출력
        
        
        try {
            
                 SQL = "select * from user_limit where limit_num="+limit_num;  
                st = dbconnection.getInstance().getConnection().createStatement(); 
                rs = st.executeQuery(SQL);  //  db연결
             
                    if(rs.next()){
                    contents = rs.getString("content"); 
                    notice_num = rs.getString("limit_num");
                    writer_num = rs.getString("stu_num");
                    title = rs.getString("title");
                    date = rs.getString("date");
                    System.out.println(notice_num +"\t" +writer_num+"\t"+title+"\n"+contents+"\n"+date); 
                
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
     /*
     public boolean ChangeList(String board_num,String title,String contents) { //공지수정   
        try {
            
                SQL = "select * from board where board_num = '" + board_num + "'";                                
                 st = dbconnection.getInstance().getConnection().createStatement();
                 rs = st.executeQuery(SQL);

                
               if (rs.next()) { 
                   SQL = "update board set title = '"+title+"',contents = '"+contents+"' ,date = "+"now()"+" where board_num = '" + board_num + "'";  //db에 정보 변경               
                    con = dbconnection.getConnection();
                    st = con.prepareStatement(SQL);
                    int addrow = st.executeUpdate(SQL);
                    System.out.println("save 성공");
                    return true;
                   
                } else { //학번 일치하는 학생 x
                   
                   System.out.println("no.");
                    return false;
                }
             

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }*/
     
     
     
     
     
     
     
     
     
}
