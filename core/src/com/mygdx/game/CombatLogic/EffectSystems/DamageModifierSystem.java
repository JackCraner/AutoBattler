package com.mygdx.game.CombatLogic.EffectSystems;


import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.BuffArray;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.DamageModifier;

public class DamageModifierSystem implements  IsEffectSystem<DamageModifier> {
    public static DamageModifierSystem instance = new DamageModifierSystem();
    private DamageModifierSystem()
    {}

    //Goes from -3 stages to 3 stages
    // +3 = 300%
    // +2 = 200%
    // +1 = 150%
    // 0 = 100%
    // -1 = 75%
    // -2 = 50%
    // -3 = 30%
    @Override
    public void execute(DamageModifier effect, BattlerFrame[] battlers)
    {

        BuffArray battlerComponent = battlers[effect.getTarget().getValue()].getComponent(BuffArray.class);
        battlerComponent.setBuffFor(effect.getType(),battlerComponent.getBuffFor(effect.getType()) + effect.getStages());

    }

}
