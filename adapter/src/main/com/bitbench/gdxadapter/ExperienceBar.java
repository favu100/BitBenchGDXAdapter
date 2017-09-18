package com.bitbench.gdxadapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bitbench.gdxadapter.BBGDXItem;
import com.bitbench.gdxadapter.GdxEngine;

import static com.bitbench.modernpong.util.GdxUtils.sSkin;
import static com.bitbench.modernpong.util.GdxUtils.sStrings;

/**
 * Created by fabian on 13.09.17.
 */


public class ExperienceBar extends BBGDXItem {

    private transient ProgressBar progressBar;

    private transient Label experienceLabel;

    private transient Label levelLabel;

    private Color reached;

    private Color notReached;

    public ExperienceBar() {

    }

    public void initialize(final Stage stage) {
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();
        ProgressBar.ProgressBarStyle style = createProgressbarStyle(width * screenWidth, height * screenHeight);
        this.progressBar = new ProgressBar(0.0f, 1.0f, 0.001f, false, style);
        this.experienceLabel = new Label("", sSkin.get("experience", Label.LabelStyle.class));
        this.levelLabel = new Label("", sSkin);
        initProgressBar(width * screenWidth, height * screenWidth);
        stage.addActor(progressBar);
        stage.addActor(experienceLabel);
        stage.addActor(levelLabel);
    }


    private ProgressBar.ProgressBarStyle createProgressbarStyle(float width, float height) {
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        Texture notReachedTexture = GdxEngine.createPolygonTexture(width, height, notReached);
        progressBarStyle.background = new TextureRegionDrawable(new TextureRegion(notReachedTexture));
        Texture expBeforeTexture = GdxEngine.createPolygonTexture(0, height, reached);
        progressBarStyle.knob = new TextureRegionDrawable(new TextureRegion(expBeforeTexture));
        Texture expTexture = GdxEngine.createPolygonTexture(width, height, reached);
        progressBarStyle.knobBefore = new TextureRegionDrawable(new TextureRegion(expTexture));
        return progressBarStyle;
    }

    private void initProgressBar(float barWidth, float barHeight) {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        progressBar.setValue(0.0f);
        progressBar.setAnimateDuration(0.0f);
        progressBar.setBounds(xPos*width, yPos*(height+barHeight/2), barWidth, barHeight);
        experienceLabel.setBounds(xPos*width, yPos*(height+barHeight/2), barWidth, barHeight);
        levelLabel.setBounds(xPos*width, (yPos-0.0325f)*(height+barHeight/2), barWidth, barHeight);
    }

    public void update(float reached, float minLevelPoints, float nextLevelPoints, int level) {
        progressBar.setValue((reached - minLevelPoints)/(nextLevelPoints - minLevelPoints));
        experienceLabel.setText(String.valueOf((int) reached) + "/" + String.valueOf((int) nextLevelPoints));
        levelLabel.setText(sStrings.format("level_number", level));
    }


}
