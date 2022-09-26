package com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents;

import com.mygdx.game.SpellLogic.Spell;


public class CastComponent extends BattleFrameComponent
{
    Spell spell;
    int castTimer;

    public CastComponent(Spell spell)
    {
        this(spell,0);
    }
    private CastComponent(Spell spell, int castTimer)
    {
        this.castTimer = castTimer;
        this.spell = spell;
    }

    public int getCastTimer() {
        return castTimer;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setCastTimer(int castTimer) {
        this.castTimer = castTimer;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    @Override
    public BattleFrameComponent clone() {
        return new CastComponent(spell,castTimer);
    }

    @Override
    public String printComponent() {
        String s ="Casting: " + getSpell().getName() + " | " +getSpell().getDescription()+" | CD: " + getCastTimer() + " | EFFECT: "+spell.getEffectDescription();
        return s;
    }
}
