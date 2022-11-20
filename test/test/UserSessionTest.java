package test;



import static model.UserSession.log;

public class UserSessionTest {
    public static void main(String[] args) {
        // TODO code application logic here
        String auth = "20183109";
        
        log.setSession(auth);//사용자 로그인 정보를 세션으로 저장
        System.out.println("세션 생성 : " + log.session);
    }
    
}
