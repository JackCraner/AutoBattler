package com.mygdx.game.CombatLogic.EffectSystems;

import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.HealthComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.HealHealth;

public class HealSystem implements IsEffectSystem<HealHealth> {
    public static HealSystem instance = new HealSystem();
    @Override
    public void execute(HealHealth effect, BattlerFrame[] battlers, BattlerFrame[] newBattlers)
    {
        HealthComponent battlerComponent = newBattlers[effect.getTarget().getValue()].getComponent(HealthComponent.class);


        if ((battlerComponent.getCurrentHealth()+ effect.getStrength(battlers)) > battlerComponent.getMaxHealth())
        {
            battlerComponent.setCurrentHealth(battlerComponent.getMaxHealth());
        }
        else
        {
            battlerComponent.setCurrentHealth((int) (battlerComponent.getCurrentHealth()+ effect.getStrength(battlers)));
        }


    }
}
