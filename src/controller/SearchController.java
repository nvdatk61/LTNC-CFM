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
import view.Tool;

/**
 *
 * @author tranm
 */
public class SearchController {

    public void SearchFoodNameId(String keyword, DefaultTableModel tbnFood, JTable tbFood) {
        ResultSet rs = null;
        try {
            Statement statement = Connect.getConnection().createStatement();
            String sql = "select * from Food";
            if (keyword.length() > 0) {
                sql = sql + " where name like N'%" + keyword + "%'or id like N'%" + keyword + "%'";
                rs = statement.executeQuery(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Vector data = null;
            tbnFood.setRowCount(0);
            if (rs != null) {
                while (rs.next()) {
                    data = new Vector();
                    data.add(rs.getString("id"));
                    data.add(rs.getString("name"));
                    data.add(rs.getString("price"));
                    String IDCategory = rs.getString("idCategory");
                    if (IDCategory.equals("1")) {
                        data.add("Cà phê");
                    }
                    if (IDCategory.equals("2")) {
                        data.add("Sinh tố");
                    }
                    if (IDCategory.equals("3")) {
                        data.add("Nước ép");
                    }
                    if (IDCategory.equals("4")) {
                        data.add("Đá xay");
                    }
                    if (IDCategory.equals("5")) {
                        data.add("Sữa chua");
                    }
                    // Thêm một dòng vào table model
                    tbnFood.addRow(data);
                }
            } else {
                ;
            }
            tbFood.setModel(tbnFood);
        } catch (Exception ex) {
            Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Connect.close();
        }
    }
    
    public void SearchFoodCategory(String NameCategory, DefaultTableModel tbnFood, JTable tbFood) {
        ResultSet rs = null;
        try {
            Statement statement = Connect.getConnection().createStatement();
            String IDCategory = null;
            if (NameCategory.equals("Cà phê")) {
                IDCategory = "1";
            }
            if (NameCategory.equals("Sinh tố")) {
                IDCategory = "2";
            }
            if (NameCategory.equals("Nước ép")) {
                IDCategory = "3";
            }
            if (NameCategory.equals("Đá xay")) {
                IDCategory = "4";
            }
            if (NameCategory.equals("Sữa chua")) {
                IDCategory = "5";
            }
            String sql = "select * from Food";
            if (IDCategory.length() > 0) {
                sql = sql + " where idCategory like N'%" + IDCategory + "%'";
                rs = statement.executeQuery(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Vector data = null;
            tbnFood.setRowCount(0);
            if (rs != null) {
                while (rs.next()) {
                    data = new Vector();
                    data.add(rs.getString("id"));
                    data.add(rs.getString("name"));
                    data.add(rs.getString("price"));
                    String IDCategory = rs.getString("idCategory");
                    if (IDCategory.equals("1")) {
                        data.add("Cà phê");
                    }
                    if (IDCategory.equals("2")) {
                        data.add("Sinh tố");
                    }
                    if (IDCategory.equals("3")) {
                        data.add("Nước ép");
                    }
                    if (IDCategory.equals("4")) {
                        data.add("Đá xay");
                    }
                    if (IDCategory.equals("5")) {
                        data.add("Sữa chua");
                    }
                    // Thêm một dòng vào table model
                    tbnFood.addRow(data);
                }
            } else {
                ;
            }
            tbFood.setModel(tbnFood);
        } catch (Exception ex) {
            Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Connect.close();
        }
    }

    public void SearchBillUserStaff(String keyword, DefaultTableModel tbnBill, JTable tbBill) {
        ResultSet rs = null;
        try {
            Statement statement = Connect.getConnection().createStatement();
            String sql = "select * from Bill";
            if (keyword.length() > 0) {
                sql = sql + " where userStaff like N'%" + keyword + "%'or id like N'%"+ keyword +"%'"  ;
                rs = statement.executeQuery(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Vector data = null;
            tbnBill.setRowCount(0);
            if (rs != null) {
                while (rs.next()) {
                    data = new Vector();
                    data.add(rs.getString("id"));
                    data.add(rs.getString("DataCheck"));
                    data.add(rs.getString("TimeCheckIn"));
                    data.add(rs.getString("TimeCheckOut"));
                    data.add(rs.getString("idTable"));
                    data.add(rs.getString("status"));
                    data.add(rs.getString("discount"));
                    data.add(rs.getString("totalPrice"));
                    data.add(rs.getString("userStaff"));
                    // Thêm một dòng vào table model
                    tbnBill.addRow(data);
                }
            }
            tbBill.setModel(tbnBill);
        } catch (Exception ex) {
            Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Connect.close();
        }
    }

}
