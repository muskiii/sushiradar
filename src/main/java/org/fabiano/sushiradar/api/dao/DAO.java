package org.fabiano.sushiradar.api.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.fabiano.sushiradar.api.utils.SQLHelper;

public class DAO<T> {

	public void insert(T t) {
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			con.createStatement()
					.executeUpdate("INSERT INTO  [sushi_radar_db].[dbo].[" + t.getClass().getSimpleName().toLowerCase() + "]"
							+ " (" + SQLHelper.getColumns(t.getClass()) + ") " + "VALUES ( \'"
							+ SQLHelper.getValues(t.getClass(),t) + "\');");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
