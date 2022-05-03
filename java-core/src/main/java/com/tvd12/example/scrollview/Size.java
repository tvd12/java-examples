package com.tvd12.example.scrollview;

import com.tvd12.ezyfox.util.EzyEquals;
import com.tvd12.ezyfox.util.EzyHashCodes;

public class Size {

    public static final Size ZERO = new Size();
    public float width;
    public float height;

    public Size() {
        this(0, 0);
    }

    public Size(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void set(float w, float h) {
        this.width = w;
        this.height = h;
    }

    public void add(Size other) {
        this.width += other.width;
        this.height += other.height;
    }

    public void minus(Size other) {
        this.width -= other.width;
        this.height -= other.height;
    }

    public void multiple(float a) {
        this.width *= a;
        this.height *= a;
    }

    public void division(float a) {
        if (a == 0.0) {
            throw new IllegalArgumentException("can't division 0");
        }
        this.width /= a;
        this.height /= a;
    }

    @Override
    public boolean equals(Object obj) {
        return new EzyEquals<Size>()
            .function(t -> t.width)
            .function(t -> t.height)
            .isEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return new EzyHashCodes()
            .append(width)
            .append(height)
            .toHashCode();
    }
}
