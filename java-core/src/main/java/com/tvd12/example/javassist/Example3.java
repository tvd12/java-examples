package com.tvd12.example.javassist;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.ClassFile;
import javassist.bytecode.FieldInfo;

public class Example3 {

	public static void main(String[] args) throws NotFoundException, SecurityException, CannotCompileException, RuntimeException {
		ClassFile cf = ClassPool.getDefault()
				  .get("com.tvd12.example.javassist.Point").getClassFile();
				 
		FieldInfo f = new FieldInfo(cf.getConstPool(), "id", "I");
		f.setAccessFlags(AccessFlag.PUBLIC);
		cf.addField(f);
		ClassPool classPool = ClassPool.getDefault();
		Field[] fields = classPool.makeClass(cf).toClass().getFields();
		List<String> fieldsList = Stream.of(fields)
		  .map(Field::getName)
		  .collect(Collectors.toList());
		  
		assert fieldsList.contains("id");
	}
	
}
