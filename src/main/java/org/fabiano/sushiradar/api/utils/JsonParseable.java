package org.fabiano.sushiradar.api.utils;

public interface JsonParseable<T> {

	public T fromJson(String json);
}
