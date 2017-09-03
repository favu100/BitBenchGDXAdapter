package com.bitbench.gdxadapter;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by fabian on 09.04.17.
 */

public class Vector extends Vector2 {

    /**
     * creates Vector with giving x and y coordinate
     * @param x - x-position of the vector
     * @param y - y-position of the vector
     */
    public Vector(float x, float y) {
        super(x,y);
    }


    /**
     * creates Vector from an other vector, both vectors have the same coordinate
     * @param v - the other vector
     */
    public Vector(Vector v) {
        super(v);
    }


    /**
     * empty default constructor because we need this for GSON injection
     * so the x and y-coordinates are set by GSON
     */
    public Vector() {

    }

    /**
     *
     * @return a new vector with the same coordinate as this vector
     */
    @Override
    public Vector cpy() {
        return new Vector(this);
    }

    /**
     *
     * @return returns the x-coordinate of the vector
     */
    public float getX() {
        return x;
    }


    /**
     *
     * @return returns the y-coordinate of the vector
     */
    public float getY() {
        return y;
    }

    /**
     *
     * @param x - sets a new x-coordinate from this parameter
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     *
     * @param y - sets a new y-coordinate from this parameter
     */
    public void setY(float y) {
        this.y = y;
    }


    /**
     *
     * @param v - gets an other Vector v
     * @return and adds the given vector to this vector and returns the result in a new vector
     */
    public Vector add(Vector2 v) {
        float newX = (float) Math.max(-Float.MAX_VALUE, Math.min(Float.MAX_VALUE, (double) (this.x) + v.x));
        float newY = (float) Math.max(-Float.MAX_VALUE, Math.min(Float.MAX_VALUE, (double) (this.y) + v.y));
        return new Vector(newX,newY);
    }


    /**
     *
     * @param x - gets a x-coordinate
     * @param y - gets a y-coordinate
     * @return and adds the given coordinates to this vector and returns the result in a new vector
     */
    public Vector add(float x, float y) {
        float newX = (float) Math.max(-Float.MAX_VALUE, Math.min(Float.MAX_VALUE, this.x + (double) x));
        float newY = (float) Math.max(-Float.MAX_VALUE, Math.min(Float.MAX_VALUE, this.y + (double) y));
        return new Vector(newX, newY);
    }

    /**
     *
     * @return returns the length of this vector
     */
    public float abs() {
        return (float) ((Math.sqrt(Math.pow(x,2) + Math.pow(y,2))));
    }

    /**
     *
     * @return returns the additive inverse of this vector
     */
    public Vector additiveInverse() {
        return new Vector(-x, -y);
    }

    /**
     *
     * @param fak - gets a factor
     * @return returns the multiplication of the giving factor to this vector in a new vector
     */
    public Vector mulWith(float fak) {
        return new Vector((float) Math.max(-Float.MAX_VALUE, Math.min(Float.MAX_VALUE, x * (double) fak)) ,
                        Math.max(-Float.MAX_VALUE, (float) Math.min(Float.MAX_VALUE,y * (double) fak)));
    }

    /**
     *
     * @return returns a vector with the same direction of this vector with length 1 in a ne vector
     */
    public Vector unitVector() {
        if(abs() == 0) {
            return new Vector(0,0);
        }
        return new Vector(x, y).mulWith(1 / abs());
    }

}
