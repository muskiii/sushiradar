package org.fabiano.sushiradar.api.dao;

import static java.lang.Math.toIntExact;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.fabiano.sushiradar.api.model.Forecast;
import org.fabiano.sushiradar.api.utils.FK;
import org.fabiano.sushiradar.api.utils.Id;
import org.fabiano.sushiradar.api.utils.OneToNRealtion;
import org.fabiano.sushiradar.api.utils.SQLHelper;
import org.postgresql.util.PSQLException;

public class PostgrePersistStrategy<T> extends PersistStrategy<T> {

	private final Class<T> type;
	private String mock = "[{\"id\":52,\"target\":\"something\",\"country\":\"Argentina\",\"city\":\"Buenos Aires\",\"latitude\":\"-34.61000061\",\"longitude\":\"-58.36999893\",\"extended\":[{\"forecastID\":52,\"day\":11,\"month\":6,\"year\":2018,\"yday\":161,\"hour\":19,\"monthName\":\"June\",\"weekday\":\"Monday\",\"ampm\":\"PM\",\"tzShort\":\"America/Argentina/Cordoba\",\"tzLong\":\"-03\",\"highT\":16.0,\"lowT\":9.0,\"aveWindKPH\":13.0,\"aveWindDir\":\"SSE\",\"aveWindDegrees\":153.0,\"precipAllDay\":\"2\",\"aveHumidity\":81.0,\"conditions\":\"Chance of Rain\",\"iconURL\":\"http://icons.wxug.com/i/c/k/chancerain.gif\"},{\"forecastID\":52,\"day\":12,\"month\":6,\"year\":2018,\"yday\":162,\"hour\":19,\"monthName\":\"June\",\"weekday\":\"Tuesday\",\"ampm\":\"PM\",\"tzShort\":\"America/Argentina/Cordoba\",\"tzLong\":\"-03\",\"highT\":11.0,\"lowT\":6.0,\"aveWindKPH\":27.0,\"aveWindDir\":\"SW\",\"aveWindDegrees\":215.0,\"precipAllDay\":\"7\",\"aveHumidity\":82.0,\"conditions\":\"Rain\",\"iconURL\":\"http://icons.wxug.com/i/c/k/rain.gif\"},{\"forecastID\":52,\"day\":13,\"month\":6,\"year\":2018,\"yday\":163,\"hour\":19,\"monthName\":\"June\",\"weekday\":\"Wednesday\",\"ampm\":\"PM\",\"tzShort\":\"America/Argentina/Cordoba\",\"tzLong\":\"-03\",\"highT\":11.0,\"lowT\":5.0,\"aveWindKPH\":18.0,\"aveWindDir\":\"WSW\",\"aveWindDegrees\":248.0,\"precipAllDay\":\"0\",\"aveHumidity\":70.0,\"conditions\":\"Mostly Cloudy\",\"iconURL\":\"http://icons.wxug.com/i/c/k/mostlycloudy.gif\"},{\"forecastID\":52,\"day\":14,\"month\":6,\"year\":2018,\"yday\":164,\"hour\":19,\"monthName\":\"June\",\"weekday\":\"Thursday\",\"ampm\":\"PM\",\"tzShort\":\"America/Argentina/Cordoba\",\"tzLong\":\"-03\",\"highT\":11.0,\"lowT\":4.0,\"aveWindKPH\":14.0,\"aveWindDir\":\"SW\",\"aveWindDegrees\":222.0,\"precipAllDay\":\"0\",\"aveHumidity\":69.0,\"conditions\":\"Partly Cloudy\",\"iconURL\":\"http://icons.wxug.com/i/c/k/partlycloudy.gif\"}]}]";

