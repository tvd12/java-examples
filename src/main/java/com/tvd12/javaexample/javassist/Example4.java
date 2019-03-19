package com.tvd12.javaexample.javassist;

import java.util.LinkedList;
import java.util.List;

import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.DuplicateMemberException;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Mnemonic;

public class Example4 {

	public static void main(String[] args) 
			throws DuplicateMemberException, NotFoundException, BadBytecode {
		ClassFile cf = ClassPool.getDefault()
				  .get("com.tvd12.javaexample.javassist.Point").getClassFile();
		Bytecode code = new Bytecode(cf.getConstPool());
		code.addAload(0);
		code.addInvokespecial("java/lang/Object", MethodInfo.nameInit, "()V");
		code.addReturn(null);
		 
		MethodInfo minfo = new MethodInfo(
		  cf.getConstPool(), MethodInfo.nameInit, "()V");
		minfo.setCodeAttribute(code.toCodeAttribute());
		cf.addMethod(minfo);
		
		CodeIterator ci = code.toCodeAttribute().iterator();
		List<String> operations = new LinkedList<>();
		while (ci.hasNext()) {
		    int index = ci.next();
		    int op = ci.byteAt(index);
		    operations.add(Mnemonic.OPCODE[op]);
		}
		 
		System.out.println(operations);

	}
	
}
