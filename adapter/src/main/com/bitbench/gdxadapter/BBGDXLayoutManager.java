package com.bitbench.gdxadapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.Json;
import com.bitbench.gdxadapter.ExperienceBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static com.bitbench.util.GdxUtils.sSkin;

/**
 * Created by fabian on 16.09.17.
 */

public class BBGDXLayoutManager {

    public static BBGDXStandardLayout loadLayout(String file, Stage stage, I18NBundle bundle) {
        Json json = new Json();
        BBGDXStandardLayout layout = json.fromJson(BBGDXStandardLayout.class, Gdx.files.internal(file));
        for(String key : layout.getItems().keySet()) {
            BBGDXItem item = layout.getItems().get(key);
            loadItem(stage, item, bundle);
        }
        return layout;
    }

    public static BBGDXStandardLayout loadLayout(String file, Stage stage) {
        return loadLayout(file, stage, null);
    }

    private static Actor loadItem(Stage stage, BBGDXItem item, I18NBundle bundle) {
        Actor actor = loadButton(stage, item, bundle);
        if(actor != null) {
            return actor;
        }
        actor = loadScrollPane(stage, item, bundle);
        if(actor != null) {
            return actor;
        }
        actor = loadTable(item);
        if(actor != null) {
            return actor;
        }
        loadExpiernceBar(stage, item);
        return null;
    }

    private static ScrollPane loadScrollPane(Stage stage, BBGDXItem item, I18NBundle bundle) {
        if(item instanceof BBGDXScrollPane) {
            int width = Gdx.graphics.getWidth();
            int height = Gdx.graphics.getHeight();
            BBGDXItem children = ((BBGDXScrollPane) item).getChildren();
            ScrollPane scrollPane = new ScrollPane(loadItem(stage, children, bundle), sSkin);
            scrollPane.setBounds(item.getXPos()*width, item.getYPos()*height, item.getWidth()*width, item.getHeight()*height);
            stage.addActor(scrollPane);
        }
        return null;
    }

    private static Table loadTable(BBGDXItem item) {
        if(item instanceof BBGDXTable) {
            int width = Gdx.graphics.getWidth();
            int height = Gdx.graphics.getHeight();
            Table table = new Table(sSkin);
            ArrayList<String> alignment = ((BBGDXTable) item).getAlignment();
            if(alignment.contains("left")) {
                table.left();
            }
            if(alignment.contains("right")) {
                table.right();
            }
            if(alignment.contains("top")) {
                table.top();
            }
            if(alignment.contains("bottom")) {
                table.bottom();
            }
            table.pad(height * ((BBGDXTable) item).getPadding());
            HashMap<String, BBGDXTableColumn> columns = ((BBGDXTable) item).getColumns();
            Iterator<String> iterator = columns.keySet().iterator();
            while(iterator.hasNext()) {
                String key = iterator.next();
                BBGDXTableColumn column = columns.get(key);
                int index = column.getIndex();
                if(column.getPrefWidth() > 0) {
                    table.columnDefaults(index).prefWidth(width * item.getWidth());
                }
                if(column.getSpace() > 0) {
                    table.columnDefaults(index).space(height * column.getSpace());
                }
                if(column.isExpandX()) {
                    table.columnDefaults(index).expandX();
                }
                if(column.getMinWidth() > 0) {
                    table.columnDefaults(index).minWidth(width * column.getMinWidth());
                }
            }
            ((BBGDXTable) item).setTable(table);
            return table;
        }
        return null;
    }

    private static ImageTextButton loadButton(Stage stage, BBGDXItem item, I18NBundle bundle) {
        if(item instanceof BBGDXButton) {
            int width = Gdx.graphics.getWidth();
            int height = Gdx.graphics.getHeight();
            String text;
            if(bundle == null) {
                text = ((BBGDXButton) item).getText();
            } else {
                text = bundle.get(((BBGDXButton) item).getText().replace("@",""));
            }
            ImageTextButton button = new ImageTextButton(text, sSkin);
            button.setSize(item.getWidth()*width, item.getHeight()*height);
            button.setPosition(item.getXPos()*width, item.getYPos()*height);
            ((BBGDXButton) item).setButton(button);
            stage.addActor(button);
            return button;
        }
        return null;
    }

    private static void loadExpiernceBar(Stage stage, BBGDXItem item) {
        if(item instanceof ExperienceBar) {
            ((ExperienceBar) item).initialize(stage);
        }
    }

}
