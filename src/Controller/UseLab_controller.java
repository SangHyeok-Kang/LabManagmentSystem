package Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ReserList_model;
import model.ReserSearch_model;
import view.Manager_Main;
import view.Student_Main;

public class UseLab_controller {

    Student_Main sm_view;
    Manager_Main m_view;
    ReserSearch_model model = new ReserSearch_model();
    ReserList_model m_model = new ReserList_model();
    String reser_num;
    LocalDate curdate = LocalDate.now(); //현재 날짜 저장
    LocalTime curTime = LocalTime.now();
    LocalDate reser_date;
    LocalTime s_time;

    public UseLab_controller(Student_Main view) {
        this.sm_view = view;
    }

    public UseLab_controller(Manager_Main view) {
        this.m_view = view;
    }

    //사용자 예약 내역 조회
    public void showReser() {
        model.reserlist();

        DefaultTableModel dfmodel = new DefaultTableModel();
        dfmodel.addColumn("예약번호");
        dfmodel.addColumn("날짜");
        dfmodel.addColumn("강의실");
        dfmodel.addColumn("시작 시간");
        dfmodel.addColumn("종료 시간");
        dfmodel.addColumn("예약 상태");

        for (int i = 0; i < model.number; i++) {
            if (model.reserinfo[i][5].equals("w")) {
                model.reserinfo[i][5] = "승인 대기";
            } else if (model.reserinfo[i][5].equals("y")) {
                model.reserinfo[i][5] = "승인 완료";
            } else if (model.reserinfo[i][5].equals("x")) {
                model.reserinfo[i][5] = "예약 취소";
            }else if (model.reserinfo[i][5].equals("e")) {
                model.reserinfo[i][5] = "사용 종료";
            }else if (model.reserinfo[i][5].equals("u")) {
                model.reserinfo[i][5] = "사용 중";
            }
            
            dfmodel.addRow(new Object[]{model.reserinfo[i][0],
                model.reserinfo[i][1],
                model.reserinfo[i][2],
                model.reserinfo[i][3],
                model.reserinfo[i][4],
                model.reserinfo[i][5]});
        }

        sm_view.RESERVATION_TABLE.setModel(dfmodel);

    }

    //관리자 금일 예약 내역 조회
    public void to_m_showReser() {
        m_model.reserlist("today");
        System.out.println("1");
        DefaultTableModel dfmodel = new DefaultTableModel();
        dfmodel.addColumn("예약번호");
        dfmodel.addColumn("학번");
        dfmodel.addColumn("이름");
        dfmodel.addColumn("강의실");
        dfmodel.addColumn("좌석");
        dfmodel.addColumn("날짜");
        dfmodel.addColumn("시작 시간");
        dfmodel.addColumn("종료 시간");
        dfmodel.addColumn("팀장");
        dfmodel.addColumn("예약 상태");
        System.out.println("2");
        for (int i = 0; i < m_model.number; i++) {
            if (m_model.reserinfo[i][9].equals("w")) {
                m_model.reserinfo[i][9] = "승인 대기";
            } else if (m_model.reserinfo[i][9].equals("y")) {
                m_model.reserinfo[i][9] = "승인 완료";
            } else if (m_model.reserinfo[i][9].equals("e")) {
                m_model.reserinfo[i][9] = "사용 종료";
            } else if (m_model.reserinfo[i][9].equals("x")) {
                m_model.reserinfo[i][9] = "예약 취소";
            } else if (m_model.reserinfo[i][9].equals("u")) {
                m_model.reserinfo[i][9] = "사용중";
            }
            dfmodel.addRow(new Object[]{m_model.reserinfo[i][0],
                m_model.reserinfo[i][1],
                m_model.reserinfo[i][2],
                m_model.reserinfo[i][3],
                m_model.reserinfo[i][4],
                m_model.reserinfo[i][5],
                m_model.reserinfo[i][6],
                m_model.reserinfo[i][7],
                m_model.reserinfo[i][8],
                m_model.reserinfo[i][9]});
        }
        m_view.REQUEST_TABLE.setModel(dfmodel);
        System.out.println("3");
    }

