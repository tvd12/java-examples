package com.tvd12.example.javassist;

import java.util.HashMap;
import java.util.Map;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;
import javassist.bytecode.ClassFile;

public class Example5 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		ClassFile cf = new ClassFile(
				false,
				PointDeserializer.class.getName() + "$EzyAutoImpl", 
				Object.class.getName());
		cf.setInterfaces(new String[] {PointDeserializer.class.getName()});
		
		CtClass cc = pool.makeClass(cf);
		
		cc.addConstructor(CtNewConstructor.make(new CtClass[0], new CtClass[0], cc));
		
		StringBuilder methodBuilder = new StringBuilder()
				.append("public com.tvd12.example.javassist.Point deserialize(java.util.Map map) {\n")
				.append("\tcom.tvd12.example.javassist.Point point = new com.tvd12.example.javassist.Point();\n")
				.append("\tpoint.setX((int)map.get(\"x\"));\n")
				.append("\tpoint.setY((int)map.get(\"y\"));\n")
				.append("\treturn point;")
				.append("\n}");
		cc.addMethod(CtNewMethod.make(methodBuilder.toString(), cc));
		Class<PointDeserializer> clazz = (Class<PointDeserializer>) cc.toClass();
		PointDeserializer de = clazz.newInstance();
		Map<String, Integer> map = new HashMap<>();
		map.put("x", 100);
		map.put("y", 200);
		Point point = de.deserialize(map);
		System.out.println(point);
	}
	
}
