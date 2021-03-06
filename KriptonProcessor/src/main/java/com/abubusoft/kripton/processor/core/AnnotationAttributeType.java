/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.core;

import javax.lang.model.element.ExecutableElement;

/**
 * Attribute name used in annotations. Introduced to avoid to type name attribute as string
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 *
 * @since 05/mag/2016
 */
public enum AnnotationAttributeType {
	ATTRIBUTE_ALL_FIELDS("allFields"),
	
	ATTRIBUTE_RAW_FIELDS("rawFields"),
	
	ATTRIBUTE_VALUE("value"),
	ATTRIBUTE_EXCLUDED_FIELDS("excludedFields"),	
	ATTRIBUTE_DISTINCT("distinct"),
	
	ATTRIBUTE_WHERE("where"),	
	ATTRIBUTE_HAVING("having"),
	ATTRIBUTE_GROUP_BY("groupBy"),
	ATTRIBUTE_ORDER_BY("orderBy"),
	ATTRIBUTE_NULLABLE("nullable"),
	ATTRIBUTE_FILENAME("fileName"),
	ATTRIBUTE_VERSION("version"),
	ATTRIBUTE_LOG("log"), 
	ATTRIBUTE_ASYNCTASK("asyncTask"), 
	ATTRIBUTE_CURSOR("cursor"), 
	ATTRIBUTE_FIELD_TYPE("fieldType"),
	ATTRIBUTE_ENABLED("enabled"),
	
	ATTRIBUTE_ORDER("order"),	
	ATTRIBUTE_MAP_KEY_NAME("mapKeyName"),
	ATTRIBUTE_MAP_VALUE_NAME("mapValueName"),
	ATTRIBUTE_MAP_ENTRY_TYPE("mapEntryType"),
	ATTRIBUTE_XML_TYPE("xmlType"), 
	ATTRIBUTE_XML_ELEMENT_TAG("elementTag"),
	
	ATTRIBUTE_ADAPTER("adapter"),
	ATTRIBUTE_DATA_TYPE("dataType"),
	ATTRIBUTE_FOREIGN_KEY("foreignKey"),
	ATTRIBUTE_COLUMN_TYPE("columnType"),
	ATTRIBUTE_DAO("dao"),
	ATTRIBUTE_INCLUDE_PRIMARY_KEY("includePrimaryKey"),
	ATTRIBUTE_INSERT_CONFLICT_ALGORITHM_TYPE("conflictAlgorithm")
	;
	
	
	private String value;
	
	public String getValue()
	{
		return value;
	}

	private AnnotationAttributeType(String value)
	{
		this.value=value;
	}

	public boolean isEquals(ExecutableElement value) {
		return this.getValue().equals(value.getSimpleName().toString());
	}
	
}
