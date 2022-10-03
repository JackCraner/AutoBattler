package com.mygdx.game.SpellLogic;




import com.mygdx.game.SpellLogic.SpellEffect.Effect;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.EffectType;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.SpellTypes;

import java.util.ArrayList;

public class Spell
{
    private int manaCost;
    private int castTime;
    private Effect[] effects;
    private String name;
    private SpellTypes spellType;
    public Spell(SpellTypes spellType,String name,int manaCost,int castTime,Effect[] effects)
    {
        this.spellType = spellType;
        this.manaCost = manaCost;
        this.castTime =castTime;
        this.effects = effects;
        this.name = name;
    }
    public Spell(SpellTypes spellType,String name, int manaCost,int castTime, Effect effect)
    {
        this(spellType,name,manaCost,castTime,new Effect[]{effect});
    }


    public Effect[] getEffects() {
        return effects;
    }

    public SpellTypes getSpellType() {
        return spellType;
    }

    public void setCastTime(int castTime) {
        this.castTime = castTime;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public int getCastTime() {
        return castTime;
    }

    public int getManaCost() {
        return manaCost;
    }
    public void setEffect(Effect[] newEffects)
    {
        this.effects = newEffects;
    }
    public String getName() {
        return name;
    }
    public String getDescription()
    {
        String s = "";
        s += "Cost: " + getManaCost() + " | Cast Time: " + getCastTime();
        return s;
    }
    public String getEffectDescription()
    {
        String s="";
        for (int i = 0;i < effects.length;i++)
        {
            s +=effects[i].printEffect();

            if (i != effects.length-1)
            {

                if (effects[i+1].getType() == EffectType.NEGATIVE)
                {
                    s += " but ";
                }
                else
                {
                    s+=" and ";
                }
            }
        }
        return s;
    }
    public Spell clone()
    {
        Effect[] newList = new Effect[effects.length];
        for (int i =0; i < effects.length;i++)
        {
            newList[i] = effects[i].clone();
        }
        return new Spell(spellType,name,manaCost,castTime,newList);


    }
    public Spell cleanClone()
    {
        for (SpellFactory s: SpellFactory.values())
        {
            if (getName() == s.spell.getName())
            {
                return s.getSpell().clone();
            }
        }
        return null;
    }


}
