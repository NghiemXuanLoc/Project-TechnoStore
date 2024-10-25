/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.UpdateHistory;
import model.User;

/**
 *
 * @author admin
 */
public class MarketingUpdateUserHistoryDAO extends DBContext {

    public void updateUserById(String fullName, String gender,String address, String email, String phone, String userId) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [Full_name] = ?,\n"
                + "	[Gender] = ?,[Address] = ?,\n"
                + "	[Email] = ?, \n"
                + "	[Phone_number] = ?\n"
                + " WHERE User_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fullName);
            ps.setString(2, gender);
            ps.setString(3, address);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Loi k update dc");
        }
    }

    public void addToUpdateHistory(String email, String userId, String fullName, String gender, String phoneNumber, String address, String updateBy) {
        String sql = "INSERT INTO [dbo].[Update_history]\n"
                + "           ([Email],\n"
                + "         [User_id]\n"
                + "           ,[Full_name]\n"
                + "           ,[gender]\n"
                + "           ,[Phone_number]\n"
                + "           ,[Address]\n"
                + "           ,[update_by]\n"
                + "           ,[update_date]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,GETDATE(),0)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, userId);
            ps.setString(3, fullName);
            ps.setString(4, gender);
            ps.setString(5, phoneNumber);
            ps.setString(6, address);
            ps.setString(7, updateBy);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Err: " + e);
            System.out.println("Loi k add dc");
        }
    }

    public List<UpdateHistory> getAllUpdateByUserId(String id) {
        String sql = "select ud.Email, ud.Full_name,ud.User_id, ud.gender, ud.Phone_number, ud.[Address],ud.update_by,ud.update_date\n"
                + "from Users u join Update_history ud\n"
                + "on u.User_id = ud.User_id\n"
                + "where u.User_id = ?";
        List<UpdateHistory> listUpdate = new ArrayList<>();
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                listUpdate.add(new UpdateHistory(rs.getString(1), 
                        rs.getInt(3), 
                        rs.getString(2), 
                        rs.getInt(4), 
                        rs.getString(5), 
                        rs.getString(6), 
                        rs.getString(7), 
                        rs.getDate(8)));
            }
        } catch (Exception e) {
            System.out.println("Loi roi");
            System.out.println("Loi roi: "+ e);
        }
        return listUpdate;
    }
    
    public List<UpdateHistory> getListByPage (List<UpdateHistory> listUser, int start, int end){
        ArrayList<UpdateHistory> arrayUser = new ArrayList<>();
        start = Math.max(0, start); // Start from index 0 if start is negative
        end = Math.min(listUser.size(), end); // Ensure end doesn't exceed list size
        for (int i = start; i < end; i++) {
            arrayUser.add(listUser.get(i));
        }
        return arrayUser;
    }

    public static void main(String[] args) {
        MarketingUpdateUserHistoryDAO dao = new MarketingUpdateUserHistoryDAO();
//        System.out.println("Date : " + dateOfBirth);
//        dao.addToUpdateHistory("sdfs@gmail.com", "1", "sdfsdf", "1", "0123123123", "Canh Nau", "Haha");
        List<UpdateHistory> list = dao.getAllUpdateByUserId("9");
        
        for (UpdateHistory updateHistory : list) {
            System.out.println("A: " + updateHistory.getUserId());
        }
        
    }
}
