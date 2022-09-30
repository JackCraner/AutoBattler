package com.mygdx.game.Screens.FightPhase.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BattlerSprite extends Actor
{
    private Sprite sprite;



    float width = 336;
    float height = 478;
    float scale;

    public BattlerSprite(float scale)
    {
        super();


        Texture t = new Texture(Gdx.files.internal("Wizard.png"));
        sprite = new Sprite(t);
        this.scale = scale;
        setSize(width * scale,height * scale);
    }




    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        sprite.setSize(width, height);
    }

    @Override
    public void setPosition(float x, float y)
    {
        sprite.setPosition(x,y);
        super.setPosition(x, y);
    }
    public void flip(boolean b,boolean b1)
    {
        sprite.flip(b,b1);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        sprite.setPosition(this.getX(),this.getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
    }
}
