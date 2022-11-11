package test;

import model.DBConnection;
import model.Signup_model;
import java.util.Scanner;

public class SignupTest {

    public static void main(String[] args) {
        if (DBConnection.getInstance().Initailize()) {
            Signup_model model = new Signup_model();
            Scanner sc = new Scanner(System.in);
            String stunum;
            String pass;
            String name;
            String phone_num;
            String email;

            System.out.print("Insert studno : ");
            stunum = sc.next();
            
            System.out.print("Insert password :");
            pass = sc.next();
            
            System.out.print("Insert name : ");
            name = sc.next();
            
            System.out.print("Insert phone_num : ");
            phone_num = sc.next();
            
            System.out.print("Insert email : ");
            email = sc.next();
                     
            model.signup(stunum, pass, name, phone_num, email);
        }
    }
}
