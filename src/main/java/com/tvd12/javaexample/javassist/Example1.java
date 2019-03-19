package com.tvd12.javaexample.javassist;

import java.lang.reflect.Field;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.ClassFile;
import javassist.bytecode.FieldInfo;

public class Example1 {

	public static void main(String[] args) 
			throws SecurityException, CannotCompileException, RuntimeException {
		ClassFile cf = new ClassFile(false, "com.tvd12.javaexample.javassist.ClassA$Proxy", null);
		cf.setInterfaces(new String[] {"java.lang.Cloneable"});
		
		FieldInfo f = new FieldInfo(cf.getConstPool(), "id", "I");
		f.setAccessFlags(AccessFlag.PUBLIC);
		cf.addField(f);
		
		ClassPool classPool = ClassPool.getDefault();
		Field[] fields = classPool.makeClass(cf).toClass().getFields();
		assert fields[0].getName().equals("id");
	}
	
}
