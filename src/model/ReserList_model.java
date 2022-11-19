package model;

import java.sql.*;
import static model.UserSession.log;
import static model.DBConnection.dbconnection;

/**
 *
 * @author 20183125 송준섭 클래스 사용 용도 : 실습실 예약 리스트 출력 및 책임자 부여 클래스
 */
//실습실 예약 리스트 출력 클래스
public class ReserList_model {

    public String[][] reserinfo = new String[100][10];
    public int number = 0;
    String SQL;
    String reser_num, name, start_time, end_time; //예약번호, 예약자, 예약 시작/종료시간
    String user_id = log.session;
    String labnum, stdno;
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    //예약 정보리스트 출력 메소드
    public String[][] reserlist(String cat) {
        try {
            //DB로부터 예약 정보 불러오는 SQL문
            if (cat.equals("today")) {
                SQL = "select * from reservation r join student s on r.stu_num = s.stu_num and r.reser_date = DATE_FORMAT(now(),'%Y-%m-%d');";
                st = dbconnection.getInstance().getConnection().createStatement();
                rs = st.executeQuery(SQL);
                while (rs.next()) {
                    reserinfo[number][0] = rs.getString("reser_num");//예약 번호 불러와 reserinfo배열에 저장
                    reserinfo[number][1] = rs.getString("stu_num");//예약 번호 불러와 reserinfo배열에 저장
                    reserinfo[number][2] = rs.getString("name");//예약 번호 불러와 reserinfo배열에 저장
                    reserinfo[number][3] = rs.getString("lab_num");
                    reserinfo[number][4] = rs.getString("seat_num");
                    reserinfo[number][5] = rs.getString("reser_date");
                    reserinfo[number][6] = rs.getString("start_time");//예약 번호 불러와 reserinfo배열에 저장
                    reserinfo[number][7] = rs.getString("end_time");//예약 번호 불러와 reserinfo배열에 저장
                    reserinfo[number][8] = rs.getString("tl_num");
                    reserinfo[number][9] = rs.getString("access");
                    number++;
                }
            } else {
                SQL = "select * from reservation r join student s on r.stu_num = s.stu_num;";
                st = dbconnection.getInstance().getConnection().createStatement();
                rs = st.executeQuery(SQL);
                while (rs.next()) {
                    reserinfo[number][0] = rs.getString("reser_num");//예약 번호 불러와 reserinfo배열에 저장
                    reserinfo[number][1] = rs.getString("stu_num");//예약 번호 불러와 reserinfo배열에 저장
                    reserinfo[number][2] = rs.getString("name");//예약 번호 불러와 reserinfo배열에 저장
                    reserinfo[number][3] = rs.getString("lab_num");
                    reserinfo[number][4] = rs.getString("seat_num");
                    reserinfo[number][5] = rs.getString("reser_date");
                    reserinfo[number][6] = rs.getString("start_time");//예약 번호 불러와 reserinfo배열에 저장
                    reserinfo[number][7] = rs.getString("end_time");//예약 번호 불러와 reserinfo배열에 저장
                    reserinfo[number][8] = rs.getString("tl_num");
                    reserinfo[number][9] = rs.getString("access");
                    number++;
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return reserinfo;

    }

    //책임자 부여 메소드
    public boolean appoint_manager(String reser_num) {
        try {
            //예약 상태가 취소 되었을 때 취소 상태로 바꿔주는 SQL문
            SQL = "update reservation set mgr = '1' where reser_num = '" + reser_num + "'";
            con = dbconnection.getConnection();
            st = con.prepareStatement(SQL);
            st.executeUpdate(SQL);
            rs.close();
            st.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //책임자 이전 메소드
    public boolean update_manager(String user_id) {
        try {
            SQL = "select lab_num from reservation where mgr ='1' and stu_num = '" + user_id + "'";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);
            if (rs.next()) {
                labnum = rs.getString("lab_num");
            }
            st.close();
            rs.close();

            SQL = "update reservation set mgr ='0' where std_num = '" + user_id + "' and mgr = '1'";
            con = dbconnection.getConnection();
            st = con.prepareStatement(SQL);
            st.executeUpdate(SQL);
            rs.close();
            st.close();

            SQL = "seelct stu_num, max(endtime) from reservation where lab_num = " + labnum + "and access = 'u'";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);
            if (rs.next()) {
                stdno = rs.getString("stu_num");
            }
            rs.close();
            st.close();

            SQL = "update reservation set mgr = '1' where lab_num = " + labnum + "and stu_num = '" + stdno + "' and access = 'u'";
            con = dbconnection.getConnection();
            st = con.prepareStatement(SQL);
            st.executeUpdate(SQL);
            rs.close();
            st.close();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
