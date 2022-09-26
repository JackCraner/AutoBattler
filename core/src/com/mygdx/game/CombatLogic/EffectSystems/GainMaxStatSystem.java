package com.mygdx.game.CombatLogic.EffectSystems;


import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.HealthComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.ManaComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.GainMaxStat;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.StatType;

public class GainMaxStatSystem implements IsEffectSystem<GainMaxStat> {

    public static GainMaxStatSystem instance = new GainMaxStatSystem();
    private GainMaxStatSystem()
    {

    }


    @Override
    public void execute(GainMaxStat effect, BattlerFrame[] battlers)
    {

        if (effect.getType() == StatType.MANA)
        {
            ManaComponent battlerManaComponent =  battlers[effect.getTarget().getValue()].getComponent(ManaComponent.class);
            battlerManaComponent.setMaxMana(battlerManaComponent.getMaxMana() + effect.getStrength());
        }
        else if (effect.getType() == StatType.HEALTH)
        {
            HealthComponent battlerHealthComponent = battlers[effect.getTarget().getValue()].getComponent(HealthComponent.class);
            battlerHealthComponent.setMaxHealth(battlerHealthComponent.getMaxHealth() + effect.getStrength());
        }
    }
}
