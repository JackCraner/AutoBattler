package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;

import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.AddSpellSystem;
import com.mygdx.game.SpellLogic.Spell;

public class AddSpell extends IsEffectComponent
{
    private int position;
    private Spell spell;
    private AddSpell(Spell spell,int position)
    {
        this.spell = spell;
        this.position = position;
    }
    public AddSpell(Spell spell)
    {
        this(spell,0);

    }

    public AddSpell(int position)
    {
        this(null,position);
    }

    public Spell getSpell() {
        return spell;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String printEffect() {
        return "Add Spell";
    }

    @Override
    public void getExecution(BattlerFrame[] battlers, BattlerFrame[] newBattlers)
    {
        AddSpellSystem.instance.execute(this,battlers,newBattlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new AddSpell(spell,position);
    }
}
