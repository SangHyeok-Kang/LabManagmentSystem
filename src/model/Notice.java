/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 20183150 김부성
 * 클래스 사용 용도 : 공지사항 객체 생성
 */
public class Notice {
    String num;
    String writer;
    String title;
    String contents;
    String date;
    String cate;

    public Notice(String num, String writer, String title, String contents, String date, String cate) { // 신고 및 문의 용
        this.num = num;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.date = date;
        this.cate = cate;
    }
    public Notice(String num, String writer, String title, String contents, String date) { // 공지사항 용
        this.num = num;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }
    
    
}
