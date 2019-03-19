package com.tvd12.javaexample.javassist;

import java.util.LinkedList;
import java.util.List;

import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Mnemonic;

public class Example2 {

	public static void main(String[] args) throws NotFoundException, BadBytecode {
		ClassPool cp = ClassPool.getDefault();
		ClassFile cf = cp.get("com.tvd12.javaexample.javassist.Point").getClassFile();
		MethodInfo minfo = cf.getMethod("move");
		CodeAttribute ca = minfo.getCodeAttribute();
		CodeIterator ci = ca.iterator();

		List<String> operations = new LinkedList<>();
		while (ci.hasNext()) {
			int index = ci.next();
			int op = ci.byteAt(index);
			operations.add(Mnemonic.OPCODE[op]);
		}
		 
		System.out.println(operations);
	}
	
}
