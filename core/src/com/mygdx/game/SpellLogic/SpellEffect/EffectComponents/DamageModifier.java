package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.DamageModifierSystem;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.DamageTypes;

public class DamageModifier extends IsEffectComponent
{

    private DamageTypes type;
    private int stages;
    public DamageModifier(int stages, DamageTypes type)
    {
        this.stages= stages;
        this.type = type;
    }
    @Override
    public String printEffect() {
        return null;
    }

    public DamageTypes getType() {
        return type;
    }

    public int getStages() {
        return stages;
    }

    @Override
    public void getExecution(BattlerFrame[] battlers)
    {
        DamageModifierSystem.instance.execute(this, battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new DamageModifier(stages,type);
    }
}
