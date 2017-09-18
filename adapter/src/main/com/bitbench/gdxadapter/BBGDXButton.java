package com.bitbench.gdxadapter;

import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;

/**
 * Created by fabian on 16.09.17.
 */

public class BBGDXButton extends BBGDXItem {

    private ImageTextButton button;

    private String text;

    public BBGDXButton() {}

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
