/*
 * Copyright © 2017 BitBench. All rights reserved.
 */

package com.bitbench.gdxadapter;

import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class BBGDXLabel extends BBGDXItem {

    private String text;

    private boolean center;

    private boolean hasPlaceholder;

    private int[] placeholder;

    private transient Label label;

    public BBGDXLabel(){}

    public String getText() {
        return text;
    }

    public boolean isCenter() {
        return center;
    }

    public boolean hasPlaceholder() {
        return hasPlaceholder;
    }

    public int[] getPlaceholder() {
        return placeholder;
    }
}
