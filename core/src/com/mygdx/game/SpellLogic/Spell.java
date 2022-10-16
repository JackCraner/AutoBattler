package com.mygdx.game.SpellLogic;




import com.mygdx.game.SpellLogic.SpellEffect.Effect;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.EffectType;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.SpellTypes;

import java.util.ArrayList;

public class Spell
{
    private int manaCost;
    private int castTime;
    private String description;
    private Effect[] effects;
    private String name;
    private SpellTypes spellType;
    public Spell(SpellTypes spellType,String name,int manaCost,int castTime,Effect[] effects, String description)
    {

        this.spellType = spellType;
        this.manaCost = manaCost;
        this.castTime =castTime;
        this.effects = effects;
        this.name = name;
        this.description = description;
    }
    public Spell(SpellTypes spellType,String name, int manaCost,int castTime, Effect effect, String description)
    {
        this(spellType,name,manaCost,castTime,new Effect[]{effect}, description);
    }
    public Spell(SpellTypes spellType,String name,int manaCost,int castTime,Effect[] effects)
    {
        this(spellType,name,manaCost,castTime,effects, null);
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

        if (description == null)
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
        return description;

    }
    public Spell clone()
    {
        Effect[] newList = new Effect[effects.length];
        for (int i =0; i < effects.length;i++)
        {
            newList[i] = effects[i].clone();
        }
        return new Spell(spellType,name,manaCost,castTime,newList,description);


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
