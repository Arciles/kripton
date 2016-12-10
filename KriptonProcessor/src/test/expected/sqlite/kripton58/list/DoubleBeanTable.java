package sqlite.kripton58.list;

import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Double;
import java.lang.Exception;
import java.lang.String;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Entity <code>DoubleBean</code> is associated to table <code>double_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see DoubleBean
 */
public class DoubleBeanTable {
  /**
   * Costant represents name of table double_bean
   */
  public static final String TABLE_NAME = "double_bean";

  /**
   * <p>
   * DDL to create table double_bean
   * </p>
   *
   * <pre>CREATE TABLE double_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE double_bean (id INTEGER PRIMARY KEY AUTOINCREMENT, value BLOB, value2 BLOB);";

  /**
   * <p>
   * DDL to drop table double_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS double_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS double_bean;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see DoubleBean#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>value</code> is associated to table column <code>value</code>. This costant represents column name.
   *
   *  @see DoubleBean#value
   */
  public static final String COLUMN_VALUE = "value";

  /**
   * Entity's property <code>value2</code> is associated to table column <code>value2</code>. This costant represents column name.
   *
   *  @see DoubleBean#value2
   */
  public static final String COLUMN_VALUE2 = "value2";

  /**
   * write
   */
  public static byte[] serializeValue(List<Double> value) {
    if (value==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        Double item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
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
      return stream.getByteBuffer();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static List<Double> parseValue(byte[] input) {
    if (input==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      List<Double> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Double> collection=new ArrayList<>();
        Double item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getDoubleValue();
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  public static byte[] serializeValue2(LinkedList<Double> value) {
    if (value==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        Double item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
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
      return stream.getByteBuffer();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static LinkedList<Double> parseValue2(byte[] input) {
    if (input==null) {
      return null;
    }
    JacksonContext context=KriptonBinder2.getJsonBinderContext();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      LinkedList<Double> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        LinkedList<Double> collection=new LinkedList<>();
        Double item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getDoubleValue();
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}
