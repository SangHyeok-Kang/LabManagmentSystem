package model;

import java.text.ParseException;
import java.util.*;

public class ReserLab_Test {

    public static void main(String[] args) throws ParseException {
        String reser_date[] = new String[4];
        String start_time[] = new String[4];
        String end_time[] = new String[4];
        List<String> stu_num = new ArrayList<String>();
        int lab_num[] = new int[4];
        String seat_num[] = new String[4];
        java.sql.Date d_date[] = new java.sql.Date[4];
        java.sql.Time t_start_time[] = new java.sql.Time[4];
        java.sql.Time t_end_time[] = new java.sql.Time[4];
        Scanner sc = new Scanner(System.in);
        List<Integer> num = new ArrayList<Integer>();
        //int lab_num = 915;
        //String date = "2022-11-10";
        
        for(int i = 13; i<=17; i++){
            num.add(i);
        }
        
        for (int i = 0; i < 2; i++) {
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
        }
         
        if (DBConnection.getInstance().Initailize()) {
            ReserLab_model model = new ReserLab_model();
           // model.isFull(lab_num, num, date);
            model.reservation(stu_num, lab_num, seat_num, d_date, t_start_time, t_end_time);
        }
    }
}
