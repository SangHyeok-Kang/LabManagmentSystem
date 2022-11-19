
package Controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.New_notice_model;
import model.Notice;
import model.NoticeList_model;
import view.Manager_Main;
import view.Student_Main;

public class Board_controller {
    
    Manager_Main mview;
    Student_Main sview;
    
    New_notice_model n_model = new New_notice_model();
    NoticeList_model l_model = new NoticeList_model();
    
    ArrayList<Notice> notice_list;
    ArrayList<Notice> inquiry_list;
    
    public Board_controller(Manager_Main view){
        this.mview = view;
        getNoticeList();
        getIquiryList();
    }
    
    public void setNotice(){
        if(mview.TITLE_F.getText().isEmpty() || mview.TEXT_F.getText().isEmpty()){
            n_model.insertNotice( mview.TITLE_F.getText(), mview.TEXT_F.getText());
            JOptionPane.showMessageDialog(null, "공지사항을 등록하였습니다.");
            mview.NOTICE_INFO.dispose();
        }else
            JOptionPane.showMessageDialog(null, "제목과 내용을 모두 입력해주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
    }
    
    public void getNoticeList(){
        this.notice_list = l_model.notice();
    }
    
    public void getIquiryList(){
        this.inquiry_list = l_model.inquriy();
    }
    
    public void getNotice(){
        mview.NOTICE_LIST.removeAll();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NO.");
        model.addColumn("날짜");
        model.addColumn("제목");
        model.addColumn("작성자");
        
        for(int i=0; i< notice_list.size(); i++){
            model.addRow(new Object [] {notice_list.get(i).getNum(),notice_list.get(i).getDate(),notice_list.get(i).getTitle(),notice_list.get(i).getWriter()});
        }
        mview.NOTICE_LIST.setModel(model);
    }
    
    public void getInquiry(){
        mview.NOTICE_LIST.removeAll();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NO.");
        model.addColumn("날짜");
        model.addColumn("제목");
        model.addColumn("작성자");
        model.addColumn("카테고리");
        
        for(int i=0; i< inquiry_list.size(); i++){
            model.addRow(new Object [] {inquiry_list.get(i).getNum(),inquiry_list.get(i).getDate(),inquiry_list.get(i).getTitle(),inquiry_list.get(i).getWriter(),inquiry_list.get(i).getCate()});
        }
        mview.NOTICE_LIST.setModel(model);
    }
    
    public void setNoticeInfo(){
        int num = mview.NOTICE_LIST.getSelectedRow();
        if(num==-1){
             JOptionPane.showMessageDialog(null, "게시물을 선택해주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }else{
           mview.jLabel30.setVisible(true);
           mview.TITLE_F.setEditable(false);
           mview.TITLE_F.setText(notice_list.get(num).getTitle());
           mview.TEXT_F.setEditable(false);
           mview.TEXT_F.setText(notice_list.get(num).getContents());
           mview.NOTICE_WRITER_L.setVisible(true);
           mview.NOTICE_WRITER_L.setText(notice_list.get(num).getWriter());
           mview.NOTICE_NYM_L.setVisible(true);
           mview.NOTICE_NYM_L.setText(notice_list.get(num).getNum());
           mview.INPUT_NOTICE_BTN.setVisible(false);
           mview.CHANGE_NOTICE_BTN.setVisible(false);
           mview.NOTICE_INFO.setVisible(true);
        }
    }
    
    public void setInquiryInfo(){
        int num = mview.NOTICE_LIST.getSelectedRow();
        if(num==-1){
             JOptionPane.showMessageDialog(null, "게시물을 선택해주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }else{
           mview.STU_L.setText(inquiry_list.get(num).getWriter());
           mview.INAUIRY_NUM_L.setText(inquiry_list.get(num).getNum());
           mview.INQUIRY_TEXT_F.setText(inquiry_list.get(num).getContents());
           mview.INQUIRY_TITLE_F.setText(inquiry_list.get(num).getTitle());
           mview.INQUIRY_DIALOG.setVisible(true);
        }
    }
    
    public void removeInfo(){
        int num = mview.NOTICE_LIST.getSelectedRow();
        if(num==-1){
             JOptionPane.showMessageDialog(null, "게시물을 선택해주세요.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }else{
            if(mview.NOTICE_TITLE.getText().equals("공지사항")){
               if(l_model.removeNotice(notice_list.get(num).getNum())){
                   JOptionPane.showMessageDialog(null, "공지사항이 삭제되었습니다.");
                    getNoticeList();
                   getNotice();
               }
            }else if(mview.NOTICE_TITLE.getText().equals("신고 및 문의")){
               if(l_model.removeInquiry(inquiry_list.get(num).getNum())){
                   JOptionPane.showMessageDialog(null, "신고 및 문의가 삭제되었습니다.");
                   getIquiryList();
                   getInquiry();
               }
            }
        }
    }
}
