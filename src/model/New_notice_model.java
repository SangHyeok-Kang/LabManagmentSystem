/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import static model.DBConnection.dbconnection;
import static model.UserSession.log;
/**
 *
 * @author pkm30, 20183150 김부성
 * 클래스 사용 용도 : 공지, 신고 문의글 등록
 */
public class New_notice_model {
     private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    String SQL;

    public boolean insertInquiry(String category,String title,String contents) { //신고, 문의글 등록
        try {              
            SQL = "insert into stu_limit values (default,'"+log.session+"','"+title+"', '"+contents+"',now(),'"+category+"')";   //db에 값 넣기
                con = dbconnection.getConnection();
                st = con.prepareStatement(SQL);
                int addrow = st.executeUpdate(SQL);
                if(addrow==1)
                    return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean insertNotice(String title,String contents) { //공지사항 글등록
        
        try {
            SQL = "insert into board value (default, '"+log.session+"', '"+ title +"', '"+ contents +"', now())";
                con = dbconnection.getConnection();
                st = con.prepareStatement(SQL);
                int addrow = st.executeUpdate(SQL);
                if(addrow==1)
                    return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}
