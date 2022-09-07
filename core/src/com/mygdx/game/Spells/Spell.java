package com.mygdx.game.Spells;

import com.mygdx.game.Cards.CanCard;
import com.mygdx.game.Spells.Effects.Effect;
import com.mygdx.game.Spells.Effects.EffectBase;
import com.mygdx.game.Spells.Effects.EffectTypes.DamageEffect;
import com.mygdx.game.Spells.Effects.EffectTypes.EffectType;
import com.mygdx.game.Spells.Effects.EffectTypes.HealEffect;
import com.mygdx.game.Spells.Effects.EffectTypes.ManaEffect;

public enum Spell implements CanCard
{
    //WHEN MAKING NEW SPELL
    // FIRST ADD HERE WITH EFFECTS
    // CREATE ASSET FOR IT
    // LINK SPELL TO ASSET

    //MANA
    MANABALL("Mana Ball", Asset.MANA,1,0,new Effect[]{new Effect(ManaEffect.MANA,5)}),
    MANAINFECTION("Mana Infection", Asset.MANA,2,2,new Effect[]{new Effect(ManaEffect.POISON,2)}),
    MANAPUNCH("Mana Punch", Asset.MANA,1,1,new Effect[]{new Effect(ManaEffect.MANA,5), new Effect(DamageEffect.SELF,5)}),
    //FIRE
    FIREBALL("Fireball",Asset.FIREBALL,1,1, new Effect[]{new Effect(DamageEffect.DAMAGE,3)}),
    FireArrow("Fire Arrow", Asset.FIREBALL,2,1,new Effect[]{new Effect(DamageEffect.DAMAGE,5)}),
    BURN("Burn",Asset.BURN,5,1,new Effect[]{new Effect(EffectType.BURN,10)}),

    //POISON
    POISON("PoisonArrow",Asset.POISON,1,1, new Effect[]{new Effect(EffectType.POISON,8)}),
    EXPUNGE("EXPUNGE",Asset.EXPUNGE,2,2,new Effect[]{new Effect(EffectType.EXPUNGE,6)}),
    ACIDRAIN("Acid Rain",Asset.POISON,3,5,new Effect[]{new Effect(EffectType.POISON,8),new Effect(EffectType.EXPUNGE,5)}),
    TOXICDRAIN("Toxic Drain",Asset.POISON,3,5,new Effect[]{new Effect(HealEffect.POISONHEAL,1)}),
    //HEAL
    HEALBALL("Healball",Asset.HEALBALL,2,30,new Effect[]{new Effect(HealEffect.HEAL,35)}),

    //CONTROL
    COUNTERSPELL("Counter Spell",Asset.COUNTERSPELL,1,1,new Effect[]{new Effect(EffectType.COUNTERSPELL,1)}),

;


    private String name;
    private int castTime;
    private  int manaCost;
    private Effect[] effects;
    private Asset asset;
    Spell(String name, Asset a, int castTime, int manaCost, Effect[] effects )
    {
        this.name = name;
        this.asset = a;
        this.castTime = castTime;
        this.manaCost = manaCost;
        this.effects = effects;
    }
    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public int getOrangeBox() {
        return castTime;
    }
    @Override
    public int getManaCost() {
        return manaCost;
    }




    public Effect[] getEffects() {
        return effects;
    }

    public Asset getAsset() {
        return asset;
    }


    public String getDescription()
    {
        String d = "";
        for (EffectBase e: effects)
        {
            d += e.getDescription() + "\n";
        }
        return d;
    }

    @Override
    public String getSplashArt() {
        return null;
    }

    @Override
    public String getCardBase() {
        return null;
    }
}
