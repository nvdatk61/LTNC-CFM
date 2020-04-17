package controller;

import Models.Account;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Models.Connect;

public class LoginController {
	public static Account checkAccount(String txtAccount, String txtPassword) throws SQLException {
                Statement statement = Connect.getConnection().createStatement();
		String sql = "select * from account where UserName = \'" + txtAccount + "\' and PassWord = \'" + txtPassword + "\'";
		ResultSet resultSet = statement.executeQuery(sql);
		try {
			if (resultSet.next()){
                            return(new Account(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
                        };
		} finally {
			Connect.close();
		}
		return null;
	}
}
