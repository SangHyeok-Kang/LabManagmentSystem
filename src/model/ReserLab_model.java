
package model;

import static model.UserSession.log;
import static model.DBConnection.dbconnection;
import java.sql.*;

//실습실 조회 및 예약 클래스
public class ReserLab_model {
    String[][] lab_info; //실습실 정보 배열
    boolean[] seat_info; //좌석 정보 배열
    String[][] insert_user; //예약 신청 배열
    String user_id = log.session; 
    //예약번호, 강의실번호, 예약 날짜, 시작 시간, 종료 시간
    String reser_num, lab_num, seat_num, date, start_time, end_time;
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    
    //실습실 조회 메소드
    String[][] searchLab(){
        return null;
    }
    
    //실습실 예약 정보 메소드
    String reservation(String lab_num, String seat_num, 
                        String date, String start_time, String end_time){
        return null;
    
    }
    
    //실습실 만석여부 확인 메소드
    boolean isFull(String lab_num){
        return false;
    
    }
}
    
