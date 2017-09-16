package com.bitbench.modernpong.util;

import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;

/**
 * Created by fabian on 16.09.17.
 */

public class BBGDXButton extends BBGDXItem {

    private ImageTextButton button;

    public BBGDXButton() {}

    private String text;

    public String getText() {
        return text;
    }

    public void setButton(ImageTextButton button) {
        this.button = button;
    }

    public ImageTextButton getButton() {
        return button;
    }
}
