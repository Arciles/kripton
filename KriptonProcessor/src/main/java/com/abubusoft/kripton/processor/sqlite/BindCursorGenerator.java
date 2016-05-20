/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.className;
import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.Date;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;

import android.database.Cursor;

import com.abubusoft.kripton.processor.BindDatabaseProcessor;
import com.abubusoft.kripton.processor.core.ModelElementVisitor;
import com.abubusoft.kripton.processor.core.ModelProperty;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteDatabaseSchema;
import com.abubusoft.kripton.processor.sqlite.transform.Transformer;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

/**
 * @author xcesco
 *
 */
public class BindCursorGenerator extends AbstractCodeGenerator implements ModelElementVisitor<SQLEntity, SQLProperty> {

	public static final String PREFIX = "Bind";
	
	public static final String SUFFIX = "Cursor";
	
	private int counter;

	public BindCursorGenerator(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) {
		super(elementUtils, filer, model);
	}

	public static void generate(Elements elementUtils, Filer filer, SQLiteDatabaseSchema model) throws Exception {
		BindCursorGenerator visitor = new BindCursorGenerator(elementUtils, filer, model);

		for (SQLEntity item : model.getEntities()) {
			visitor.visit(item);
		}

	}

	@Override
	public void visit(SQLEntity entity) throws Exception {
		String classTableName = PREFIX+entity.getSimpleName()+SUFFIX;		

		PackageElement pkg = elementUtils.getPackageOf(entity.getElement());
		String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();
		
		ClassName className=TypeUtility.className(packageName, classTableName);

		builder = TypeSpec.classBuilder(classTableName).addModifiers(Modifier.PUBLIC);
		// generiamo il javadoc contenente la data di generazione della classe
		builder.addJavadoc("Generated by Kripton Library.\n\n @since $L\n\n", (new Date()).toString());
		// builder.addMethod(buildCreateMethod(definition, elementUtils));

		FieldSpec fieldSpec = FieldSpec.builder(Cursor.class, "cursor", Modifier.PROTECTED).build();
		builder.addField(fieldSpec);
		
		// add constructor
		builder.addMethod(MethodSpec.constructorBuilder().addParameter(Cursor.class, "cursor").addCode("wrap(cursor);\n").build());

		// add wrap method
		MethodSpec.Builder wrapMethodBuilder = MethodSpec.methodBuilder("wrap").addModifiers(Modifier.PUBLIC).addParameter(Cursor.class, "cursor").returns(className).addCode("this.cursor=cursor;\n");
		//MethodSpec.Builder wrapMethodBuilder = MethodSpec.methodBuilder("wrap").addModifiers(Modifier.PUBLIC).addParameter(Cursor.class, "cursor").returns(Void.TYPE).addCode("this.cursor=cursor;\n");
		counter=0;
		wrapMethodBuilder.addCode("\n");
		for (ModelProperty item : entity.getCollection()) {
			wrapMethodBuilder.addCode("index$L=cursor.getColumnIndex($S);\n", counter, SQLUtility.getColumnName(item));
			counter++;
		}
		wrapMethodBuilder.addCode("\n");
		wrapMethodBuilder.addCode("return this;\n");
		
		builder.addMethod(wrapMethodBuilder.build());
		
		// add execute method		
		builder.addMethod(generateExecuteMethod(packageName, entity).build());
		
		// add execute listener method
		builder.addMethod(generateExecuteListener(packageName, entity).build());

		// add create
		builder.addMethod(MethodSpec.methodBuilder("create").addModifiers(Modifier.STATIC, Modifier.PUBLIC).addParameter(Cursor.class, "cursor").returns(className(packageName, classTableName)).addCode("return new "+classTableName+"(cursor);\n").build());

		// define column name set
		counter=0;
		for (ModelProperty item : entity.getCollection()) {
			item.accept(this);
		}
		
		TypeSpec typeSpec = builder.build();
		BindDatabaseProcessor.info("WRITE "+typeSpec.name);		
		JavaFile.builder(packageName, typeSpec).build().writeTo(filer);

	}

