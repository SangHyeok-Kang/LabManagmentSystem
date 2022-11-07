package model;

import java.sql.*;
import static model.UserSession.log;
import static model.DBConnection.dbconnection;

//실습실 예약 리스트 출력 클래스
public class ReserList_model {
    String[][] reser_info; //예약 정보 배열
    int number = 0;
    String reser_num, name, start_time, end_time; //예약번호, 예약자, 예약 시작/종료시간
    String user_id = log.session;
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    
    //예약 정보리스트 출력 메소드
    String[][] reserlist(){
        return null;
        
    }
    
    //책임자 부여 메소드
    boolean appoint_manager(String user_id){
        return false;
        
    }
}
