package org.fabiano.sushiradar.api.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class SQLHelper {

	public static String getColumns(Class clazz) {
		return Arrays.stream(clazz.getDeclaredFields())
				.filter(f->!f.isAnnotationPresent(Ignore.class) && !f.isAnnotationPresent(OneToNRealtion.class))
				.map(f->f.getName())
				.collect(Collectors.joining(","));
	}
	
	public static String getValues(Class clazz, Object object) {
		return Arrays.stream(clazz.getDeclaredFields())
				.filter(f->!f.isAnnotationPresent(Ignore.class) && !f.isAnnotationPresent(OneToNRealtion.class))
				.map(f->{
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
				.filter(f->!f.isAnnotationPresent(Ignore.class) && !f.isAnnotationPresent(OneToNRealtion.class))
				.map(f->f.getName())
				.collect(Collectors.joining(","));
		return s += "," + fk;
	}
	
	public static String getChildValues(Class clazz, Object object, long fk) {
		String s =  Arrays.stream(clazz.getDeclaredFields())
				.filter(f->!f.isAnnotationPresent(Ignore.class) && !f.isAnnotationPresent(OneToNRealtion.class))
				.map(f->{
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
		return s += "," + fk;
	}
	
}
