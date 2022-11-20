package Controller;

import java.util.Arrays;
import javax.swing.JOptionPane;
import model.Login_model;
import view.Login;
import view.Manager_Main;
import view.Student_Main;

public class Login_controller {

    Login view;
    Login_model model = new Login_model();

    public Login_controller(Login view) {
        this.view = view;
        //this.view.LOGIN_BTNActionPerformed(this);
    }

    public void login() {
        char id = view.ID_FIELD.getText().charAt(0);
        String answer;
        String pw = new String(view.PW_FIELD.getPassword());
        System.out.println(view.ID_FIELD.getText());
        if (view.ID_FIELD.getText().isEmpty() || pw.isEmpty()) {
            JOptionPane.showMessageDialog(null, "아이디, 비밀번호 모두 입력 해주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        } else {
            System.out.println(view.ID_FIELD.getText() + pw);
            if ((model.isLogin(view.ID_FIELD.getText(), pw).equals("success"))) {
                if (id == 'S') {
                    if (model.isAccess()) {
                        Student_Main s = new Student_Main();
                        s.STU_NUM.setText(model.auth);
                        s.setVisible(true);
                    } else {
                        answer = JOptionPane.showInputDialog("토큰을 입력하세요");
                        if (model.InsertToken(answer)) {
                            if (id == 'S') {
                                Student_Main s = new Student_Main();
                                s.STU_NUM.setText(model.auth);
                                s.setVisible(true);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "토큰 값을 잘 못 입력하셨습니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else if (id == 'M' || id == 'P') {
                    Manager_Main m = new Manager_Main();
                    m.SNAME.setText(model.name);
                    m.setVisible(true);
                    view.dispose();
                }
                view.dispose();
            } else if((model.isLogin(view.ID_FIELD.getText(), pw).equals("noid"))){
                JOptionPane.showMessageDialog(null, "등록되지 않은 사용자 입니다. 다시 시도해주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }else if((model.isLogin(view.ID_FIELD.getText(), pw).equals("nopass"))){
                JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다. 다시 시도해주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }else if((model.isLogin(view.ID_FIELD.getText(), pw).equals("limit"))){
                JOptionPane.showMessageDialog(null, model.end_limit+" 까지 제한된 상태입니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "로그인 실패", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
