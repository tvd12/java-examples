package com.tvd12.javaexample.scrollview;

import java.util.List;

public class ScrollView extends Layer {

	protected ScrollViewDelegate _delegate;
	protected Direction _direction;
	protected Size _viewSize;
	protected Vec2 _contentOffset;
	protected boolean _touchMoved;
	protected boolean _dragging;
	protected List<Touch> _touches;
	protected boolean _bounceable;
	protected boolean _clippingToBounds;
	protected float _touchLength;
	protected EventListenerTouchOneByOne _touchListener;

	public static final float BOUNCE_DURATION = 0.15f;

	protected boolean initWithViewSize(Size size, Node container) {
		if (super.init()) {
			_container = container;

			if (this._container == null) {
				_container = Layer.create();
				_container.setIgnoreAnchorPointForPosition(false);
				_container.setAnchorPoint(new Vec2(0.0f, 0.0f));
			}

			this.setViewSize(size);

			setTouchEnabled(true);

			// _touches.reserve(EventTouch.MAX_TOUCHES);

			_delegate = null;
			_bounceable = true;
			_clippingToBounds = true;
			// _container.setContentSize(Size::ZERO);
			_direction = Direction.BOTH;
			_container.setPosition(0.0f, 0.0f);
			_touchLength = 0.0f;

			this.addChild(_container);
			_minScale = _maxScale = 1.0f;

			return true;
		}
		return false;
	}

	public boolean init() {
		return this.initWithViewSize(new Size(200, 200), null);
	}

	public boolean isNodeVisible(Node node) {
		Vec2 offset = this.getContentOffset();
		Size size = this.getViewSize();
		float scale = this.getZoomScale();

		Rect viewRect;

		viewRect = new Rect(-offset.x / scale, -offset.y / scale, size.width / scale, size.height / scale);

		return viewRect.intersectsRect(node.getBoundingBox());
	}

	public void pause(Object sender) {
		_container.pause();

		List<Node> children = _container.getChildren();
		for (Node child : children) {
			child.pause();
		}
	}

	public void resume(Object sender) {
		List<Node> children = _container.getChildren();
		for (Node child : children) {
			child.resume();
		}

		_container.resume();
	}

	public boolean isTouchEnabled() {
		return _touchListener != null;
	}

	public void setTouchEnabled(boolean enabled) {
		_eventDispatcher.removeEventListener(_touchListener);
		_touchListener = null;

		if (enabled) {
			_touchListener = EventListenerTouchOneByOne.create();
			_touchListener.setSwallowTouches(true);
			_touchListener.onTouchBegan = this::onTouchBegan;
			_touchListener.onTouchMoved = this::onTouchMoved;
			_touchListener.onTouchEnded = this::onTouchEnded;
			_touchListener.onTouchCancelled = this::onTouchCancelled;

			_eventDispatcher.addEventListenerWithSceneGraphPriority(_touchListener, this);
		} else {
			_dragging = false;
			_touchMoved = false;
			_touches.clear();
		}
	}

	public void setSwallowTouches(boolean needSwallow) {
		if (_touchListener != null) {
			_touchListener.setSwallowTouches(needSwallow);
		}
	}

	public void setContentOffset(Vec2 offset, boolean animated) {
		if (animated) { // animate scrolling
			this.setContentOffsetInDuration(offset, BOUNCE_DURATION);
		} else { // set the container position directly
			if (!_bounceable) {
				Vec2 minOffset = this.minContainerOffset();
				Vec2 maxOffset = this.maxContainerOffset();

				offset.x = Math.max(minOffset.x, Math.min(maxOffset.x, offset.x));
				offset.y = Math.max(minOffset.y, Math.min(maxOffset.y, offset.y));
			}

			_container.setPosition(offset);

			if (_delegate != null) {
				_delegate.scrollViewDidScroll(this);
			}
		}
	}
	
	@Override
	public void setContentOffsetInDuration(Vec2 offset, float dt)
	{
	}

	public boolean onTouchBegan(Touch pTouch, Event pEvent) {
		return true;
	}

	public void onTouchMoved(Touch pTouch, Event pEvent) {
	}

	public void onTouchCancelled(Touch pTouch, Event pEvent) {
	}

	public void onTouchEnded(Touch pTouch, Event pEvent) {
	}

	public boolean isTouchMoved() {
		return _touchMoved;
	}

	public Direction getDirection() {
		return this._direction;
	}

	public void setDirection(Direction direction) {
		this._direction = direction;
	}

	public void setDelegate(ScrollViewDelegate delegate) {
		this._delegate = delegate;
	}

	public Vec2 getContentOffset() {
		return this._contentOffset;
	}

	public void setContentOffset(Vec2 offset) {
		this._contentOffset = offset;
	}

	protected Vec2 minContainerOffset() {
		return Vec2.ZERO;
	}

	protected Vec2 maxContainerOffset() {
		return Vec2.ZERO;
	}
}
