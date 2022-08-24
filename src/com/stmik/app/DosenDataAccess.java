package com.stmik.app;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author casper
 */

public class DosenDataAccess {
    private static Connection conn;
    
    static  {
        if (conn == null) {
            try {
                MysqlDataSource ds = new MysqlDataSource();
                ds.setServerName("lokalhost");
                ds.setDatabaseName("akademik");
                ds.setUser("azeezee");
                ds.setPassword("123");
                conn = (Connection) ds.getConnection();
            } catch (SQLException ex) {
                System.out.println("Koneksi Mysql Error !" +ex.getMessage());
            }
        } 
    }
    
    public List<Dosen> getAll() {
        List<Dosen> list = new ArrayList<>();
        String sql = "SELECT * FROM dosen";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            Dosen dosen;
            while(rs.next()) {
                dosen = new Dosen(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("kompetensi"));
                list.add(dosen);
            }
        } catch (SQLException ex) {
            System.out.println("Get dosen error : " + ex.getMessage());
        }
        return list;
    }
    
    public Dosen getByID(int id) {
        String sql = "SELECT * FROM WHERE id=?";
        Dosen dosen = null;
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                dosen = new Dosen (
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("kompetensi"));
            }
        } catch (SQLException ex) {
            System.out.println("Get dosen error !");
        }
        return dosen;
    }
    
    public boolean insert(Dosen dosen) {
        boolean berhasilKah = false;
        String sql = "INSERT INTO SET nama =?, kompetensi=?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, dosen.getNama());
            stm.setString(2, dosen.getKompetensi());
            int jumlahTerinsert = stm.executeUpdate();
            berhasilKah = jumlahTerinsert > 0;
        } catch (SQLException ex) {
            System.out.println("Insert dosen error :" + ex.getMessage());
        }
        return berhasilKah;
    }
    
    
    public boolean update(Dosen dosen) {
        boolean berhasilKah = false;
        String sql = "UPDATE dosen SET nama=?, kompetenesi=? WHERE id=?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, dosen.getNama());
            stm.setString(2, dosen.getKompetensi());
            stm.setInt(3, dosen.getId());
            int jumlahTerupdate = stm.executeUpdate();
            berhasilKah = jumlahTerupdate > 0;
        } catch (SQLException ex) {
            System.out.println("Update dosen error: " + ex.getMessage());
        }
        return berhasilKah;
    }
    
    public boolean delete(int id) {
        boolean berhasilKah = false;
        String sql = "DELETE FROM dosen WHERE id =?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            int jumlahTerhapus = stm.executeUpdate();
            berhasilKah = jumlahTerhapus > 0;
        } catch (SQLException ex) {
            System.out.println("Delete dosen error: " + ex.getMessage());
        }
        return berhasilKah;
    }
}
