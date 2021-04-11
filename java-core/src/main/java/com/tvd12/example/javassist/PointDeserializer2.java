package com.tvd12.example.javassist;

import java.io.Serializable;

public interface PointDeserializer2 extends Serializable {

	Point deserialize(int[] array);
	
}
