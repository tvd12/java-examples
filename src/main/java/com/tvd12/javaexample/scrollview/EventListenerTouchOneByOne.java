package com.tvd12.javaexample.scrollview;

public class EventListenerTouchOneByOne extends EventListener {
	
	public TouchFunctionBoolean onTouchBegan;
	public TouchFunctionVoid onTouchMoved;
	public TouchFunctionVoid onTouchEnded;
	public TouchFunctionVoid onTouchCancelled;
	
	public static EventListenerTouchOneByOne create() {
		return new EventListenerTouchOneByOne();
	}

}
