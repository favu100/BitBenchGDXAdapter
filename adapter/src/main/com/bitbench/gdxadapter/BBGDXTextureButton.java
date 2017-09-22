/*
 * Copyright © 2017 BitBench. All rights reserved.
 */

package com.bitbench.gdxadapter;


public class BBGDXTextureButton extends BBGDXItem {

    private String pathUp;

    private String pathDown;

    private boolean toggle;

    public BBGDXTextureButton(){}

    public String getPathDown() {
        return pathDown;
    }

    public String getPathUp() {
        return pathUp;
    }

    public boolean isToggle() {
        return toggle;
    }
}
