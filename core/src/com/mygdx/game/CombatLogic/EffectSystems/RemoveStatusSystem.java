package com.mygdx.game.CombatLogic.EffectSystems;



import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.EffectListComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.BattlerFrames.EffectOnBattler;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.RemoveStatus;

import java.util.Objects;
import java.util.Random;

public class RemoveStatusSystem implements IsEffectSystem<RemoveStatus>
{
    public static RemoveStatusSystem instance = new RemoveStatusSystem();
    private RemoveStatusSystem()
    {


    }

    Random r = new Random();
    @Override
    public void execute(RemoveStatus effect, BattlerFrame[] battlers)
    {

        EffectOnBattler foundEffect = null;
        EffectListComponent battlerComponent = battlers[effect.getTarget().getValue()].getComponent(EffectListComponent.class);
        String effectToAffect = "";
        if (effect.getStatusName() == "random")
        {
            effectToAffect = battlerComponent.getEffectOnBattlers().get(r.nextInt(battlerComponent.getEffectOnBattlers().size())).getStatusObject().getStatus_name();
        }
        else
        {
            effectToAffect = effect.getStatusName();
        }
        for (EffectOnBattler eb : battlerComponent.getEffectOnBattlers()) {
            if (Objects.equals(effectToAffect, eb.getStatusObject().getStatus_name()))
            {
                if (effect.getStrength() ==-1)
                {
                    eb.setStackNumber(0);
                }
                else
                {
                    eb.setStackNumber(eb.getStackNumber() - effect.getStrength());
                }

                if (eb.getStackNumber() <=0)
                {
                    foundEffect = eb;

                }
            }
        }

        if (foundEffect != null)
        {
            battlerComponent.getEffectOnBattlers().remove(foundEffect);
        }
    }
}
