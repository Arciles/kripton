package com.abubusoft.kripton.android;

import com.abubusoft.kripton.android.sqlite.SQLiteHandler;
import com.abubusoft.kripton.binder.database.DatabaseHandler;
import com.abubusoft.kripton.exception.MappingException;

public enum DatabaseType {
	SQLITE(SQLiteHandler.class);
	
	public DatabaseHandler handler;

	private DatabaseType(Class<? extends DatabaseHandler> handlerClazz) 
	{
		try {
			handler=handlerClazz.newInstance();
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}
}