package org.fabiano.sushiradar.api.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class SQLHelper {

	public static String getColumns(Class clazz) {
		return Arrays.stream(clazz.getDeclaredFields())
				.filter(f->f.isAnnotationPresent(Ignore.class))
				.map(f->f.getName())
				.collect(Collectors.joining(","));
	}
	
	public static String getValues(Class clazz, Object object) {
		return Arrays.stream(clazz.getDeclaredFields())
				.filter(f->f.isAnnotationPresent(Ignore.class))
				.map(f->{
			try {
				f.setAccessible(true);
				return String.valueOf(f.get(object));
			} catch (IllegalArgumentException e) {					
				e.printStackTrace();
				return "";
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return "";
			}
		}).collect(Collectors.joining("\',\'"));
	}
	
	
}
