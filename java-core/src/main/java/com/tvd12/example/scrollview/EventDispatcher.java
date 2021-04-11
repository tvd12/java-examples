package com.tvd12.example.scrollview;

import java.util.ArrayList;
import java.util.List;

public class EventDispatcher {

	protected List<EventListener> _toAddedListeners = new ArrayList<>();
	protected List<EventListener> _toRemovedListeners = new ArrayList<>();
	
	public void removeEventListener(EventListener listener) {
		_toRemovedListeners.add(listener);
	}
	
	public void addEventListenerWithSceneGraphPriority(EventListener listener, Node node) {
		addEventListener(listener);
	}
	
	public void addEventListener(EventListener listener) {
		_toAddedListeners.add(listener);
	}
}
