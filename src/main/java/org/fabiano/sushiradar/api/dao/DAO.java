package org.fabiano.sushiradar.api.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
				+ " (" + SQLHelper.getColumns(t.getClass()) + ") " + "VALUES ( "
				+ SQLHelper.getValues(t.getClass(),t) + ");";
		System.out.println(insertStatement);
		Long id = null;
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			PreparedStatement statement = con.prepareStatement(insertStatement,
                 Statement.RETURN_GENERATED_KEYS);
			
			int affectedRows = statement.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	id = generatedKeys.getLong(1);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
	        String insertChildStatement = null;
			List<Field> fs = Arrays.stream(t.getClass().getDeclaredFields())
					.filter(f->f.isAnnotationPresent(OneToNRealtion.class))
					.collect(Collectors.toList());
			if (fs.size()>0) {
				for (Field field: fs) {
				Class<?> clazz = field.getDeclaredAnnotation(OneToNRealtion.class).clazz();				
				List<Object> typedList = null;
				field.setAccessible(true);
				
				try {
					typedList = (List<Object>) field.get(t);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				
				for (Object object : typedList) {					
					insertChildStatement = "INSERT INTO  [sushi_radar_db].[dbo].[" + clazz.getSimpleName().toLowerCase() + "]"
							+ " (" + SQLHelper.getChildColumns(clazz,field.getDeclaredAnnotation(OneToNRealtion.class).foreingKey()) + ") " + "VALUES ( "
							+ SQLHelper.getChildValues(clazz, object, id) + ");";
					System.out.println(insertChildStatement);
					PreparedStatement statementChild = con.prepareStatement(insertChildStatement,
			                 Statement.RETURN_GENERATED_KEYS);
					statementChild.executeUpdate();
					}
				}
				
			}
			con.commit();
			con.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
