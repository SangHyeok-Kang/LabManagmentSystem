package test;

import model.DBConnection;
import model.Signup_model;
        
public class SignupTest{
    public static void main(String[] args) {
         if (DBConnection.getInstance().Initailize()) {
        Signup_model model = new Signup_model();
        String stunum = "20183150";
        String pass = "1234";
        String name = "김부성";
        String phone_num = "010-1231-2221";
        String email = "rlaqntjd@naver.com";
        
        model.signup(stunum, pass, name, phone_num, email);
      }
    }
}
