package com.tvd12.example.javassist;

import java.util.HashMap;
import java.util.Map;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewMethod;

public class Example8 {

    @SuppressWarnings({"rawtypes"})
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass deserClass = pool.makeClass(PointDeserializer.class.getName() + "$EzyAutoImpl");

        StringBuilder methodBody = new StringBuilder()
            .append("public com.tvd12.example.javassist.Point deserialize(java.util.Map map) {\n")
            .append("\tcom.tvd12.example.javassist.Point point = new com.tvd12.example.javassist.Point();\n")
            .append("\tif(map.containsKey(\"x\"))")
            .append("\t\tpoint.setX(((java.lang.Number)map.get(\"x\")).intValue());\n")
            .append("\tif(map.containsKey(\"y\"))")
            .append("\t\tpoint.setY(((java.lang.Number)map.get(\"y\")).intValue());\n")
            .append("\treturn point;")
            .append("\n}");

        deserClass.addMethod(
            CtNewMethod.make(
                methodBody.toString(),
                deserClass));
        deserClass.setInterfaces(
            new CtClass[]{pool.makeClass(PointDeserializer.class.getName())});
        deserClass.detach();
        Class clazz = deserClass.toClass();
        PointDeserializer obj = (PointDeserializer) clazz.newInstance();
        Map<String, Integer> map = new HashMap<>();
        map.put("x", 100);
        map.put("y", 200);
        Point point = obj.deserialize(map);
        System.out.println(point);
    }

}
