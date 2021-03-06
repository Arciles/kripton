package sqlite.quickstart.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;
import sqlite.quickstart.model.Comment;

/**
 * <p>
 * DAO implementation for entity <code>Comment</code>, based on interface <code>CommentDao</code>
 * </p>
 *
 *  @see Comment
 *  @see CommentDao
 *  @see sqlite.quickstart.model.CommentTable
 */
public class CommentDaoImpl extends AbstractDao implements CommentDao {
  public CommentDaoImpl(BindQuickStartDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO comment (post_id, id, name, email, body) VALUES (${bean.postId}, ${bean.id}, ${bean.name}, ${bean.email}, ${bean.body})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>post_id</dt><dd>is mapped to <strong>${bean.postId}</strong></dd>
   * 	<dt>id</dt><dd>is mapped to <strong>${bean.id}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>email</dt><dd>is mapped to <strong>${bean.email}</strong></dd>
   * 	<dt>body</dt><dd>is mapped to <strong>${bean.body}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   *
   */
  @Override
  public void insert(Comment bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("post_id", bean.postId);

    contentValues.put("id", bean.id);

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }

    if (bean.email!=null) {
      contentValues.put("email", bean.email);
    } else {
      contentValues.putNull("email");
    }

    if (bean.body!=null) {
      contentValues.put("body", bean.body);
    } else {
      contentValues.putNull("body");
    }

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO comment (post_id, id, name, email, body) VALUES ('"+StringUtils.checkSize(contentValues.get("post_id"))+"', '"+StringUtils.checkSize(contentValues.get("id"))+"', '"+StringUtils.checkSize(contentValues.get("name"))+"', '"+StringUtils.checkSize(contentValues.get("email"))+"', '"+StringUtils.checkSize(contentValues.get("body"))+"')"));
    long result = database().insert("comment", null, contentValues);
    bean.id=result;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT post_id, id, name, email, body FROM comment WHERE postId = ${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>post_id</dt><dd>is associated to bean's property <strong>postId</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>email</dt><dd>is associated to bean's property <strong>email</strong></dd>
   * 	<dt>body</dt><dd>is associated to bean's property <strong>body</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>postId</strong></dd>
   * </dl>
   *
   * @param postId
   * 	is binded to <code>${value}</code>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Comment> selectByPostId(long postId) {
    // build where condition
    String[] args={String.valueOf(postId)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT post_id, id, name, email, body FROM comment WHERE post_id = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT post_id, id, name, email, body FROM comment WHERE post_id = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Comment> resultList=new LinkedList<Comment>();
    Comment resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("post_id");
      int index1=cursor.getColumnIndex("id");
      int index2=cursor.getColumnIndex("name");
      int index3=cursor.getColumnIndex("email");
      int index4=cursor.getColumnIndex("body");

      do
       {
        resultBean=new Comment();

        if (!cursor.isNull(index0)) { resultBean.postId=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.id=cursor.getLong(index1); }
        if (!cursor.isNull(index2)) { resultBean.name=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.email=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.body=cursor.getString(index4); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT post_id, id, name, email, body FROM comment WHERE id = ${value}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>post_id</dt><dd>is associated to bean's property <strong>postId</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>email</dt><dd>is associated to bean's property <strong>email</strong></dd>
   * 	<dt>body</dt><dd>is associated to bean's property <strong>body</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${value}</dt><dd>is binded to method's parameter <strong>postId</strong></dd>
   * </dl>
   *
   * @param postId
   * 	is binded to <code>${value}</code>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Comment selectOneByPostId(long postId) {
    // build where condition
    String[] args={String.valueOf(postId)};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT post_id, id, name, email, body FROM comment WHERE id = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT post_id, id, name, email, body FROM comment WHERE id = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Comment resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("post_id");
      int index1=cursor.getColumnIndex("id");
      int index2=cursor.getColumnIndex("name");
      int index3=cursor.getColumnIndex("email");
      int index4=cursor.getColumnIndex("body");

      resultBean=new Comment();

      if (!cursor.isNull(index0)) { resultBean.postId=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.id=cursor.getLong(index1); }
      if (!cursor.isNull(index2)) { resultBean.name=cursor.getString(index2); }
      if (!cursor.isNull(index3)) { resultBean.email=cursor.getString(index3); }
      if (!cursor.isNull(index4)) { resultBean.body=cursor.getString(index4); }

    }
    cursor.close();

    return resultBean;
  }
}
