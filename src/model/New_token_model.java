package model;

import java.util.Random;

import java.sql.*;

import static model.DBConnection.dbconnection;

public class New_token_model {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    String SQL;

    public boolean newtoken(String manager_id) {
        try {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 5;

            Random random = new Random();
            String randomtoken = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();   //랜덤 알파벳 5자리 생성

            SQL = "select * from token where man_num = '" + manager_id + "'";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL); //db연결

            SQL = "insert into token(token_num, man_num, issue_date)" //토큰값 db에 입력
                    + "values('" + randomtoken + "','" + manager_id + "'," + "now()" + ")";

            con = dbconnection.getConnection();
            st = con.prepareStatement(SQL);
            int addrow = st.executeUpdate(SQL);
            System.out.println("token 저장 성공");
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return false;
    }

}
