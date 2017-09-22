/*
 * Copyright © 2017 BitBench. All rights reserved.
 */

package com.bitbench.gdxadapter;


public class BBGDXCheckableButton extends BBGDXButton {

    protected String buttonGroup;

    protected boolean checked;

    public BBGDXCheckableButton(){}


    public String getButtonGroup() {
        return buttonGroup;
    }

    public boolean isChecked() {
        return checked;
    }
}
