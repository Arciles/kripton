package com.abubusoft.kripton.android.adapter;

import java.util.Currency;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a java.util.Currency object
 * 
 * @author bulldog
 *
 */
class CurrencyAdapter implements SqliteAdapter<Currency> {


	@Override
	public Currency readCursor(Cursor cursor, int columnIndex) throws Exception {
		String value=cursor.getString(columnIndex);
		if (value==null) return null;
		return Currency.getInstance(value);
	}

	@Override
	public void writeValue(Currency value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value.toString());
	}

}
