/**
 * 
 */
package issue.kripton_5;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.util.Base64;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.namespace.QName;

import com.abubusoft.kripton.annotation.BindDefault;
import com.abubusoft.kripton.annotation.BindElement;
import com.abubusoft.kripton.annotation.BindRoot;
import com.abubusoft.kripton.annotation.BindValue;

@BindRoot
@BindDefault
public class Bean1 {

	public enum BeanType 
	{
		TEST_1,
		TEST_2;
	}
	
	BeanType fieldEnum;
	
	Locale fieldLocale;
	
	QName fieldQName;
	
	Calendar fieldCalendar;
	
	Character fieldChar;
	
	Date fieldDate;
	
	BigDecimal fieldBigDecimal;
	
	URL fieldUrl;
	
	Base64 fieldBase64;
	
	Boolean fieldBoolean;
	
	Byte fieldByte;
	
	Currency fieldCurrency;
	
	Double fieldDouble;
	
	Float fieldFloat;
	
	Long fieldLong;
	
	Short fieldShort;
	
	Time fieldTime;
	
	TimeZone fieldTimeZone;
	
	@BindElement(name="fieldString")
	Integer fieldInteger;
	
	String fieldString;
	
	public BigInteger fieldBigInteger;

}
