package com.tvd12.javaexample.javassist;

import java.io.Serializable;
import java.util.Map;

public interface PointDeserializer extends Serializable {

	Point deserialize(Map<String, Integer> map);
	
}
