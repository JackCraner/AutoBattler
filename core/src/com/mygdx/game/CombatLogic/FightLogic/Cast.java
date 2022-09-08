package com.mygdx.game.CombatLogic.FightLogic;

import com.mygdx.game.Spells.Spell;
import com.mygdx.game.CombatLogic.FightLogic.SpellLogic.SpellBuff;
import com.mygdx.game.CombatLogic.FightLogic.SpellLogic.SpellWrapper;

import java.util.ArrayList;

public class Cast
{

    private CastCodeTypes castCode;
    private SpellWrapper spell;
    private int castTimer;
    private ArrayList<SpellBuff> buffs;

    private Cast(CastCodeTypes castCode, Spell spell, int castTimer)
    {
        this.castCode = castCode;
        this.spell = new SpellWrapper(spell);
        this.castTimer = castTimer;
        this.buffs = new ArrayList<>();
    }
    public Cast(CastCodeTypes castCode, Spell spell)
    {
        this(castCode,spell,0);
    }



    public SpellWrapper getSpellWrapper()
    {
        return spell;
    }

    public CastCodeTypes getCastCode() {
        return castCode;
    }

    public void setCastCode(CastCodeTypes castCode) {
        this.castCode = castCode;
    }

    public int getCastTimer() {
        return castTimer;
    }

    public void setCastTimer(int castTimer) {
        this.castTimer = castTimer;
    }

    public ArrayList<SpellBuff> getBuffs() {
        return buffs;
    }

    public Cast clone()
    {
        return new Cast(castCode,spell.getSpell(),castTimer);
    }
}
