package org.fabiano.sushiradar.api.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.fabiano.sushiradar.api.utils.OneToNRealtion;
import org.fabiano.sushiradar.api.utils.SQLHelper;

public class DAO<T> {

	public void insert(T t) {
		String insertStatement = "INSERT INTO  [sushi_radar_db].[dbo].[" + t.getClass().getSimpleName().toLowerCase() + "]"
				+ " (" + SQLHelper.getColumns(t.getClass()) + ") " + "VALUES ( \'"
				+ SQLHelper.getValues(t.getClass(),t) + "\');";
		System.out.println(insertStatement);
		
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			
//			con.createStatement().executeUpdate(insertStatement);
			
			List<Field> fs = Arrays.stream(t.getClass().getFields()).filter(f->f.getAnnotation(OneToNRealtion.class)!= null).collect(Collectors.toList());
			if (fs.size()>0) {
				for (Field field: fs) {
				Class clazz = field.getClass().getAnnotation(OneToNRealtion.class).clazz();
				
				List<Object> typedList = null;
				try {
					typedList = (List<Object>) field.get(t);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (Object f2 : typedList) {
					String insertChildStatement = "INSERT INTO  [sushi_radar_db].[dbo].[" + clazz.getSimpleName().toLowerCase() + "]"
							+ " (" + SQLHelper.getColumns(clazz) + ") " + "VALUES ( \'"
							+ SQLHelper.getValues(clazz,typedList) + "\');";
					System.out.println(insertChildStatement);
					}
				}
				
			}
//			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