    //관리자 실습실 사용 기록 조회
    public void m_showReser() {
        m_model.reserlist("all");
        System.out.println("1");
        DefaultTableModel dfmodel = new DefaultTableModel();
        dfmodel.addColumn("예약번호");
        dfmodel.addColumn("학번");
        dfmodel.addColumn("이름");
        dfmodel.addColumn("강의실");
        dfmodel.addColumn("좌석");
        dfmodel.addColumn("날짜");
        dfmodel.addColumn("시작 시간");
        dfmodel.addColumn("종료 시간");
        dfmodel.addColumn("팀장");
        dfmodel.addColumn("예약 상태");
        System.out.println("2");
        for (int i = 0; i < m_model.number; i++) {
            if (m_model.reserinfo[i][9].equals("w")) {
                m_model.reserinfo[i][9] = "승인 대기";
            } else if (m_model.reserinfo[i][9].equals("y")) {
                m_model.reserinfo[i][9] = "승인 완료";
            } else if (m_model.reserinfo[i][9].equals("x")) {
                m_model.reserinfo[i][9] = "예약 취소";
            } else if (m_model.reserinfo[i][9].equals("u")) {
                m_model.reserinfo[i][9] = "사용 중";
            } else if (m_model.reserinfo[i][9].equals("e")) {
                m_model.reserinfo[i][9] = "사용 종료";
            }
            dfmodel.addRow(new Object[]{m_model.reserinfo[i][0],
                m_model.reserinfo[i][1],
                m_model.reserinfo[i][2],
                m_model.reserinfo[i][3],
                m_model.reserinfo[i][4],
                m_model.reserinfo[i][5],
                m_model.reserinfo[i][6],
                m_model.reserinfo[i][7],
                m_model.reserinfo[i][8],
                m_model.reserinfo[i][9]});
        }
        m_view.HISTORY_TALBE.setModel(dfmodel);
        System.out.println("3");
    }
    
    //책임자 부여 연결
    public void giveMgr(){
        reser_num = m_view.getTable();
        boolean result = m_model.appoint_manager(reser_num);
        if(result){
             JOptionPane.showMessageDialog(null, "책임자 부여 완료되었습니다.");
        }else{
             JOptionPane.showMessageDialog(null, "해당 강의실에 이미 책임자가 존재합니다.");
        }
    }
    //예약 확인
    public void checkReser(){
        String resernum = sm_view.getTable();
        model.reserlist(resernum);
        reser_date = LocalDate.parse(model.reserinfo2[0], DateTimeFormatter.ISO_DATE);
        s_time = LocalTime.parse(model.reserinfo2[1]);
        String acc = model.reserinfo2[2];
        if(acc.equals("x")){
            sm_view.DELETE_RE_BTN.setEnabled(false);
        }
        //예약 날짜가 오늘이고 시작시간이 현재시간보다 이전이며 예약 상태가 y인 상태
        else if(reser_date.isEqual(curdate) && s_time.isBefore(curTime) && acc.equals("y")){
            sm_view.checkin.setEnabled(true);//입실 버튼 활성화
         //예약 날짜가 오늘이고 시작시간이 현재시간보다 이전이며 예약 상태가 u인 상태    
        }else if(reser_date.isEqual(curdate) && s_time.isBefore(curTime) && acc.equals("u")){
            sm_view.checkout.setEnabled(true); //퇴실버튼 활성화
            sm_view.extendreser.setEnabled(true); //예약연장 활성화
         //예약 날짜가 오늘 이후이거나 시작시간이 현재시간보다 이후인 상태
        }else if(reser_date.isAfter(curdate) || s_time.isAfter(curTime)){
            sm_view.DELETE_RE_BTN.setEnabled(true); //예약취소 버튼 활성화
        } 
    }    
}
