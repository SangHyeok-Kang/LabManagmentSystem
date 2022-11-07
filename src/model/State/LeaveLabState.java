package model.State;

import model.State.State;
import java.sql.*;
import static model.DBConnection.dbconnection;

public class LeaveLabState implements State {
    String reser_num, end_time;
    private Connection con = null;
    private Statement st =  null;
    private ResultSet rs = null;
    
    public void updateState(String reser_num){
        
    }
}
