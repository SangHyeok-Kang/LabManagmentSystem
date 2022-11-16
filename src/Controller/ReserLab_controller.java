package Controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.*;
import view.*;

/**
 *
 * @author 20183109 강상혁 클래스 사용 목적 : 실습실 조회 및 예약 컨트롤러
 */
public class ReserLab_controller {

    Student_Main sm_view = new Student_Main();
    ReserLab_model rm_model = new ReserLab_model();
    String reser_date[] = new String[4];
    String start_time[] = new String[4];
    String end_time[] = new String[4];
    List<String> stu_num = new ArrayList<String>();
    int lab_num[] = new int[4];
    String seat_num[] = new String[4];
    java.sql.Date d_date[] = new java.sql.Date[4];
    java.sql.Time t_start_time[] = new java.sql.Time[4];
    java.sql.Time t_end_time[] = new java.sql.Time[4];
    List<Integer> num = new ArrayList<Integer>();
    String lab_num2;
    String year;
    String month;
    String day;
    String date;
    String s_time;
    String e_time;
    String seat;

    public boolean TeamSelectLabinfo() {
        lab_num2 = sm_view.getLabNum();
        year = sm_view.getYear();
        month = sm_view.getMonth();
        day = sm_view.getDate();
        date = year + "-" + month + "-" + day;
        s_time = sm_view.getStartTime();
        e_time = sm_view.getEndTime();
        int index = s_time.indexOf(":");
        int index2 = e_time.indexOf(":");
        s_time = s_time.substring(0, index);
        e_time = e_time.substring(0, index2);

        for (int i = Integer.parseInt(s_time); i <= Integer.parseInt(e_time); i++) {
            num.add(i);
        }
        if (lab_num2.equals("915호")) {
            lab_num2 = "915";
            rm_model.isFull(Integer.parseInt(lab_num2), num, date);
            if (false) {
                JFrame jframe = new JFrame();
                int result = JOptionPane.showConfirmDialog(jframe, "현재 강의실은 20명이 넘었습니다 다음 강의실에서 예약하시겠습니까?");
                if (result == 0) {
                    lab_num2 = "916";
                    rm_model.isFull(Integer.parseInt(lab_num2), num, date);
                    if (false) {
                        result = JOptionPane.showConfirmDialog(jframe, "현재 강의실은 20명이 넘었습니다 다음 강의실에서 예약하시겠습니까?");
                        if (result == 0) {
                            lab_num2 = "918";
                            rm_model.isFull(Integer.parseInt(lab_num2), num, date);
                            if (false) {
                                result = JOptionPane.showConfirmDialog(jframe, "현재 강의실은 20명이 넘었습니다 다음 강의실에서 예약하시겠습니까?");
                                if (result == 0) {
                                    lab_num2 = "911";
                                    rm_model.isFull(Integer.parseInt(lab_num2), num, date);
                                    if (false) {
                                        JOptionPane.showMessageDialog(jframe, "현재 이용가능한 강의실이 없습니다, 다음에 이용해주세요.");
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            } else {
                                return true;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
        return true;
    }

    public void SelectLab() {
        lab_num2 = sm_view.getLabNum();
        year = sm_view.getYear();
        month = sm_view.getMonth();
        day = sm_view.getDate();
        date = year + "-" + month + "-" + day;
        s_time = sm_view.getStartTime();
        e_time = sm_view.getEndTime();
        int index = s_time.indexOf(":");
        int index2 = e_time.indexOf(":");
        s_time = s_time.substring(0, index);
        e_time = e_time.substring(0, index2);

    }
    
    public void TeamSelectSeat(){
        
    }
    
    public void SelectSeat(){
        
    }

    /* for (int i = 0; i < 2; i++) {
            System.out.print("insert stunum : ");
            stu_num.add(sc.next());
            System.out.print("insert lab_num : ");
            lab_num[i] = sc.nextInt();
            System.out.print("insert seatnum : ");
            seat_num[i] = sc.next();
            System.out.print("insert reser_date : ");
            reser_date[i] = sc.next();
            d_date[i] = java.sql.Date.valueOf(reser_date[i]);
            System.out.println(d_date[i]);

            System.out.print("insert start_time : ");
            start_time[i] = sc.next();
            t_start_time[i] = java.sql.Time.valueOf(start_time[i]);
            System.out.println(t_start_time[i]);

            System.out.print("insert end_time : ");
            end_time[i] = sc.next();
            t_end_time[i] = java.sql.Time.valueOf(end_time[i]);
            System.out.println(t_end_time[i]);
        }*/
    model.searchLab (lab_num, num, date);
    int numb = model.number;
    for (int i = 0;
    i< numb ;
    i

    
        ++) {
            for (int j = 0; j < 5; j++) {
            System.out.print(model.lab_info[i][j] + " ");
        }
        System.out.println("");
    }
    //model.reservation(stu_num, lab_num, seat_num, d_date, t_start_time, t_end_time);

}
