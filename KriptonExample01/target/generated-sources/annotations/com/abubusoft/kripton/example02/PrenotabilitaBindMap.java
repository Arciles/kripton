package com.abubusoft.kripton.example02;

import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.binder.context.AbstractJacksonContext;
import com.abubusoft.kripton.binder.context.KriptonXmlContext;
import com.abubusoft.kripton.binder.core.AbstractMapper;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.binder.xml.XMLEventConstants;
import com.abubusoft.kripton.binder.xml.XmlParser;
import com.abubusoft.kripton.binder.xml.XmlSerializer;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is the shared preference binder defined for Prenotabilita
 *
 * <p><strong>This class is generated by Kripton Annotation Processor (1.5.0-SNAPSHOT)</strong></p>
 *
 * @since Thu Dec 15 21:17:08 CET 2016
 * @see Prenotabilita
 */
@BindMap
public class PrenotabilitaBindMap extends AbstractMapper<Prenotabilita> {
  /**
   * create new object instance
   */
  @Override
  public Prenotabilita createInstance() {
    return new Prenotabilita();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, Prenotabilita object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field callCenter
      fieldCount++;
      jacksonSerializer.writeBooleanField("callCenter", object.callCenter);

      // field onLine
      fieldCount++;
      jacksonSerializer.writeBooleanField("onLine", object.onLine);

      // field sportelliCup
      fieldCount++;
      jacksonSerializer.writeBooleanField("sportelliCup", object.sportelliCup);

      jacksonSerializer.writeEndObject();
      return fieldCount;
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJacksonAsString(AbstractJacksonContext context, Prenotabilita object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field callCenter
      jacksonSerializer.writeStringField("callCenter", PrimitiveUtils.writeBoolean(object.callCenter));

      // field onLine
      jacksonSerializer.writeStringField("onLine", PrimitiveUtils.writeBoolean(object.onLine));

      // field sportelliCup
      jacksonSerializer.writeStringField("sportelliCup", PrimitiveUtils.writeBoolean(object.sportelliCup));

      jacksonSerializer.writeEndObject();
      return fieldCount;
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnXml(KriptonXmlContext context, Prenotabilita object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("prenotabilita");
      }

      // Persisted fields:

      // field callCenter
      xmlSerializer.writeStartElement("callCenter");
      xmlSerializer.writeBoolean(object.callCenter);
      xmlSerializer.writeEndElement();

      // field onLine
      xmlSerializer.writeStartElement("onLine");
      xmlSerializer.writeBoolean(object.onLine);
      xmlSerializer.writeEndElement();

      // field sportelliCup
      xmlSerializer.writeStartElement("sportelliCup");
      xmlSerializer.writeBoolean(object.sportelliCup);
      xmlSerializer.writeEndElement();

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
  public Prenotabilita parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Prenotabilita instance = createInstance();
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
            case "callCenter":
              // field callCenter
              instance.callCenter=jacksonParser.getBooleanValue();
            break;
            case "onLine":
              // field onLine
              instance.onLine=jacksonParser.getBooleanValue();
            break;
            case "sportelliCup":
              // field sportelliCup
              instance.sportelliCup=jacksonParser.getBooleanValue();
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (IOException e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public Prenotabilita parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Prenotabilita instance = createInstance();
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
            case "callCenter":
              // field callCenter
              instance.callCenter=PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false);
            break;
            case "onLine":
              // field onLine
              instance.onLine=PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false);
            break;
            case "sportelliCup":
              // field sportelliCup
              instance.sportelliCup=PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false);
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (IOException e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public Prenotabilita parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlParser xmlParser = wrapper.xmlParser;
      Prenotabilita instance = createInstance();
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
            case XMLEventConstants.START_ELEMENT:
              currentTag = xmlParser.getName().toString();
              switch(currentTag) {
                  case "callCenter":
                    // property callCenter
                    instance.callCenter=PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false);
                  break;
                  case "onLine":
                    // property onLine
                    instance.onLine=PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false);
                  break;
                  case "sportelliCup":
                    // property sportelliCup
                    instance.sportelliCup=PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false);
                  break;
                  default:
                    xmlParser.skipElement();
                  break;
                }
              break;
              case XMLEventConstants.END_ELEMENT:
                if (elementName.equals(xmlParser.getName().getLocalPart())) {
                  currentTag = elementName;
                  elementName = null;
                }
              break;
              case XMLEventConstants.CDATA:
              case XMLEventConstants.CHARACTERS:
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
