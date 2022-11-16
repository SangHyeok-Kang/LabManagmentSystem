package model;

import java.util.Scanner;

public class ReserList_model_test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String reser_num = null;
        ReserList_model model = new ReserList_model();
        if (DBConnection.getInstance().Initailize()) {
            model.reserlist();
            for (int i = 0; i < model.number; i++) {
                for(int j =0; j < 10; j++)
                System.out.print(model.reserinfo[i][j] + " ");
                System.out.println("");
            }
            System.out.print("관리자로 선정할 예약번호를 선택해주세요 : ");
            reser_num = sc.next();
            System.out.println(reser_num);
            model.appoint_manager(reser_num);
        }
    }
}
