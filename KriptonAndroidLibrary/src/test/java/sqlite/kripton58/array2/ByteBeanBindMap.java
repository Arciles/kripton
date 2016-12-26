package sqlite.kripton58.array2;

import com.abubusoft.kripton.AbstractJacksonContext;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.KriptonXmlContext;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.XmlAttributeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.persistence.XmlSerializer;
import com.abubusoft.kripton.persistence.XmlWrapperParser;
import com.abubusoft.kripton.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.persistence.xml.internal.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Byte;
import java.lang.Exception;
import java.lang.Override;
import java.util.ArrayList;

/**
 * This class is the shared preference binder defined for ByteBean
 *
 * @see ByteBean
 */
@BindMap(ByteBean.class)
public class ByteBeanBindMap extends AbstractMapper<ByteBean> {
  /**
   * create new object instance
   */
  @Override
  public ByteBean createInstance() {
    return new ByteBean();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, ByteBean object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field id (mapped with "id")
      fieldCount++;
      jacksonSerializer.writeNumberField("id", object.getId());

      // field value (mapped with "value")
      if (object.getValue()!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("value", object.getValue());
      }

      // field value2 (mapped with "value2")
      if (object.getValue2()!=null)  {
        fieldCount++;
        int n=object.getValue2().length;
        Byte item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("value2");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValue2()[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      jacksonSerializer.writeEndObject();
      return fieldCount;
    } catch(Exception e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJacksonAsString(AbstractJacksonContext context, ByteBean object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field id (mapped with "id")
      jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.getId()));

      // field value (mapped with "value")
      if (object.getValue()!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("value", object.getValue());
      }

      // field value2 (mapped with "value2")
      if (object.getValue2()!=null)  {
        fieldCount++;
        int n=object.getValue2().length;
        Byte item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("value2");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (int i=0; i<n; i++) {
            item=object.getValue2()[i];
            if (item==null) {
              jacksonSerializer.writeString("null");
            } else {
              jacksonSerializer.writeString(PrimitiveUtils.writeByte(item));
            }
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeString("");
        }
      }

      jacksonSerializer.writeEndObject();
      return fieldCount;
    } catch(Exception e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnXml(KriptonXmlContext context, ByteBean object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("byteBean");
      }

      // Persisted fields:

      // field id (mapped with "id")
      xmlSerializer.writeStartElement("id");
      xmlSerializer.writeLong(object.getId());
      xmlSerializer.writeEndElement();

      // field value (mapped with "value")
      if (object.getValue()!=null) {
        xmlSerializer.writeStartElement("value");
        xmlSerializer.writeBinary(object.getValue());
        xmlSerializer.writeEndElement();
      }

      // field value2 (mapped with "value2")
      if (object.getValue2()!=null)  {
        int n=object.getValue2().length;
        Byte item;
        for (int i=0; i<n; i++) {
          item=object.getValue2()[i];
          if (item==null) {
            xmlSerializer.writeEmptyElement("value2");
          } else {
            xmlSerializer.writeStartElement("value2");
            xmlSerializer.writeInt(item);
            xmlSerializer.writeEndElement();
          }
        }
        // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
        if (n==0) {
          xmlSerializer.writeStartElement("value2");
          xmlSerializer.writeAttribute("emptyCollection", "true");
          xmlSerializer.writeEndElement();
        }
      }

      if (currentEventType == 0) {
        xmlSerializer.writeEndElement();
      }
    } catch(Exception e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * create new object instance
   */
  @Override
  public ByteBean parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      ByteBean instance = createInstance();
      String fieldName;
      if (jacksonParser.currentToken() == null) {
        jacksonParser.nextToken();
      }
      if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
        jacksonParser.skipChildren();
        return instance;
      }
      while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
        fieldName = jacksonParser.getCurrentName();
        jacksonParser.nextToken();

        // Parse fields:
        switch (fieldName) {
            case "id":
              // field id (mapped with "id")
              instance.setId(jacksonParser.getLongValue());
            break;
            case "value":
              // field value (mapped with "value")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setValue(jacksonParser.getBinaryValue());
              }
            break;
            case "value2":
              // field value2 (mapped with "value2")
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
                instance.setValue2(CollectionUtils.asByteArray(collection));
              }
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (Exception e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public ByteBean parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      ByteBean instance = createInstance();
      String fieldName;
      if (jacksonParser.getCurrentToken() == null) {
        jacksonParser.nextToken();
      }
      if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
        jacksonParser.skipChildren();
        return instance;
      }
      while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
        fieldName = jacksonParser.getCurrentName();
        jacksonParser.nextToken();

        // Parse fields:
        switch (fieldName) {
            case "id":
              // field id (mapped with "id")
              instance.setId(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
            break;
            case "value":
              // field value (mapped with "value")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setValue(Base64Utils.decode(jacksonParser.getValueAsString()));
              }
            break;
            case "value2":
              // field value2 (mapped with "value2")
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Byte> collection=new ArrayList<>();
                Byte item=null;
                String tempValue=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  tempValue=jacksonParser.getValueAsString();
                  if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                    item=null;
                  } else {
                    item=PrimitiveUtils.readByte(jacksonParser.getText(), null);
                  }
                  collection.add(item);
                }
                instance.setValue2(CollectionUtils.asByteArray(collection));
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                ArrayList<Byte> collection=new ArrayList<>();
                instance.setValue2(CollectionUtils.asByteArray(collection));
              }
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (Exception e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public ByteBean parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlPullParser xmlParser = wrapper.xmlParser;
      ByteBean instance = createInstance();
      int eventType = currentEventType;
      boolean read=true;

      if (currentEventType == 0) {
        eventType = xmlParser.next();
      } else {
        eventType = xmlParser.getEventType();
      }
      String currentTag = xmlParser.getName().toString();
      String elementName = currentTag;
      // No attributes found

      //sub-elements
      while (xmlParser.hasNext() && elementName!=null) {
        if (read) {
          eventType = xmlParser.next();
        } else {
          eventType = xmlParser.getEventType();
        }
        read=true;
        switch(eventType) {
            case XmlPullParser.START_TAG:
              currentTag = xmlParser.getName().toString();
              switch(currentTag) {
                  case "id":
                    // property id (mapped on "id")
                    instance.setId(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                  break;
                  case "value":
                    // property value (mapped on "value")
                    instance.setValue(xmlParser.getElementAsBinary());
                  break;
                  case "value2":
                    // property value2 (mapped on "value2")
                     {
                      ArrayList<Byte> collection=new ArrayList<>();
                      Byte item;
                      // add first element
                      item=null;
                      if (xmlParser.isEmptyElement()) {
                        // if there's a an empty collection it marked with attribute emptyCollection
                        if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                          collection.add(item);
                        }
                        xmlParser.nextTag();
                      } else {
                        item=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null);
                        collection.add(item);
                      }
                      while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("value2")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null);
                        }
                        collection.add(item);
                      }
                      instance.setValue2(CollectionUtils.asByteArray(collection));
                      read=false;
                    }
                  break;
                  default:
                  break;
                }
              break;
              case XmlPullParser.END_TAG:
                if (elementName.equals(xmlParser.getName())) {
                  currentTag = elementName;
                  elementName = null;
                }
              break;
              case XmlPullParser.CDSECT:
              case XmlPullParser.TEXT:
                // no property is binded to VALUE o CDATA break;
              default:
              break;
          }
        }
        return instance;
      } catch(Exception e) {
        e.printStackTrace();
        throw (new KriptonRuntimeException(e));
      }
    }
  }