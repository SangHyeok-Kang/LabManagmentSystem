
package Controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import javax.swing.JOptionPane;
import model.Class_model;
import model.Lecture;
import view.Manager_Main;

/*
*
* @author 20183150 김부성
* 클래스 사용 용도 : 시간표 입력, 출력을 위한 컨트롤러
*
*/
public class Timetable_controller {
    private Manager_Main view;
    private Class_model model;
    public Timetable_controller(){
        this.view = new Manager_Main();
        this.model = new Class_model();
    }

    
    
    public void insertClass(){ // 수업 입력
        Lecture l = new Lecture();
        String stime = view.INPUT_SEMINAR_STIME.getSelectedItem().toString().substring(0,view.INPUT_SEMINAR_STIME.getSelectedItem().toString().indexOf(":"));
        String etime = view.INPUT_SEMINAR_ETIME.getSelectedItem().toString().substring(0,view.INPUT_SEMINAR_ETIME.getSelectedItem().toString().indexOf(":"));
        
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
            String semester = "2";
            String lab = "911";
            if(view.SEMESTER1.isSelected())
                semester = "1";
            if(view.CLASS_LAB_915.isSelected())
                lab = "915";
            else if(view.CLASS_LAB_916.isSelected())
                lab = "916";
            else if(view.CLASS_LAB_918.isSelected())
                lab = "918";
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

            if(!model.searchClass(l)){
                if(model.insertClass(l) == 1){
                    JOptionPane.showMessageDialog(null, "강의를 등록하였습니다.");
                }else{
                    JOptionPane.showMessageDialog(null, "강의 입력에 실패하였습니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "해당 시간에 강의가 존재합니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }      
        }
    }
    
    public void insertSeminar(){
        Lecture l = new Lecture();
        String lab = "911";
        
        String stime = view.INPUT_SEMINAR_STIME.getSelectedItem().toString().substring(0,view.INPUT_SEMINAR_STIME.getSelectedItem().toString().indexOf(":"));
        String etime = view.INPUT_SEMINAR_ETIME.getSelectedItem().toString().substring(0,view.INPUT_SEMINAR_ETIME.getSelectedItem().toString().indexOf(":"));
        
        LocalDate date = LocalDate.of(Integer.parseInt(view.INPUT_SEMINAR_YEAR.getSelectedItem().toString()), Integer.parseInt(view.INPUT_SEMINAR_MONTH.getSelectedItem().toString()), Integer.parseInt(view.INPUT_SEMINAR_DATE.getSelectedItem().toString()));
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String day = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN);
        
        String set_date = view.INPUT_SEMINAR_YEAR.getSelectedItem().toString() + "-" + view.INPUT_SEMINAR_MONTH.getSelectedItem().toString() + "-" + view.INPUT_SEMINAR_DATE.getSelectedItem().toString();
        
        if(view.SPECIAL_LAB_915.isSelected())
            lab = "915";
        else if(view.SPECIAL_LAB_916.isSelected())
            lab = "916";
        else if(view.SPECIAL_LAB_918.isSelected())
            lab = "918";
        if(view.INPUT_SEMINAR_ID.getText().isEmpty() || view.INPUT_SEMINAR_NAME.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "작성되지 않은 항목이 존재합니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }else if(view.INPUT_SEMINAR_ID.getText().length() != 9){
            JOptionPane.showMessageDialog(null, "예약자 번호의 자릿 수를 확인하세요(9자리)", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }else{
            l.setName(view.INPUT_SEMINAR_ID.getText());
            l.setDay(day);
            l.setProf_id(view.INPUT_SEMINAR_NAME.getText());
            l.setLab_num(lab);
            l.setStime(stime);
            l.setEtime(etime);
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
        }      
    }
}
