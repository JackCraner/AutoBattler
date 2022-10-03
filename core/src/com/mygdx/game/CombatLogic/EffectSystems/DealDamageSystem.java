package com.mygdx.game.CombatLogic.EffectSystems;


import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.ArmorArray;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.BuffArray;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.HealthComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.DealDamage;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.DamageTypes;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.TargetType;

public class DealDamageSystem implements IsEffectSystem<DealDamage>
{
    public static DealDamageSystem instance = new DealDamageSystem();
    private DealDamageSystem()
    {

    }


    @Override
    public void execute(DealDamage effect, BattlerFrame[] battlers)
    {

        HealthComponent battlerComponent = battlers[effect.getTarget().getValue()].getComponent(HealthComponent.class);
        BuffArray battlerBuffComponent = battlers[TargetType.SELF.getValue()].getComponent(BuffArray.class);
        ArmorArray battlerArmorComponent = battlers[effect.getTarget().getValue()].getComponent(ArmorArray.class);
        float damageValue = calculateStrength(effect.getStrength(),effect.getType(),battlerArmorComponent.getArmorFor(effect.getType()),battlerBuffComponent.getBuffFor(effect.getType()));
        battlerComponent.setCurrentHealth(battlerComponent.getCurrentHealth() - (int)damageValue);
    }



    private float calculateStrength(float amount, DamageTypes type, int armorStage, int buffStage)
    {
        return amount * (stageToPercent(buffStage) / stageToPercent(armorStage));

    }
    private float stageToPercent(int stage)
    {
        switch (stage)
        {
            case -3:
                return 0.3f;
            case -2:
                return 0.5f;
            case -1:
                return 0.75f;
            case 0:
                return 1;
            case 1:
                return 1.5f;
            case 2:
                return 2f;
            case 3:
                return 3;


        }
        return stage>0?3:0.3f;
    }


}

