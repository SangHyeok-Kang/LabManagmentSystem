package Controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.ReserLab_model;
import view.Student_Main;

public class ReserLab_controller {

    Student_Main sm_view;
    ReserLab_model model = new ReserLab_model();
    List<Integer> num = new ArrayList<Integer>();

    public ReserLab_controller(Student_Main view) {
        this.sm_view = view;
    }

    //예약 가능 시간 조회
    public void SearchPossibleTime() {
    }

    //예약 가능 좌석 조회
    public ArrayList<Integer>  SearchPossibleSeat() {
        String date = sm_view.SET_DATE_L.getText();
        int s_time = Integer.parseInt(sm_view.getStartTime());
        int e_time = Integer.parseInt(sm_view.getEndtime());
        int lab_num = Integer.parseInt(sm_view.getLabNum().substring(0, 3));
        ArrayList<Integer> seat = new ArrayList<Integer>();
        for(int i=0; i<40; i++){
            seat.add(0);
        }
       
        for (int i = s_time; i <= e_time; i++) {
            num.add(i);
        }
        if (sm_view.TEAM_CHECK.isSelected()) {
            boolean result = model.isFull(lab_num, num, date);
            if (result == false) {
                JOptionPane.showMessageDialog(null, "현재 강의실의 예약 인원이 20명 이상입니다. 계속 예약 진행 하시겠습니까? 그렇지 않다면 다음 강의실로 이동해주세요.");
            }
        }
        int number;
        model.searchLab(lab_num, num, date);
         for (int i = 0; i < model.number; i++) {
            number = Integer.parseInt(model.lab_info[i][1]);
            seat.set(i, 1);
        }
         return seat;
    }

}
