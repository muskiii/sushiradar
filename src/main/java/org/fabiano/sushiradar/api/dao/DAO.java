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

import org.fabiano.sushiradar.api.model.Column;
import org.fabiano.sushiradar.api.model.FK;
import org.fabiano.sushiradar.api.model.Id;
import org.fabiano.sushiradar.api.utils.OneToNRealtion;
import org.fabiano.sushiradar.api.utils.SQLHelper;

public class DAO<T> {

	private final Class<T> type;

	public DAO(Class<T> type) {
		super();
		this.type = type;
	}

	public void insert(T t) {
		String insertStatement = "INSERT INTO  [sushi_radar_db].[dbo].[" + t.getClass().getSimpleName().toLowerCase()
				+ "]" + " (" + SQLHelper.getColumns(t.getClass()) + ") " + "VALUES ( "
				+ SQLHelper.getValues(t.getClass(), t) + ");";
		System.out.println(insertStatement);
		Long id = null;
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			PreparedStatement statement = con.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					id = generatedKeys.getLong(1);
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}
			String insertChildStatement = null;
			List<Field> fs = Arrays.stream(t.getClass().getDeclaredFields())
					.filter(f -> f.isAnnotationPresent(OneToNRealtion.class)).collect(Collectors.toList());
			if (fs.size() > 0) {
				for (Field field : fs) {
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
						insertChildStatement = "INSERT INTO  [sushi_radar_db].[dbo].["
								+ clazz.getSimpleName().toLowerCase() + "]" + " ("
								+ SQLHelper.getChildColumns(clazz,
										field.getDeclaredAnnotation(OneToNRealtion.class).foreingKey())
								+ ") " + "VALUES ( " + SQLHelper.getChildValues(clazz, object, id) + ");";
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

	public List<T> lazyFindAll() {
		String selectTableSQL = "SELECT * from " + type.getSimpleName().toLowerCase();
		Connection con = null;
		List<T> entities = null;
		try {
			con = DatabaseConnection.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);
			entities = new SQLHelper<T>().mapRersultSetToList(rs, type);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entities;
	}

	public List<T> findAll() {
		String selectTableSQL = "SELECT * from " + type.getSimpleName().toLowerCase();
		Connection con = null;
		List<T> entities = null;
		try {
			con = DatabaseConnection.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);
			entities = new SQLHelper<T>().mapRersultSetToList(rs, type);
			boolean hasRelations = Arrays.stream(entities.get(0).getClass().getDeclaredFields())
					.anyMatch(f -> f.isAnnotationPresent(OneToNRealtion.class));
			rs.close();
			if (hasRelations) {
				for (T t : entities) {
					Field relationField = Arrays.stream(t.getClass().getDeclaredFields())
							.filter(f -> f.isAnnotationPresent(OneToNRealtion.class)).findAny().get();
					Class clazz = relationField.getDeclaredAnnotation(OneToNRealtion.class).clazz();
					Field fkField = Arrays.stream(clazz.getDeclaredFields())
							.filter(f -> f.isAnnotationPresent(FK.class)).findAny().get();
					String fk = fkField.getDeclaredAnnotation(FK.class).name();
					Field Idfield = Arrays.stream(t.getClass().getDeclaredFields())
							.filter(f -> f.isAnnotationPresent(Id.class)).findAny().get();
					Idfield.setAccessible(true);
					int id = Idfield.getInt(t);

					String selectChildTableSQL = "SELECT * from " + clazz.getSimpleName().toLowerCase() + " where " + fk
							+ " = " + id;
					System.out.println(selectChildTableSQL);
					Statement childStatement = con.createStatement();
					ResultSet childRS = childStatement.executeQuery(selectChildTableSQL);
					relationField.setAccessible(true);
					relationField.set(t, new SQLHelper<>().mapRersultSetToList(childRS, clazz));
					System.out.println(t);
					childRS.close();

				}

			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entities;
	}

	public T findById(String id) {
		String selectTableSQL = "SELECT * from " + type.getSimpleName().toLowerCase() + " where id = " + id;
		Connection con = null;
		T entities = null;
		try {
			con = DatabaseConnection.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);
			entities = new SQLHelper<T>().mapRersultSetToObject(rs, type);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entities;

	}

	public T findByFkId(String fk_field, String id) {
		String selectTableSQL = "SELECT * from " + type.getSimpleName().toLowerCase() + " where " + fk_field + " = "
				+ id;
		Connection con = null;
		T entities = null;
		try {
			con = DatabaseConnection.getInstance().getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(selectTableSQL);
			entities = new SQLHelper<T>().mapRersultSetToObject(rs, type);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entities;

	}

}
