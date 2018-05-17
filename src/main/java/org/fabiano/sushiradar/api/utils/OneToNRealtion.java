package org.fabiano.sushiradar.api.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface OneToNRealtion {
	String foreingKey() ;
	Class<?> clazz();
}
