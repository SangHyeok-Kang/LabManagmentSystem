
package Controller;

import java.util.Arrays;
import javax.swing.JOptionPane;
import model.Signup_model;
import view.Login;

public class MemberManage_controller {
    Login view;
    Signup_model model = new Signup_model();
    
    public MemberManage_controller(Login view){
        this.view = view;
    }
    
    public void singUp(){ //회원 가입 함수
        if(view.NUMBER.getText().isEmpty() || view.NAME.getText().isEmpty() || view.SIGN_PW.getPassword().length == 0 || view.PHONE1.getText().isEmpty() || view.PHONE2.getText().isEmpty() || view.EMAIL.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "입력하지 않은 사항이 존재합니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }else{
            String email = view.EMAIL.getText() + "@" + view.EMAIL_LIST.getSelectedItem().toString();
            String phone = "010-"+view.PHONE1.getText()+"-"+view.PHONE2.getText();
            if((view.NUMBER.getText().length()!=9) || view.NUMBER.getText().charAt(0)!='S'){
                JOptionPane.showMessageDialog(null, "학번을 재입력하세요(S+8자리)", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }else{
                if(!model.searchUser(view.NUMBER.getText())){ //먼저 이미 같은 학번의 학생이 있는지 조회
                    if(model.signup(view.NUMBER.getText(), Arrays.toString(view.SIGN_PW.getPassword()) , view.NAME.getText(), phone, email)){
                        JOptionPane.showMessageDialog(null, "회원가입에 성공하였습니다.");
                        view.panelClear();
                        view.LOGIN_P.setVisible(true);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "회원 가입에 실패하였습니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }else
                    JOptionPane.showMessageDialog(null, "학번이 중복됩니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
