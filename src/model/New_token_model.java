/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Random;

import java.sql.*;

import static model.DBConnection.dbconnection;
import static model.UserSession.log;

public class New_token_model {
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    String SQL;
    
    public boolean newtoken(){
        DBConnection.getInstance().Initailize();
        String manager_id = log.session;
         boolean a = true;
         int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5; 
        int addrow;
        try{
            
            con = dbconnection.getConnection(); //디비 연결

            Random random = new Random();
            String randomtoken = random.ints(leftLimit, rightLimit + 1)
                                 .limit(targetStringLength)
                                 .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                 .toString();   //랜덤 알파벳 5자리 생성

            SQL = "delete from token"; //테이블에 있는 토큰값 지우기
            st = con.prepareStatement(SQL);     
            addrow = st.executeUpdate(SQL);
            
            SQL = "insert into token "+ "values('" + randomtoken + "','" + manager_id + "'," + "now()" + ")";  //토큰값 db에 입력
            st = con.prepareStatement(SQL);     
            addrow = st.executeUpdate(SQL);           
            if(addrow==1){
                SQL = "update student set access = '0'"; //학생들 승인 해제
                st = con.prepareStatement(SQL);     
                addrow = st.executeUpdate(SQL); 
            }else if(addrow==0)
                a = false;
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            try {
                if(st!=null){st.close();}
                if(con!=null){con.close();}
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return a;
    }
    
    public String nowToken(){
        DBConnection.getInstance().Initailize();
        String token=null;
        
        SQL = "select * from token"; //테이블에 있는 토큰값 지우기
        
        try { 
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(SQL);
            if(rs.next()){
                token = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return token;
    }
}
