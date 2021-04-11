package com.tvd12.example.javassist;

import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewMethod;

public class Example6 {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass evalClass = pool.makeClass("Eval");
		evalClass.addMethod(
		        CtNewMethod.make(
		            "public double eval (double x) { return 10.0D ; }",
		            evalClass));
		Class clazz = evalClass.toClass();
		Object obj = clazz.newInstance();
		Class[] formalParams = new Class[] { double.class };
		Method meth = clazz.getDeclaredMethod("eval", formalParams);
		Object[] actualParams = new Object[] { new Double(17) };
		double result = ((Double) meth.invoke(obj, actualParams)).doubleValue();
		System.out.println(result);
	}
	
}
