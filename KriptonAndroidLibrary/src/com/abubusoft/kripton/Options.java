package com.abubusoft.kripton;

/**
 * XML/JSON format like encoding and indent.
 * 
 * @author bulldog
 * @author xcesco
 *
 */
public class Options {

	public static final String DEFAULT_ENCODING = "utf-8";
	
	public static final String ENCODING_UTF_8 = DEFAULT_ENCODING;

	/**
	 * Builder for format class
	 * 
	 * @return
	 */
	public static Options build() {
		return new Options();
	}

	/**
	 * Encoding for the xml
	 */
	private String encoding;

	/**
	 * Indicates if serialized xml should be indented or not
	 */
	private boolean indent;
	
	/**
	 * if true use apostrophe to delimit strings. Used only for xml format.
	 */
	private boolean useApostrophe;

	/**
	 * if true use apostrophe to delimit strings. Used only for xml format.
	 * @return
	 */
	public boolean isUseApostrophe() {
		return useApostrophe;
	}

	/**
	 * if true use apostrophe to delimit strings. Used only for xml format.
	 */
	public Options useApostrophe(boolean useApostrophe) {
		this.useApostrophe = useApostrophe;
		
		return this;
	}

	/**
	 * Constructor,
	 * 
	 * encoding defaults to utf-8, indent defaults to false.
	 * 
	 */
	public Options() {
		encoding = DEFAULT_ENCODING;
		indent = false;
	}

	/**
	 * Get the file encoding setting.
	 * 
	 * @return
	 */
	public Options encoding(String value) {
		this.encoding = value;
		return this;
	}

	/**
	 * Get the file encoding setting
	 * 
	 * @return file encoding setting
	 */
	public String getEncoding() {
		return this.encoding;
	}

	/**
	 * Indicates if serialized xml should be indented or not.
	 * 
	 * @return
	 */
	public Options indent(boolean value) {
		this.indent = value;
		return this;
	}

	/**
	 * Indicates if serialized xml should be indented or not.
	 * 
	 * @return
	 */
	public boolean isIndent() {
		return this.indent;
	}

}