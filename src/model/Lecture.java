/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 20183150 김부성
 * 클래스 사용 용도 : 강의에 대한 객체 생성을 위한 클래스
 */
public class Lecture {
    String id;
    String name;
    String semester;
    String year;
    String day;
    String division;
    String prof_id;
    String lab_num;
    String stime;
    String etime;

    public Lecture(String id, String name, String day, String prof_id, String lab_num, String stime, String etime) { // 시간표 출력 용
        this.id = id;
        this.name = name;
        this.day = day;
        this.prof_id = prof_id;
        this.lab_num = lab_num;
        this.stime = stime;
        this.etime = etime;
    }

    public Lecture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setProf_id(String prof_id) {
        this.prof_id = prof_id;
    }

    public void setLab_num(String lab_num) {
        this.lab_num = lab_num;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSemester() {
        return semester;
    }

    public String getDivision() {
        return division;
    }

    public String getProf_id() {
        return prof_id;
    }

    public String getLab_num() {
        return lab_num;
    }

    public String getStime() {
        return stime;
    }

    public String getEtime() {
        return etime;
    }
    
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
