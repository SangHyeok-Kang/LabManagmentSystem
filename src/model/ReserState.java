package model;

import model.State.AcceptAccessState;
import model.State.CancelReserState;
import model.State.LeaveLabState;
import model.State.State;
import model.State.UseLabState;
import static model.UserSession.log;


public class ReserState {
    String user_id = log.session;
    String[][] reser_info;
    State CancelReserState;
    State AcceptAccessState;
    State WaitAccessState;
    State LeaveLabState;
    State UseLabState;
    State state = WaitAccessState;
    
    public ReserState(String reser, String butt_contents){
        System.out.println(reser);
        CancelReserState = new CancelReserState(reser);
        AcceptAccessState = new AcceptAccessState(reser);
        LeaveLabState = new LeaveLabState(reser);
        
        
        if(butt_contents.equals("취소하기")){
            state = CancelReserState;
        }else if(butt_contents.equals("퇴실하기")){
            state = LeaveLabState;
        }else if(butt_contents.equals("승인")){
            state = AcceptAccessState;
        }
    }
    
    public ReserState(String butt_contents){
        UseLabState = new UseLabState(user_id);
        state = UseLabState;
    }
    
    public boolean updateState(){
        boolean result = state.updateState();
        if(result == true){
            return true;
        }else{
            return false;
        }
    }   
}
