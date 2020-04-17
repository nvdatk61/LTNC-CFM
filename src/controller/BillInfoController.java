/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Models.BillInfo;
import Models.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author qcuon
 */
public class BillInfoController {

    public static int addNewFood(int idBill, int idFood, int count) {
        try {
            PreparedStatement preparedStatement = Connect.getConnection()
                    .prepareStatement("INSERT dbo.BillInfo (idBill, idFood, count) VALUES ( " + idBill + "," + idFood + "," + count + ")");
            if (preparedStatement.executeUpdate() == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
        return 0;
    }

    public static ArrayList<BillInfo> getListBillInfoByIdBill(int idBill) {
        ArrayList<BillInfo> listBillInfo = new ArrayList<BillInfo>();
        try {
            Statement statement = Connect.getConnection().createStatement();
            String sql = "select * from BillInfo where idBill = " + idBill;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int idBillInfo = resultSet.getInt(1);
                int idFood = resultSet.getInt(3);
                int status = resultSet.getInt(4);
                BillInfo billInfo = new BillInfo(idBillInfo, idBill, idFood, status);
                listBillInfo.add(billInfo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillInfoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
        return listBillInfo;
    }

    public static boolean deleteFood(String nameFood, int idBill) {
        try {
            PreparedStatement preparedStatement = Connect.getConnection()
                    .prepareStatement("select id from Food where name like N'" + nameFood + "'"); //getIdFoodByNameFood
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) { 
                int idFood = resultSet.getInt(1);
                System.out.println(idFood+" "+idBill);
                Connect.close();
                preparedStatement = Connect.getConnection()
                        .prepareStatement("delete from BillInfo where idBill = " + idBill + " and idFood = " + idFood);
                if (preparedStatement.executeUpdate() == 1) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillInfoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
        return false;
    }

    public static boolean tryUpdateFood(int idBill, int idFood, int count) {
        try {
            PreparedStatement preparedStatement = Connect.getConnection()
                    .prepareStatement("select * from BillInfo where idBill = " + idBill + " and idFood = " + idFood);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) { // đã có món thì update  
                int countCurrent = resultSet.getInt("count") + count;     
                Connect.close();
                preparedStatement = Connect.getConnection()
                        .prepareStatement("update BillInfo set count = " + countCurrent + " where idBill = " + idBill + " and idFood = " + idFood);
                if (preparedStatement.executeUpdate() == 1) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillInfoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
        return false;
    }
}
