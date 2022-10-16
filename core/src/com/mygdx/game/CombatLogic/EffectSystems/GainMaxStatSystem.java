package com.mygdx.game.CombatLogic.EffectSystems;


import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.HealthComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.ManaComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.GainMaxStat;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.ChangeType;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.StatType;

public class GainMaxStatSystem implements IsEffectSystem<GainMaxStat> {

    public static GainMaxStatSystem instance = new GainMaxStatSystem();
    private GainMaxStatSystem()
    {

    }


    @Override
    public void execute(GainMaxStat effect, BattlerFrame[] battlers, BattlerFrame[] newBattlers)
    {

        if (effect.getType() == StatType.MANA)
        {
            ManaComponent battlerManaComponent =  newBattlers[effect.getTarget().getValue()].getComponent(ManaComponent.class);
            if (battlerManaComponent.getMaxMana() + (effect.getStrengthType()== ChangeType.INCREASE?effect.getStrength(battlers):-effect.getStrength(battlers)) > 1)
            {
                battlerManaComponent.setMaxMana(battlerManaComponent.getMaxMana() + (effect.getStrengthType()== ChangeType.INCREASE?effect.getStrength(battlers):-effect.getStrength(battlers)));
            }
            else
            {
                battlerManaComponent.setMaxMana(1);
            }

            if (battlerManaComponent.getCurrentMana() >= battlerManaComponent.getMaxMana())
            {
                battlerManaComponent.setCurrentMana(battlerManaComponent.getMaxMana());
            }


        }
        else if (effect.getType() == StatType.HEALTH)
        {
            HealthComponent battlerHealthComponent = newBattlers[effect.getTarget().getValue()].getComponent(HealthComponent.class);
            if (battlerHealthComponent.getMaxHealth() + (effect.getStrengthType()== ChangeType.INCREASE?effect.getStrength(battlers):effect.getStrength(battlers)*-1) > 1)
            {
                battlerHealthComponent.setMaxHealth(battlerHealthComponent.getMaxHealth() + (effect.getStrengthType()== ChangeType.INCREASE?effect.getStrength(battlers):effect.getStrength(battlers)*-1));
            }
            else
            {
                battlerHealthComponent.setMaxHealth(1);
            }

            if (battlerHealthComponent.getCurrentHealth() >= battlerHealthComponent.getMaxHealth())
            {
                battlerHealthComponent.setCurrentHealth(battlerHealthComponent.getMaxHealth());
            }

        }
    }
}
