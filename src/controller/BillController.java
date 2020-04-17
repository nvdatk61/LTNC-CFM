/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Models.Bill;
import Models.Connect;
import Models.Food;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tranm
 */
public class BillController {

    public static int getIdBillByIdTable(int idTable) {
        try {
            Statement statement = Connect.getConnection().createStatement();
            String sql = "select id from Bill where idTable = " + idTable + " and status = 0";
            ResultSet resultSet = statement.executeQuery(sql);
            int idBill = 0;
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
        return 0;

    }

    public static boolean updateDiscount(int discount, int idBill) {
        try {
            PreparedStatement preparedStatement = Connect.getConnection()
                    .prepareStatement("update Bill set discount = " + discount + " where id = " + idBill);
            if (preparedStatement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
        return false;
    }

    public static void updateIdTableByIdBill(int idBill, int idTable) {
        try {
            PreparedStatement preparedStatement = Connect.getConnection()
                    .prepareStatement("update Bill set idTable = " + idTable + " where id = " + idBill);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
    }

    public static void payBillByIdTable(int idTable, int total, String userName) {
        try {
            PreparedStatement preparedStatement = Connect.getConnection()
                    .prepareStatement("update Bill set status = 1, totalPrice = " + total + ", TimeCheckOut = GETDATE(), userStaff = \'" + userName + "\' where idTable = " + idTable + " and status = 0");
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
    }

    public static int getDiscountByIdBill(int idBill) {
        try {
            Statement statement = Connect.getConnection().createStatement();
            String sql = "select discount from Bill where id = " + idBill;
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            };
        } catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
        return 0;
    }

    public void loadDataBill(DefaultTableModel tbnBill, JTable tbBill) {
        try {
            int number;
            Vector row, column;
            column = new Vector();
            Statement statement = Connect.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("Select * From Bill ");
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();

            column.add("Mã hóa đơn");
            column.add("Ngày ");
            column.add("Giờ vào");
            column.add("Giờ ra");
            column.add("Mã bàn");
            column.add("Trạng thái");
            column.add("Giảm giá");
            column.add("Tổng tiền");
            column.add("Nhân viên");
            tbnBill.setColumnIdentifiers(column);

            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                }
                tbnBill.addRow(row);
                tbBill.setModel(tbnBill);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            Connect.close();
        }
    }

    public static int addNewBill(int idTable) {
        try {
            PreparedStatement preparedStatement = Connect.getConnection()
                    .prepareStatement("INSERT dbo.Bill (DataCheck, TimeCheckIn, TimeCheckOut, idTable , status )"
                            + " VALUES  (GETDATE(), GETDATE(), NULL, " + idTable + ", 0)");
            if (preparedStatement.executeUpdate() == 1) {
                Connect.close();
                preparedStatement = Connect.getConnection().prepareStatement("update TableFood set status = 1 where id = " + idTable);
                if (preparedStatement.executeUpdate() == 1) {
                    return 1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
        return 0;
    }
}
