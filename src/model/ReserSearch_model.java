package model;

import java.sql.*;
import static model.UserSession.log;

//예약 정보확인 클래스
public class ReserSearch_model {
    String[][] reser_info; //예약 정보 배열
    String reser_num, name, start_time, end_time; //예약 번호, 이름, 시작시간, 종료시간
    String user_id = log.session;
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    
    //예약 정보 출력 메소드
    String[][] reserlist(){
        
        return null;
        
    }
    
    //에약 연장 메소드
    void extendReser(String reser_num, String end_time){
        
    }
}
