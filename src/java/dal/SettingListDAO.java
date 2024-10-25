/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.SettingListt;

/**
 *
 * @author admin
 */
public class SettingListDAO extends DBContext {

    public List<SettingListt> getAllSetting() {
        String sql = "select * from Setting_List\n"
                + "order by Status_setting asc, Type_setting asc";
        List<SettingListt> listSett = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SettingListt a = new SettingListt(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6));
                listSett.add(a);

            }
        } catch (Exception e) {
        }
        return listSett;
    }

    public int getValueOfPaging() {
        String sql = "select top 1 Value_setting from Setting_List\n"
                + "where Type_setting = N'Phân trang' and Status_setting = 0";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SettingListt a = new SettingListt(rs.getInt(1));
                return a.getValue();
            }
        } catch (Exception e) {
        }
        return 5;
    }

    public int getValueOfTimeToSendEmail() {
        String sql = "select top 1 Value_setting from Setting_List\n"
                + "where Type_setting = N'Thời gian gửi email' and Status_setting = 0";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SettingListt a = new SettingListt(rs.getInt(1));
                return a.getValue() * 1000;
            }
        } catch (Exception e) {
        }
        return 10 * 1000;
    }

    public int getValueOfTimeToVerifyToken() {
        String sql = "select top 1 Value_setting from Setting_List\n"
                + "where Type_setting = N'Thời gian xác nhận token mật khẩu' and Status_setting = 0";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SettingListt a = new SettingListt(rs.getInt(1));
                return a.getValue() * 1000;
            }
        } catch (Exception e) {
        }
        return 40 * 1000;
    }
    
    public int getValueOfSessionTimeOut() {
        String sql = "select top 1 Value_setting from Setting_List \n" +
"		where Type_setting = N'Session time out' and Status_setting = 0";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SettingListt a = new SettingListt(rs.getInt(1));
                return a.getValue() * 60;
            }
        } catch (Exception e) {
        }
        return 10 * 60;
    }

    public List<SettingListt> getListByPage(List<SettingListt> listAuthor, int start, int end) {
        ArrayList<SettingListt> arrayAuthor = new ArrayList<>();
        start = Math.max(0, start); // Start from index 0 if start is negative
        end = Math.min(listAuthor.size(), end); // Ensure end doesn't exceed list size
        for (int i = start; i < end; i++) {
            arrayAuthor.add(listAuthor.get(i));
        }
        return arrayAuthor;
    }

    public List<SettingListt> filterByType(String type) {
        String sql = "select * from Setting_List\n"
                + "where Type_setting = ?";
        List<SettingListt> listSett = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SettingListt a = new SettingListt(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6));
                listSett.add(a);

            }
        } catch (Exception e) {
        }
        return listSett;
    }

    public List<SettingListt> filterByStatus(int status) {
        String sql = "select * from Setting_List \n"
                + "where Status_setting = ?";
        List<SettingListt> listSett = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SettingListt a = new SettingListt(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6));
                listSett.add(a);

            }
        } catch (Exception e) {
        }
        return listSett;
    }

    public List<SettingListt> searchSettingByTypeAndValue(String typeAndValue) {
        String sql = "select * from Setting_List\n"
                + "where Type_setting like ? or Value_setting like ?";
        List<SettingListt> listSett = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + typeAndValue + "%");
            ps.setString(2, "%" + typeAndValue + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SettingListt a = new SettingListt(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6));
                listSett.add(a);

            }
        } catch (Exception e) {
        }
        return listSett;
    }

    public List<SettingListt> orderBySomeThing(String order) {
        String sql = "select * from Setting_List\n"
                + "order by " + order + " desc";
        List<SettingListt> listSett = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SettingListt a = new SettingListt(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6));
                listSett.add(a);

            }
        } catch (Exception e) {
        }
        return listSett;
    }

    public List<SettingListt> getAllType() {
        String sql = "select distinct Type_setting from Setting_List";
        List<SettingListt> listType = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listType.add(new SettingListt(rs.getString(1)));
            }
        } catch (Exception e) {
        }
        return listType;
    }

    public List<SettingListt> getSizeBySomeThing(String Type) {
        String sql = "select * from Setting_List\n"
                + "where Type_setting = ?";
        List<SettingListt> listType = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, Type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listType.add(new SettingListt(rs.getString(1)));
            }
        } catch (Exception e) {
        }
        return listType;
    }

    public void insetSetting(SettingListt u) {
        String sql = "INSERT INTO [dbo].[Setting_List]\n"
                + "           ([Type_setting]\n"
                + "           ,[Value_setting]\n"
                + "           ,[Order_setting]\n"
                + "           ,[Description_setting]\n"
                + "           ,[Status_setting])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, u.getType());
            ps.setInt(2, u.getValue());
            ps.setInt(3, u.getOrder());
            ps.setString(4, u.getDescription());
            ps.setInt(5, u.getStatus());
            ps.executeQuery();
        } catch (Exception e) {
        }
    }

    public boolean checkExistSetting(String type, int value, int status) {
        String sql = "select * from Setting_List\n"
                + "where Type_setting = ? and Value_setting = ? and Status_setting = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, type);
            ps.setInt(2, value);
            ps.setInt(3, status);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public SettingListt getSettingById(String id) {
        String sql = "select * from Setting_List\n"
                + "where Setting_id = ?";
        SettingListt st;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                st = new SettingListt(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6));
                return st;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public void updateSetting(int id, String type, int value, String description, int status) {
        String sql = "UPDATE [dbo].[Setting_List]\n"
                + "   SET [Type_setting] = ?\n"
                + "      ,[Value_setting] = ?\n"
                + "      ,[Description_setting] = ?\n"
                + "      ,[Status_setting] = ?\n"
                + " WHERE Setting_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, type);
            ps.setInt(2, value);
            ps.setString(3, description);
            ps.setInt(4, status);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        SettingListDAO dao = new SettingListDAO();

        try {
            SettingListt a = dao.getSettingById("1");
            int b = dao.getValueOfTimeToVerifyToken();
            System.out.println("B: " + b);
        } catch (Exception e) {
            System.out.println("E: " + e);
        }

    }
}
