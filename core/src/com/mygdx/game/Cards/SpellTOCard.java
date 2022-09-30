package com.mygdx.game.Cards;

import com.mygdx.game.AssetFinder.CardBaseAsset;
import com.mygdx.game.SpellLogic.Spell;
import com.mygdx.game.SpellLogic.SpellFactory;

public class SpellTOCard implements CanCard
{
    private Spell spell;

    public SpellTOCard(Spell spell)
    {
        this.spell = spell;
    }

    public Spell getSpell() {
        return spell;
    }

    @Override
    public String getTitle() {
        return spell.getName();
    }

    @Override
    public String getDescription() {
        return spell.getEffectDescription();
    }

    @Override
    public String getSplashArt() {

        for (SpellFactory s: SpellFactory.values())
        {
            if (spell.getName() == s.getSpell().getName())
            {
                return s.getSpellSplash().getLocation();
            }
        }
        return"";

    }

    @Override
    public String getCardBase() {
        for (CardBaseAsset cba: CardBaseAsset.values())
        {
            if (cba.name() == spell.getSpellType().name())
            {
                return cba.getLocation();
            }
        }
        return "";
    }

    @Override
    public int getManaCost() {
        return spell.getManaCost();
    }

    @Override
    public int getOrangeBox() {
        return spell.getCastTime();
    }
}
