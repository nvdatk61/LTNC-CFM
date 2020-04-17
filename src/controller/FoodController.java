package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Models.Connect;
import Models.Food;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class FoodController {

    public int AddFood(String FoodName, String NameCategory, String Price) {
        try {
            if (FoodName.equals("") || Price.equals("")) {
                return 0;
            } else {
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
                PreparedStatement prestatement = Connect.getConnection().prepareStatement("insert dbo.Food (name, idCategory, price) values(?,?,?)");
                prestatement.setString(1, FoodName);
                prestatement.setString(2, IDCategory);
                prestatement.setString(3, Price);
                int check = prestatement.executeUpdate();
                return check;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return 0;
        } finally {
            Connect.close();
        }
    }

    public void EditFood(String Id, String FoodName, String NameCategory, String Price) {
        try {
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
            PreparedStatement prestatement = Connect.getConnection().prepareStatement("update Food set name=?,idCategory=?,price=? where Id=?");
            prestatement.setString(4, Id);
            prestatement.setString(1, FoodName);
            prestatement.setString(2, IDCategory);
            prestatement.setString(3, Price);
            prestatement.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            Connect.close();
        }
    }

    public void DeleteFood(String Id) {
        try {
            PreparedStatement prestatement = Connect.getConnection().prepareStatement("Delete FROM Food where id=?");
            prestatement.setString(1, Id);
            prestatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            Connect.close();
        }
    }

    public void loadComobox(JComboBox cbCategory) {
        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement("Select name from FoodCategory ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cbCategory.addItem(rs.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            Connect.close();
        }
    }

    public void loadData(DefaultTableModel tbnFood, JTable tbFood, JTextField txtIdFood, JTextField tfUnitFood, JTextField tfNameFood, JComboBox cbCategory) {
        try {
            int number;
            Vector row, column;
            column = new Vector();
            Statement statement = Connect.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT Food.id, Food.name,food.price,FoodCategory.name FROM Food, FoodCategory WHERE Food.idCategory = FoodCategory.id;");
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();

            column.add("Mã món");
            column.add("Tên món");
            column.add("Đơn giá");
            column.add("Loại món");
            
            tbnFood.setColumnIdentifiers(column);

            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.addElement(rs.getString(i));
                }
                tbnFood.addRow(row);
                tbFood.setModel(tbnFood);
            }

            tbFood.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (tbFood.getSelectedRow() >= 0) {
                        txtIdFood.setText(tbFood.getValueAt(tbFood.getSelectedRow(), 0) + "");
                        tfNameFood.setText(tbFood.getValueAt(tbFood.getSelectedRow(), 1) + "");
                        tfUnitFood.setText(tbFood.getValueAt(tbFood.getSelectedRow(), 2) + "");
                        cbCategory.setSelectedItem(tbFood.getModel().getValueAt(tbFood.getSelectedRow(), 3) + "");
                    }
                }
            });
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            Connect.close();
        }
    }

    public static ArrayList<Food> getListFoodByIdCategory(int idCategory) throws SQLException {
        ArrayList<Food> arrayList = new ArrayList<Food>();
        Statement statement = Connect.getConnection().createStatement();
        String sql = "select * from Food where idCategory = " + idCategory;
        ResultSet resultSet = statement.executeQuery(sql);
        try {
            while (resultSet.next()) {
                int idFood = resultSet.getInt(1);
                String foodName = resultSet.getString(2);
                int price = resultSet.getInt(4);
                Food food = new Food(idFood, foodName, idCategory, price);
                arrayList.add(food);
            };
        } finally {
            Connect.close();
        }
        return arrayList;
    }

    public static ArrayList<Food> getNameAndPriceFoodByIdFood(int idFood) {
        ArrayList<Food> arrayList = new ArrayList<Food>();
        try {
            arrayList = new ArrayList<Food>();
            Statement statement = Connect.getConnection().createStatement();
            String sql = "select name, price from Food where id = " + idFood;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString(1);
                int price = resultSet.getInt(2);
                arrayList.add(new Food(idFood, name, 0, price));
            };
        } catch (SQLException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close();
        }
        return arrayList;
    }
}
