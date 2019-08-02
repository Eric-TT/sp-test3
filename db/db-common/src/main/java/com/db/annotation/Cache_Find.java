package com.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.db.enums.TYPE_ENUM;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache_Find {
	String value() default "";

	TYPE_ENUM type() default TYPE_ENUM.AUTO;

	int secondes() default 0;
}
