package com.mygdx.game.CombatLogic.EffectSystems;


import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.ManaComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.GainLoseMana;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.ChangeType;

public class GainManaSystem implements IsEffectSystem<GainLoseMana> {
    public static GainManaSystem instance = new GainManaSystem();
    private GainManaSystem()
    {}

    @Override
    public void execute(GainLoseMana effect, BattlerFrame[] battlers, BattlerFrame[] newBattlers) {

        ManaComponent battlerComponentNew = newBattlers[effect.getTarget().getValue()].getComponent(ManaComponent.class);
        if ((battlerComponentNew.getCurrentMana() + (effect.getType()== ChangeType.INCREASE?effect.getStrength(battlers):effect.getStrength(battlers)*-1)) > battlerComponentNew.getMaxMana())
        {
            battlerComponentNew.setCurrentMana(battlerComponentNew.getMaxMana());
        }
        else if ((battlerComponentNew.getCurrentMana() + (effect.getType()== ChangeType.INCREASE?effect.getStrength(battlers):effect.getStrength(battlers)*-1)) < 0)
        {
            battlerComponentNew.setCurrentMana(0);
        }
        else
        {
            battlerComponentNew.setCurrentMana(battlerComponentNew.getCurrentMana() + (effect.getType()== ChangeType.INCREASE?effect.getStrength(battlers):effect.getStrength(battlers)*-1));
        }

    }
}
