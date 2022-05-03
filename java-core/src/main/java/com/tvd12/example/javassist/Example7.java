package com.tvd12.example.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewMethod;

public class Example7 {

    @SuppressWarnings({"rawtypes"})
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass evalClass = pool.makeClass(PointDeserializer.class.getName() + "$EzyAutoImpl");
        evalClass.addMethod(
            CtNewMethod.make(
                "public double eval (double x) { return 10.0D ; }",
                evalClass));
        evalClass.setInterfaces(
            new CtClass[]{pool.makeClass(Evaluator.class.getName())});
        Class clazz = evalClass.toClass();
        Evaluator obj = (Evaluator) clazz.newInstance();
        double result = obj.eval(17);
        System.out.println(result);
    }

}
