/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Memory;

/**
 *
 * @author PC
 */
public class MemoryDAO extends DBContext {

    public Memory getMemoryById(int memoryId) {
        String sql = "select * from Memorys as m\n"
                + "where m.deleted = ? and m.Memory_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, memoryId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int memorySize = rs.getInt("Memory_size");
                int deleted = rs.getInt("deleted");

                return new Memory(memoryId, memorySize, deleted);
            }
        } catch (SQLException e) {
            System.out.println("loi get memory by Id: " + e);
        }
        return null;
    }

    public List<Memory> getMemoryAll() {
        List<Memory> list = new ArrayList<>();
        String sql = "select * from Memorys\n"
                + "where deleted = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int memoryId = rs.getInt("Memory_id");
                int memorySize = rs.getInt("Memory_size");
                int deleted = rs.getInt("deleted");
                
                Memory m = new Memory(memoryId, memorySize, deleted);
                
                list.add(m);
            }
        } catch (SQLException e) {
            System.out.println("loi get all: " + e);
        }
        return list;
    }

    public static void main(String[] args) {
        MemoryDAO md = new MemoryDAO();
        
        for(Memory x : md.getMemoryAll()){
            System.out.println(x);
        }

        System.out.println(md.getMemoryById(1));
    }
}
