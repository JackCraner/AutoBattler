package com.mygdx.game.Spells;

import com.mygdx.game.Cards.CanCard;
import com.mygdx.game.Cards.CardTypes;
import com.mygdx.game.Spells.Conditions.Condition;
import com.mygdx.game.Spells.Conditions.ConditionObject;
import com.mygdx.game.Spells.Conditions.ConditionType;
import com.mygdx.game.Spells.Effects.BuffEffect;
import com.mygdx.game.Spells.Effects.CanEffect;
import com.mygdx.game.Spells.Effects.DamageEffect;
import com.mygdx.game.Spells.Effects.Effect;
import com.mygdx.game.Spells.Effects.EffectTypes.DamageTypes;
import com.mygdx.game.Spells.Effects.EffectTypes.ManaEffect;
import com.mygdx.game.Spells.Effects.EffectTypes.StatusEffect;
import com.mygdx.game.Spells.Effects.TargetTypes;
//Change to Class Factory Pattern so I can make new spells with same name for damage buffs/mana buffs
// Have ENUM of all spell Names and basis stats (cast time and mana cost)
public enum Spell implements CanCard
{
    //WHEN MAKING NEW SPELL
    // FIRST ADD HERE WITH EFFECTS
    // CREATE ASSET FOR IT
    // LINK SPELL TO ASSET

    //MANA
    MANABALL("Mana Ball", CardTypes.NEUTRAL, Asset.MANA,3,0,new Condition( new CanEffect[]{new Effect(ManaEffect.MANA,5, TargetTypes.SELF)})),

    POISONARROW("Poison Arrow", CardTypes.NATURE, Asset.POISON,2,2,new Condition(new CanEffect[]{new Effect(StatusEffect.POISON,5,TargetTypes.OTHER)})),
    ICEBOLT("Ice Bolt", CardTypes.ICE,Asset.HEALBALL,2,2,new Condition( new CanEffect[]{new Effect(StatusEffect.FREEZE,3,TargetTypes.OTHER)})),




    //GameSet
    //0 Mana
    ARCANEBOLT("Arcane Bolt", CardTypes.ARCANE,Asset.ARCANEBOLT,1,0,new Condition(new CanEffect[]{new DamageEffect(DamageTypes.ARCANE,2,TargetTypes.OTHER)})),
    GUST("Gust",CardTypes.WIND,Asset.GUST,3,0,new Condition( new CanEffect[]{new DamageEffect(DamageTypes.WIND,8,TargetTypes.OTHER)})),
    FIREBOLT("Firebolt", CardTypes.FIRE,Asset.FIREBOLT,1,0,new Condition( new CanEffect[]{new DamageEffect(DamageTypes.FIRE,2,TargetTypes.OTHER)})),
    EMBER("Ember", CardTypes.FIRE,Asset.EMBER,2,0,new Condition(new CanEffect[]{new Effect(StatusEffect.BURN,2,TargetTypes.OTHER)}, new ConditionObject(ConditionType.ONPERCENTAGE,50f),new CanEffect[]{})),
    //POISONDART("Poison Dart", CardTypes.NATURE, Asset.POISON)
    ICESPEAR("Ice Spear", CardTypes.ICE,Asset.ICESPEAR,1,0, new Condition(new CanEffect[]{new DamageEffect(DamageTypes.ICE,1,TargetTypes.OTHER)})),
    ICEBOOST("Ice Boost", CardTypes.ICE, Asset.ICESPEAR,1,0,new Condition(new CanEffect[]{new BuffEffect(DamageTypes.ICE,50,10,TargetTypes.SELF)}));
    ;


    private String name;
    private int castTime;
    private  int manaCost;
    private Asset asset;
    private Condition condition;
    private CardTypes type;

    Spell(String name, CardTypes type,Asset a, int castTime, int manaCost, Condition conditionEffect)
    {
        this.name = name;
        this.type =type;
        this.asset = a;
        this.castTime = castTime;
        this.manaCost = manaCost;
        this.condition = conditionEffect;
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

    public Condition getCondition() {
        return condition;
    }

    public Asset getAsset() {
        return asset;
    }


    public String getDescription()
    {
        String d = "";

        d = condition.getDescription();
        for (CanEffect e:condition.getEffectOnPass())
        {
            d += e.getDescription() + "\n";
        }
        if (condition.getEffectOnFail().length > 0)
        {
            d+= " On Fail \n";
            for (CanEffect e:condition.getEffectOnFail())
            {
                d += e.getDescription() + "\n";
            }
        }

        return d;

    }

    @Override
    public String getSplashArt() {
        return asset.getIcon();
    }

    @Override
    public CardTypes getCardBase() {
        return type;
    }
}
