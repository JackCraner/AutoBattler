package com.mygdx.game.CombatLogic.FightLogic.SpellLogic;

import com.mygdx.game.Spells.Conditions.Condition;
import com.mygdx.game.Spells.Effects.CanEffect;
import com.mygdx.game.Spells.Spell;

import java.util.ArrayList;

public class SpellWrapper
{
    //Way to have enum of spells but a class so I can create new versions of spells during combat
        //eg Fireball but only costs 2 mana (due to buff) then graphics engine can notice this fireball only costs 2 and change color of mana text
        //too visualize reduction

    Spell spell;
    int castTime;
    int manaCost;
    Condition condition;
    public SpellWrapper(Spell s)
    {
        spell = s;
        castTime = s.getOrangeBox();
        manaCost = s.getManaCost();
        condition = s.getCondition();
    }

    public Spell getSpell() {
        return spell;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public void setCastTime(int castTime) {
        this.castTime = castTime;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getCastTime() {
        return castTime;
    }

    public Condition getCondition() {
        return condition;
    }
}
