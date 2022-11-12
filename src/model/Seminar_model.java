/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.DBConnection.dbconnection;


/**
 *
 * @author 20183150 김부성 
 * 클래스 사용 용도 : 세미나 시간 등록, 수정, 삭제 ,조회
 * 마지막 수정 일시 : 2022-11-12
 */
public class Seminar_model extends Lecture {
     
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    String sql;
    int success;
  
    public int insertSeminar(Lecture l){ //강의 시간표 입력을 위한 함수
        DBConnection.getInstance().Initailize();
        try {
            sql = "insert into seminar values('" 
                    + l.getId() + "','"  //예약번호
                    + l.getProf_id() + "','" //담당자 이름
                    + l.getName() +"','" //세미나 이름
                    + l.getLab_num() + "','"// 강의실 번호
                    + l.getDay() + "','" //날짜
                    + l.getStime() + "','" //시작 시간
                    + l.getEtime()+ "')"; // 종료 시간
            con = dbconnection.getConnection();
            st = con.prepareStatement(sql);
            success = st.executeUpdate(sql);
            if(success == 0 ) System.out.println("insert에 실패하였습니다.");
            if(success == 1 ) System.out.println("insert에 성공하였습니다.");
        } catch (SQLException ex) {
            System.out.println("insert SQL구문 오류 입니다."+ ex.getMessage());
        }finally{
            try {
                if(st!=null){st.close();}
                if(con!=null){con.close();}
            } catch (SQLException ex) {
                System.out.println("insert 구문 JDBC 커넥트 닫기 오류");
            }
        } 
        return success;
    }
 
    public int deleteSeminar(Lecture l){ //강의 시간표 삭제를 위한 함수
        DBConnection.getInstance().Initailize();
        try {
            sql = "delete from Seminar where regi_num='" + l.getId() + "'";
            con = dbconnection.getConnection();
            st = con.prepareStatement(sql);
            success = st.executeUpdate(sql);
            if(success == 0 ) System.out.println("delete에 실패하였습니다.");
            if(success == 1 ) System.out.println("delete에 성공하였습니다.");
        } catch (SQLException ex) {
            System.out.println("delete SQL구문 오류 입니다."+ex.getMessage());
        }finally{
            try {
                if(st!=null){st.close();}
                if(con!=null){con.close();}
            } catch (SQLException ex) {
                Logger.getLogger(Class_model.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return success;
    }
    
    public int changechange(Lecture old_l, Lecture new_l){ //강의 시간표 수정을 위한 함수
        success = deleteSeminar(old_l);
        if(success == 1)
            success = insertSeminar(new_l);
        return success;
    }
    
    public boolean searchSeminar(Lecture l){ // 강의 찾기 함수
        DBConnection.getInstance().Initailize();
        boolean a= false;
        try {
            sql = "select * from seminar where regi_num='" + l.getId() + "'";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                a = true;
                if(a) System.out.println("search에 성공하였습니다." + rs.getString(1));
            }else{
                a = false;
                if(!a) System.out.println("search에 실패하였습니다.");
            }
        } catch (SQLException ex) {
            System.out.println("search SQL구문 오류 입니다." + ex.getMessage());
        }finally{
            try {
                if(st!=null){st.close();}
                if(con!=null){con.close();}
            } catch (SQLException ex) {
                Logger.getLogger(Class_model.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return a;
    }
}
