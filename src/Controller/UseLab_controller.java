package Controller;

import javax.swing.table.DefaultTableModel;
import model.ReserSearch_model;
import view.Student_Main;

public class UseLab_controller {

    Student_Main sm_view;
    ReserSearch_model model = new ReserSearch_model();

    public UseLab_controller(Student_Main view) {
        this.sm_view = view;
    }

    public void showReser() {
        model.reserlist();
        System.out.println("1");
        DefaultTableModel dfmodel = new DefaultTableModel();
        dfmodel.addColumn("날짜");
        dfmodel.addColumn("강의실");
        dfmodel.addColumn("시작 시간");
        dfmodel.addColumn("종료 시간");
        dfmodel.addColumn("예약 상태");
        for (int i = 0; i < model.number; i++) {
            if (model.reserinfo[i][4].equals("w")) {
                model.reserinfo[i][4] = "승인 대기";
            } else if (model.reserinfo[i][4].equals("y")) {
                model.reserinfo[i][4] = "승인 완료";
            } else if (model.reserinfo[i][4].equals("x")) {
                model.reserinfo[i][4] = "사용 종료";
            }
            dfmodel.addRow(new Object[]{model.reserinfo[i][0],
                model.reserinfo[i][1],
                model.reserinfo[i][2],
                model.reserinfo[i][3],
                model.reserinfo[i][4]});
        }
        sm_view.RESERVATION_TABLE.setModel(dfmodel);
        System.out.println("2");
    }
}