package com.abubusoft.kripton.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.abubusoft.kripton.exception.KriptonRuntimeException;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * Acts as a ByteArrayOutputStream, but allow direct access to array of byte
 * inside, without copy it.
 * 
 * @author xcesco
 *
 */
public class KriptonByteArrayOutputStream extends ByteArrayOutputStream {
	public KriptonByteArrayOutputStream() {
	}

	public KriptonByteArrayOutputStream(int size) {
		super(size);
	}

	public int getCount() {
		return count;
	}

	public byte[] getByteBuffer() {
		return buf;
	}

	public byte[] getByteBufferCopy() {
		return Arrays.copyOf(buf, buf.length);
	}

	@Override
	public void close() {
		try {
			super.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e));
		}
	}
}