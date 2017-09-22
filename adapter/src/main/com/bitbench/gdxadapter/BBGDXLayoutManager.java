/*
 * Copyright © 2017 BitBench. All rights reserved.
 */

package com.bitbench.gdxadapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static com.bitbench.gdxadapter.GdxUtils.sSkin;


public class BBGDXLayoutManager {

    public static BBGDXStandardLayout loadLayout(String file, Stage stage, I18NBundle bundle) {
        Json json = new Json();
        BBGDXStandardLayout layout = json.fromJson(BBGDXStandardLayout.class, Gdx.files.internal(file));
        for(String key : layout.getItems().keySet()) {
            BBGDXItem item = layout.getItems().get(key);
            loadItem(stage, item, bundle);
        }
        layout.initialize();
        return layout;
    }

    public static BBGDXStandardLayout loadLayout(String file, Stage stage) {
        return loadLayout(file, stage, null);
    }

    public static Actor loadItem(Stage stage, BBGDXItem item, I18NBundle bundle) {
        Actor actor = loadTextureButton(stage, item);
        if(actor != null) {
            return actor;
        }
        actor = loadButton(stage, item, bundle);
        if(actor != null) {
            return actor;
        }
        actor = loadLabel(stage, item, bundle);
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
        actor = loadSelectBox(stage, item, bundle);
        if(actor != null) {
            return actor;
        }
        actor = loadCheckBox(stage, item);
        if(actor != null) {
            return actor;
        }
        loadExperienceBar(stage, item);
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
            if(bundle == null || !((BBGDXButton) item).getText().startsWith("@")) {
                text = ((BBGDXButton) item).getText();
            } else {
                text = bundle.get(((BBGDXButton) item).getText().replace("@",""));
            }
            ImageTextButton button;
            if(item instanceof BBGDXToggleButton) {
                button = new ImageTextButton(text, sSkin.get("toggle", ImageTextButton.ImageTextButtonStyle.class));
                button.setTransform(true);
            } else if(item instanceof BBGDXRadioButton) {
                button = new ImageTextButton(text, sSkin.get("radio", ImageTextButton.ImageTextButtonStyle.class));
                button.setTransform(true);
            } else {
                button = new ImageTextButton(text, sSkin);
            }
            button.setSize(item.getWidth()*width, item.getHeight()*height);
            button.setPosition(item.getXPos()*width, item.getYPos()*height);
            item.setActor(button);
            stage.addActor(button);
            return button;
        }
        return null;
    }

    //TODO: just works for ActionBarButtons for the moment
    private static ImageButton loadTextureButton(Stage stage, BBGDXItem item) {
        if(item instanceof BBGDXTextureButton) {
            int width = Gdx.graphics.getWidth();
            int height = Gdx.graphics.getHeight();
            BBGDXTextureButton gdxButton = (BBGDXTextureButton) item;
            ImageButton button = GdxEngine.createImageButton(gdxButton.getPathUp(), gdxButton.getPathDown(), gdxButton.isToggle());
            float size = button.getHeight() / Constants.ACTIONBAR_XXXHDPI_DENSITY_FACTOR * Gdx.graphics.getDensity();
            button.setSize(size, size);
            if (item instanceof BBGDXActionBarButton) {
                button.setPosition(width - (size + width * 0.025f) - (size + width * 0.05f) *
                                (((BBGDXActionBarButton) gdxButton).getIndexFromRight()),
                        height * 0.95f - size/2);
            }
            item.setActor(button);
            stage.addActor(button);
            return button;
        }
        return null;
    }

    private static Label loadLabel(Stage stage, BBGDXItem item, I18NBundle bundle) {
        if(item instanceof BBGDXLabel) {
            int width = Gdx.graphics.getWidth();
            int height = Gdx.graphics.getHeight();
            String text;
            if(bundle == null || !((BBGDXLabel) item).getText().startsWith("@")) {
                text = ((BBGDXLabel) item).getText();
            } else {
                if(((BBGDXLabel) item).hasPlaceholder()) {
                    Object[] args = new Object[((BBGDXLabel) item).getPlaceholder().length];
                    for(int index : ((BBGDXLabel) item).getPlaceholder()) {
                        args[index] = index;
                    }
                    text = bundle.format(((BBGDXLabel) item).getText().replace("@", ""), args);
                } else {
                    text = bundle.get(((BBGDXLabel) item).getText().replace("@", ""));
                }
            }
            Label label = new Label(text, sSkin);
            if(!((BBGDXLabel) item).isCenter()) {
                label.setPosition(item.getXPos() * width, item.getYPos() * height);
            } else {
                label.setPosition(item.getXPos() * width - label.getPrefWidth()/2, item.getYPos() * height);
            }
            item.setActor(label);
            stage.addActor(label);
            return label;
        }
        return null;
    }

    private static SelectBox loadSelectBox(Stage stage, BBGDXItem item, I18NBundle bundle) {
        if(item instanceof BBGDXSelectBox) {
            SelectBox<String> selectBox = new SelectBox<>(sSkin);
            Array<String> array = new Array<>();
            for(String str : ((BBGDXSelectBox) item).getItems()) {
                String text;
                if(bundle == null || !str.startsWith("@")) {
                    text = str;
                } else {
                    text = bundle.get(str.replace("@",""));
                }
                array.add(text);
            }
            selectBox.setItems(array);
            item.setActor(selectBox);
            stage.addActor(selectBox);
            return selectBox;
        }
        return null;
    }

    private static CheckBox loadCheckBox(Stage stage, BBGDXItem item) {
        if(item instanceof BBGDXCheckBox) {
            int height = Gdx.graphics.getHeight();
            int width = Gdx.graphics.getWidth();
            CheckBox checkBox = new CheckBox("", sSkin);
            if(((BBGDXCheckBox) item).isChecked()) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
            //checkBox.getImageCell().size(width * item.getWidth(), height * item.getHeight());
            item.setActor(checkBox);
            stage.addActor(checkBox);
            return checkBox;
        }
        return null;
    }

    private static void loadExperienceBar(Stage stage, BBGDXItem item) {
        if(item instanceof ExperienceBar) {
            ((ExperienceBar) item).initialize(stage);
        }
    }

}
