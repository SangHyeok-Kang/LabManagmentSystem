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
        DefaultTableModel dtmodel = new DefaultTableModel();
        for (int i = 0; i < model.number; i++) {
            dtmodel.addRow(model.reserinfo);
        }
    }
}
