package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static model.DBConnection.dbconnection;
import static model.UserSession.log;

public class Login_model {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:00:00");
    public String auth = null;
    public String passw = null;
    public String name;
    public String endlimit;
    public LocalDateTime end_limit;
    public LocalDateTime curdate = LocalDateTime.now();
    String SQL;

    public String isLogin(String user_id, String pass) { //로그인            
        char a = user_id.charAt(0);

        try {
            if (a == 'S') { //학생으로 로그인시
                SQL = "select * from student where stu_num = '" + user_id + "'";
                st = dbconnection.getInstance().getConnection().createStatement();
                rs = st.executeQuery(SQL); //db연결
                if (rs.next()) {
                    auth = rs.getString("stu_num");
                    passw = rs.getString("pass");
                    name = rs.getString("name");
                    endlimit = rs.getString("end_limit");
                }
                end_limit = LocalDateTime.parse(endlimit, formatter);
            } else if (a == 'M' || a == 'P') {
                SQL = "select * from manager where man_num = '" + user_id + "'";
                st = dbconnection.getInstance().getConnection().createStatement();
                rs = st.executeQuery(SQL);
                if (rs.next()) {
                    auth = rs.getString("man_num");
                    passw = rs.getString("pass");
                    name = rs.getString("name");
                }
            }
            if (end_limit.isBefore(curdate)) {
                return "limit";
            } else if (user_id.equals(auth) && pass.equals(passw)) { //아이디 패스워드 일치할 경우
                System.out.println("로그인 on");
                log.setSession(auth);//사용자 로그인 정보를 세션으로 저장
                System.out.println("session 생성 : " + log.session);
                return "success";
            } else if (!user_id.equals(auth)) { // 아이디 존재하지않을경우
                System.out.println("등록되지 않은 사용자 입니다. try 아이디를 확인해 주세요");
                return "noid";
            } else { // 암호 불일치
                System.out.println("암호가 wrong.   다시 확인해주세요");
                return "nopass";
            }

        } catch (SQLException e) {
            System.out.println("데이터베이스 검색 error :" + e);
            return "failed";
        }      
    }

    public boolean isAccess() { //토큰값 비교
        String user_id = log.session;
        String token = null;
        String access = null;
        try {
            SQL = "select * from student where stu_num = '" + user_id + "'";
            st = dbconnection.getInstance().getConnection().createStatement();//DB접속
            rs = st.executeQuery(SQL);
            if (rs.next()) {
                access = rs.getString("access");  //DB의 승인값                
            }
            rs.close();
            st.close();

            if (access.equals("0")) { // 승인되지 않은 학생일 경우
                return false;
            } else {
                System.out.println("이미 승인되었습니다");
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean InsertToken(String token) {
        String user_id = log.session;
        String tokennum = null;
        try {
            SQL = "select * from token";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);
            if (rs.next()) {
                tokennum = rs.getString("token_num"); //DB의 토큰넘버
            }
            st.close();
            rs.close();
            if (tokennum.equals(token)) { //토큰 일치시 
                SQL = "update student set access = '" + "1" + "' where stu_num = '" + user_id + "'";  // 해당학생 승인값 1로 설정                
                con = dbconnection.getConnection();
                st = con.prepareStatement(SQL);
                st.executeUpdate(SQL);
                System.out.println("save 성공");
                return true;
            } else {
                System.out.println("토큰 값을 잘못 입력하셨습니다.");
                return false;
            }

        } catch (SQLException e) {
            return false;
        }
    }
}
