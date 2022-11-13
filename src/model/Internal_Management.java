package model;

import static model.UserSession.log;
import model.facade.*;

/**
 *
 * @author pkm30
 */
public class Internal_Management {
    String user_num = log.session;
    public DeleteUserInfo user_info;
    public DeleteReserInfo reser_info;
    public DeleteBoardInfo board_info;

    public Internal_Management(DeleteUserInfo user_info, DeleteReserInfo reser_info, DeleteBoardInfo board_info) {
        this.user_info = user_info;
        this.reser_info = reser_info;
        this.board_info = board_info;
    }

    boolean delete_Info() {
        user_info.deleteInfo(user_num);
        reser_info.deleteInfo(user_num);
        board_info.deleteInfo(user_num);
        return false;
    }
    
}
