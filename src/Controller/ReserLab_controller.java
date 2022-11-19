package Controller;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.ReserLab_model;
import model.ReserState;
import view.Manager_Main;
import view.Student_Main;

public class ReserLab_controller {

    Student_Main sm_view;
    Manager_Main m_view;
    ReserLab_model model = new ReserLab_model();
    String user;
    List<Integer> num = new ArrayList<Integer>();

    //예약 정보 저장
    List<String> stu_num = new ArrayList<String>();
    String[] seat_num = new String[4];
    java.sql.Time[] start_time = new java.sql.Time[4];
    java.sql.Time[] end_time = new java.sql.Time[4];
    int[] lab_num = new int[4];
    java.sql.Date[] date = new java.sql.Date[4];

    //현재시간 비교 하기 위한 변수
    LocalDate currentDate = LocalDate.now();
    LocalTime currentTime = LocalTime.now();

    public ReserLab_controller(Student_Main view) {
        this.sm_view = view;
    }
    public ReserLab_controller(Manager_Main view) {
        this.m_view = view;
    }

    //예약 가능 좌석 조회
    public ArrayList<Integer> SearchPossibleSeat() {
        String day = sm_view.SET_DATE_L.getText();
        int s_time = Integer.parseInt(sm_view.getStartTime());
        int e_time = Integer.parseInt(sm_view.getEndtime());
        String s_time2;
        String e_time2;
        int lab_num = Integer.parseInt(sm_view.getLabNum().substring(0, 3));
        ArrayList<Integer> seat = new ArrayList<Integer>();
        
        
        if (s_time >= e_time) {
            JOptionPane.showMessageDialog(null, "종료시간이 시작시간보다 이전입니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        } else {
            s_time2 = String.valueOf(s_time) + ":00:00";
            e_time2 = String.valueOf(e_time) + ":00:00";
            if (LocalDate.parse(day, DateTimeFormatter.ISO_DATE).isBefore(currentDate) == true) {
                JOptionPane.showMessageDialog(null, "입력한 날짜가 현재 날짜보다 이전입니다. 다시 선택해 주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            } else if (LocalDate.parse(day, DateTimeFormatter.ISO_DATE).isBefore(currentDate) == true && LocalTime.parse(s_time2).isBefore(currentTime)) {
                JOptionPane.showMessageDialog(null, "입력한 시작 시간이 현재 시간보다 이전입니다. 다시 선택해 주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            } else {
                for (int i = 0; i < 40; i++) {
                    seat.add(0);
                }

                for (int i = s_time; i <= e_time; i++) {
                    num.add(i);
                }
                if (sm_view.TEAM_CHECK.isSelected()) {
                    boolean result = model.isFull(lab_num, num, day);
                    if (result == false) {
                        JOptionPane.showMessageDialog(null, "현재 강의실의 예약 인원이 20명 이상입니다. 계속 예약 진행 하시겠습니까? 그렇지 않다면 다음 강의실로 이동해주세요.");
                    }
                }
                int number;
                model.searchLab(lab_num, num, day);
                for (int i = 0; i < model.number; i++) {
                    number = Integer.parseInt(model.lab_info[i][1]);
                    seat.set(number, 1);
                }
            }
        }
        return seat;
    }

    //예약 입력 메소드   
    public void NewReser() {
        user = model.user_id;
        System.out.println(user + "3");
        //예약 시작시간 및 종료 시간 저장
        String day = sm_view.SET_DATE_L.getText();
        String s_time = sm_view.getStartTime();
        String e_time = sm_view.getEndtime();

        int labnum = Integer.parseInt(sm_view.getLabNum().substring(0, 3));//강의실 번호 저장

        //팀원 학번 및 좌석 값 저장
        String seatnum = sm_view.getSeat();
        String team1stdno = sm_view.getTeam1Stdno();
        String team1seatnum = sm_view.getTeam1Seatnum();
        String team2stdno = sm_view.getTeam2Stdno();
        String team2seatnum = sm_view.getTeam2Seatnum();
        String team3stdno = sm_view.getTeam2Stdno();
        String team3seatnum = sm_view.getTeam2Seatnum();
        if (Integer.parseInt(s_time) >= Integer.parseInt(e_time)) {
            JOptionPane.showMessageDialog(null, "종료시간이 시작시간보다 이전입니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        } else {
            s_time = s_time + ":00:00";
            e_time = e_time + ":00:00";
            if (LocalDate.parse(day, DateTimeFormatter.ISO_DATE).isBefore(currentDate) == true) {
                JOptionPane.showMessageDialog(null, "입력한 날짜가 현재 날짜보다 이전입니다. 다시 선택해 주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            } else if (LocalDate.parse(day, DateTimeFormatter.ISO_DATE).isBefore(currentDate) == true && LocalTime.parse(s_time).isBefore(currentTime)) {
                JOptionPane.showMessageDialog(null, "입력한 시작 시간이 현재 시간보다 이전입니다. 다시 선택해 주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            } else {
                stu_num.add(user);
                if (!team1stdno.isEmpty()) {
                    stu_num.add(team1stdno);
                    if (!team2stdno.isEmpty()) {
                        stu_num.add(team2stdno);
                    }
                    if (!team3stdno.isEmpty()) {
                        stu_num.add(team3stdno);
                    }
                }
                for (int i = 0; i < 4; i++) {
                    lab_num[i] = labnum;
                    date[i] = java.sql.Date.valueOf(day);
                    start_time[i] = java.sql.Time.valueOf(s_time);
                    end_time[i] = java.sql.Time.valueOf(e_time);
                }

                seat_num[0] = seatnum;
                seat_num[1] = team1seatnum;
                seat_num[2] = team2seatnum;
                seat_num[3] = team3seatnum;
                System.out.println(stu_num.get(0));
                String result = model.reservation(stu_num, lab_num, seat_num, date, start_time, end_time);
                if (result.equals("nonstd")) {
                    JOptionPane.showMessageDialog(null, "회원가입 하지 않은 학생이 입력되었습니다. 다시 입력해주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                } else if (result.equals("failed16")) {
                    JOptionPane.showMessageDialog(null, "현재 시각이 16시 30분이 넘었으므로 예약이 불가합니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                } else if (result.equals("success")) {
                    JOptionPane.showMessageDialog(null, "예약 신청되었습니다.");
                } else {
                    JOptionPane.showMessageDialog(null, "예기치 않은 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    //예약 승인 메소드
    public void SignReser(){
        String resernum = m_view.getTable();
        ReserState rs = new ReserState(resernum, "승인");
        rs.updateState();
        JOptionPane.showMessageDialog(null, "예약 승인 완료되었습니다.");
    }
    
    //실습실 사용 메소드
    public void UseLab(){
        ReserState rs = new ReserState("사용하기");
        rs.updateState();
        JOptionPane.showMessageDialog(null, "체크인 하였습니다.");
    }
    //예약 취소 메소드
    public void CancelReser(){
        String resernum = m_view.getTable();
        ReserState rs = new ReserState(resernum, "취소하기");
        rs.updateState();
        JOptionPane.showMessageDialog(null, "예약 취소하였습니다.");
        
    }
    //퇴실 메소드
    public void ExitLab(){
        String resernum = null;
        ReserState rs = new ReserState(resernum, "퇴실하기");
        rs.updateState();
        JOptionPane.showMessageDialog(null, "퇴실 완료되었습니다.");
    }
}
