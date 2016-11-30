package com.abubusoft.kripton.binder2.context;

import com.abubusoft.kripton.binder2.BinderType;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class CborBinderContext extends JacksonContext {

	@Override
	public BinderType getSupportedFormat()
	{
		return BinderType.CBOR;
	}
	
	@Override
	public JsonFactory createInnerFactory()
	{
		return new CBORFactory();
	}
	
	@Override
	public <E> E parse(byte[] is, Class<E> objectClazz) {
		return mapperFor(objectClazz).parse(this, is);
	}
}