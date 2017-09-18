package com.bitbench.gdxadapter;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.ArrayList;
import java.util.HashMap;

import static com.bitbench.modernpong.util.GdxUtils.sStrings;

/**
 * Created by fabian on 18.09.17.
 */

public class BBGDXTable extends BBGDXItem {

    private HashMap<String, BBGDXTableColumn> columns;

    private ArrayList<String> alignment;

    private float padding;

    private transient BBGDXTableAdapter adapter;

    private transient Table table;

    public BBGDXTable(){}

    public HashMap<String, BBGDXTableColumn> getColumns() {
        return columns;
    }

    public ArrayList<String> getAlignment() {
        //TODO: handling errors
        return alignment;
    }

    public float getPadding() {
        return padding;
    }

    public void setAdapter(BBGDXTableAdapter adapter) {
        this.adapter = adapter;
        adapter.apply(this, sStrings);

    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }
}
