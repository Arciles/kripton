package sqlite.kripton58.array2;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>ByteBean</code>, based on interface <code>ByteDao</code>
 * </p>
 *
 *  @see ByteBean
 *  @see ByteDao
 *  @see ByteBeanTable
 */
public class ByteDaoImpl extends AbstractDao implements ByteDao {
  public ByteDaoImpl(BindByteDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, value, value2 FROM byte_bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public ByteBean selectOne() {
    // build where condition
    String[] args={};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id, value, value2 FROM byte_bean"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM byte_bean", args);
    Logger.info("Rows found: %s",cursor.getCount());

    ByteBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new ByteBean();

      if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setValue(ByteBeanTable.parseValue(cursor.getBlob(index1))); }
      if (!cursor.isNull(index2)) { resultBean.setValue2(ByteBeanTable.parseValue2(cursor.getBlob(index2))); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, value, value2 FROM byte_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is binded to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>${value}</code>
   * @param value2
   * 	is binded to <code>${value2}</code>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public ByteBean selectOne(byte[] value, Byte[] value2) {
    // build where condition
    String[] args={(value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)), (value2==null?"":new String(serializer2(value2),StandardCharsets.UTF_8))};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id, value, value2 FROM byte_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM byte_bean WHERE value=? and value2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    ByteBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      resultBean=new ByteBean();

      if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setValue(ByteBeanTable.parseValue(cursor.getBlob(index1))); }
      if (!cursor.isNull(index2)) { resultBean.setValue2(ByteBeanTable.parseValue2(cursor.getBlob(index2))); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, value, value2 FROM byte_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is binded to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>${value}</code>
   * @param value2
   * 	is binded to <code>${value2}</code>
   * @param listener
   * 	is the ByteBean listener
   */
  @Override
  public void selectOne(byte[] value, Byte[] value2, OnReadBeanListener<ByteBean> listener) {
    // build where condition
    String[] args={(value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)), (value2==null?"":new String(serializer2(value2),StandardCharsets.UTF_8))};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id, value, value2 FROM byte_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM byte_bean WHERE value=? and value2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    ByteBean resultBean=new ByteBean();
    try {
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value");
        int index2=cursor.getColumnIndex("value2");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.setId(0L);
          resultBean.setValue(null);
          resultBean.setValue2(null);

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
          if (!cursor.isNull(index1)) { resultBean.setValue(ByteBeanTable.parseValue(cursor.getBlob(index1))); }
          if (!cursor.isNull(index2)) { resultBean.setValue2(ByteBeanTable.parseValue2(cursor.getBlob(index2))); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    } finally {
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, value, value2 FROM byte_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>no bean's property is associated</dd>
   * 	<dt>value</dt><dd>no bean's property is associated</dd>
   * 	<dt>value2</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is binded to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>${value}</code>
   * @param value2
   * 	is binded to <code>${value2}</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectOne(byte[] value, Byte[] value2, OnReadCursorListener listener) {
    // build where condition
    String[] args={(value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)), (value2==null?"":new String(serializer2(value2),StandardCharsets.UTF_8))};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id, value, value2 FROM byte_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM byte_bean WHERE value=? and value2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    try {
      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    } finally {
      if (!cursor.isClosed()) {
        cursor.close();
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, value, value2 FROM byte_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is associated to bean's property <strong>value2</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is binded to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>${value}</code>
   * @param value2
   * 	is binded to <code>${value2}</code>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<ByteBean> selectList(byte[] value, Byte[] value2) {
    // build where condition
    String[] args={(value==null?"":new String(serializer1(value),StandardCharsets.UTF_8)), (value2==null?"":new String(serializer2(value2),StandardCharsets.UTF_8))};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id, value, value2 FROM byte_bean WHERE value='%s' and value2='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value, value2 FROM byte_bean WHERE value=? and value2=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<ByteBean> resultList=new LinkedList<ByteBean>();
    ByteBean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value");
      int index2=cursor.getColumnIndex("value2");

      do
       {
        resultBean=new ByteBean();

        if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
        if (!cursor.isNull(index1)) { resultBean.setValue(ByteBeanTable.parseValue(cursor.getBlob(index1))); }
        if (!cursor.isNull(index2)) { resultBean.setValue2(ByteBeanTable.parseValue2(cursor.getBlob(index2))); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>SQL update:</p>
   * <pre>UPDATE byte_bean SET id=${id} WHERE value=${value} and value2=${value2}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${value}</dt><dd>is mapped to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is mapped to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param value
   * 	is used as where parameter <strong>${value}</strong>
   * @param value2
   * 	is used as where parameter <strong>${value2}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, byte[] value, Byte[] value2) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(value==null?null:new String(serializer1(value),StandardCharsets.UTF_8)), (value2==null?null:new String(serializer2(value2),StandardCharsets.UTF_8))};

    Logger.info(StringUtils.formatSQL("UPDATE byte_bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE value=%s and value2=%s"), (Object[])whereConditionsArray);
    int result = database().update("byte_bean", contentValues, "value=? and value2=?", whereConditionsArray);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO byte_bean (id, value, value2) VALUES (${id}, ${value}, ${value2})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>${value}</strong> and method's parameter <strong>value</strong></dd>
   * 	<dt>value2</dt><dd>is binded to query's parameter <strong>${value2}</strong> and method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to column <strong>id</strong>
   * @param value
   * 	is binded to column <strong>value</strong>
   * @param value2
   * 	is binded to column <strong>value2</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(long id, byte[] value, Byte[] value2) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("id", id);

    if (value!=null) {
      contentValues.put("value", serializer1(value));
    } else {
      contentValues.putNull("value");
    }

    if (value2!=null) {
      contentValues.put("value2", serializer2(value2));
    } else {
      contentValues.putNull("value2");
    }

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO byte_bean (id, value, value2) VALUES ('"+StringUtils.checkSize(contentValues.get("id"))+"', '"+StringUtils.checkSize(contentValues.get("value"))+"', '"+StringUtils.checkSize(contentValues.get("value2"))+"')"));
    long result = database().insert("byte_bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO byte_bean (value, value2) VALUES (${bean.value}, ${bean.value2})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>${bean.value}</strong></dd>
   * 	<dt>value2</dt><dd>is mapped to <strong>${bean.value2}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(ByteBean bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.getValue()!=null) {
      contentValues.put("value", ByteBeanTable.serializeValue(bean.getValue()));
    } else {
      contentValues.putNull("value");
    }

    if (bean.getValue2()!=null) {
      contentValues.put("value2", ByteBeanTable.serializeValue2(bean.getValue2()));
    } else {
      contentValues.putNull("value2");
    }

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO byte_bean (value, value2) VALUES ('"+StringUtils.checkSize(contentValues.get("value"))+"', '"+StringUtils.checkSize(contentValues.get("value2"))+"')"));
    long result = database().insert("byte_bean", null, contentValues);
    bean.setId(result);

    return result;
  }

  /**
   * <p>SQL delete:</p>
   * <pre>DELETE byte_bean WHERE value=${value} and value2=${value2}</pre>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${value}</dt><dd>is mapped to method's parameter <strong>value</strong></dd>
   * 	<dt>${value2}</dt><dd>is mapped to method's parameter <strong>value2</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as where parameter <strong>${value}</strong>
   * @param value2
   * 	is used as where parameter <strong>${value2}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(byte[] value, Byte[] value2) {
    String[] whereConditionsArray={(value==null?null:new String(serializer1(value),StandardCharsets.UTF_8)), (value2==null?null:new String(serializer2(value2),StandardCharsets.UTF_8))};

    Logger.info(StringUtils.formatSQL("DELETE byte_bean WHERE value=%s and value2=%s"), (Object[])whereConditionsArray);
    int result = database().delete("byte_bean", "value=? and value2=?", whereConditionsArray);
    return result;
  }

  /**
   * write
   */
  private byte[] serializer2(Byte[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        int n=value.length;
        Byte item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  private Byte[] parser2(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Byte[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Byte> collection=new ArrayList<>();
        Byte item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getByteValue();
          }
          collection.add(item);
        }
        result=CollectionUtils.asByteArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  private byte[] serializer1(byte[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        jacksonSerializer.writeBinaryField("element", value);
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  private byte[] parser1(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      byte[] result=null;
      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
        result=jacksonParser.getBinaryValue();
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}
