package controller;

import java.awt.List;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Models.Connect;
import Models.FoodCategory;

public class FoodCategoryController {
	@SuppressWarnings("null")
	public static ArrayList<FoodCategory> getListCategory() throws SQLException {
		ArrayList<FoodCategory> arrayList = new ArrayList<FoodCategory>();
		Statement statement = Connect.getConnection().createStatement();
		String sql = "select * from FoodCategory";
		ResultSet resultSet = statement.executeQuery(sql);
		String idCategory;
		String nameCategory;
		try {
			while (resultSet.next()) {
				idCategory = resultSet.getString(1);
				nameCategory = resultSet.getString(2);
				FoodCategory foodCategory = new FoodCategory(idCategory, nameCategory);
				arrayList.add(foodCategory);
			};
		} finally {
			Connect.close();
		}
		return arrayList;
		
	}
}