	public PostgrePersistStrategy(Class<T> type) {
		super();
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	public void insert(T t) throws SQLException{
		String insertStatement = "INSERT INTO  " + t.getClass().getSimpleName().toLowerCase() + " ("
				+ SQLHelper.getColumns(t.getClass()) + ") " + "VALUES ( " + SQLHelper.getValues(t.getClass(), t) + ");";
		System.out.println(insertStatement);

		Connection con = null;
		try {
			con = DatabaseConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			PreparedStatement statement = con.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("no rows affected.");
			}

			Long id = null;
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					id = generatedKeys.getLong(1);
				} else {
					throw new SQLException("no ID obtained.");
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}

			statement.close();

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

					String insertChildStatement = "";
					for (Object object : typedList) {

						Field fkField = Arrays.stream(object.getClass().getDeclaredFields())
								.filter(f -> f.isAnnotationPresent(FK.class)).findAny().get();
						fkField.setAccessible(true);
						fkField.set(object, toIntExact(id));

						insertChildStatement = "INSERT INTO " + clazz.getSimpleName().toLowerCase() + " ("
								+ SQLHelper.getChildColumns(clazz,
										field.getDeclaredAnnotation(OneToNRealtion.class).foreingKey())
								+ ") " + "VALUES ( " + SQLHelper.getChildValues(clazz, object, id) + ");";
						System.out.println(insertChildStatement);

						PreparedStatement statementChild = con.prepareStatement(insertChildStatement);
						statementChild.executeUpdate();
						statementChild.close();
					}
				}

			}
			con.commit();
		} catch (PSQLException e) {
			System.out.println(" duplicate key ");
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<T> lazyFindAll() {
		String selectTableSQL = "SELECT * from " + type.getSimpleName().toLowerCase();
		Connection con = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			con = DatabaseConnection.getInstance().getConnection();
			statement = con.createStatement();
			rs = statement.executeQuery(selectTableSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		List<T> entities = new SQLHelper<T>().mapRersultSetToList(rs, type);
		return entities;
	}

	public List<T> findAll() {
		String selectTableSQL = "SELECT * from " + type.getSimpleName().toLowerCase();
		Connection con = null;
		ResultSet rs = null;
		List<T> entities = null;
		try {
			con = DatabaseConnection.getInstance().getConnection();
			Statement statement = con.createStatement();
			rs = statement.executeQuery(selectTableSQL);
			entities = new SQLHelper<T>().mapRersultSetToList(rs, type);

			if (entities != null) {
				boolean hasRelations = Arrays.stream(entities.get(0).getClass().getDeclaredFields())
						.anyMatch(f -> f.isAnnotationPresent(OneToNRealtion.class));
				rs.close();

				if (hasRelations) {
					for (T t : entities) {

						Field relationField = Arrays.stream(t.getClass().getDeclaredFields())
								.filter(f -> f.isAnnotationPresent(OneToNRealtion.class)).findAny().get();
						Class<?> clazz = relationField.getDeclaredAnnotation(OneToNRealtion.class).clazz();

						Field fkField = Arrays.stream(clazz.getDeclaredFields())
								.filter(f -> f.isAnnotationPresent(FK.class)).findAny().get();
						String fk = fkField.getDeclaredAnnotation(FK.class).name();

						Field Idfield = Arrays.stream(t.getClass().getDeclaredFields())
								.filter(f -> f.isAnnotationPresent(Id.class)).findAny().get();
						Idfield.setAccessible(true);
						int id = Idfield.getInt(t);

						String selectChildTableSQL = "SELECT * from " + clazz.getSimpleName().toLowerCase() + " where "
								+ fk + " = " + id;
						System.out.println(selectChildTableSQL);

						Statement childStatement = con.createStatement();
						ResultSet childRS = childStatement.executeQuery(selectChildTableSQL);
						relationField.setAccessible(true);
						relationField.set(t, new SQLHelper<>().mapRersultSetToList(childRS, clazz));
						System.out.println(t);

						childRS.close();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return entities;
	}

	public T findById(String id) {
		String selectTableSQL = "SELECT * from " + type.getSimpleName().toLowerCase() + " where id = " + id;
		Connection con = null;
		T entities = null;
		ResultSet rs = null;

		try {
			con = DatabaseConnection.getInstance().getConnection();
			Statement statement = con.createStatement();
			rs = statement.executeQuery(selectTableSQL);
			entities = new SQLHelper<T>().mapRersultSetToObject(rs, type);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return entities;
	}

	public T findByFkId(String fk_field, String id) {
		String selectTableSQL = "SELECT * from " + type.getSimpleName().toLowerCase() + " where " + fk_field + " = "
				+ id;
		Connection con = null;
		T entities = null;
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getInstance().getConnection();
			Statement statement = con.createStatement();
			rs = statement.executeQuery(selectTableSQL);
			entities = new SQLHelper<T>().mapRersultSetToObject(rs, type);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return entities;
	}

	public void deleteAll() {

		Connection con = null;		
		try {			
			con = DatabaseConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			String selectTableSQL = "DELETE FROM " + type.getSimpleName().toLowerCase();

			List<Field> fs = Arrays.stream(type.getDeclaredFields())
					.filter(f -> f.isAnnotationPresent(OneToNRealtion.class)).collect(Collectors.toList());
			if (fs.size() > 0) {
				for (Field field : fs) {

					Class<?> clazz = field.getDeclaredAnnotation(OneToNRealtion.class).clazz();

						String deletChildStatement = "DELETE FROM " + clazz.getSimpleName().toLowerCase();
						System.out.println(deletChildStatement);

						Statement statementChild = con.createStatement();
						statementChild.executeUpdate(deletChildStatement);
						statementChild.close();

					}
				}
			
			Statement statement = con.createStatement();
			statement.executeUpdate(selectTableSQL);
			System.out.println(selectTableSQL);

			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<T> findWhere(String field, String value) {
		String whereStatement = "SELECT * FROM " + type.getSimpleName().toLowerCase() + " WHERE " + field + "=" + "\'" + value + "\'";
		System.out.println(whereStatement);
		Connection con = null;
		ResultSet rs = null;
		List<T> entities = null;
		try {
			con = DatabaseConnection.getInstance().getConnection();
			Statement statement = con.createStatement();
			rs = statement.executeQuery(whereStatement);
			entities = new SQLHelper<T>().mapRersultSetToList(rs, type);

			if (entities != null) {
				boolean hasRelations = Arrays.stream(entities.get(0).getClass().getDeclaredFields())
						.anyMatch(f -> f.isAnnotationPresent(OneToNRealtion.class));
				rs.close();

				if (hasRelations) {
					for (T t : entities) {

						Field relationField = Arrays.stream(t.getClass().getDeclaredFields())
								.filter(f -> f.isAnnotationPresent(OneToNRealtion.class)).findAny().get();
						Class<?> clazz = relationField.getDeclaredAnnotation(OneToNRealtion.class).clazz();

						Field fkField = Arrays.stream(clazz.getDeclaredFields())
								.filter(f -> f.isAnnotationPresent(FK.class)).findAny().get();
						String fk = fkField.getDeclaredAnnotation(FK.class).name();

						Field Idfield = Arrays.stream(t.getClass().getDeclaredFields())
								.filter(f -> f.isAnnotationPresent(Id.class)).findAny().get();
						Idfield.setAccessible(true);
						int id = Idfield.getInt(t);

						String selectChildTableSQL = "SELECT * from " + clazz.getSimpleName().toLowerCase() + " where "
								+ fk + " = " + id;
						System.out.println(selectChildTableSQL);

						Statement childStatement = con.createStatement();
						ResultSet childRS = childStatement.executeQuery(selectChildTableSQL);
						relationField.setAccessible(true);
						relationField.set(t, new SQLHelper<>().mapRersultSetToList(childRS, clazz));
						System.out.println(t);

						childRS.close();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return entities;
	}
}