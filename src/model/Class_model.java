/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.DBConnection.dbconnection;


/**
 *
 * @author20183150 김부성
 * 클래스 사용 용도 : 강의 시간표 입력, 수정, 조회
 */

public class Class_model extends Lecture{
    
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
                    + l.getStime() + "','"
                    + l.getEtime()+ "')";
            con = dbconnection.getConnection();
            st = con.prepareStatement(sql);
            success = st.executeUpdate(sql);
            if(success == 0 ) System.out.println("insert에 실패하였습니다.");
            if(success == 1 ) System.out.println("insert에 성공하였습니다.");
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
    
    public int changeClass(Lecture old_l, Lecture new_l){ //강의 시간표 수정을 위한 함수
        success = deleteClass(old_l);
        if(success == 1)
            success = insertClass(new_l);
        return success;
    }
    
    public boolean searchClass(Lecture l){ // 강의 찾기 함수
        DBConnection.getInstance().Initailize();
        boolean a= false;
        try {
            sql = "select * from class where class_id='" + l.getId() + "'";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                a = true;
                if(a) System.out.println("search에 성공하였습니다." + rs.getString(1));
            }else{
                a = false;
                if(!a) System.out.println("search에 실패하였습니다.");
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
    
    public ArrayList<Lecture> searchAllClass(String year, String semester){ //전체 강의 출력
        ArrayList<Lecture> l = new ArrayList<Lecture>();
        DBConnection.getInstance().Initailize();
        boolean a= false;
        int i=0;
        try {
            sql = "select * from class";
            st = dbconnection.getInstance().getConnection().createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                l.get(i).setId(rs.getString(1));
                l.get(i).setName(rs.getString(2));
                l.get(i).setYear(rs.getString(3));
                l.get(i).setSemester(rs.getString(4));
                l.get(i).setDay(rs.getString(5));
                l.get(i).setDivision(rs.getString(6));
                l.get(i).setProf_id(rs.getString(7));
                l.get(i).setLab_num(rs.getString(8));
                l.get(i).setStime(rs.getString(9));
                l.get(i).setEtime(rs.getString(10));
                i++;
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
    /*
    추후 원하는 분기별 전체 강의 출력 생길시
    select * 
    from class 
    where year = date_format(now(),'%Y')
    and semester;*/ 
    
}
