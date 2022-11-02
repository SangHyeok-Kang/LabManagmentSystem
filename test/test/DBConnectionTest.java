package test;

import model.DBConnection;

public class DBConnectionTest {
    public static void main(String[] args) {
          if (DBConnection.getInstance().Initailize()) {
              System.out.println("success");
          }
    }
    
}
