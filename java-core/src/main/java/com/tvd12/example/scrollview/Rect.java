package com.tvd12.example.scrollview;

import com.tvd12.ezyfox.util.EzyEquals;
import com.tvd12.ezyfox.util.EzyHashCodes;

public class Rect {

    public static final Rect ZERO = new Rect();
    protected Vec2 origin = new Vec2();
    protected Size size = new Size();

    public Rect() {
        this(0.0F, 0.0F, 0.0F, 0.0F);
    }

    public Rect(float x, float y, float width, float height) {
        set(x, y, width, height);
    }

    public void set(float x, float y, float width, float height) {
        this.origin.set(x, y);
        this.size.set(width, height);
    }

    public float getMaxX() {
        return origin.x + size.width;
    }

    public float getMidX() {
        return origin.x + size.width / 2.0f;
    }

    public float getMinX() {
        return origin.x;
    }

    public float getMaxY() {
        return origin.y + size.height;
    }

    public float getMidY() {
        return origin.y + size.height / 2.0f;
    }

    public float getMinY() {
        return origin.y;
    }

    public boolean containsPoint(Vec2 point) {

        return point.x >= getMinX() &&
            point.x <= getMaxX() &&
            point.y >= getMinY() &&
            point.y <= getMaxY();
    }

    public boolean intersectsRect(Rect rect) {
        return !(getMaxX() < rect.getMinX() ||
            rect.getMaxX() < getMinX() ||
            getMaxY() < rect.getMinY() ||
            rect.getMaxY() < getMinY());
    }

    public boolean intersectsCircle(Vec2 center, float radius) {

        Vec2 rectangleCenter = new Vec2(
            origin.x + size.width / 2,
            origin.y + size.height / 2);

        float w = size.width / 2;
        float h = size.height / 2;

        float dx = Math.abs(center.x - rectangleCenter.x);
        float dy = Math.abs(center.y - rectangleCenter.y);

        if (dx > (radius + w) || dy > (radius + h)) {
            return false;
        }

        Vec2 circleDistance = new Vec2(
            Math.abs(center.x - origin.x - w),
            Math.abs(center.y - origin.y - h));

        if (circleDistance.x <= (w)) {
            return true;
        }
        if (circleDistance.y <= (h)) {
            return true;
        }

        double cornerDistanceSq = Math.pow(circleDistance.x - w, 2) + Math.pow(circleDistance.y - h, 2);
        return (cornerDistanceSq <= (Math.pow(radius, 2)));
    }

    public void merge(Rect rect) {
        float minX = Math.min(getMinX(), rect.getMinX());
        float minY = Math.min(getMinY(), rect.getMinY());
        float maxX = Math.max(getMaxX(), rect.getMaxX());
        float maxY = Math.max(getMaxY(), rect.getMaxY());
        set(minX, minY, maxX - minX, maxY - minY);
    }

    public void unionWithRect(Rect rect) {
        float thisLeftX = origin.x;
        float thisRightX = origin.x + size.width;
        float thisTopY = origin.y + size.height;
        float thisBottomY = origin.y;

        if (thisRightX < thisLeftX) {
            float tmp = thisRightX;
            thisRightX = thisLeftX;
            thisLeftX = tmp;
            //swap(thisRightX, thisLeftX)
        }

        if (thisTopY < thisBottomY) {
            float tmp = thisTopY;
            thisTopY = thisBottomY;
            thisBottomY = tmp;
            //swap(thisTopY, thisBottomY)
        }

        float otherLeftX = rect.origin.x;
        float otherRightX = rect.origin.x + rect.size.width;
        float otherTopY = rect.origin.y + rect.size.height;
        float otherBottomY = rect.origin.y;

        if (otherRightX < otherLeftX) {
            float tmp = otherRightX;
            otherRightX = otherLeftX;
            otherLeftX = tmp;
            //swap(otherRightX, otherLeftX)
        }

        if (otherTopY < otherBottomY) {
            float tmp = otherTopY;
            otherTopY = otherBottomY;
            otherBottomY = tmp;
            //swap(otherTopY, otherBottomY)
        }

        float combinedLeftX = Math.min(thisLeftX, otherLeftX);
        float combinedRightX = Math.max(thisRightX, otherRightX);
        float combinedTopY = Math.max(thisTopY, otherTopY);
        float combinedBottomY = Math.min(thisBottomY, otherBottomY);

        set(combinedLeftX, combinedBottomY, combinedRightX - combinedLeftX, combinedTopY - combinedBottomY);
    }

    @Override
    public boolean equals(Object obj) {
        return new EzyEquals<Rect>()
            .function(t -> t.origin)
            .function(t -> t.size)
            .isEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return new EzyHashCodes()
            .append(origin)
            .append(size)
            .toHashCode();
    }

}
