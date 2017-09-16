package com.bitbench.modernpong.util;

import java.util.HashMap;

/**
 * Created by fabian on 16.09.17.
 */

public class BBGDXStandardLayout {

    private HashMap<String,BBGDXItem> items;

    public BBGDXStandardLayout() {
        this.items = new HashMap<>();
    }

    public HashMap<String,BBGDXItem> getItems() {
        return items;
    }
}
