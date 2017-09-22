/*
 * Copyright © 2017 BitBench. All rights reserved.
 */

package com.bitbench.gdxadapter;

import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;

import java.util.ArrayList;
import java.util.HashMap;


public class BBGDXStandardLayout {

    private HashMap<String,BBGDXItem> items;

    private ArrayList<String> buttonGroups;

    private transient HashMap<String, ButtonGroup<ImageTextButton>> buttonGroupMap;

    public BBGDXStandardLayout() {
        this.items = new HashMap<>();
        this.buttonGroups = new ArrayList<>();
        this.buttonGroupMap = new HashMap<>();
    }

    public void initialize() {
        for(String buttonGroupKey : buttonGroups) {
            ButtonGroup<ImageTextButton> buttonGroup = new ButtonGroup<>();
            buttonGroup.setMinCheckCount(1);
            buttonGroup.setMaxCheckCount(1);
            buttonGroup.setUncheckLast(true);
            buttonGroupMap.put(buttonGroupKey, buttonGroup);
        }

        for(String key : items.keySet()) {
            if(items.get(key) instanceof BBGDXCheckableButton) {
                BBGDXCheckableButton button = (BBGDXCheckableButton) items.get(key);
                if(button.isChecked()) {
                    ((ImageTextButton) button.getActor()).setChecked(true);
                }
                addButton(button);
            }
        }
    }

    public HashMap<String,BBGDXItem> getItems() {
        return items;
    }

    private void addButton(BBGDXCheckableButton button) {
        buttonGroupMap.get(button.getButtonGroup()).add((ImageTextButton) button.getActor());
    }
}
