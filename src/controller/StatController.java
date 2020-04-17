/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Models.Connect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import view.Stat;

/**
 *
 * @author tranm
 */
public class StatController {

    public void ThongKe(String DateCheckFrom, String DateCheckTo, DefaultTableModel tbnBillStat, JTable tbBillStat) {
        ResultSet rs = null;
        try {
            Vector column;
            column = new Vector();
            Statement statement = Connect.getConnection().createStatement();
            String sql = "select * from Bill" + " where DataCheck between '" + DateCheckFrom + "' and '" + DateCheckTo + "'and status =1";
            rs = statement.executeQuery(sql);
            column.add("Ngày ");
            column.add("Mã hóa đơn ");
            column.add("Tổng tiền ");
            tbnBillStat.setColumnIdentifiers(column);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Vector data = null;
            tbnBillStat.setRowCount(0);
            if (rs != null) {
                while (rs.next()) {
                    data = new Vector();
                    data.addElement(rs.getString("DataCheck"));
                    data.addElement(rs.getString("id"));
                    data.addElement(rs.getString("totalPrice"));
                    // Thêm một dòng vào table model
                    tbnBillStat.addRow(data);
                }
            }
            tbBillStat.setModel(tbnBillStat);
        } catch (Exception ex) {
            Logger.getLogger(Stat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
    }

    public String TongDoanhThu(String DateCheckFrom, String DateCheckTo) {
        ResultSet rs = null;
        String DoanhThu = "";
        try {
            Statement statement = Connect.getConnection().createStatement();
            String sql = "select sum(totalPrice) as 'DoanhThu' from Bill" + " where DataCheck between '" + DateCheckFrom + "' and '" + DateCheckTo + "' and status =1";
            rs = statement.executeQuery(sql);
            if (rs != null) {
                rs.next();
                DoanhThu = rs.getString("DoanhThu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connect.close();
        }
        return DoanhThu;
    }

    public void MonUaChuong(DefaultTableModel tbnUaChuong, JTable tbUaChuong) {
        ResultSet rs = null;
        try {
            Vector column = null;
            Statement statement = Connect.getConnection().createStatement();
            String sql = "select idFood, name ,sum(count) as 'countTotal'  from BillInfo Inner Join Food on BillInfo.idFood = Food.id group by idFood, name order by countTotal desc";
            rs = statement.executeQuery(sql);
            column = new Vector();
            column.add("Mã món");
            column.add("Tên món");
            column.add("Tổng số lượng ");
            tbnUaChuong.setColumnIdentifiers(column);
            
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Vector data = null;
            tbnUaChuong.setRowCount(0);
            if (rs != null) {
                while (rs.next()) {
                    data = new Vector();
                    data.addElement(rs.getString("idFood"));
                    data.addElement(rs.getString("name"));
                    data.addElement(rs.getString("countTotal"));
                    // Thêm một dòng vào table model
                    tbnUaChuong.addRow(data);
                }
            }
            tbUaChuong.setModel(tbnUaChuong);
        } catch (Exception ex) {
            Logger.getLogger(Stat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
    }
    public void MonItUaChuong(DefaultTableModel tbnUaChuong, JTable tbUaChuong) {
        ResultSet rs = null;
        try {
            Vector column = null;
            Statement statement = Connect.getConnection().createStatement();
            String sql = "select idFood, name ,sum(count) as 'countTotal'  from BillInfo Inner Join Food on BillInfo.idFood = Food.id group by idFood, name order by countTotal asc";
            rs = statement.executeQuery(sql);
            column = new Vector();
            column.add("Mã món");
            column.add("Tên món");
            column.add("Tổng số lượng ");
            tbnUaChuong.setColumnIdentifiers(column);
            
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Vector data = null;
            tbnUaChuong.setRowCount(0);
            if (rs != null) {
                while (rs.next()) {
                    data = new Vector();
                    data.addElement(rs.getString("idFood"));
                    data.addElement(rs.getString("name"));
                    data.addElement(rs.getString("countTotal"));
                    // Thêm một dòng vào table model
                    tbnUaChuong.addRow(data);
                }
            }
            tbUaChuong.setModel(tbnUaChuong);
        } catch (Exception ex) {
            Logger.getLogger(Stat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
    }
}
