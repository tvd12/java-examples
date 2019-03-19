package com.tvd12.javaexample.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewMethod;

public class Example9 {

	@SuppressWarnings({ "rawtypes" })
	public static void main(String[] args) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass deserClass = pool.makeClass(PointDeserializer2.class.getName() + "$EzyAutoImpl");
		
		StringBuilder methodBody = new StringBuilder()
				.append("public com.tvd12.javaexample.javassist.Point deserialize([I arg0) {\n")
				.append("\tcom.tvd12.javaexample.javassist.Point point = new com.tvd12.javaexample.javassist.Point();\n")
				.append("\tpoint.setX(arg0[0]);\n")
				.append("\tpoint.setY(arg0[1]);\n")
				.append("\treturn point;")
				.append("\n}");
		
		deserClass.addMethod(
		        CtNewMethod.make(
		        		methodBody.toString(),
		            deserClass));
		deserClass.setInterfaces(
		        new CtClass[] { pool.makeClass(PointDeserializer2.class.getName()) });
		deserClass.detach();
		Class clazz = deserClass.toClass();
		PointDeserializer2 obj = (PointDeserializer2) clazz.newInstance();
		Point point = obj.deserialize(new int[] {100, 200});
		System.out.println(point);
	}
	
}
