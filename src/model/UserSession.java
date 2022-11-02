
package model;

//로그인 세션을 지정하는 클래스를 생성하여 하나의 라이브러리처럼 사용
public class UserSession {
    public static final UserSession log = new UserSession();
    String session;   
    //사용자 로그인 아이디를 받아와 세션으로 저장
    void setSession(String auth) {
        log.session= auth;
        System.out.println(log.session);
    }
}
