package com.tvd12.javaexample.scrollview;

import java.util.ArrayList;
import java.util.List;

public class Node {

	protected Node _parent = null;
	protected Node _container = null;
	protected Size _contentSize = Size.ZERO;
	protected Rect _boundingBox = Rect.ZERO;
	protected boolean _visible = true;
	protected Vec2 _anchorPoint = new Vec2(0.5F, 0.5F);
	protected Vec2 _position = Vec2.ZERO;
	protected float _scaleX = 1.0F;
	protected float _scaleY = 1.0F;
	protected boolean ignoreAnchorPointForPosition;
	protected Size _viewSize;
	protected boolean _touchEnabled;
	protected float _minScale;
	protected float _maxScale;
	protected float _zoomScale;
	protected List<Node> _children = new ArrayList<>();
	protected EventDispatcher _eventDispatcher;
	
	public EventDispatcher getEventDispatcher() {
		return this._eventDispatcher;
	}
	
	public void setEventDispatcher(EventDispatcher eventDispatcher) {
		this._eventDispatcher = eventDispatcher;
	}
	
	public float getZoomScale() {
		return _zoomScale;
	}
	
	public void setZoomScale(float scale) {
		this._zoomScale = scale;
	}
	
	public Node getParent() {
		return this._parent;
	}

	public Node getContainer() {
		return this._container;
	}
	
	public void removeChild(TableViewCell cell, boolean b) {
		
	}

	public void addChild(TableViewCell cell) {
		
	}
	
	public void setContentSize(Size size) {
		this._contentSize = size;
	}
	
	public Size getContentSize() {
		return this._contentSize;
	}
	
	public Rect getBoundingBox() {
		return _boundingBox;
	}
	
	public boolean isVisible() {
		return _visible;
	}
	
	public void setVisible(boolean visible) {
		this._visible = visible;
	}

	public Vec2 convertTouchToNodeSpace(Touch pTouch) {
		return null;
	}

	public Vec2 convertToWorldSpace(Vec2 origin) {
		return origin;
	}
	
	public Vec2 getAnchorPoint() {
		return this._anchorPoint;
	}
	
	public void setAnchorPoint(Vec2 anchorPoint) {
		this._anchorPoint = anchorPoint;
	}
	
	public Vec2 getPosition() {
		return this._position;
	}
	
	public void setPosition(Vec2 position) {
		this._position = position;
	}
	
	public void setPosition(float x, float y) {
		setPosition(new Vec2(x, y));
	}
	
	public float getScaleX() {
		return _scaleX;
	}
	
	public void setScaleX(float scaleX) {
		this._scaleX = scaleX;
	}
	
	public float getScaleY() {
		return _scaleY;
	}
	
	public void setScaleY(float scaleY) {
		this._scaleY = scaleY;
	}
	
	public void setIgnoreAnchorPointForPosition(boolean value) {
		this.ignoreAnchorPointForPosition = value;
	}
	
	public boolean isIgnoreAnchorPointForPosition() {
		return this.ignoreAnchorPointForPosition;
	}
	
	public Size getViewSize() {
		return _viewSize;
	}
	
	public void setViewSize(Size viewSize) {
		this._viewSize = viewSize;
	}
	
	public boolean isTouchEnable() {
		return _touchEnabled;
	}
	
	public void setTouchEnabled(boolean value) {
		this._touchEnabled = value;
	}
	
	public void setContentOffsetInDuration(Vec2 offset, float bounceDuration) {
		
	}
	
	public void addChild(Node node) {
		_children.add(node);
	}
	
	public List<Node> getChildren() {
		return _children;
	}
	
	public void pause() {
	}
	
	public void resume() {
	}
	
	public void retain() {
	}

	public void autorelease() {

	}

}
