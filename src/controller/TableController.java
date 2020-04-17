/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Models.Connect;
import Models.TableFood;
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
public class TableController {
    public static ArrayList<TableFood> getListTable(){
        ArrayList<TableFood> listTable = null;
        try {
            listTable = new ArrayList<TableFood>();
            Statement statement = Connect.getConnection().createStatement();
            String sql = "select * from TableFood";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {                
                int idTable = resultSet.getInt(1);
                String nameTable = resultSet.getString(2);
                int statusTable = resultSet.getInt(3);
                listTable.add(new TableFood(idTable,nameTable,statusTable));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            Connect.close();
        }
        return listTable;
    }

    public static void setTableEmpty(int idTable) {
        try {
            PreparedStatement preparedStatement = Connect.getConnection()
                    .prepareStatement("update TableFood set status = 0 where id = " + idTable);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
    }

    public static void setTableFull(int idTable) {
        try {
            PreparedStatement preparedStatement = Connect.getConnection()
                    .prepareStatement("update TableFood set status = 1 where id = " + idTable);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
    }
}
