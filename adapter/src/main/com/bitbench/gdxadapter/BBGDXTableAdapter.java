/*
 * Copyright © 2017 BitBench. All rights reserved.
 */

package com.bitbench.gdxadapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.ArrayList;
import java.util.HashMap;


public class BBGDXTableAdapter {

    private ArrayList<? extends BBGDXTableItem> items;

    public BBGDXTableAdapter(ArrayList<? extends BBGDXTableItem> items) {
        this.items = items;
    }

    public void apply(BBGDXTable table, Stage stage, I18NBundle bundle) {
        int height = Gdx.graphics.getHeight();
        int width = Gdx.graphics.getWidth();
        Table gdxTable = table.getTable();
        gdxTable.clearChildren();
        HashMap<Integer, BBGDXTableColumn> indexMap = new HashMap<>();
        HashMap<Integer, Object> keyMap = new HashMap<>();

        for(BBGDXTableColumn columns : table.getColumns().values()) {
            indexMap.put(columns.getIndex(), columns);
        }

        for(Object obj: table.getColumns().keySet()) {
            keyMap.put(table.getColumns().get(obj).getIndex(), obj);
        }


        for(int i = 0; i < table.getColumns().size(); i++) {
            String name;
            String key = indexMap.get(i).getName();
            if(bundle == null || !key.startsWith("@")) {
                name = key;
            } else {
                name = bundle.get(key.replace("@",""));
            }
            gdxTable.add(name);
        }

        for(BBGDXTableItem item: items) {
            gdxTable.row();
            for(int i = 0; i < table.getColumns().size(); i++) {
                Object obj = item.getRow().get(keyMap.get(i));
                if(obj instanceof CharSequence) {
                    gdxTable.add((CharSequence) obj);
                } else if(obj instanceof BBGDXItem) {
                    BBGDXLayoutManager.loadItem(stage, (BBGDXItem) obj, bundle);
                    BBGDXItem gdxItem = (BBGDXItem) obj;
                    gdxTable.add(gdxItem.getActor()).width(gdxItem.getWidth() * width).height(gdxItem.getHeight() * height);
                }
            }
        }
    }

}
