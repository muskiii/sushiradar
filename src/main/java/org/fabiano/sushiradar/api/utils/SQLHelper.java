package org.fabiano.sushiradar.api.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;

public final class SQLHelper<T> {

	public static String getColumns(Class clazz) {
		return Arrays.stream(clazz.getDeclaredFields())
				.filter(f -> !f.isAnnotationPresent(Ignore.class) && !f.isAnnotationPresent(OneToNRealtion.class)  && !f.isAnnotationPresent(Id.class))
				.map(f -> {
				if(f.isAnnotationPresent(Column.class)) {
					return f.getDeclaredAnnotation(Column.class).name();
				}
					return f.getName();
				}).collect(Collectors.joining(","));
	}	

	public static String getValues(Class clazz, Object object) {
		return Arrays.stream(clazz.getDeclaredFields())
				.filter(f -> !f.isAnnotationPresent(Ignore.class) && !f.isAnnotationPresent(OneToNRealtion.class) && !f.isAnnotationPresent(Id.class))
				.map(f -> {
					try {
						f.setAccessible(true);
						if (f.get(object) instanceof String) {
							return "\'" + String.valueOf(f.get(object)) + "\'";
						}
						return String.valueOf(f.get(object));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						return "";
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						return "";
					}
				}).collect(Collectors.joining(","));
	}

	public static String getChildColumns(Class clazz, String fk) {
		String s = Arrays.stream(clazz.getDeclaredFields())
				.filter(f -> !f.isAnnotationPresent(Ignore.class) && !f.isAnnotationPresent(OneToNRealtion.class) && !f.isAnnotationPresent(Id.class))
				.map(f -> {
					if(f.isAnnotationPresent(Column.class)) {
						return f.getDeclaredAnnotation(Column.class).name();
					}
						return f.getName();
					}).collect(Collectors.joining(","));
		return s;
//		return s += "," + fk;
	}

	public static String getChildValues(Class clazz, Object object, long fk) {
		String s = Arrays.stream(clazz.getDeclaredFields())
				.filter(f -> !f.isAnnotationPresent(Ignore.class) && !f.isAnnotationPresent(OneToNRealtion.class) && !f.isAnnotationPresent(Id.class))
				.map(f -> {
					try {
						f.setAccessible(true);
						if (f.get(object) instanceof String) {
							return "\'" + String.valueOf(f.get(object)) + "\'";
						}
						return String.valueOf(f.get(object));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						return "";
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						return "";
					}
				}).collect(Collectors.joining(","));
//		return s += "," + fk;
		return s;
	}

	@SuppressWarnings("unchecked")
	public T mapRersultSetToObject(ResultSet rs, Class outputClass) {
		T bean = null;
		try {
			// make sure resultset is not null
			if (rs != null) {
				// check if outputClass has 'Entity' annotation
				if (outputClass.isAnnotationPresent(Entity.class)) {
					// get the resultset metadata
					ResultSetMetaData rsmd = rs.getMetaData();
					// get all the attributes of outputClass
					Field[] fields = outputClass.getDeclaredFields();
					while (rs.next()) {
						bean = (T) outputClass.newInstance();
						for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
							// getting the SQL column name
							String columnName = rsmd.getColumnName(_iterator + 1);
							// reading the value of the SQL column
							Object columnValue = rs.getObject(_iterator + 1);
							// iterating over outputClass attributes to check if any attribute has 'Column'
							// annotation with matching 'name' value
							for (Field field : fields) {
								if (field.isAnnotationPresent(Column.class)) {
									Column column = field.getAnnotation(Column.class);
									if (column.name().equalsIgnoreCase(columnName) && columnValue != null) {
										BeanUtils.setProperty(bean, field.getName(), columnValue);
										break;
									}
								}
							}
						}
					}
					if (bean == null) {
						return null;
					}
				} else {
					return null;
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return bean;
	}

	@SuppressWarnings("unchecked")
	public List<T> mapRersultSetToList(ResultSet rs, Class outputClass) {
		List<T> outputList = null;
		try {
			// make sure resultset is not null
			if (rs != null) {
				// check if outputClass has 'Entity' annotation
				if (outputClass.isAnnotationPresent(Entity.class)) {
					// get the resultset metadata
					ResultSetMetaData rsmd = rs.getMetaData();
					// get all the attributes of outputClass
					Field[] fields = outputClass.getDeclaredFields();
					while (rs.next()) {
						T bean = (T) outputClass.newInstance();
						for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
							// getting the SQL column name
							String columnName = rsmd.getColumnName(_iterator + 1);
							// reading the value of the SQL column
							Object columnValue = rs.getObject(_iterator + 1);
							// iterating over outputClass attributes to check if any attribute has 'Column'
							// annotation with matching 'name' value
							for (Field field : fields) {
								if (field.isAnnotationPresent(Column.class)) {
									Column column = field.getAnnotation(Column.class);
									if (column.name().equalsIgnoreCase(columnName) && columnValue != null) {
										BeanUtils.setProperty(bean, field.getName(), columnValue);
										break;
									}
								}
							}
						}
						if (outputList == null) {
							outputList = new ArrayList<T>();
						}
						outputList.add(bean);
					}

				} else {
					// throw some error
				}
			} else {
				return null;
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return outputList;
	}
}
