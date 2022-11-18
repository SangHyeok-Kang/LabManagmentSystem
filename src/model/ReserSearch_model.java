package model;

import java.sql.*;
import static model.UserSession.log;
import static model.DBConnection.dbconnection;

//예약 정보확인 클래스
public class ReserSearch_model {
    String[][] reser_info; //예약 정보 배열
    String SQL;
    String reser_num, name, start_time, end_time; //예약 번호, 이름, 시작시간, 종료시간
    String user_id = log.session;
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    
    //예약 정보 출력 메소드
    String[][] reserlist(){
         String[][] reserinfo = new String[10][4];
        int number = 0;
        
        try {
            //DB로부터 예약 정보 불러오는 SQL문
            SQL = "select *from reservation";
            con = dbconnection.getConnection();
            st = con.prepareStatement(SQL);
            rs = st.executeQuery(SQL);
            while(rs.next()){
                reserinfo[number][0] = rs.getString("reser_num");//예약 번호 불러와 reserinfo배열에 저장
                reserinfo[number][1] = rs.getString("name");//예약 번호 불러와 reserinfo배열에 저장
                reserinfo[number][2] = rs.getString("start_time");//예약 번호 불러와 reserinfo배열에 저장
                reserinfo[number][3] = rs.getString("end_time");//예약 번호 불러와 reserinfo배열에 저장
                number++;
            }
        } catch (SQLException e){
            System.out.println("[INSERT 쿼리 오류]\n" + SQL);
        }
        return reserinfo;
    }
    
    //에약 연장 메소드
    void extendReser(String reser_num, String end_time){
        
    }
}
