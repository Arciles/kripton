package com.abubusoft.kripton.processor.test04primary_key;

import android.database.Cursor;
import com.abubusoft.kripton.android.sqlite.AbstractBindDao;
import java.lang.Override;

/**
 * Generated by Kripton Library.
 *
 * @since Thu May 26 00:21:21 CEST 2016
 *
 */
public class BindDaoBean02 extends AbstractBindDao implements DaoBean02 {
  /**
   * <p>Select query is:</p>
   * <pre>select id, text from bean02 where id=${id}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, text]</pre>
   *
   * @param id
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean02 selectOne(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    Cursor cursor = database.rawQuery("select id, text from bean02 where id=?", args);

    Bean02 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("text");

      resultBean=new Bean02();

      if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setText(cursor.getString(index1)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean02 WHERE id=${id}</pre>
   *
   * @param id
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteOne(long id) {
    String[] whereConditions={String.valueOf(id)};

    int result = database.delete("bean02", "id=?", whereConditions);
    return result;
  }
}
