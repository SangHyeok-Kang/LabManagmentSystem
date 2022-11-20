
package Controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Class_model;
import model.Lecture;
import model.ReserList_model;
import view.Manager_Main;
import view.Student_Main;

/*
*
* @author 20183150 김부성
* 클래스 사용 용도 : 시간표 입력, 출력을 위한 컨트롤러
*
*/
public class Timetable_controller {
    Student_Main sview;
    private Manager_Main view;
    private Class_model model;
    ReserList_model r_model = new ReserList_model();
    
    public Timetable_controller(Manager_Main view){
        this.view = view;
        this.model = new Class_model();
    }
    public Timetable_controller(Student_Main view){
        this.sview = view;
        this.model = new Class_model();
    }

    
    
    public void insertClass(){ // 수업 입력
        Lecture l = new Lecture();
        String stime;
        stime = view.INPUT_CLASS_STIME.getSelectedItem().toString().substring(0,view.INPUT_CLASS_STIME.getSelectedItem().toString().indexOf(":"));
        String etime;
        etime = view.INPUT_CLASS_ETIME.getSelectedItem().toString().substring(0,view.INPUT_CLASS_ETIME.getSelectedItem().toString().indexOf(":"));
        
        if(view.INPUT_CLASS_NUM.getText().isEmpty() || view.INPUT_CLASS_NAME.getText().isEmpty() || view.INPUT_CLASS_DIVISON.getText().isEmpty() ||view.INPUT_CLASS_PRO.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "작성되지 않은 항목이 존재합니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }else if(view.INPUT_CLASS_NUM.getText().length() != 9 ){
            JOptionPane.showMessageDialog(null, "강의번호의 자릿 수가 다릅니다.(9자리)", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }else if(view.INPUT_CLASS_PRO.getText().length() != 9){
            JOptionPane.showMessageDialog(null, "교수 번호를 다시 확인하세요(9자리)", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }else if(view.INPUT_CLASS_DIVISON.getText().length() != 1){
            JOptionPane.showMessageDialog(null, "분반 설정을 다시해주세요(1자리)", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }else if((Integer.parseInt(etime)-Integer.parseInt(stime)) <=0){
            // 종료 시간보다 시작 시간이 늦을 경우
            JOptionPane.showMessageDialog(null, "시작, 종료 시간을 다시 입력하세요", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }else{
            String semester = null;
            String lab = null;
            if(view.SEMESTER1.isSelected())
                semester = "1";
            else if(view.SEMESTER2.isSelected())
                semester = "2";
            if(view.CLASS_LAB_915.isSelected())
                lab = "915";
            else if(view.CLASS_LAB_916.isSelected())
                lab = "916";
            else if(view.CLASS_LAB_918.isSelected())
                lab = "918";
            else if(view.CLASS_LAB_911.isSelected())
                lab = "911";
            /*수업 객체 정보 저장 */
            l.setId(view.INPUT_CLASS_NUM.getText());
            l.setName(view.INPUT_CLASS_NAME.getText());
            l.setYear(view.INPUT_CLASS_YEAR.getSelectedItem().toString());
            l.setSemester(semester);
            l.setDay(view.INPUT_CLASS_DAY.getSelectedItem().toString());
            l.setDivision(view.INPUT_CLASS_DIVISON.getText());
            l.setProf_id(view.INPUT_CLASS_PRO.getText());
            l.setLab_num(lab);
            l.setStime(stime);
            l.setEtime(etime);
            
            if(l.getProf_id().charAt(0)=='P'){
                if(model.searchUser(l.getProf_id())){
                    if(!model.searchClass(l)){
                        if(model.insertClass(l) == 1){
                            JOptionPane.showMessageDialog(null, "강의를 등록하였습니다.");
                        }else{
                            JOptionPane.showMessageDialog(null, "강의 입력에 실패하였습니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "해당 시간에 강의가 존재합니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    }      
                }else{
                    JOptionPane.showMessageDialog(null, "해당 사용자가 존재하지 않습니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                 JOptionPane.showMessageDialog(null, "교수번호가 아닌 사용자입니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertSeminar(){
        Lecture l = new Lecture();
        String lab = "911";
        
        String stime = view.INPUT_SEMINAR_STIME.getSelectedItem().toString().substring(0,view.INPUT_SEMINAR_STIME.getSelectedItem().toString().indexOf(":"));
        String etime = view.INPUT_SEMINAR_ETIME.getSelectedItem().toString().substring(0,view.INPUT_SEMINAR_ETIME.getSelectedItem().toString().indexOf(":"));
        
        if(view.SPECIAL_LAB_915.isSelected())
            lab = "915";
        else if(view.SPECIAL_LAB_916.isSelected())
            lab = "916";
        else if(view.SPECIAL_LAB_918.isSelected())
            lab = "918";
        if(view.INPUT_SEMINAR_ID.getText().isEmpty() || view.INPUT_SEMINAR_NAME.getText().isEmpty() || view.SET_DATE_L.getText().equals("선택하기")){
            JOptionPane.showMessageDialog(null, "작성되지 않은 항목이 존재합니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }else if(view.INPUT_SEMINAR_ID.getText().length() != 9){
            JOptionPane.showMessageDialog(null, "예약자 번호의 자릿 수를 확인하세요(9자리)", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }else{
            String [] str = view.SET_DATE_L.getText().split("-");
            LocalDate date = LocalDate.of(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            String day = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN);
            
            String set_date = view.SET_DATE_L.getText();
            l.setName(view.INPUT_SEMINAR_NAME.getText());
            l.setDay(day);
            l.setProf_id(view.INPUT_SEMINAR_ID.getText());
            l.setLab_num(lab);
            l.setStime(stime);
            l.setEtime(etime);
            System.out.println(view.INPUT_SEMINAR_NAME.getText());
            System.out.println(day);
            System.out.println(view.INPUT_SEMINAR_ID.getText());
            System.out.println(lab);
            System.out.println(stime);
            System.out.println(etime);
            System.out.println(set_date);
          
            
            
            if(model.searchUser(l.getProf_id())){
                if(!model.searchClass(l)){ //시간에 겹치는 강의가 있는지 비교
                    l.setDay(set_date);// 날짜 형식으로 설정
                    if(!model.searchSeminar(l)){
                        if(model.insertSeminar(l) == 1){
                            JOptionPane.showMessageDialog(null, "특강 및 세미나를 등록하였습니다.");
                        }else{
                            JOptionPane.showMessageDialog(null, "특강 및 세미나 입력에 실패하였습니다..", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "해당 시간에 특강이나 세미나가 존재합니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "해당 시간에 강의가 존재합니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "해당 사용자가 존재하지 않습니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void setTimeTable(String lab){
        ArrayList<Lecture> class_list = model.searchAllClass(lab);
        ArrayList<Lecture> seminar_list = model.searchAllSeminar(lab);
        String manager = r_model.get_manager(lab);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
        String now = LocalTime.now().format(formatter);
        if(9 <= Integer.parseInt(now) && Integer.parseInt(now) < 17){
            sview.LAB_HEAD_L.setText("관리자");
        }else{
            if(manager.equals("Empty")){
                sview.LAB_HEAD_L.setText(manager);
            }else{
                String [] a = manager.split("/");
                sview.LAB_HEAD_L.setText(a[0] +" "+a[1]);
            }
        }
        DefaultTableModel dfmodel = new DefaultTableModel();
        dfmodel.addColumn("구분");
        dfmodel.addColumn("일");
        dfmodel.addColumn("월");
        dfmodel.addColumn("화");
        dfmodel.addColumn("수");
        dfmodel.addColumn("목");
        dfmodel.addColumn("금");
        dfmodel.addColumn("토");
        dfmodel.setRowCount(14);
        
        for(int i =0; i<14;i++){
            dfmodel.setValueAt(i+1, i, 0);
        }
        //강의 추가
        for(int i=0; i<class_list.size();i++){
            int day = 0;
            
            if(class_list.get(i).getDay().equals("일"))
                day = 1;
            else if(class_list.get(i).getDay().equals("월"))
                day = 2;
            else if(class_list.get(i).getDay().equals("화"))
                day = 3;
            else if(class_list.get(i).getDay().equals("수"))
                day = 4;
            else if(class_list.get(i).getDay().equals("목"))
                day = 5;
            else if(class_list.get(i).getDay().equals("금"))
                day = 6;
            else if(class_list.get(i).getDay().equals("토"))
                day = 7;
            int stime = Integer.parseInt(class_list.get(i).getStime().substring(0,class_list.get(i).getStime().indexOf(":")));
            int etime = Integer.parseInt(class_list.get(i).getEtime().substring(0,class_list.get(i).getEtime().indexOf(":")));
            for(int k= stime; k < etime; k++){
                dfmodel.setValueAt(class_list.get(i).getName(), k-9, day);;
            }    
        }
        //세미나 추가
        for(int i=0; i<seminar_list.size();i++){
            int day = 0;
            
            if(seminar_list.get(i).getDay().equals("일"))
                day = 1;
            else if(seminar_list.get(i).getDay().equals("월"))
                day = 2;
            else if(seminar_list.get(i).getDay().equals("화"))
                day = 3;
            else if(seminar_list.get(i).getDay().equals("수"))
                day = 4;
            else if(seminar_list.get(i).getDay().equals("목"))
                day = 5;
            else if(seminar_list.get(i).getDay().equals("금"))
                day = 6;
            else if(seminar_list.get(i).getDay().equals("토"))
                day = 7;
            int stime = Integer.parseInt(seminar_list.get(i).getStime().substring(0,seminar_list.get(i).getStime().indexOf(":")));
            int etime = Integer.parseInt(seminar_list.get(i).getEtime().substring(0,seminar_list.get(i).getEtime().indexOf(":")));
            for(int k= stime; k < etime; k++){
                dfmodel.setValueAt(seminar_list.get(i).getName(), k-9, day);;
            }    
        }
        sview.TIME_TABLE.setModel(dfmodel);
        sview.STU_MAIN_P.setVisible(true);
    }
}
