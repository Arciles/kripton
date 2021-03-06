package sqlite.foreignKey;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>BeanA_4</code>, based on interface <code>DaoBeanA_4</code>
 * </p>
 *
 *  @see BeanA_4
 *  @see DaoBeanA_4
 *  @see BeanA_4Table
 */
public class DaoBeanA_4Impl extends AbstractDao implements DaoBeanA_4 {
  public DaoBeanA_4Impl(BindDummy2DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, bean_a2_id, value_string FROM bean_a_4</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>bean_a2_id</dt><dd>is associated to bean's property <strong>beanA2Id</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * </dl>
   *
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_4> selectAll() {
    // build where condition
    String[] args={};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id, bean_a2_id, value_string FROM bean_a_4"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, bean_a2_id, value_string FROM bean_a_4", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<BeanA_4> resultList=new LinkedList<BeanA_4>();
    BeanA_4 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("bean_a2_id");
      int index2=cursor.getColumnIndex("value_string");

      do
       {
        resultBean=new BeanA_4();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        resultBean.beanA2Id=cursor.getLong(index1);
        if (!cursor.isNull(index2)) { resultBean.valueString=cursor.getString(index2); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, bean_a2_id, value_string FROM bean_a_4 WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>bean_a2_id</dt><dd>is associated to bean's property <strong>beanA2Id</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_4> selectById(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id, bean_a2_id, value_string FROM bean_a_4 WHERE id='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, bean_a2_id, value_string FROM bean_a_4 WHERE id=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<BeanA_4> resultList=new LinkedList<BeanA_4>();
    BeanA_4 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("bean_a2_id");
      int index2=cursor.getColumnIndex("value_string");

      do
       {
        resultBean=new BeanA_4();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        resultBean.beanA2Id=cursor.getLong(index1);
        if (!cursor.isNull(index2)) { resultBean.valueString=cursor.getString(index2); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id FROM bean_a_4 WHERE valueString=${dummy}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${dummy}</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>${dummy}</code>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_4> selectByString(String value) {
    // build where condition
    String[] args={(value==null?"":value)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id FROM bean_a_4 WHERE value_string='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id FROM bean_a_4 WHERE value_string=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<BeanA_4> resultList=new LinkedList<BeanA_4>();
    BeanA_4 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");

      do
       {
        resultBean=new BeanA_4();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean_a_4 (bean_a2_id, value_string) VALUES (${bean.beanA2Id}, ${bean.valueString})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>bean_a2_id</dt><dd>is mapped to <strong>${bean.beanA2Id}</strong></dd>
   * 	<dt>value_string</dt><dd>is mapped to <strong>${bean.valueString}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(BeanA_4 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("bean_a2_id", bean.beanA2Id);

    if (bean.valueString!=null) {
      contentValues.put("value_string", bean.valueString);
    } else {
      contentValues.putNull("value_string");
    }

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO bean_a_4 (bean_a2_id, value_string) VALUES ('"+StringUtils.checkSize(contentValues.get("bean_a2_id"))+"', '"+StringUtils.checkSize(contentValues.get("value_string"))+"')"));
    long result = database().insert("bean_a_4", null, contentValues);
    bean.id=result;

    return (int)result;
  }

  /**
   * <p>SQL Update:</p>
   * <pre>UPDATE bean_a_4 SET bean_a2_id=${bean.beanA2Id}, value_string=${bean.valueString} WHERE valueString=${bean.valueString}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>bean_a2_id</dt><dd>is mapped to <strong>${bean.beanA2Id}</strong></dd>
   * 	<dt>value_string</dt><dd>is mapped to <strong>${bean.valueString}</strong></dd>
   * </dl>
   *
   * <p><strong>Parameters used in where conditions:</strong></p>
   * <dl>
   * 	<dt>${bean.valueString}</dt><dd>is mapped to method's parameter <strong>bean.valueString</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public int update(BeanA_4 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("bean_a2_id", bean.beanA2Id);

    if (bean.valueString!=null) {
      contentValues.put("value_string", bean.valueString);
    } else {
      contentValues.putNull("value_string");
    }

    String[] whereConditionsArray={(bean.valueString==null?null:bean.valueString)};

    Logger.info(StringUtils.formatSQL("UPDATE bean_a_4 SET bean_a2_id='"+StringUtils.checkSize(contentValues.get("bean_a2_id"))+"', value_string='"+StringUtils.checkSize(contentValues.get("value_string"))+"' WHERE valueString='%s'"), (Object[]) whereConditionsArray);
    int result = database().update("bean_a_4", contentValues, "value_string=?", whereConditionsArray);
    return result;
  }
}
