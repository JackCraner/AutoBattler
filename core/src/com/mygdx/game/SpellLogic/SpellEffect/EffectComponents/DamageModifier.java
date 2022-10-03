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
    public DamageModifier(int stages)
    {
        this.stages = stages;
        this.type = DamageTypes.NEUTRAL;
    }
    @Override
    public String printEffect() {
        return (stages>0?"Increase " : " Decrease ") + getTarget().getName(0) + " " + type.name() + " Damage by " + stages + " stages";
    }

    public DamageTypes getType() {
        return type;
    }

    public int getStages() {
        return stages;
    }

    @Override
    public void getExecution(BattlerFrame[] battlers, BattlerFrame[] newBattlers)
    {
        DamageModifierSystem.instance.execute(this, battlers,newBattlers );
    }

    @Override
    public IsEffectComponent clone() {
        return new DamageModifier(stages,type);
    }
}
