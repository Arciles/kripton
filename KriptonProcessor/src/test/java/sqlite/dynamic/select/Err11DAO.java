package sqlite.dynamic.select;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.dynamic.Person;

@BindDao(Person.class)
public interface Err11DAO {
	
	@BindSqlInsert
	void insertOne(String name, String surname, String birthCity, @BindSqlOrderBy Date birthDay);
	
	@BindSqlSelect(where="name like ${nameTemp} || '%' ")
	List<Person> selectOne(@BindSqlOrderBy String name, @BindSqlParam("nameTemp") String nameValue);
}