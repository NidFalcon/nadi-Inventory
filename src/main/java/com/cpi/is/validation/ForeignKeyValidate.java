package com.cpi.is.validation;

import org.hibernate.Session;

public interface ForeignKeyValidate {
	    boolean isValidForeignKey(String foreignKey);
}
