package model;

import model.State.State;
import static model.UserSession.log;


public class ReserState {
    String user_id = log.session;
    String[][] reser_info;
    State CancelReserState;
    State AcceptAccessState;
    State WaitAccessState;
    State LeaveLabState;
    State state = WaitAccessState;
    
    
    ReserState(String[][] reser_info, String butt_contents){
        
    }
    
    public void updateState(){
        
    }
}
