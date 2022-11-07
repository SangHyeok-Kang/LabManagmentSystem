/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 *
 * @author 20183150 김부성
 * 클래스 사용 용도
 * 로그인과 회원가입의 화면이다.
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        panelClear();
        LOGIN_P.setVisible(true);
    }
    
    public void setaddActionListener(ActionListener listener) {
        LOGIN_BTN.addActionListener(listener); // 로그인 버튼 리스너
        SIGN_UP_BTN.addActionListener(listener); //회원 가입 버튼 리스너
        CANCEL_BTN.addActionListener(listener); //취소 버튼 리스너
    }
    
    public void panelClear(){ //패널들 전부 안보이게하는 함수
        LOGIN_P.setVisible(false);
        SIGN_UP_P.setVisible(false);
    }
    
    public String getID(){ //로그인시 아이디 받기
        return ID_FIELD.getText();
    }
    
    public String getPW(){ //로그인시 비밀번호 받기
        return Arrays.toString(PW_FIELD.getPassword());
    }
    
    public String getNumber(){ //회원가입 시 학번 받기
        return NUMBER.getText();
    }
    
    public String getName(){ //회원 가입 시 이름 받기
        return NAME.getText();
    }
    
    public String getSIGN_PW(){ //회원 가입 시 비밀 번호 받기
        return Arrays.toString(SIGN_PW.getPassword());
    }
    
    public String getPhone(){ //회원 가입 시 전화번호 받기
        return PHONE.getText();
    }
    
    public String getEmail(){ //회원 가입 시 이메일 받기
        return EMAIL.getText();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SIGN_UP_P = new javax.swing.JPanel();
        NUMBER = new javax.swing.JTextField();
        NAME = new javax.swing.JTextField();
        PHONE = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        SIGN_PW = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        EMAIL = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        SIGN_UP_BTN = new javax.swing.JButton();
        CANCEL_BTN = new javax.swing.JButton();
        LOGIN_P = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        ID_FIELD = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        PW_FIELD = new javax.swing.JPasswordField();
        LOGIN_BTN = new javax.swing.JButton();
        SIGN_UP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("실습실 관리 시스템");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SIGN_UP_P.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("굴림", 1, 24)); // NOI18N
        jLabel3.setText("회원가입");

        jLabel5.setText("학번");

        jLabel6.setText("이름");

        jLabel7.setText("비밀번호");

        jLabel8.setText("전화번호");

        jLabel9.setText("이메일");

        SIGN_UP_BTN.setText("가입하기");

        CANCEL_BTN.setText("취소하기");

        javax.swing.GroupLayout SIGN_UP_PLayout = new javax.swing.GroupLayout(SIGN_UP_P);
        SIGN_UP_P.setLayout(SIGN_UP_PLayout);
        SIGN_UP_PLayout.setHorizontalGroup(
            SIGN_UP_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SIGN_UP_PLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(SIGN_UP_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SIGN_UP_PLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(SIGN_UP_PLayout.createSequentialGroup()
                        .addGroup(SIGN_UP_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(SIGN_UP_PLayout.createSequentialGroup()
                                .addGroup(SIGN_UP_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(SIGN_UP_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(NUMBER, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(NAME, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(SIGN_UP_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(PHONE)
                                    .addComponent(EMAIL, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)))
                            .addGroup(SIGN_UP_PLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(SIGN_UP_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(CANCEL_BTN)
                                    .addGroup(SIGN_UP_PLayout.createSequentialGroup()
                                        .addComponent(SIGN_PW, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(129, 129, 129)
                                        .addComponent(SIGN_UP_BTN)))))
                        .addGap(134, 134, 134))))
            .addGroup(SIGN_UP_PLayout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SIGN_UP_PLayout.setVerticalGroup(
            SIGN_UP_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SIGN_UP_PLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(SIGN_UP_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SIGN_UP_PLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NUMBER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(4, 4, 4)
                        .addComponent(NAME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SIGN_UP_PLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PHONE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(3, 3, 3)
                        .addComponent(EMAIL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SIGN_UP_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SIGN_PW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SIGN_UP_BTN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CANCEL_BTN)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        getContentPane().add(SIGN_UP_P, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 360));

        LOGIN_P.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("맑은 고딕", 1, 36)); // NOI18N
        jLabel4.setText("S.M.P");

        ID_FIELD.setFont(new java.awt.Font("굴림", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        jLabel1.setText("ID : ");

        jLabel2.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        jLabel2.setText("PW : ");

        PW_FIELD.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N

        LOGIN_BTN.setBackground(new java.awt.Color(255, 255, 255));
        LOGIN_BTN.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        LOGIN_BTN.setText("로그인");

        SIGN_UP.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        SIGN_UP.setText("회원가입하기");
        SIGN_UP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SIGN_UPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout LOGIN_PLayout = new javax.swing.GroupLayout(LOGIN_P);
        LOGIN_P.setLayout(LOGIN_PLayout);
        LOGIN_PLayout.setHorizontalGroup(
            LOGIN_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LOGIN_PLayout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addGroup(LOGIN_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LOGIN_PLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel4))
                    .addGroup(LOGIN_PLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6)
                        .addComponent(ID_FIELD, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LOGIN_PLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(PW_FIELD, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(LOGIN_BTN, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LOGIN_PLayout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(SIGN_UP)))
                .addGap(118, 118, 118))
        );
        LOGIN_PLayout.setVerticalGroup(
            LOGIN_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LOGIN_PLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel4)
                .addGap(10, 10, 10)
                .addGroup(LOGIN_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(LOGIN_PLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(ID_FIELD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(LOGIN_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LOGIN_PLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addGroup(LOGIN_PLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(PW_FIELD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LOGIN_BTN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(SIGN_UP)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        getContentPane().add(LOGIN_P, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 360));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SIGN_UPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SIGN_UPMouseClicked
        // 회원가입화면으로 이동
        panelClear();
        SIGN_UP_P.setVisible(true);
    }//GEN-LAST:event_SIGN_UPMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CANCEL_BTN;
    private javax.swing.JTextField EMAIL;
    private javax.swing.JTextField ID_FIELD;
    private javax.swing.JButton LOGIN_BTN;
    private javax.swing.JPanel LOGIN_P;
    private javax.swing.JTextField NAME;
    private javax.swing.JTextField NUMBER;
    private javax.swing.JTextField PHONE;
    private javax.swing.JPasswordField PW_FIELD;
    private javax.swing.JPasswordField SIGN_PW;
    private javax.swing.JLabel SIGN_UP;
    private javax.swing.JButton SIGN_UP_BTN;
    private javax.swing.JPanel SIGN_UP_P;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
