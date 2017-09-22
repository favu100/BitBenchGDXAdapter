/*
 * Copyright © 2017 BitBench. All rights reserved.
 */

package com.bitbench.gdxadapter;


public class BBGDXTableColumn {

    private float prefWidth;

    private float space;

    private boolean expandX;

    private float minWidth;

    private String name;

    private int index;

    public BBGDXTableColumn(){}

    public float getPrefWidth() {
        return prefWidth;
    }

    public float getSpace() {
        return space;
    }

    public boolean isExpandX() {
        return expandX;
    }

    public float getMinWidth() {
        return minWidth;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
