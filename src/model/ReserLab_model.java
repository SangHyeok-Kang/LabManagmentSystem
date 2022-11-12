package model;

import static java.lang.Math.random;
import static model.UserSession.log;
import static model.DBConnection.dbconnection;
import java.sql.*;
import java.util.*;
import java.time.*;

/**
 *
 * @author 20183109 강상혁 클래스 사용 용도 : 실습실 조회 및 예약 클래스
 */
public class ReserLab_model {

    java.sql.Time time = new java.sql.Time(17, 00, 00); //17시 시간 저장
    LocalTime curtime = LocalTime.now(); //현재 시간 저장
    //java.sql.Time current = java.sql.Time.valueOf(curtime); //LocalTime to java.sql.Time (형변환)
    LocalTime deadline = LocalTime.of(16, 30, 00); //예약 마감 시간 저장
    LocalDate curdate = LocalDate.now(); //현재 날짜 저장
    String[][] lab_info = new String[100][5];
    boolean[] seat_info; //좌석 정보 배열
    int rand = (int) ((Math.random() * 300) + 100); //난수 생성
    int count = 0; //실습실 이용자수 저장받기 위한 변수
    int number = 0; //2차원배열 위치를 위한 변수
    String SQL;
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    int[] labnum = {915,916,918,911};

    String user_id = log.session;

    //실습실 현재 사용현황 메소드
    String[][] searchLab(int lab_num, List<Integer> num, String date) {
        try {
            for (int i = 0; i < num.size(); i++) {
                SQL = "SELECT * "
                        + " from reservation "
                        + " where ('" + num.get(i) + "' >= hour(start_time)"
                        + " and hour(end_time) > '" + num.get(i) + "')"
                        + " and reser_date = '" + date + "'"
                        + " and lab_num = '" + lab_num + "'"
                        + " and (access != 'x' or access != 'e')";
                st = dbconnection.getInstance().getConnection().createStatement();
                rs = st.executeQuery(SQL);
                while (rs.next()) {
                    lab_info[number][0] = rs.getString("reser_num");
                    lab_info[number][1] = rs.getString("seat_num");
                    lab_info[number][2] = rs.getString("reser_date");
                    lab_info[number][3] = rs.getString("start_time");
                    lab_info[number][4] = rs.getString("end_time");
                    number++;
                }
                rs.close();
                st.close();
            }
        } catch (SQLException e) {
            System.out.println(e + SQL);
        }
        return lab_info;
    }

    //실습실 예약 정보 입력 메소드
    boolean reservation(List<String> stu_num, int[] lab_num, String[] seat_num, java.sql.Date[] date, java.sql.Time[] start_time, java.sql.Time[] end_time) {
        try {
            for (int i = 0; i < stu_num.size(); i++) { //입력받은 학생의 크기만큼 반복
                //입력받은 학생들이 student 테이블에 존재여부 확인
                SQL = "select * from student where stu_num = '" + stu_num.get(i) + "'";
                st = dbconnection.getInstance().getConnection().createStatement();
                rs = st.executeQuery(SQL);
                if (rs.next()) {
                } else {
                    System.out.println(SQL);
                    System.out.println("회원가입 하지 않은 학생이 입력되었습니다. 다시 입력해주세요.");
                    return false;
                }
            }
            rs.close();
            st.close();

            for (int i = 0; i < stu_num.size(); i++) {//입력받은 학생의 크기만큼 반복
                //예약 테이블의 존재하지 않는 예약번호가 나올때까지 난수 생성
                while (true) {
                    String sql = "select * from reservation where reser_num =" + rand;
                    st = dbconnection.getInstance().getConnection().createStatement();
                    rs = st.executeQuery(sql);
                    if (rs.next()) {
                        rand = (int) ((Math.random() * 300) + 100);
                    } else {
                        break;
                    }

                }
                //예약 시작시간이 17시 이전일 경우, 자동 승인
                if (start_time[i].before(time)) {
                    SQL = "insert into reservation(reser_num, stu_num, lab_num, mgr, "
                            + "seat_num, reser_date, start_time, end_time, tl_num, access) "
                            + "values(" + rand + ",'"
                            + stu_num.get(i) + "',"
                            + lab_num[i] + ","
                            + "default" + ",'"
                            + seat_num[i] + "','"
                            + date[i] + "','"
                            + start_time[i] + "','"
                            + end_time[i] + "','"
                            + user_id + "',"
                            + "'y'" + ")";
                    con = dbconnection.getConnection();
                    st = con.prepareStatement(SQL);
                    st.executeUpdate(SQL);
                    st.close();
                } //예약 시작 시간이 17시부터일 경우, 예약 승인 대기 상태 
                else {
                    if (curtime.isAfter(deadline)) { //현재 시간이 16시30분 이후일 경우, 예약 불가 추후 컨트롤러에서 팝업창으로 예약 불가능시간 출력
                        System.out.println("Reservation failed");
                        return false;
                    } else { //현재 시간이 16시30분 이전일 경우 예약 가능
                        SQL = "insert into reservation(reser_num, stu_num, lab_num, mgr, "
                                + "seat_num, reser_date, start_time, end_time, tl_num, access) "
                                + "values(" + rand + ",'"
                                + stu_num.get(i) + "',"
                                + lab_num[i] + ","
                                + "default" + ",'"
                                + seat_num[i] + "','"
                                + date[i] + "','"
                                + start_time[i] + "','"
                                + end_time[i] + "','"
                                + user_id + "',"
                                + "'w'" + ")";
                        con = dbconnection.getConnection();
                        st = con.prepareStatement(SQL);
                        st.executeUpdate(SQL);
                        st.close();
                    }
                    rs.close();
                }
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e + SQL);
            return false;
        }
    }

    //실습실 만석여부 확인 메소드
    boolean isFull(int lab_num, List<Integer> num, String date) {
        try {
            //예약 시작시간부터 한시간마다 체크하여 만석여부 확인
            for (int i = 0; i < num.size(); i++) {
                SQL = "SELECT count(*) count "
                        + " from reservation "
                        + " where ('" + num.get(i) + "' >= hour(start_time)"
                        + " and hour(end_time) > '" + num.get(i) + "')"
                        + " and reser_date = '" + date + "'"
                        + " and lab_num = '" + lab_num + "'"
                        + " and (access != 'x' or access != 'e')"
                        + " group by lab_num;";
                st = dbconnection.getInstance().getConnection().createStatement();
                rs = st.executeQuery(SQL);
                if (rs.next()) {
                    count = rs.getInt(1);
                    System.out.println(count);
                }

                if (count >= 20) {
                    return false;
                }
                st.close();
                rs.close();
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
