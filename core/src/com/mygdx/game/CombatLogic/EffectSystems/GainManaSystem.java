package com.mygdx.game.CombatLogic.EffectSystems;


import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.ManaComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.GainLoseMana;

public class GainManaSystem implements IsEffectSystem<GainLoseMana> {
    public static GainManaSystem instance = new GainManaSystem();
    private GainManaSystem()
    {}

    @Override
    public void execute(GainLoseMana effect, BattlerFrame[] battlers) {

        ManaComponent battlerComponent = battlers[effect.getTarget().getValue()].getComponent(ManaComponent.class);
        if ((battlerComponent.getCurrentMana() + effect.getStrength()) > battlerComponent.getMaxMana())
        {
            battlerComponent.setCurrentMana(battlerComponent.getMaxMana());
        }
        else if ((battlerComponent.getCurrentMana() + effect.getStrength()) < 0)
        {
            battlerComponent.setCurrentMana(0);
        }
        else
        {
            battlerComponent.setCurrentMana(battlerComponent.getCurrentMana() + effect.getStrength());
        }

    }
}
