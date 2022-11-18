package model;

import java.sql.*;
import static model.DBConnection.dbconnection;
import static model.UserSession.log;

public class ChangeInfo_model {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    String SQL;
    String user_id = log.session;

    public boolean isChangeinfo(String changepass, String currentpass, String phone_num, String email) {
        try {
            SQL = "select * from student where stu_num = '" + user_id + "' and pass = '" + currentpass + "'";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);
            if (rs.next()) {
                //비밀번호만 변경할 경우
                if (!changepass.equals("null") && phone_num.equals("null") && email.equals("null")) {
                    SQL = "update student set pass = '" + changepass + "' where stu_num = '" + user_id + "'";
                    con = dbconnection.getConnection();
                    st = con.prepareStatement(SQL);
                    st.executeUpdate(SQL);
                    rs.close();
                    st.close();
                }
                //휴대폰 번호만 변경할 경우
                else if(changepass.equals("null") && !phone_num.equals("null") && email.equals("null")){
                    SQL = "update student set ph_num = '" + phone_num + "' where stu_num = '" + user_id + "'";
                    con = dbconnection.getConnection();
                    st = con.prepareStatement(SQL);
                    st.executeUpdate(SQL);
                    rs.close();
                    st.close();
                }
                //이메일만 변경할 경우
                 else if(changepass.equals("null") && phone_num.equals("null") && !email.equals("null")){
                    SQL = "update student set email = '" + email + "' where stu_num = '" + user_id + "'";
                    con = dbconnection.getConnection();
                    st = con.prepareStatement(SQL);
                    st.executeUpdate(SQL);
                    rs.close();
                    st.close();
                }
                //비밀번호와 휴대폰 번호를 변경할 경우
                 else if(!changepass.equals("null") && !phone_num.equals("null") && email.equals("null")){
                    SQL = "update student set ph_num = '" + phone_num + "', pass = '"+changepass+"' where stu_num = '" + user_id + "'";
                    con = dbconnection.getConnection();
                    st = con.prepareStatement(SQL);
                    st.executeUpdate(SQL);
                    rs.close();
                    st.close();
                }
                //비밀번호와 이메일을 변경할 경우
                 else if(!changepass.equals("null") && phone_num.equals("null") && !email.equals("null")){
                    SQL = "update student set pass = '" + changepass + "', email = '"+email+"' where stu_num = '" + user_id + "'";               
                    con = dbconnection.getConnection();
                    st = con.prepareStatement(SQL);
                    st.executeUpdate(SQL);
                    rs.close();
                    st.close();
                }
                //휴대폰번호와 이메일을 변경할 경우
                else if(changepass.equals("null") && !phone_num.equals("null") && !email.equals("null")){
                    SQL = "update student set ph_num = '" + phone_num + "', email = '"+email+"' where stu_num = '" + user_id + "'";
                    con = dbconnection.getConnection();
                    st = con.prepareStatement(SQL);
                    st.executeUpdate(SQL);
                    rs.close();
                    st.close();
                }
                //모두 변경할 경우
                else if(!changepass.equals("null") && !phone_num.equals("null") && !email.equals("null")){
                    SQL = "update student set pass = '" + changepass + "',ph_num = '" + phone_num + "',email = '" + email + "' where stu_num = '" + user_id + "'";
                    con = dbconnection.getConnection();
                    st = con.prepareStatement(SQL);
                    st.executeUpdate(SQL);
                    rs.close();
                    st.close();
                }
                //아무것도 입력하지 않았을 경우
                else{
                    System.out.println("변경할 내용을 입력해주세요");
                    return false;
                }
                return true;
            } else {
                System.out.println("현재 비밀번호를 잘못 입력하셨습니다.");
                return false;
            }
        
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
