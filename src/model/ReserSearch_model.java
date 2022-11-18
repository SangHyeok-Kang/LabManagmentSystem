package model;

import java.sql.*;
import static model.UserSession.log;
import static model.DBConnection.dbconnection;

/**
 *
 * @author 20183125 송준섭 클래스 사용 용도 : 예약 정보확인 및 예약 연장 클래스
 */
public class ReserSearch_model {
    public String[][] reserinfo = new String[100][5];
    String[][] reser_info; //예약 정보 배열
    String SQL;
    String labnum, seatnum;
    java.sql.Time endtime;
    java.sql.Date reserdate;
    String user_id = log.session;
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    public int number = 0;
    String posstime;
    
    //예약 정보 출력 메소드
    public String[][] reserlist() {        
        try {
            //DB로부터 예약 정보 불러오는 SQL문
            SQL = "select * from reservation where stu_num = '" + user_id + "'";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);
            while (rs.next()) {             
                reserinfo[number][0] = rs.getString("reser_date"); //날짜 번호 불러와 reserinfo배열에 저장
                reserinfo[number][1] = rs.getString("lab_num");//강의실 번호 불러와 reserinfo배열에 저장
                reserinfo[number][2] = rs.getString("start_time");//예약 시작 시간 불러와 reserinfo배열에 저장
                reserinfo[number][3] = rs.getString("end_time");//예약 종료시간 불러와 reserinfo배열에 저장
                reserinfo[number][4] = rs.getString("access");//예약 상태 불러와 reserinfo배열에 저장
                number++;
            }
        } catch (SQLException e) {
            System.out.println("[select 쿼리 오류]\n" + SQL);
        }
        return reserinfo;
    }

    //예약 연장 가능 시간 조회
    String PossibleExtend() {
        try {
            SQL = "select lab_num, seat_num, end_time, reser_date from reservation where stu_num = '" + user_id + "' and access = 'y'";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);
            if (rs.next()) {
                labnum = rs.getString("lab_num");
                seatnum = rs.getString("seat_num");
                endtime = java.sql.Time.valueOf(rs.getString("end_time"));
                reserdate = java.sql.Date.valueOf(rs.getString("reser_date"));

            }
            st.close();
            rs.close();
            SQL = "select start_time from reservation where lab_num = " + labnum
                    + " and seat_num = '" + seatnum + "'"
                    + " and reser_date = '" + reserdate + "'"
                    + " and (hour(" + endtime + ") <= hour(start_time))";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);
            if (rs.next()) {
                posstime = rs.getString("start_time");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return posstime;
    }

    boolean extendReser(String reser_num, String end_time) {
        endtime = java.sql.Time.valueOf(end_time);
        try {
            SQL = "update reservation set end_time = '" + endtime + "' where reser_num = '" + reser_num + "'";
            con = dbconnection.getConnection();
            st = con.prepareStatement(SQL);
            st.executeUpdate(SQL);
            rs.close();
            st.close();
            
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}