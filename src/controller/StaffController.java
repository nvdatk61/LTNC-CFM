/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Models.Account;
import Models.Connect;
import com.sun.prism.Presentable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.Home;
import view.Staff;

/**
 *
 * @author Admin
 */
public class StaffController {

    public static Account loadInfor(String UserName) {
        Account account = null;
        try {
            Statement statement = Connect.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Account WHERE UserName = \'" + UserName + "'");
            try {
                if (rs.next()) {
                    account = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                }
            } finally {
                Connect.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;

    }

    public static void AddStaff(String fullName, String userName, String passWord, int Type) {
        try {
            PreparedStatement preparedStatement = Connect.getConnection().prepareStatement("insert into Account (UserName,PassWord,FullName,Type) values (?,?,?,?)");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, passWord);
            preparedStatement.setString(3, fullName);
            preparedStatement.setInt(4, Type);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
        JOptionPane.showMessageDialog(null, "Thêm thành công!!!");
    }

    public static void EditMySelf(String fullName, String passWordNew, int type, String userName) {
        try {
            PreparedStatement preparedStatement = Connect.getConnection().prepareStatement("UPDATE Account SET PassWord = ?, FullName = ?, Type = ? where UserName = ?");
            preparedStatement.setString(1, passWordNew);
            preparedStatement.setString(2, fullName);
            preparedStatement.setInt(3, type);
            preparedStatement.setString(4, userName);
            if (preparedStatement.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "Cập nhật thành công!!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
    }

    public static void EditStaff(String fullName, int type, String userName) {
        try {
            PreparedStatement preparedStatement = Connect.getConnection().prepareStatement("UPDATE Account SET FullName = ?, Type = ? where UserName = ?");
            preparedStatement.setString(1, fullName);
            preparedStatement.setInt(2, type);         
            preparedStatement.setString(3, userName);
            if (preparedStatement.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "Cập nhật thành công!!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
    }
    
    public static void DeleteStaff(String userName){
        try {
            PreparedStatement statement = Connect.getConnection().prepareStatement("delete from Account where UserName = \'" + userName +"'");
            if (statement.executeUpdate() == 1) {
            JOptionPane.showMessageDialog(null, "Xóa thành công!!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            Connect.close();
        }
    }
    public static int CheckUserName(String userName){
        int check = 0;
        try {
            Statement statement = Connect.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Account WHERE UserName = \'" + userName + "'");
            if (rs.next()) {
                check = 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            Connect.close();
        }
        return check;
    }
}
