package com.bitbench.modernpong.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.Json;

import static com.bitbench.modernpong.util.GdxUtils.sSkin;

/**
 * Created by fabian on 16.09.17.
 */

public class BBGDXLayoutManager {

    public static BBGDXStandardLayout loadLayout(String file, Stage stage, I18NBundle bundle) {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        Json json = new Json();
        BBGDXStandardLayout layout = json.fromJson(BBGDXStandardLayout.class, Gdx.files.internal(file));

        for(String key : layout.getItems().keySet()) {
            String text;
            BBGDXItem item = layout.getItems().get(key);
            if(item instanceof BBGDXButton) {
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
            }
        }
        return layout;
    }

    public static BBGDXStandardLayout loadLayout(String file, Stage stage) {
        return loadLayout(file, stage, null);
    }

}
