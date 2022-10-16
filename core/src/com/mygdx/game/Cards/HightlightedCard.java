package com.mygdx.game.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.StaticTerms;

import java.util.Arrays;
import java.util.Objects;

public class HightlightedCard extends Group
{
    private Card card;
    public HightlightedCard(Card c)
    {
        this.card = c;
        c.setPosition(0,0);
        addActor(c);
        addActor(defineInfoCards(c.getCardItem().getDescription()));

    }

    public Group defineInfoCards(String description)
    {
        int numOfInfoCards = 0;
        Group base = new Group();
        for (StaticTerms t:StaticTerms.values())
        {
            for (String s:description.split("\\W+"))
            {

                if (Objects.equals(t.getName(), s))
                {
                    InfoCard i = new InfoCard(t,card.getScale());
                    i.setPosition(-300*card.getScale(),card.getHeight()-i.getHeight() -30 -(300*numOfInfoCards*card.getScale()));
                    numOfInfoCards++;
                    base.addActor(i);

                }
            }
        }
        return base;
    }

    public Card getCard() {
        return card;
    }

    @Override
    public void setScale(float scaleXY) {
        super.setScale(scaleXY);
        card.setScale(scaleXY);
        clear();
        addActor(card);
        addActor(defineInfoCards(card.getCardItem().getDescription()));
    }

    @Override
    public float getHeight() {
        return this.card.getHeight();
    }

    @Override
    public float getWidth() {
        return this.card.getWidth();
    }
}
class InfoCard extends Group
{
    private Image cardBase;
    private Label desc;
    private Label title;
    public InfoCard(StaticTerms term, float scale)
    {
        this.cardBase = new Image(new Texture(Gdx.files.local("assets/CardBase/InfoCard.png")));
        cardBase.setSize(300*scale,300*scale);
        this.desc = new Label(term.getDescription(), MyGdxGame.skin,"default");
        this.desc.setSize(200*scale,200*scale);
        this.desc.setWrap(true);
        this.desc.setFontScale(getTextSize(term.getDescription(),scale));
        this.desc.setPosition(50*scale,20*scale);
        this.desc.setAlignment(Align.center);
        this.title = new Label(term.getName(),MyGdxGame.skin,"title");
        this.title.setSize(200*scale,200*scale);
        this.title.setWrap(true);
        this.title.setFontScale(getTitleSize(term.getName(),scale));
        this.title.setAlignment(Align.center);
        this.title.setPosition(50*scale,150*scale);

        addActor(cardBase);
        addActor(this.desc);
        addActor(this.title);
    }

    @Override
    public float getHeight() {
        return cardBase.getHeight();
    }
    public float getTextSize(String s, float scale)
    {
        if (s.length() > 8)
        {
            return(float) (scale*3/(1 + (s.length()*0.025)));
        }
        return scale*3;
    }
    public float getTitleSize(String s, float scale)
    {
        return(float) (scale/(1 + (s.length()*0.02)));

    }


}
