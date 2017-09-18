package com.bitbench.gdxadapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by fabian on 18.09.17.
 */

public class BBGDXTableAdapter {

    private ArrayList<? extends BBGDXTableItem> items;

    public BBGDXTableAdapter(ArrayList<? extends BBGDXTableItem> items) {
        this.items = items;
    }

    public void apply(BBGDXTable table, I18NBundle bundle) {
        Table gdxTable = table.getTable();
        gdxTable.clearChildren();
        HashMap<Integer, BBGDXTableColumn> indexMap = new HashMap<>();
        HashMap<Integer, String> keyMap = new HashMap<>();

        for(BBGDXTableColumn columns : table.getColumns().values()) {
            indexMap.put(columns.getIndex(), columns);
        }

        for(String str: table.getColumns().keySet()) {
            keyMap.put(table.getColumns().get(str).getIndex(), str);
        }


        for(int i = 0; i < table.getColumns().size(); i++) {
            String name;
            String key = indexMap.get(i).getName();
            if(bundle == null) {
                name = key;
            } else {
                name = bundle.get(key.replace("@",""));
            }
            gdxTable.add(name);
        }

        for(BBGDXTableItem item: items) {
            gdxTable.row();
            for(int i = 0; i < table.getColumns().size(); i++) {
                gdxTable.add(item.getRow().get(keyMap.get(i)));
            }
        }
    }

}
