package com.mygdx.game.Screens.BuyPhase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Spells.Asset;
import com.mygdx.game.Spells.Spell;

public class Card extends Group
{
    private float width = 440;
    private float height = 674;
    private Spell spell;
    private Image cardSprite;
    private Image imageSprite;
    private Label spellTitle;
    private Label spellDescription;
    private Label spellCostLabel;
    private Label castTimeLabel;
    private float scale;
    private Asset design;


    public Card(Spell spell, float scale)
    {
        this.spell = spell;
        this.scale =scale;

        design = spell.getAsset();
        cardSprite = new Image(new Texture(Gdx.files.local("assets/CardBase.png")));
        cardSprite.setSize(width * scale,height * scale);
        addActor(cardSprite);

        imageSprite = new Image(new Texture(Gdx.files.local(design.getIcon())));
        imageSprite.setSize(300*scale,300*scale);
        imageSprite.setPosition(cardSprite.getX()+(cardSprite.getWidth()*0.18f),cardSprite.getY()+(cardSprite.getHeight()*0.45f));
        addActor(imageSprite);

        spellTitle = new Label(spell.getName(), MyGdxGame.skin,"title");
        spellTitle.setFontScale(scale);
        spellTitle.setWrap(true);
        spellTitle.setAlignment(Align.center);
        spellTitle.setWidth(270 * scale);
        spellTitle.setHeight(40*scale);
        spellTitle.setPosition(cardSprite.getX() + (float)(cardSprite.getWidth()*0.21),(cardSprite.getY()+(cardSprite.getHeight()*0.41f))-(spellTitle.getHeight()));
        addActor(spellTitle);

        spellDescription = new Label(spell.getDescription(), MyGdxGame.skin,"default");
        spellDescription.setFontScale(scale*1.2f);
        spellDescription.setWrap(true);
        spellDescription.setAlignment(Align.center);
        spellDescription.setWidth(270 * scale);
        spellDescription.setPosition(cardSprite.getX() + (float)(cardSprite.getWidth()*0.21),(cardSprite.getY()+(cardSprite.getHeight()*0.25f))-(spellDescription.getHeight()));
        addActor(spellDescription);

        spellCostLabel = new Label(Integer.toString(spell.getManaCost()),MyGdxGame.skin,"title");
        spellCostLabel.setFontScale(scale);
        spellCostLabel.setWrap(true);
        spellCostLabel.setAlignment(Align.center);
        spellCostLabel.setWidth(270 * scale);
        spellCostLabel.setHeight(32 * scale);
        spellCostLabel.setPosition(cardSprite.getX()- (float)(cardSprite.getWidth()*0.25),(cardSprite.getY()+(cardSprite.getHeight()*0.96f))-(spellCostLabel.getHeight()));
        addActor(spellCostLabel);

        castTimeLabel = new Label(Integer.toString(spell.getCastTime()),MyGdxGame.skin,"title");
        castTimeLabel.setFontScale(scale);
        castTimeLabel.setWrap(true);
        castTimeLabel.setAlignment(Align.center);
        castTimeLabel.setWidth(270 * scale);
        castTimeLabel.setHeight(32 * scale);
        castTimeLabel.setPosition(cardSprite.getX()+ (float)(cardSprite.getWidth()*0.57),(cardSprite.getY()+(cardSprite.getHeight()*0.96f))-(castTimeLabel.getHeight()));
        addActor(castTimeLabel);


    }

    public Spell getSpell() {
        return spell;
    }

    @Override
    public float getHeight() {
        return cardSprite.getHeight();
    }

    @Override
    public float getWidth() {
        return cardSprite.getWidth();
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }

    @Override
    public void setScale(float scale) {
        this.scale = scale;
        cardSprite.setWidth(width * scale);
        cardSprite.setHeight(height * scale);
        scale();
        move();

    }



    @Override
    protected void positionChanged() {
        super.positionChanged();

    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return x >= 0 && x < width * scale && y >= 0 && y < height * scale ? this : null;
        //return x >= getX() - getParent().getX() && x <getX()  - getParent().getX() + cardSprite.getWidth() && y >= getY() - getParent().getY() && y <getY()+ cardSprite.getHeight()- getParent().getY()? this : null;
    }

    public void move()
    {

        imageSprite.setPosition(cardSprite.getX()+(cardSprite.getWidth()*0.18f),cardSprite.getY()+(cardSprite.getHeight()*0.45f));
        spellTitle.setPosition(cardSprite.getX() + (float)(cardSprite.getWidth()*0.21),(cardSprite.getY()+(cardSprite.getHeight()*0.41f))-(spellTitle.getHeight()));
        spellDescription.setPosition(cardSprite.getX() + (float)(cardSprite.getWidth()*0.21),(cardSprite.getY()+(cardSprite.getHeight()*0.25f))-(spellDescription.getHeight()));
        spellCostLabel.setPosition(cardSprite.getX()- (float)(cardSprite.getWidth()*0.25),(cardSprite.getY()+(cardSprite.getHeight()*0.96f))-(spellCostLabel.getHeight()));
        castTimeLabel.setPosition(cardSprite.getX()+ (float)(cardSprite.getWidth()*0.57),(cardSprite.getY()+(cardSprite.getHeight()*0.96f))-(castTimeLabel.getHeight()));

    }
    public void scale()
    {

        spellTitle.setFontScale(scale);
        spellDescription.setFontScale(scale*1.2f);
        spellCostLabel.setFontScale(scale);
        castTimeLabel.setFontScale(scale);


        imageSprite.setSize(300*scale,300*scale);
        spellTitle.setWidth(270 * scale);
        spellTitle.setHeight(40*scale);
        spellDescription.setWidth(270 * scale);
        spellCostLabel.setWidth(270 * scale);
        spellCostLabel.setHeight(32 * scale);
        castTimeLabel.setWidth(270 * scale);
        castTimeLabel.setHeight(32 * scale);


    }

}
