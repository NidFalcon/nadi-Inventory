package com.cpi.is.validation;

import org.hibernate.Session;

public interface ForeignKeyValidate {
	    boolean isValidForeignKey(Session session, String tableName, String columnName, String value);
}
