package Controller;

import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Signup_model;
import model.ChangeInfo_model;
import view.Login;
import view.Student_Main;

public class MemberManage_controller {

    Login view;
    Student_Main sm_view;
    Signup_model model = new Signup_model();

    public MemberManage_controller(Login view) {
        this.view = view;
    }

    public MemberManage_controller(Student_Main view) {
        this.sm_view = view;
    }

    public void singUp() { //회원 가입 함수
        String pw = new String(view.SIGN_PW.getPassword());
        System.out.println("pass : " + pw);
        if (view.NUMBER.getText().isEmpty() || view.NAME.getText().isEmpty() || view.SIGN_PW.getPassword().length == 0 || view.PHONE1.getText().isEmpty() || view.PHONE2.getText().isEmpty() || view.EMAIL.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "입력하지 않은 사항이 존재합니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        } else {
            String email = view.EMAIL.getText() + "@" + view.EMAIL_LIST.getSelectedItem().toString();
            String phone = "010-" + view.PHONE1.getText() + "-" + view.PHONE2.getText();
            if ((view.NUMBER.getText().length() != 9) || view.NUMBER.getText().charAt(0) != 'S') {
                JOptionPane.showMessageDialog(null, "학번을 재입력하세요(S+8자리)", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println(view.NUMBER.getText());
                if (model.searchUser(view.NUMBER.getText())) { //먼저 이미 같은 학번의 학생이 있는지 조회
                    if (model.signup(view.NUMBER.getText(), pw, view.NAME.getText(), phone, email)) {
                        JOptionPane.showMessageDialog(null, "회원가입에 성공하였습니다.");
                        view.panelClear();
                        view.LOGIN_P.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "회원 가입에 실패하였습니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "학번이 중복됩니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void UserInfo() {
        ChangeInfo_model change = new ChangeInfo_model();
        change.SearchUser();
        String user_id = change.Userinfo[0];
        String name = change.Userinfo[1];
        System.out.println(user_id + " " + name);
        sm_view.CHANGE_NUMBER.setText(user_id);
        sm_view.CHANGE_NAME.setText(name);
        sm_view.CHANGE_NUMBER.setVisible(true);
        sm_view.CHANGE_NAME.setVisible(true);

    }

    public void ChangeInfo() {
        ChangeInfo_model change = new ChangeInfo_model();
        String changepass = sm_view.getChangePw();
        String currentpass = sm_view.getNowPw();
        String phone_num = sm_view.getChangePhone();
        String email = sm_view.getChangeEmail();
        String result = change.isChangeinfo(changepass, currentpass, phone_num, email);
        System.out.println(phone_num);
        if (result.equals("null")) {
            JOptionPane.showMessageDialog(null, "변경할 내용을 입력해주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        } else if(result.equals("failed")){
            JOptionPane.showMessageDialog(null, "현재 비밀번호가 일치하지 않습니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);           
        }else if(result.equals("success")){
            JOptionPane.showMessageDialog(null, "회원정보가 수정되었습니다.");
        }
    }
}
