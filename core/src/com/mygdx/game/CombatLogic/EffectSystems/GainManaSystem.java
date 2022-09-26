package com.mygdx.game.CombatLogic.EffectSystems;


import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.ManaComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.GainMana;

public class GainManaSystem implements IsEffectSystem<GainMana> {
    public static GainManaSystem instance = new GainManaSystem();
    private GainManaSystem()
    {}

    @Override
    public void execute(GainMana effect, BattlerFrame[] battlers) {

        ManaComponent battlerComponent = battlers[effect.getTarget().getValue()].getComponent(ManaComponent.class);
        battlerComponent.setCurrentMana(battlerComponent.getCurrentMana() + effect.getStrength());
    }
}