	private MethodSpec.Builder generateExecuteMethod(String packageName, SQLEntity entity) {		
		ParameterizedTypeName parameterizedReturnTypeName = ParameterizedTypeName.get(className("java.util","LinkedList"), className(packageName, entity.getSimpleName()));
		
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("execute").addModifiers(Modifier.PUBLIC).returns(parameterizedReturnTypeName);

		TypeName entityClass= typeName(entity.getElement());
		
		methodBuilder.addCode("\n");
		methodBuilder.addCode("$T resultList=new $T();\n",parameterizedReturnTypeName, parameterizedReturnTypeName);
		methodBuilder.addCode("$T resultBean=new $T();\n",entityClass, entityClass);
		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("if (cursor.moveToFirst())");

		methodBuilder.beginControlFlow("do\n");			
		methodBuilder.addCode("resultBean=new $T();\n\n",entityClass);
		
		// generate mapping
		int i=0;
		for (ModelProperty item : entity.getCollection()) {			
			methodBuilder.addCode("if (index$L>=0 && !cursor.isNull(index$L)) { ",i,i);
			Transformer.cursor2Bean(methodBuilder, item, "resultBean", "cursor","index"+i+"");		
			methodBuilder.addCode(";");
			methodBuilder.addCode("}\n");
			
			i++;
		}
		methodBuilder.addCode("\n");
		
		methodBuilder.addCode("resultList.add(resultBean);\n");			
		methodBuilder.endControlFlow("while (cursor.moveToNext())");						
		
		methodBuilder.endControlFlow();
		methodBuilder.addCode("cursor.close();\n");
		
		methodBuilder.addCode("\n");
		methodBuilder.addCode("return resultList;\n");
		
		return methodBuilder;
	}
	
	private MethodSpec.Builder generateExecuteListener(String packageName, SQLEntity entity) {		
		String interfaceName="On" + entity.getSimpleName() + "Listener";
		Builder listenerInterface = TypeSpec.interfaceBuilder(interfaceName).addModifiers(Modifier.PUBLIC);
		listenerInterface.addMethod(MethodSpec.methodBuilder("onRow").addParameter(ParameterSpec.builder(typeName(entity.getElement()), "bean").build()).addParameter(ParameterSpec.builder(Integer.TYPE, "rowPosition").build()).addParameter(ParameterSpec.builder(Integer.TYPE, "rowCount").build())
				.returns(Void.TYPE).addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT).build());
		ClassName interfaceType=TypeUtility.className("", interfaceName);
		
		
		// javadoc for interface
		listenerInterface.addJavadoc("Listener for row read from database.\n\n");
		listenerInterface.addJavadoc("@param bean bean read from database. Only selected columns/fields are valorized.\n");
		listenerInterface.addJavadoc("@param rowPosition position of row.\n");
		listenerInterface.addJavadoc("@param rowCount total number of rows.\n");
		listenerInterface.addJavadoc("\n");
		
		TypeSpec listenerClass = listenerInterface.build();
		
		builder.addType(listenerClass);
		
		MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("executeListener").addModifiers(Modifier.PUBLIC).addParameter(ParameterSpec.builder(interfaceType, "listener").build()).returns(TypeName.VOID);
		//MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("executeListener").returns(TypeName.VOID);

		TypeName entityClass= typeName(entity.getElement());
		
		methodBuilder.addCode("$T resultBean=new $T();\n",entityClass, entityClass);
		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("if (cursor.moveToFirst())");

		methodBuilder.beginControlFlow("do\n");					
		
		// reset mapping
		{
			int i=0;
			for (ModelProperty item : entity.getCollection()) {			
				methodBuilder.addCode("if (index$L>=0) { ",i);
				Transformer.resetBean(methodBuilder, item, "resultBean", "cursor","index"+i+"");
				methodBuilder.addCode(";");
				methodBuilder.addCode("}\n");
				
				i++;
			}
		}
		methodBuilder.addCode("\n");
		
		// generate mapping
		{
			int i=0;
			for (ModelProperty item : entity.getCollection()) {			
				methodBuilder.addCode("if (index$L>=0 && !cursor.isNull(index$L)) { ",i,i);
				Transformer.cursor2Bean(methodBuilder, item, "resultBean", "cursor","index"+i+"");
				methodBuilder.addCode(";");
				methodBuilder.addCode("}\n");
				
				i++;
			}
		}

		// send to listener
		methodBuilder.addCode("\n");
		methodBuilder.addCode("listener.onRow(resultBean, cursor.getPosition(),cursor.getCount());\n");
				
		methodBuilder.endControlFlow("while (cursor.moveToNext())");						
		
		methodBuilder.endControlFlow();
		methodBuilder.addCode("cursor.close();\n");
		
		return methodBuilder;
	}

	@Override
	public void visit(SQLProperty property) throws Exception {
		// add property index
		builder.addField(FieldSpec.builder(Integer.TYPE, "index"+(counter++), Modifier.PROTECTED).addJavadoc("Index for column $S\n", property.getName()).build());

	}

	

}