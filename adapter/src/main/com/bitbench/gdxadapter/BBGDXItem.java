/*
 * Copyright © 2017 BitBench. All rights reserved.
 */

package com.bitbench.gdxadapter;

import com.badlogic.gdx.scenes.scene2d.Actor;


public abstract class BBGDXItem {

    protected float xPos;

    protected float yPos;

    protected float width;

    protected float height;

    protected transient Actor actor;

    public BBGDXItem() {
    }


    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }
}
