package com.tvd12.javaexample.javassist;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Point {
	private int x;
    private int y;
 
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
