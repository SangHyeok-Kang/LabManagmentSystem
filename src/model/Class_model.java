/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.DBConnection.dbconnection;

/**
 *
 * @author20183150 김부성
 * 클래스 사용 용도 : 강의 시간표 입력, 수정, 조회
 */

public class Class_model {
    
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    String sql;
    int success;
  
    public int insertClass(Lecture l){ //강의 시간표 입력을 위한 함수
        DBConnection.getInstance().Initailize();
        try {
            sql = "insert into class values('" 
                    + l.getId() + "','" 
                    + l.getName() +"','"
                    + l.getYear() + "','"
                    + l.getSemester() +"','"
                    + l.getDay() + "','"
                    + l.getDivision() + "','"
                    + l.getProf_id() + "','"
                    + l.getLab_num() + "','"
                    + l.getStime()+":00" + "','"
                    + l.getEtime()+":00" + "')";
            con = dbconnection.getConnection();
            st = con.prepareStatement(sql);
            success = st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("insert SQL구문 오류 입니다."+ ex.getMessage());
        }finally{
            try {
                if(st!=null){st.close();}
                if(con!=null){con.close();}
            } catch (SQLException ex) {
                System.out.println("insert 구문 JDBC 커넥트 닫기 오류");
            }
        } 
        return success;
    }
 /*
    public int deleteClass(Lecture l){ //강의 시간표 삭제를 위한 함수
        DBConnection.getInstance().Initailize();
        try {
            sql = "delete from class where class_id='" + l.getId() + "'";
            con = dbconnection.getConnection();
            st = con.prepareStatement(sql);
            success = st.executeUpdate(sql);
            if(success == 0 ) System.out.println("delete에 실패하였습니다.");
            if(success == 1 ) System.out.println("delete에 성공하였습니다.");
        } catch (SQLException ex) {
            System.out.println("delete SQL구문 오류 입니다."+ex.getMessage());
        }finally{
            try {
                if(st!=null){st.close();}
                if(con!=null){con.close();}
            } catch (SQLException ex) {
                Logger.getLogger(Class_model.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return success;
    }
    
    */
    public boolean searchClass(Lecture l){ // 강의 찾기 함수
        DBConnection.getInstance().Initailize();
        boolean a = false;
        try {
            sql = "select * from class where lab_num= '"+ l.getLab_num() +" ' and day='"+l.getDay()+"' and " //요일 체크
                    + "(( "+l.getStime()+" <= hour(start_time) and hour(end_time) <="+l.getEtime()+") or " // 겹치는 강의 시간 체크
                    + "( "+l.getStime()+" >= hour(start_time) and hour(end_time) <="+l.getEtime()+" and hour(end_time) >"+ l.getStime()+")or "
                    + "( "+l.getStime()+" <= hour(start_time) and hour(end_time) >="+l.getEtime()+" and hour(start_time) <"+ l.getEtime()+") or "
                    + "( "+l.getStime()+" >= hour(start_time) and hour(end_time) >="+l.getEtime()+"))";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                a = true;
            }
        } catch (SQLException ex) {
            System.out.println("search SQL구문 오류 입니다." + ex.getMessage());
        }finally{
            try {
                if(st!=null){st.close();}
                if(con!=null){con.close();}
            } catch (SQLException ex) {
                Logger.getLogger(Class_model.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return a;
    }
    
    public ArrayList<Lecture> searchAllClass(String lab){ //전체 강의 출력
        ArrayList<Lecture> l = new ArrayList<Lecture>();
        String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        String semester;
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        if(month>=1 && month<=6){
            semester = "1";
        }else
            semester = "2";
        try {
            sql = "select * from class where year='"+year+"' and semester ='"+semester+"'"+"and lab_num='"+lab+"'";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                l.add(new Lecture(rs.getString(1),rs.getString(2),rs.getString(5),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)));
            }
        } catch (SQLException ex) {
            System.out.println("search SQL구문 오류 입니다." + ex.getMessage());
        }finally{
            try {
                if(st!=null){st.close();}
                if(con!=null){con.close();}
            } catch (SQLException ex) {
                Logger.getLogger(Class_model.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        return l;
    }
    public ArrayList<Lecture> searchAllSeminar(){ //한 주의 강의 출력
        ArrayList<Lecture> l = new ArrayList<Lecture>();
        DBConnection.getInstance().Initailize();
        int i=0;
        try {
            sql = "select * from ";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                l.add(new Lecture(rs.getString(1),rs.getString(3),rs.getString(5),rs.getString(2),rs.getString(4),rs.getString(6),rs.getString(7)));
            }
        } catch (SQLException ex) {
            System.out.println("search SQL구문 오류 입니다." + ex.getMessage());
        }finally{
            try {
                if(st!=null){st.close();}
                if(con!=null){con.close();}
            } catch (SQLException ex) {
                Logger.getLogger(Class_model.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        return l;
    }
    
    public int insertSeminar(Lecture l){ //특강 시간표 입력을 위한 함수
        try {
            sql = "insert into seminar values(default,'" 
                    + l.getProf_id() + "','" //담당자 이름
                    + l.getName() +"','" //세미나 이름
                    + l.getLab_num() + "','"// 강의실 번호
                    + l.getDay() + "','" //날짜
                    + l.getStime()+":00" + "','" //시작 시간
                    + l.getEtime()+":00"+ "')"; // 종료 시간
            con = dbconnection.getConnection();
            st = con.prepareStatement(sql);
            success = st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("insert SQL구문 오류 입니다."+ ex.getMessage());
        }finally{
            try {
                if(st!=null){st.close();}
                if(con!=null){con.close();}
            } catch (SQLException ex) {
                System.out.println("insert 구문 JDBC 커넥트 닫기 오류");
            }
        } 
        return success;
    }
    
    public boolean searchSeminar(Lecture l){ // 강의 찾기 함수
        boolean a = false;
        try {
            sql = "select * from seminar where lab_num= '"+ l.getLab_num() +" ' and date='"+l.getDay()+"' and " //날짜 체크
                   + "(( "+l.getStime()+" <= hour(start_time) and hour(end_time) <="+l.getEtime()+") or " // 겹치는 강의 시간 체크
                    + "( "+l.getStime()+" >= hour(start_time) and hour(end_time) <="+l.getEtime()+" and hour(end_time) >"+ l.getStime()+")or "
                    + "( "+l.getStime()+" <= hour(start_time) and hour(end_time) >="+l.getEtime()+" and hour(start_time) <"+ l.getEtime()+") or "
                    + "( "+l.getStime()+" >= hour(start_time) and hour(end_time) >="+l.getEtime()+"))";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                a = true;
            }
        } catch (SQLException ex) {
            System.out.println("search SQL구문 오류 입니다." + ex.getMessage());
        }finally{
            try {
                if(st!=null){st.close();}
                if(con!=null){con.close();}
            } catch (SQLException ex) {
                Logger.getLogger(Class_model.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return a;
    }
    
    public boolean searchUser(String num){
        DBConnection.getInstance().Initailize();
        boolean a = false;
        String table = null;
        String search = null;
        System.out.println(num);
        if(num.charAt(0) == 'S'){
            table = "student";
            search = "stu_num";
        }else if(num.charAt(0) == 'P' || num.charAt(0) == 'M'){
            table = "manager";
            search = "man_num";
        }else
            return a;
        
        try {
            sql = "select * from "+ table + " where "+ search+"= '" +num+"'"; 
            System.out.println(sql);
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                a = true;
            }
        } catch (SQLException ex) {
            System.out.println("search SQL구문 오류 입니다." + ex.getMessage());
        }finally{
            try {
                if(st!=null){st.close();}
                if(con!=null){con.close();}
            } catch (SQLException ex) {
                Logger.getLogger(Class_model.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        return a;
    }
    
}
