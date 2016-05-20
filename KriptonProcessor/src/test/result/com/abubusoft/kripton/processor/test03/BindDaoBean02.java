package com.abubusoft.kripton.processor.test03;

import com.abubusoft.kripton.android.sqlite.AbstractBindDao;
import java.lang.Override;
import java.lang.String;

/**
 * Generated by Kripton Library.
 *
 *  @since Thu May 19 15:36:10 CEST 2016
 *
 */
public class BindDaoBean02 extends AbstractBindDao implements DaoBean02 {
  /**
   *
   * ModelAnnotation [name=com.abubusoft.kripton.android.annotation.BindInsertBean, attributes={excludedFields={}, value={}}]
   */
  @Override
  public long insert(Bean01 bean) {
    contentValues.clear();

    contentValues.put("message_date", bean.getMessageDate());
    contentValues.put("message_text", bean.getMessageText());
    contentValues.put("value", bean.getValue());

    long result = database.insert("bean01", null, contentValues);
    bean.setId(result);

    return result;
  }

  /**
   *
   * ModelAnnotation [name=com.abubusoft.kripton.android.annotation.BindInsert, attributes={excludedFields={}, value={}}]
   */
  @Override
  public long insert(String value, long messageDate) {
    contentValues.clear();

    contentValues.put("value", value);
    contentValues.put("message_date", messageDate);

    long result = database.insert("bean01", null, contentValues);
    return result;
  }

  /**
   *
   * ModelAnnotation [name=com.abubusoft.kripton.android.annotation.BindDelete, attributes={where=id=${id}}]
   */
  @Override
  public long delete(long id) {
    String[] whereConditions={String.valueOf(id)};
    int result = database.delete("bean01", "id=?", whereConditions);
    return result;
  }

  /**
   *
   * ModelAnnotation [name=com.abubusoft.kripton.android.annotation.BindDeleteBean, attributes={where=id=${bean.id}}]
   */
  @Override
  public long delete(Bean01 bean) {

    String[] whereConditions={String.valueOf(bean.getId())};
    int result = database.delete("bean01", "id=?", whereConditions);
    return result;
  }

  /**
   *
   * ModelAnnotation [name=com.abubusoft.kripton.android.annotation.BindUpdate, attributes={where=id>${id}}]
   */
  @Override
  public long update(String value, long id) {
    contentValues.clear();

    contentValues.put("value", value);
    String[] whereConditions={String.valueOf(id)};
    int result = database.update("bean01", contentValues, "id>?", whereConditions);
    return result;
  }

  /**
   *
   * ModelAnnotation [name=com.abubusoft.kripton.android.annotation.BindUpdateBean, attributes={excludedFields={}, value={}, where=value=${bean.value}}]
   */
  @Override
  public long update(Bean01 bean) {
    contentValues.clear();

    contentValues.put("message_date", bean.getMessageDate());
    contentValues.put("message_text", bean.getMessageText());
    contentValues.put("value", bean.getValue());

    String[] whereConditions={String.valueOf(bean.getValue())};
    int result = database.update("bean01", contentValues, "value=?", whereConditions);
    return result;
  }
}