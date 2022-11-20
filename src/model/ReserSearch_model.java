package model;

import java.sql.*;
import static model.UserSession.log;
import static model.DBConnection.dbconnection;

/**
 *
 * @author 20183125 송준섭 클래스 사용 용도 : 예약 정보확인 및 예약 연장 클래스
 */
public class ReserSearch_model {

    public String[][] reserinfo = new String[100][6];
    public String[] reserinfo2 = new String[3];
    String[][] reser_info; //예약 정보 배열
    String SQL;
    String labnum, seatnum;
    String endtime;
    String reserdate;
    String user_id = log.session;
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    public int number = 0;
    String posstime;
    public String[] result = new String[2];

    //예약 정보 출력 메소드
    public String[][] reserlist() {
        try {
            //DB로부터 예약 정보 불러오는 SQL문
            SQL = "select * from reservation where stu_num = '" + user_id + "'";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);
            while (rs.next()) {
                reserinfo[number][0] = rs.getString("reser_num");
                reserinfo[number][1] = rs.getString("reser_date"); //날짜 번호 불러와 reserinfo배열에 저장
                reserinfo[number][2] = rs.getString("lab_num");//강의실 번호 불러와 reserinfo배열에 저장
                reserinfo[number][3] = rs.getString("start_time");//예약 시작 시간 불러와 reserinfo배열에 저장
                reserinfo[number][4] = rs.getString("end_time");//예약 종료시간 불러와 reserinfo배열에 저장
                reserinfo[number][5] = rs.getString("access");//예약 상태 불러와 reserinfo배열에 저장
                number++;
            }
        } catch (SQLException e) {
            System.out.println("[select 쿼리 오류]\n" + SQL);
        }
        return reserinfo;
    }

    public String[] reserlist(String resernum) {
        try {
            //DB로부터 예약 정보 불러오는 SQL문
            SQL = "select * from reservation where reser_num = '" + resernum + "'";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);
            while (rs.next()) {
                reserinfo2[0] = rs.getString("reser_date"); //날짜 번호 불러와 reserinfo배열에 저장
                reserinfo2[1] = rs.getString("start_time");//예약 시작 시간 불러와 reserinfo배열에 저장
                reserinfo2[2] = rs.getString("access");//예약 상태 불러와 reserinfo배열에 저장
            }
        } catch (SQLException e) {
            System.out.println("[select 쿼리 오류]\n" + SQL);
        }
        return reserinfo2;
    }

    //예약 연장 가능 시간 조회
    public String[] PossibleExtend() {
        try {
            SQL = "select lab_num, seat_num, end_time, reser_date, hour(end_time) e_time from reservation where stu_num = '" + user_id + "' and access = 'u'";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);
            if (rs.next()) {
                labnum = rs.getString("lab_num");
                seatnum = rs.getString("seat_num");
                endtime = rs.getString("end_time");
                reserdate = rs.getString("reser_date");
                result[0] = rs.getString("e_time");
            }
            st.close();
            rs.close();
            SQL = "select hour(start_time) s_time from reservation where lab_num = " + labnum
                    + " and seat_num = '" + seatnum + "'"
                    + " and reser_date = '" + reserdate + "'"
                    + " and (hour('"+ endtime + "') <= hour(start_time))";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);
            if (rs.next()) {
                result[1] = rs.getString("s_time");
            }else{
                result[1] = "23";
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return result;
    }

    public boolean extendReser(String reser_num) {
        try {
            SQL = "SELECT DATE_ADD(end_time, INTERVAL 1 HOUR) result from reservation WHERE RESER_NUM ='"+reser_num+"'";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);
            if (rs.next()) {
                posstime = rs.getString("result");
            }
            rs.close();
            st.close();
            
            SQL = "update reservation set end_time = '" + posstime + "' where reser_num = '" + reser_num + "'";
            con = dbconnection.getConnection();
            st = con.prepareStatement(SQL);
            st.executeUpdate(SQL);;
            st.close();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
